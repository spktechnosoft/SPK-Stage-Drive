package com.stagedriving.modules.bundle.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.justbit.aws.S3Utils;
import com.stagedriving.commons.v1.resources.BundleDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

@Singleton
@Slf4j
public class BundleController {

    @Inject
    S3Utils s3Utils;
    @Inject
    AppConfiguration configuration;
    @Inject
    ObjectMapper objectMapper;

    private Client client;

    @Inject
    public BundleController() {
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
    }

    public void updateBundleCache() throws JsonProcessingException, InterruptedException {
        Response response = client
                .target(configuration.getBaseUri()+"v1/bundle")
                .request("application/json")
                .get(Response.class);

        BundleDTO bundleDTO = response.readEntity(new GenericType<BundleDTO>() {
        });

        String url = s3Utils.uploadData(objectMapper.writeValueAsString(bundleDTO), configuration.getStorage().getEventBucket(), "bundle.json");
        log.info("Bundle cache url {}", url);
    }
}
