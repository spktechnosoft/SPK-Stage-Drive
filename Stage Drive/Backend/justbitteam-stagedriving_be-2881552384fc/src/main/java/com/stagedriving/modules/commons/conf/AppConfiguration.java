/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.commons.conf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.justbit.aws.AwsConfiguration;
import com.justbit.es.config.EsConfiguration;
import com.justbit.jedis.JedisConfiguration;
import com.justbit.mailer.bundle.MailerConfiguration;
import com.justbit.pubnub.bundle.PnConfiguration;
import com.justbit.pusher.bundle.PusherConfiguration;
import com.justbit.sque.bundle.SqueConfiguration;
import com.stagedriving.modules.commons.payment.BraintreeConfiguration;
import com.stagedriving.modules.commons.payment.paypal.PaypalConfiguration;
import com.stagedriving.modules.commons.queue.RabbitMQConfiguration;
import com.stagedriving.modules.commons.queue.WorkerConfiguration;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.logging.AbstractAppenderFactory;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class AppConfiguration extends Configuration {

    private String defaultLimit = "10";

    private String defaultPage = "0";

    private Boolean production = false;

    @Valid
    @NotNull
    private String origin = "*";

    @Valid
    @NotNull
    private String baseUri = "http://scx.zapto.org:8080/";

    @Valid
    @NotNull
    private String webAppUri = "http://scx.zapto.org:9000";

    @Valid
    @NotNull
    private FacebookConfiguration facebook;

    @Valid
    @NotNull
    private TwitterConfiguration twitter;

    @Valid
    @NotNull
    private GoogleConfiguration google;

    @Valid
    @NotNull
    private LinkedinConfiguration linkedin;

    @Valid
    private MailerConfiguration mail;

    @Valid
    private PusherConfiguration push;

    @Valid
    @NotNull
    private StorageConfiguration storage;

    @Valid
    @NotNull
    private PnConfiguration pubnub;

    private BraintreeConfiguration braintree = new BraintreeConfiguration();

    private PaypalConfiguration paypal = new PaypalConfiguration();

    @Valid
    @NotNull
    private SwaggerBundleConfiguration swagger;

    @Valid
    private AbstractAppenderFactory gelf;

    @Valid
    private JbRedisConfiguration redis;

    @Valid
    @NotNull
    private AwsConfiguration aws;

    @Valid
    private GMapsConfiguration gmaps = new GMapsConfiguration();

    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    @Valid
    @NotNull
    private JedisConfiguration jedis = new JedisConfiguration();

    @Valid
    @NotNull
    private SqueConfiguration sque = new SqueConfiguration();

    @Valid
    @NotNull
    private EsConfiguration es;

    @Valid
    @NotNull
    private JerseyClientConfiguration httpClient = new JerseyClientConfiguration();

    private RabbitMQConfiguration rabbitMq = new RabbitMQConfiguration();

    private List<WorkerConfiguration> workers;

    public PusherConfiguration getPush() {
        return push;
    }

    public void setPush(PusherConfiguration push) {
        this.push = push;
    }

    public Boolean getProduction() {
        return production;
    }

    public PaypalConfiguration getPaypal() {
        return paypal;
    }

    public void setPaypal(PaypalConfiguration paypal) {
        this.paypal = paypal;
    }

    public BraintreeConfiguration getBraintree() {
        return braintree;
    }

    public void setBraintree(BraintreeConfiguration braintree) {
        this.braintree = braintree;
    }

    public List<WorkerConfiguration> getWorkers() {
        return workers;
    }

    public void setWorkers(List<WorkerConfiguration> workers) {
        this.workers = workers;
    }

    public RabbitMQConfiguration getRabbitMq() {
        return rabbitMq;
    }

    public void setRabbitMq(RabbitMQConfiguration rabbitMq) {
        this.rabbitMq = rabbitMq;
    }

    public GMapsConfiguration getGmaps() {
        return gmaps;
    }

    public void setGmaps(GMapsConfiguration gmaps) {
        this.gmaps = gmaps;
    }

    public EsConfiguration getEs() {
        return es;
    }

    public void setEs(EsConfiguration es) {
        this.es = es;
    }

    public PnConfiguration getPubnub() {
        return pubnub;
    }

    public void setPubnub(PnConfiguration pubnub) {
        this.pubnub = pubnub;
    }

    public SwaggerBundleConfiguration getSwagger() {
        return swagger;
    }

    public void setSwagger(SwaggerBundleConfiguration swagger) {
        this.swagger = swagger;
    }

    public LinkedinConfiguration getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(LinkedinConfiguration linkedin) {
        this.linkedin = linkedin;
    }

    public DataSourceFactory getDatabase() {
        return database;
    }

    public SqueConfiguration getSque() {
        return sque;
    }

    public void setSque(SqueConfiguration sque) {
        this.sque = sque;
    }

    public JedisConfiguration getJedis() {
        return jedis;
    }

    public void setJedis(JedisConfiguration jedis) {
        this.jedis = jedis;
    }

    public JerseyClientConfiguration getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(JerseyClientConfiguration httpClient) {
        this.httpClient = httpClient;
    }

    public GoogleConfiguration getGoogle() {
        return google;
    }

    public void setGoogle(GoogleConfiguration google) {
        this.google = google;
    }

    public JerseyClientConfiguration getHttpClientConfiguration() {
        return httpClient;
    }

    public TwitterConfiguration getTwitter() {
        return twitter;
    }

    public void setTwitter(TwitterConfiguration twitter) {
        this.twitter = twitter;
    }

    public AwsConfiguration getAws() {
        return aws;
    }

    public void setAws(AwsConfiguration aws) {
        this.aws = aws;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDatabase(DataSourceFactory database) {
        this.database = database;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    public String getWebAppUri() {
        return webAppUri;
    }

    public void setWebAppUri(String webAppUri) {
        this.webAppUri = webAppUri;
    }

    public FacebookConfiguration getFacebook() {
        return facebook;
    }

    public void setFacebook(FacebookConfiguration facebook) {
        this.facebook = facebook;
    }

    public String getDefaultLimit() {
        return defaultLimit;
    }

    public void setDefaultLimit(String defaultLimit) {
        this.defaultLimit = defaultLimit;
    }

    public JbRedisConfiguration getRedis() {
        return redis;
    }

    public void setRedis(JbRedisConfiguration redis) {
        this.redis = redis;
    }

    public String getDefaultPage() {
        return defaultPage;
    }

    public void setDefaultPage(String defaultPage) {
        this.defaultPage = defaultPage;
    }

    public MailerConfiguration getMail() {
        return mail;
    }

    public void setMail(MailerConfiguration mail) {
        this.mail = mail;
    }

    public StorageConfiguration getStorage() {
        return storage;
    }

    public void setStorage(StorageConfiguration storage) {
        this.storage = storage;
    }

    public Boolean isProduction() {
        return production;
    }

    public void setProduction(Boolean production) {
        this.production = production;
    }

    public AbstractAppenderFactory getGelf() {
        return gelf;
    }

    public void setGelf(AbstractAppenderFactory gelf) {
        this.gelf = gelf;
    }
}