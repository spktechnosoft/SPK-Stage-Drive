/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving;

import com.braintreegateway.BraintreeGateway;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.justbit.aws.AwsBundle;
import com.justbit.aws.AwsConfiguration;
import com.justbit.es.JbEsBundle;
import com.justbit.es.config.EsConfiguration;
import com.justbit.jedis.JedisBundle;
import com.justbit.jedis.JedisConfiguration;
import com.justbit.mailer.bundle.MailerBundle;
import com.justbit.mailer.bundle.MailerConfiguration;
import com.justbit.pubnub.bundle.PnBundle;
import com.justbit.pusher.bundle.PusherBundle;
import com.justbit.pusher.bundle.PusherConfiguration;
import com.justbit.sque.bundle.SqueBundle;
import com.justbit.sque.bundle.SqueConfiguration;
import com.paypal.core.PayPalEnvironment;
import com.stagedriving.modules.commons.AppModule;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.dispatcher.GlobalEventListener;
import com.stagedriving.modules.commons.ds.CommonEventListener;
import com.stagedriving.modules.commons.ds.JbMySQLDialect;
import com.stagedriving.modules.commons.ds.JbSessionFactoryFactory;
import com.stagedriving.modules.commons.interceptor.restricted.RestrictedValueFactoryProvider;
import com.stagedriving.modules.commons.service.init.InitService;
import com.stagedriving.modules.commons.service.model.StgdrvAsyncEventBus;
import com.stagedriving.modules.commons.service.model.StgdrvSyncEventBus;
import com.stagedriving.modules.commons.servlet.CrossOriginResourceSharingFilter;
import com.stagedriving.modules.commons.swagger.SwaggerDateModelConverter;
import com.stagedriving.modules.commons.swagger.SwaggerIgnoreFilter;
import com.stagedriving.modules.commons.utils.DateUtils;
import com.stagedriving.modules.commons.utils.MetricsHelper;
import com.stagedriving.modules.event.batches.EventBatchController;
import com.stagedriving.modules.event.interceptors.EventInterestInterceptor;
import com.stagedriving.modules.event.interceptors.EventsEventListener;
import com.stagedriving.modules.metrics.interceptors.MetricsEventListener;
import com.stagedriving.modules.notification.sender.EmailFeedbackListener;
import com.stagedriving.modules.notification.sender.PushFeedbackListener;
import com.stagedriving.modules.ride.interceptors.RideEventListener;
import com.stagedriving.modules.user.interceptors.AccountEventListener;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.forms.MultiPartBundle;
import io.dropwizard.hibernate.ScanningHibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import io.swagger.config.FilterFactory;
import io.swagger.converter.ModelConverters;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class Service extends Application<AppConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Service.class);

    public ScanningHibernateBundle<AppConfiguration> hibernate;
    private JedisBundle<AppConfiguration> jedisBundle;
    private SqueBundle<AppConfiguration> squeBundle;
    private PusherBundle<AppConfiguration> pushBundle;
    private MailerBundle emailBundle;
    private MultiPartBundle multiPartBundle;
    private AwsBundle<AppConfiguration> awsBundle;
    public static BraintreeGateway braintreeGateway;
    public static PayPalEnvironment payPalEnvironment;
    private PnBundle<AppConfiguration> pnBundle;
    private JbEsBundle<AppConfiguration> esBundle;
    private SwaggerBundle<AppConfiguration> swaggerBundle;
    private static GuiceBundle<AppConfiguration> guice;
    private InitService initService;
    public static MetricRegistry metrics;

    private StgdrvSyncEventBus eventBus;
    private StgdrvAsyncEventBus eventSBus;

    private CrossOriginResourceSharingFilter corsFilter;

    public static void main(String[] args) throws Exception {
        System.setProperty("user.timezone", "UTC");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        new Service().run(args);
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {

//        metrics = new MetricRegistry();
        esBundle = new JbEsBundle<AppConfiguration>() {
            @Override
            public EsConfiguration getConfiguration(AppConfiguration configuration) {
                return configuration.getEs();
            }
        };
        hibernate = new ScanningHibernateBundle<AppConfiguration>(Service.class.getPackage().getName(), new JbSessionFactoryFactory()) {

            @Override
            public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
                Map<String, String> props = new HashMap<String, String>(configuration.getDatabase().getProperties());

                props.put(org.hibernate.cfg.Environment.ALLOW_UPDATE_OUTSIDE_TRANSACTION, ""+true);

                props.put(org.hibernate.cfg.Environment.USE_SECOND_LEVEL_CACHE, "" + configuration.getRedis().isUseSecondLevelCache());
                props.put(org.hibernate.cfg.Environment.USE_QUERY_CACHE, "" + configuration.getRedis().isUseQueryCache());
                props.put(org.hibernate.cfg.Environment.CACHE_REGION_FACTORY, org.hibernate.cache.redis.hibernate52.SingletonRedisRegionFactory.class.getName());
                props.put(org.hibernate.cfg.Environment.CACHE_REGION_PREFIX, configuration.getRedis().getCacheRegionPrefix());

                // optional setting for second level cache statistics
                props.put(org.hibernate.cfg.Environment.GENERATE_STATISTICS, "" + configuration.getRedis().isUseStatistics());
                props.put(org.hibernate.cfg.Environment.USE_STRUCTURED_CACHE, "" + configuration.getRedis().isUseStructuredCache());
//                props.put(org.hibernate.cfg.Environment.TRANSACTION_COORDINATOR_STRATEGY, JdbcCoordinator.class.getName());

                // configuration for Redis that used by hibernate
                props.put(org.hibernate.cfg.Environment.CACHE_PROVIDER_CONFIG, configuration.getRedis().getPropertiesFile());

//                props.put(org.hibernate.cfg.Environment.FORMAT_SQL, "true");
//                props.put(org.hibernate.cfg.Environment.SHOW_SQL, "true");

                props.put(org.hibernate.cfg.Environment.DIALECT, JbMySQLDialect.class.getName());


                configuration.getDatabase().setProperties(props);

                return configuration.getDatabase();
            }
        };

        GuiceBundle.Builder guiceBuilder = GuiceBundle.newBuilder();
        guiceBuilder.setConfigClass(AppConfiguration.class);
        guiceBuilder.addModule(new AppModule(hibernate));
        guiceBuilder.enableAutoConfig(Service.class.getPackage().getName());


        jedisBundle = new JedisBundle<AppConfiguration>(guiceBuilder) {
            @Override
            public JedisConfiguration getJedisConfiguration(AppConfiguration configuration) {
                return configuration.getJedis();
            }
        };

        guice = guiceBuilder.build();

        // Loading other modules

        squeBundle = new SqueBundle<AppConfiguration>(guice) {
            @Override
            public SqueConfiguration getSqueConfiguration(AppConfiguration configuration) {
                return configuration.getSque();
            }
        };

        awsBundle = new AwsBundle<AppConfiguration>(guice) {
            @Override
            public AwsConfiguration getConfiguration(AppConfiguration configuration) {
                return configuration.getAws();
            }
        };

        emailBundle = new MailerBundle<AppConfiguration>(guice, EmailFeedbackListener.class) {
            @Override
            public MailerConfiguration getEmailConfiguration(AppConfiguration configuration) {
                return configuration.getMail();
            }
        };

        pushBundle = new PusherBundle<AppConfiguration>(guice, PushFeedbackListener.class) {
            @Override
            public PusherConfiguration getPushConfiguration(AppConfiguration configuration) {
                return configuration.getPush();
            }
        };

        swaggerBundle = new SwaggerBundle<AppConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(AppConfiguration configuration) {
                return configuration.getSwagger();
            }
        };

        bootstrap.addBundle(hibernate);
        bootstrap.addBundle(jedisBundle);
        bootstrap.addBundle(squeBundle);
        bootstrap.addBundle(awsBundle);
        bootstrap.addBundle(guice);
        bootstrap.addBundle(esBundle);
        bootstrap.addBundle(new MultiPartBundle());
        bootstrap.addBundle(emailBundle);
        bootstrap.addBundle(pushBundle);

        bootstrap.addBundle(new ViewBundle<AppConfiguration>());

        bootstrap.addBundle(swaggerBundle);
    }

    @Override
    public void run(AppConfiguration configuration,
                    Environment environment) throws Exception {

        metrics = environment.metrics();
        metrics.removeMatching(MetricFilter.startsWith("jvm"));
        metrics.removeMatching(MetricFilter.startsWith("thread"));
        metrics.removeMatching(MetricFilter.startsWith("ch"));
        metrics.removeMatching(MetricFilter.startsWith("clients"));
        metrics.removeMatching(MetricFilter.startsWith("http_server"));
        metrics.removeMatching(MetricFilter.startsWith("thread_pools"));
                MetricsHelper metricsHelper = guice.getInjector().getInstance(MetricsHelper.class);
        metricsHelper.setMetrics(metrics);
        corsFilter = guice.getInjector().getInstance(CrossOriginResourceSharingFilter.class);

        DateFormat eventDateFormat = new SimpleDateFormat(DateUtils.DATE_PATTERN__);
        environment.getObjectMapper().setDateFormat(eventDateFormat);

        File tempPath = new File(configuration.getStorage().getTempFilePath());
        tempPath.mkdir();

        EventListenerRegistry registry = ((SessionFactoryImpl) hibernate.getSessionFactory()).getServiceRegistry().getService(EventListenerRegistry.class);

        // How add Hibernate interceptor
        EventInterestInterceptor eventInterestInterceptor = Service.getGuice().getInjector().getInstance(EventInterestInterceptor.class);
        registry.prependListeners( EventType.POST_COMMIT_INSERT, eventInterestInterceptor);
        registry.prependListeners( EventType.POST_COMMIT_UPDATE, eventInterestInterceptor);
//        TransactionInterceptor transactionInterceptor = Service.getGuice().getInjector().getInstance(TransactionInterceptor.class);
//        registry.prependListeners( EventType.POST_COMMIT_INSERT, transactionInterceptor);
//        registry.prependListeners( EventType.POST_COMMIT_UPDATE, transactionInterceptor);
        GlobalEventListener globalEventListener = Service.getGuice().getInjector().getInstance(GlobalEventListener.class);
        registry.prependListeners( EventType.POST_COMMIT_INSERT, globalEventListener);
        registry.prependListeners( EventType.POST_COMMIT_UPDATE, globalEventListener);
        CommonEventListener commonEventListener = Service.getGuice().getInjector().getInstance(CommonEventListener.class);
        registry.prependListeners( EventType.POST_COMMIT_INSERT, commonEventListener);
        registry.prependListeners( EventType.POST_COMMIT_UPDATE, commonEventListener);
        AccountEventListener accountEventListener = Service.getGuice().getInjector().getInstance(AccountEventListener.class);
        registry.prependListeners( EventType.POST_COMMIT_INSERT, accountEventListener);
        registry.prependListeners( EventType.POST_COMMIT_UPDATE, accountEventListener);
        EventsEventListener eventsEventListener = Service.getGuice().getInjector().getInstance(EventsEventListener.class);
        registry.prependListeners( EventType.POST_COMMIT_INSERT, eventsEventListener);
        registry.prependListeners( EventType.POST_COMMIT_UPDATE, eventsEventListener);
        RideEventListener rideEventListener = Service.getGuice().getInjector().getInstance(RideEventListener.class);
        registry.prependListeners( EventType.POST_COMMIT_INSERT, rideEventListener);
        registry.prependListeners( EventType.POST_COMMIT_UPDATE, rideEventListener);
        MetricsEventListener metricsEventListener = Service.getGuice().getInjector().getInstance(MetricsEventListener.class);
        registry.prependListeners( EventType.POST_COMMIT_INSERT, metricsEventListener);
        registry.prependListeners( EventType.POST_COMMIT_UPDATE, metricsEventListener);
        registry.prependListeners( EventType.POST_COMMIT_DELETE, metricsEventListener);
//        NotificationEventListener notificationEventListener = Service.getGuice().getInjector().getInstance(NotificationEventListener.class);
//        registry.prependListeners( EventType.POST_COMMIT_INSERT, notificationEventListener);
//        registry.prependListeners( EventType.POST_COMMIT_UPDATE, notificationEventListener);

        eventBus = guice.getInjector().getInstance(StgdrvSyncEventBus.class);
        eventSBus = guice.getInjector().getInstance(StgdrvAsyncEventBus.class);

        initService = guice.getInjector().getInstance(InitService.class);
        eventBus.registerReceiver(initService);

        FilterFactory.setFilter(new SwaggerIgnoreFilter());
        ModelConverters.getInstance().addConverter(new SwaggerDateModelConverter());

//        FilterRegistration.Dynamic filter = environment.servlets().addFilter("Gelf", new JbGelfLoggingFilter());
//        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORS", corsFilter);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

//        filter = environment.servlets().addFilter("QueryParams", guice.getInjector().getInstance(QueryParamsFilter.class));
//        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        filter = environment.servlets().addFilter("TeeFilter", new ch.qos.logback.access.servlet.TeeFilter());
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/v1/*");
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/auth");

        new AssetsBundle("/assets", "/assets", null, "sd-assets").run(environment);

        if (configuration.getBraintree() != null) {
            com.braintreegateway.Environment env = com.braintreegateway.Environment.DEVELOPMENT;
            if (configuration.getBraintree().getEnvironment().equals("sandbox")) {
                env = com.braintreegateway.Environment.SANDBOX;
            } else if (configuration.getBraintree().getEnvironment().equals("production")) {
                env = com.braintreegateway.Environment.PRODUCTION;
            }

            braintreeGateway= new BraintreeGateway(
                    env,
                    configuration.getBraintree().getMerchantId(),
                    configuration.getBraintree().getPublicKey(),
                    configuration.getBraintree().getPrivateKey()
            );
        }
        if (configuration.getPaypal() != null) {

            if (configuration.getPaypal().getEnvironment().equals("sandbox")) {
                payPalEnvironment = new PayPalEnvironment.Sandbox(
                        configuration.getPaypal().getClientId(),
                        configuration.getPaypal().getClientSecret());
            } else if (configuration.getPaypal().getEnvironment().equals("production")) {
                payPalEnvironment = new PayPalEnvironment.Live(
                        configuration.getPaypal().getClientId(),
                        configuration.getPaypal().getClientSecret());
            }
        }

        environment.jersey().register(RestrictedValueFactoryProvider.class);


        EventBatchController eventBatchController = guice.getInjector().getInstance(EventBatchController.class);
        eventBatchController.scheduleBatch();
//        ScheduledReporter reporter = InfluxdbReporter.forRegistry(configuration.getMetricsFactory().).build();
//        reporter.start(10, TimeUnit.SECONDS);

//        InitAsyncEvent base = new InitAsyncEvent();
//        base.setOperation(InitAsyncEvent.Task.CATALOG);
//        eventBus.post(base);
    }

    public static GuiceBundle<AppConfiguration> getGuice() {
        return guice;
    }
}
