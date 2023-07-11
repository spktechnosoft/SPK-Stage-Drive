package com.stagedriving.modules.commons.service.init;

import com.esotericsoftware.yamlbeans.YamlException;
import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;
import com.justbit.commons.TokenUtils;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.daos.*;
import com.stagedriving.modules.commons.ds.entities.*;
import com.stagedriving.modules.commons.utils.common.ImportUtils;
import com.stagedriving.modules.commons.utils.ride.CarBrands;
import io.dropwizard.hibernate.UnitOfWork;
import org.hibernate.*;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ManagedSessionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by man on 25/04/16.
 */
public class InitService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitService.class);

    private AppConfiguration configuration;
    private SessionFactory sessionFactory;

    @Inject
    AccountGroupDAO groupDAO;
    @Inject
    BrandDAO brandDAO;
    @Inject
    ItemHasBrandDAO itemHasBrandDAO;
    @Inject
    CatalogHasItemDAO catalogHasItemDAO;
    @Inject
    CatalogDAO catalogDAO;
    @Inject
    ImportUtils importUtils;
    @Inject
    ItemDAO itemDAO;

    private final String[] headers = {"NOME"};

    @Inject
    public InitService(SessionFactory sessionFactory, AppConfiguration configuration) {
        this.configuration = configuration;
        this.sessionFactory = sessionFactory;
    }

    @Subscribe
    @AllowConcurrentEvents
    @UnitOfWork
    public void onInitAsync(InitAsyncEvent event) throws Exception {
        if (event != null) {
            switch (event.getOperation()) {
                case CATALOG:
                    final Session sessionCatalog = sessionFactory.openSession();
                    try {
                        sessionCatalog.setDefaultReadOnly(false);
                        sessionCatalog.setCacheMode(CacheMode.NORMAL);
                        sessionCatalog.setFlushMode(FlushMode.MANUAL);
                        ManagedSessionContext.bind(sessionCatalog);
                        sessionCatalog.beginTransaction();
                        try {
                            this.initCatalog(sessionCatalog);

                            final Transaction txn = sessionCatalog.getTransaction();
                            if (txn != null && txn.isActive()) {
                                txn.commit();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            final Transaction txn = sessionCatalog.getTransaction();
                            if (txn != null && txn.isActive()) {
                                txn.rollback();
                            }
                        }
                    } finally {
                        sessionCatalog.close();
                        ManagedSessionContext.unbind(sessionFactory);
                    }
                    break;
                case GROUP:
                    final Session sessionGroup = sessionFactory.openSession();
                    try {
                        sessionGroup.setDefaultReadOnly(false);
                        sessionGroup.setCacheMode(CacheMode.NORMAL);
                        sessionGroup.setFlushMode(FlushMode.MANUAL);
                        ManagedSessionContext.bind(sessionGroup);
                        sessionGroup.beginTransaction();
                        try {
                            this.initGroup(sessionGroup);

                            final Transaction txn = sessionGroup.getTransaction();
                            if (txn != null && txn.isActive()) {
                                txn.commit();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            final Transaction txn = sessionGroup.getTransaction();
                            if (txn != null && txn.isActive()) {
                                txn.rollback();
                            }
                        }
                    } finally {
                        sessionGroup.close();
                        ManagedSessionContext.unbind(sessionFactory);
                    }
                    break;
                case TRUNCATE:
                    final Session sessionTruncate = sessionFactory.openSession();
                    try {
                        sessionTruncate.setDefaultReadOnly(false);
                        sessionTruncate.setCacheMode(CacheMode.NORMAL);
                        sessionTruncate.setFlushMode(FlushMode.MANUAL);
                        ManagedSessionContext.bind(sessionTruncate);
                        sessionTruncate.beginTransaction();
                        try {
                            this.initTruncate(sessionTruncate);

                            final Transaction txn = sessionTruncate.getTransaction();
                            if (txn != null && txn.isActive()) {
                                txn.commit();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            final Transaction txn = sessionTruncate.getTransaction();
                            if (txn != null && txn.isActive()) {
                                txn.rollback();
                            }
                        }
                    } finally {
                        sessionTruncate.close();
                        ManagedSessionContext.unbind(sessionFactory);
                    }
                    break;
                case BRAND:
                    final Session sessionBrand = sessionFactory.openSession();
                    try {
                        sessionBrand.setDefaultReadOnly(false);
                        sessionBrand.setCacheMode(CacheMode.NORMAL);
                        sessionBrand.setFlushMode(FlushMode.MANUAL);
                        ManagedSessionContext.bind(sessionBrand);
                        sessionBrand.beginTransaction();
                        try {
                            this.initBrand(sessionBrand);

                            final Transaction txn = sessionBrand.getTransaction();
                            if (txn != null && txn.isActive()) {
                                txn.commit();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            final Transaction txn = sessionBrand.getTransaction();
                            if (txn != null && txn.isActive()) {
                                txn.rollback();
                            }
                        }
                    } finally {
                        sessionBrand.close();
                        ManagedSessionContext.unbind(sessionFactory);
                    }
                    break;
                case COLOR:
                    final Session sessionColor = sessionFactory.openSession();
                    try {
                        sessionColor.setDefaultReadOnly(false);
                        sessionColor.setCacheMode(CacheMode.NORMAL);
                        sessionColor.setFlushMode(FlushMode.MANUAL);
                        ManagedSessionContext.bind(sessionColor);
                        sessionColor.beginTransaction();
                        try {
                            this.initColor(sessionColor);

                            final Transaction txn = sessionColor.getTransaction();
                            if (txn != null && txn.isActive()) {
                                txn.commit();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            final Transaction txn = sessionColor.getTransaction();
                            if (txn != null && txn.isActive()) {
                                txn.rollback();
                            }
                        }
                    } finally {
                        sessionColor.close();
                        ManagedSessionContext.unbind(sessionFactory);
                    }
                    break;
            }
        }
    }

    public void initCatalog(Session session) throws IOException {

        Catalog catalog = catalogDAO.findByName("car");
        if (catalog != null) {
            File file = null;
            String currentPathFile = null;
            if (configuration.isProduction()) {
                Path currentRelativePath = Paths.get("");
                currentPathFile = currentRelativePath.toAbsolutePath().toString() + "/env/production/resources/cars-list.json";
            } else {
                Path currentRelativePath = Paths.get("");
                currentPathFile = currentRelativePath.toAbsolutePath().toString() + "/env/development/resources/cars-list.json";
            }
            byte[] encoded = Files.readAllBytes(Paths.get(currentPathFile));

            Gson gson = new Gson();
            ArrayList<CarBrands> carBrands = gson.fromJson(new String(encoded, Charset.defaultCharset()), new TypeToken<ArrayList<CarBrands>>() {
            }.getType());
            Brand brand = new Brand();
            for (CarBrands carBrand : carBrands) {
                if (brandDAO.findByName(carBrand.getBrand()) == null) {
                    brand = new Brand();
                    brand.setName(carBrand.getBrand());
                    brand.setUid(TokenUtils.generateUid());
                    brand.setCreated(new Date());
                    brand.setModified(new Date());
                    brand.setVisible(true);
                    brand.setBase(true);
                    brand.setStatus(StgdrvData.BrandStatus.APPROVED);
                    brandDAO.create(brand);
                }
                for (String carModel : carBrand.getModels()) {
                    Item item = new Item();
                    item.setUid(TokenUtils.generateUid());
                    item.setCreated(new Date());
                    item.setModified(new Date());
                    item.setVisible(true);
                    item.setTag(carModel);
                    item.setName(carModel);
                    item.setDescription(carModel);
                    itemDAO.create(item);

                    CatalogHasItemId catalogHasItemId = new CatalogHasItemId();
                    catalogHasItemId.setCatalogId(catalog.getId());
                    catalogHasItemId.setItemId(item.getId());
                    CatalogHasItem catalogHasItem = new CatalogHasItem();
                    catalogHasItem.setId(catalogHasItemId);
                    catalogHasItem.setCreated(new Date());
                    catalogHasItem.setModified(new Date());
                    catalogHasItem.setVisible(true);
                    catalogHasItemDAO.create(catalogHasItem);

                    ItemHasBrandId itemHasBrandId = new ItemHasBrandId();
                    itemHasBrandId.setBrandId(brand.getId());
                    itemHasBrandId.setItemId(item.getId());
                    ItemHasBrand itemHasBrand = new ItemHasBrand();
                    itemHasBrand.setId(itemHasBrandId);
                    itemHasBrand.setCreated(new Date());
                    itemHasBrand.setModified(new Date());
                    itemHasBrand.setVisible(true);
                    itemHasBrandDAO.create(itemHasBrand);
                }
            }
            session.flush();
        }
    }

    public void initGroup(Session session) throws YamlException, FileNotFoundException {

        AccountGroup accountGroupUser = new AccountGroup();
        accountGroupUser.setUid(TokenUtils.generateUid());
        accountGroupUser.setCreated(new Date());
        accountGroupUser.setModified(new Date());
        accountGroupUser.setName(StgdrvData.AccountRoles.USER);
        accountGroupUser.setDescription(StgdrvData.AccountRoles.USER);
        accountGroupUser.setStatus(StgdrvData.Status.ENABLED);
        groupDAO.create(accountGroupUser);

        AccountGroup accountGroupcOrganizer = new AccountGroup();
        accountGroupcOrganizer.setUid(TokenUtils.generateUid());
        accountGroupcOrganizer.setCreated(new Date());
        accountGroupcOrganizer.setModified(new Date());
        accountGroupcOrganizer.setName(StgdrvData.AccountRoles.ORGANIZER);
        accountGroupcOrganizer.setDescription(StgdrvData.AccountRoles.ORGANIZER);
        accountGroupcOrganizer.setStatus(StgdrvData.Status.ENABLED);
        groupDAO.create(accountGroupcOrganizer);

        AccountGroup accountGroupcAdmin = new AccountGroup();
        accountGroupcAdmin.setUid(TokenUtils.generateUid());
        accountGroupcAdmin.setCreated(new Date());
        accountGroupcAdmin.setModified(new Date());
        accountGroupcAdmin.setName(StgdrvData.AccountRoles.ADMIN);
        accountGroupcAdmin.setDescription(StgdrvData.AccountRoles.ADMIN);
        accountGroupcAdmin.setStatus(StgdrvData.Status.ENABLED);
        groupDAO.create(accountGroupcAdmin);

        session.flush();
    }

    public void initTruncate(Session session) throws YamlException, FileNotFoundException, ClassNotFoundException, SQLException {

        Connection connect;
        Statement statement;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection("jdbc:mysql://mysql:" + 3306 + "/buyu_db", "rest", "123mysqlere");
        statement = connect.createStatement();

        resultSet = statement.executeQuery("SET FOREIGN_KEY_CHECKS=0");

        preparedStatement = connect.prepareStatement("delete from buyu_db.item_category");
        preparedStatement.executeUpdate();

        preparedStatement = connect.prepareStatement("delete from buyu_db.item_family");
        preparedStatement.executeUpdate();

        resultSet = statement.executeQuery("SET FOREIGN_KEY_CHECKS=1");
    }

    public void initColor(Session session) throws YamlException, FileNotFoundException, URISyntaxException {
    }

    public void initBrand(Session session) throws Exception {

        File file = null;
        URL url = null;

        if (configuration.isProduction()) {
            Path currentRelativePath = Paths.get("");
            String currentPath = currentRelativePath.toAbsolutePath().toString();
            String currentPathFile = currentPath + "/env/production/resources/brand.csv";
            file = new File(currentPathFile);
        } else {
            url = Resources.getResource("brand.csv");
            file = new File(url.getFile());
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder csvBuilder = new StringBuilder();
        try {
            String line = br.readLine();

            while (line != null) {
                csvBuilder.append(line);
                csvBuilder.append("\n");
                line = br.readLine();
            }
        } finally {
            br.close();
        }
        List<Map> importMap = importUtils.importRawData(csvBuilder.toString(), StgdrvData.RawDataTypes.CSV, null, null, headers);
        Iterator<Map> it = importMap.iterator();
        while (it.hasNext()) {
            Map raw = it.next();
            if (raw.get("NOME") != null) {
                String name = (String) raw.get("NOME");

                if (name != null && !name.equalsIgnoreCase("")) {
                    Brand brand = new Brand();
                    brand.setName(name);
                    brand.setDescription(name);
                    brand.setUid(TokenUtils.generateUid());
                    brand.setCreated(new Date());
                    brand.setModified(new Date());
                    brand.setVisible(true);
                    brand.setStatus(StgdrvData.BrandStatus.APPROVED);
                    brand.setBase(true);
                    LOGGER.info("Brand: " + brand.getName());
                    brandDAO.create(brand);
                }
            }
        }
    }
}