/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.export;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.google.common.eventbus.Subscribe;
import com.google.common.io.Files;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.justbit.aws.S3Utils;
import com.justbit.commons.TokenUtils;
import com.justbit.sque.SqueController;
import com.justbit.sque.SqueEventBus;
import com.justbit.sque.SqueServiceAbstract;
import com.stagedriving.Service;
import com.stagedriving.commons.v1.resources.ExportDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.export.model.ExportEvent;
import com.stagedriving.modules.export.views.ExportReadyEmailNotificationView;
import com.stagedriving.modules.notification.controllers.NotificationController;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.glassfish.jersey.client.ClientConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.vault.support.JsonMapFlattener;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.*;

/**
 * @author simone
 */
@Slf4j
@Singleton
public class ExportService extends SqueServiceAbstract<ExportEvent, Boolean> {

    private class CustomStyles {
        public HSSFCellStyle headerStyle;
        public HSSFCellStyle leftHeaderStyle;
        public HSSFCellStyle dataStyle;
        public short defaultFillColor;
    }

    @Inject
    private ObjectMapper objectMapper;
    @Inject
    private NotificationController notificationController;

    private Client client;

    @Inject
    public ExportService(SessionFactory sessionFactory, SqueEventBus eventBusService, SqueController squeController) {
        super(sessionFactory, squeController);

        ClientConfig config = new ClientConfig();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.setDateFormat(new SimpleDateFormat(DateUtils.DATE_PATTERN));
        objectMapper.registerModule(new JodaModule());

        JacksonJaxbJsonProvider jacksonProvider = new JacksonJaxbJsonProvider();
        jacksonProvider.setMapper(objectMapper);

        config.register(jacksonProvider);
        config.register(objectMapper);

        this.client = ClientBuilder.newClient(config);

        eventBusService.registerReceiver(this);
    }

    @Subscribe
    public void onMessage(ExportEvent item) {
        super.onMessage(item);
    }

    @Override
    protected String execute(Session session, ExportEvent item) throws Exception {

        log.info("Exporting data");

        ExportDTO exportDTO = item.getExport();
        if (exportDTO == null || exportDTO.getUrl() == null) {
            log.warn("Skipping");
            return null;
        }

        int page = 0;
        int limit = 20;

        boolean hasData = false;
        boolean addHeaders = true;
        String filepath = null;
        String uid = TokenUtils.generateUid();

        final MultivaluedMap<String, Object> httpHeaders = new MultivaluedHashMap<>();
        if (item.getExport().getReqHeaders() != null) {
            for (Map.Entry<String, Object> header : item.getExport().getReqHeaders().entrySet()) {
                httpHeaders.add(header.getKey(), header.getValue());
            }
        }

        List<String> headers = null;
        headers = item.getExport().getHeaders();

        List<String> labels = item.getExport().getLabels();
        if (labels == null) {
            labels = headers;
        }

        do {

            String url = prepareUrl(exportDTO.getUrl(), page, limit);

            Response response = client
                    .target(url)
                    .request("application/json")
                    .headers(httpHeaders)
                    .get(Response.class);

            List<Map<String, Object>> fetchedData = response.readEntity(new GenericType<List<Map<String, Object>>>() {
            });
            List<Map<String, String>> data = new ArrayList<>();

            for (int i = 0; i < fetchedData.size(); i++) {
                Map<String, String> flattenMap = JsonMapFlattener.flattenToStringMap(fetchedData.get(i));
                log.info("flattenMap", flattenMap);

                data.add(i, flattenMap);
            }

            if (page == 0) {

                if (headers == null) {
                    headers = prepareHeaders(data);
                    labels = headers;
                }
                addHeaders = true;
            } else {
                addHeaders = false;
            }

            if (data != null && data.size() > 0) {
                hasData = true;
                page++;

                log.info("Data retrieved " + data.size());

                filepath = prepareExport(addHeaders, headers, labels, data, uid, item.getJobId(), "");
            } else {
                hasData = false;
            }

        } while (hasData);

        if (filepath != null) {
            String url = upload(item.getJobId(), filepath);

            sendNotification(exportDTO.getSendTo(), url);
        }


        return null;
    }

