package com.stagedriving.modules.commons.utils.common;

import com.stagedriving.commons.StgdrvData;
import org.supercsv.io.CsvMapReader;
import org.supercsv.prefs.CsvPreference;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
public class ImportUtils {

    public static List<Map> importRawData(String csv, String type, String[] types, String[] processors, String[] headers) throws Exception {
        CsvMapReader beanReader = null;
        List<Map> emps = new ArrayList<Map>();
        try {
            CsvPreference csvPreference = null;
            if (type.equals(StgdrvData.RawDataTypes.CSV)) {
                csvPreference = CsvPreference.STANDARD_PREFERENCE;
            } else if (type.equals(StgdrvData.RawDataTypes.TSV)) {
                csvPreference = CsvPreference.TAB_PREFERENCE;
            } else if (type.equals(StgdrvData.RawDataTypes.EXCEL)) {
                csvPreference = CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE;
            } else {
                csvPreference = CsvPreference.STANDARD_PREFERENCE;
            }
            beanReader = new CsvMapReader(new StringReader(csv), csvPreference);

            Map<String, String> emp = null;

            while ((emp = beanReader.read(headers)) != null) {
                emps.add(emp);
            }

        } catch (Exception ex) {
            throw ex;
        } finally {
            if (beanReader != null) {
                beanReader.close();
            }
        }
        return emps;
    }
}