    private void sendNotification(String[] tos, String url) throws Exception {
        ExportReadyEmailNotificationView exportReadyEmailNotificationView = new ExportReadyEmailNotificationView();
        exportReadyEmailNotificationView.setSubject("Esportazione pronta");
        exportReadyEmailNotificationView.setDownload(url);

        Account account = new Account();
        account.setEmail(tos[0]);
        exportReadyEmailNotificationView.setUser(account);

        notificationController.sendEmailNotification("me", Arrays.asList(tos), exportReadyEmailNotificationView);

    }

    private List<String> prepareHeaders(List<Map<String, String>> data) {
        List<String> headers = new ArrayList<>();

        Map<String, String> firstRow = data.get(0);
        for (String key : firstRow.keySet()) {
            headers.add(key);
        }

        return headers;
    }

    private String prepareUrl(String url, int page, int limit) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(url);
        urlBuilder.append("?page=");
        urlBuilder.append(page);
        urlBuilder.append("&limit=");
        urlBuilder.append(limit);

        return urlBuilder.toString();
    }

    @Override
    protected void onPostExecute(Boolean item) {

    }


    public List<DataEntry> prepareEntries(String... values) {
        List<DataEntry> columns = new ArrayList<>();

        for (String value : values) {
            columns.add(new DataEntry(value));
        }

        return columns;
    }

    public String prepareExport(boolean addHeaders, List<String> headers, List<String> labels, List<Map<String, String>> data, String uid, String jobId, String dataType) throws IOException, InterruptedException {

        AppConfiguration configuration = Service.getGuice().getInjector().getInstance(AppConfiguration.class);

        Map<String, Integer> headersIndexes = new HashMap<>();
        Integer index = 0;
        for (String head : headers) {
            headersIndexes.put(head, index);
            index++;
        }

        if (data == null || dataType == null) {
            squeController.logError(jobId, "Missing event or dataType fields");
            return null;
        }

//        squeController.logInfo(jobId, "Exporting '" + event.getName() + " type "+dataType);

        List<List<DataEntry>> rows = new ArrayList<List<DataEntry>>();
        List<DataEntry> columns = null;

        for (Map<String, String> row : data) {

            DataEntry[] integers = new DataEntry[headers.size()];
            Arrays.fill(integers, null);
            columns = Arrays.asList(integers);
            for (Map.Entry<String, String> item : row.entrySet()) {

                Integer i = null;
                if (headersIndexes.containsKey(item.getKey())) {
                    i = headersIndexes.get(item.getKey());
                }
                if (i != null) {
                    String value = item.getValue();
                    columns.set(i, new DataEntry(value));
                }
            }

            rows.add(columns);
        }

        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-yyyy");
        String filename = "Export_" + "_" + dataType + (uid != null ? "_" + uid : "") + "_" + fmt.print(new DateTime()) + ".xls";
        String filepath = configuration.getStorage().getTempFilePath() + File.separator + filename;
        String filepathTmp = configuration.getStorage().getTempFilePath() + File.separator + "tmp" + filename;
        squeController.logInfo(jobId, "Writing to temp file " + filepath);

        HSSFWorkbook workbook = null;

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        if (new File(filepath).exists()) {
            fileInputStream = new FileInputStream(filepath);
            fileOutputStream = new FileOutputStream(filepathTmp);
            workbook = new HSSFWorkbook(fileInputStream);
        } else {
            fileOutputStream = new FileOutputStream(filepathTmp);
            workbook = new HSSFWorkbook();
        }
        CustomStyles customStyles = generateStyles(workbook);


        squeController.logInfo(jobId, "Writing " + rows.size() + " entries to xls...");
        writeToExcel(jobId, "Data", rows, addHeaders ? labels : null, workbook, customStyles, fileOutputStream);

        workbook.write(fileOutputStream);
        fileOutputStream.close();

        Files.move(new File(filepathTmp), new File(filepath));

        return filepath;
    }

    private String upload(String jobId, String filename) throws FileNotFoundException, InterruptedException {
        AppConfiguration configuration = Service.getGuice().getInjector().getInstance(AppConfiguration.class);
        S3Utils s3Utils = Service.getGuice().getInjector().getInstance(S3Utils.class);

        squeController.logInfo(jobId, "Uploading xls to S3...");
        String url = s3Utils.uploadData(new File(filename), configuration.getStorage().getReportBucket(), filename);
        squeController.logInfo(jobId, "Export uploaded to: " + url);

        return url;
    }

    private CustomStyles generateStyles(HSSFWorkbook workbook) {
        CustomStyles customStyles = new CustomStyles();
        // Font setting for sheet.
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(new HSSFColor.WHITE().getIndex());

        // Font setting for sheet.
        HSSFFont blackFont = workbook.createFont();
        blackFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//        blackFont.setColor(new HSSFColor.WHITE().getIndex());

        HSSFPalette palette = workbook.getCustomPalette();
// get the color which most closely matches the color you want to use
        HSSFColor color = palette.findSimilarColor(9, 102, 171);
// get the palette index of that color
        customStyles.defaultFillColor = color.getIndex();

        // Create Styles for sheet.
        customStyles.headerStyle = workbook.createCellStyle();
        customStyles.headerStyle.setFillForegroundColor(customStyles.defaultFillColor);
        customStyles.headerStyle.setFont(font);
        customStyles.headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        // Create Styles for sheet.
        customStyles.leftHeaderStyle = workbook.createCellStyle();
//        leftHeaderStyle.setFillForegroundColor(new HSSFColor.LIGHT_GR().getIndex());
        customStyles.leftHeaderStyle.setFont(blackFont);
//        leftHeaderStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        customStyles.dataStyle = workbook.createCellStyle();
        customStyles.dataStyle.setWrapText(true);

        return customStyles;
    }

    private class Result {
        public Result(HSSFSheet sheet, int lastRow) {
            this.sheet = sheet;
            this.lastRow = lastRow;
        }

        public HSSFSheet sheet;
        public int lastRow;
    }

    private void writeToExcel(String jobId, String sheetname, List<List<DataEntry>> rows, List<String> headers, HSSFWorkbook workbook, CustomStyles customStyles, FileOutputStream fileOutputStream) throws IOException, InterruptedException {
        HSSFSheet sheet = workbook.getSheet(sheetname);
        if (sheet == null) {
            sheet = workbook.createSheet(sheetname);
            sheet.setDefaultColumnWidth(30);
        }

        int c = 0;
        int r = 0;
        int total = rows.size();

        Iterator<Row> iterator = sheet.rowIterator();

        while (iterator.hasNext()) {
            iterator.next();

            r++;
        }

        if (headers != null) {
            squeController.logInfo(jobId, "Writing headers");
            writeRowTo(sheet, customStyles, true, null, r, c, headers.toArray());
            r++;
        }

        for (List<DataEntry> columns : rows) {
            c = 0;

//            if (r % 50 == 0) {
            squeController.logInfo(jobId, "Writing row " + r + " of " + total);
            //workbook.write(fileOutputStream);
//            }

            List<Object> values = new ArrayList<Object>();
            for (DataEntry entry : columns) {

                if (entry == null || entry.value == null) {
                    values.add("");
                } else {
                    values.add(entry.value);
                }
            }

            writeRowTo(sheet, customStyles, false, null, r, c, values.toArray());
            r++;
        }

    }

    private void writeRowTo(HSSFSheet sheet, CustomStyles customStyles, boolean isHeader, Object label, int r, int c, Object... values) {
        writeRowTo2(sheet, customStyles, isHeader, label, r, c, -1, values);
    }

    private void writeRowTo2(HSSFSheet sheet, CustomStyles customStyles, boolean isHeader, Object label, int r, int c, int span, Object... values) {

        Row row = sheet.createRow(r);

        Cell cell = null;
        if (label != null) {

            cell = row.createCell(c);

            if (isHeader) {
                cell.setCellStyle(customStyles.headerStyle);
            }

            cell.setCellValue("" + label);


            sheet.addMergedRegion(new CellRangeAddress(r, r, c, c + 1));

            c++;
        }
        for (int i = c; i < values.length; i++) {
            cell = row.createCell(i);
            if (isHeader) {
                cell.setCellStyle(customStyles.headerStyle);
            } else {
                cell.setCellStyle(customStyles.dataStyle);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
            }
            if (values != null && values.length > 0 && values[i] != null) {
                try {
                    Integer val = Integer.valueOf("" + values[i]);
                    cell.setCellValue(val);
                } catch (NumberFormatException e) {
                    if (values[i].equals("null")) {
                        cell.setCellValue("");
                    } else {
                        cell.setCellValue("" + values[i]);
                    }
                }
            } else {
                cell.setCellValue("");
            }
        }
    }

    public static class DataEntry {

        public DataEntry(String value, String type) {
            this.value = value;
            this.type = type;
        }

        public DataEntry(String value) {
            this(value, "string");
        }

        public String label;
        public String value;
        public String type;
    }
}
