package com.stagedriving.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.stagedriving.AppTestSuite;
import com.stagedriving.commons.v1.auth.TokenDTO;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.*;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.ClientConfig;
import org.joda.time.DateTime;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by simone on 15/09/14.
 */
public class EventUtils {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(EventUtils.class);

    private static DropwizardAppRule<AppConfiguration> service = AppTestSuite.service;
    private Client client;

    public EventUtils() {
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

    public StgdrvResponseDTO postEvent(TokenDTO tokenDTO, String name) throws JsonProcessingException {
        return postEvent(tokenDTO, name, null, null);
    }

    public StgdrvResponseDTO postEvent(TokenDTO tokenDTO, String name, Double lat, Double lon) throws JsonProcessingException {

        EventDTO eventDTO = new EventDTO();
        eventDTO.setName(name);
        eventDTO.setDescription("Desc");
        eventDTO.setAddress("Via Corso 1");
        eventDTO.setCity("Rome");
        eventDTO.setTown("Rome");
        eventDTO.setCountry("italy");
        eventDTO.setZipcode("00100");
        eventDTO.setStart(new DateTime());
        eventDTO.setFinish(DateTime.now().plusDays(30));
        if (lat != null && lon != null) {
            CoordinateDTO coordinateDTO = new CoordinateDTO();
            coordinateDTO.setLatitude(lat);
            coordinateDTO.setLongitude(lon);
            eventDTO.setCoordinate(coordinateDTO);
        }

        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setUri("http://s3-eu-west-1.amazonaws.com/scx.events/41eb9fe6-7bd4-45cb-b00b-2f75fd47377b.png");
        List<ImageDTO> images = new ArrayList<>();
        images.add(imageDTO);
        eventDTO.setImages(images);

        Response response = client
                .target("http://localhost:" + service.getLocalPort() + "/v1/events")
                .request("application/json")
                .accept("application/json")
                .header("Authorization", "Bearer " + tokenDTO.getAccessToken())
                .post(Entity.json(eventDTO));

        StgdrvResponseDTO responseDTO = response.readEntity(new GenericType<StgdrvResponseDTO>() {
        });

        LOGGER.info("Response: " + responseDTO.getMessage());

        assertThat(response.getStatus()).isEqualTo(200);

        return responseDTO;
    }

    public EventDTO getEvent(TokenDTO tokenDTO, String eventId) {

        Response response = client
                .target("http://localhost:" + service.getLocalPort() + "/v1/events/" + eventId)
                .request("application/json").header("Authorization", "Bearer " + tokenDTO.getAccessToken())
                .get(Response.class);

        assertThat(response.getStatus()).isEqualTo(200);

        EventDTO eventDTO = response.readEntity(new GenericType<EventDTO>() {
        });

        assertThat(eventDTO.getId()).isEqualTo(eventId);

        return eventDTO;
    }

    public List<EventDTO> getEvents(TokenDTO tokenDTO, String page, String limit, String search, String sdate, String fdate, String category, Double lat, Double lon, Double distance) throws UnsupportedEncodingException {

        String coords = "";
        if (lat != null && lon != null) {
            coords = lat+","+lon;
        }

        Response response = client
                .target("http://localhost:" + service.getLocalPort() + "/v1/events?page="+page+"&limit="+limit+"&search="+search+"&sdate="+ URLEncoder.encode(sdate, "utf-8")+"&fdate="+URLEncoder.encode(fdate, "utf-8")+"&category="+category+"&coordinates="+coords+"&distance="+(distance != null ? distance : ""))
                .request("application/json").header("Authorization", "Bearer " + tokenDTO.getAccessToken())
                .get(Response.class);

        assertThat(response.getStatus()).isEqualTo(200);

        List<EventDTO> events = response.readEntity(new GenericType<List<EventDTO>>() {
        });

        return events;
    }

    public StgdrvResponseDTO postEventBooking(TokenDTO tokenDTO, String eventId, Boolean withTicket) throws JsonProcessingException {

        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setWithTicket(withTicket);

        Response response = client
                .target("http://localhost:" + service.getLocalPort() + "/v1/events/"+eventId+"/bookings")
                .request("application/json")
                .accept("application/json")
                .header("Authorization", "Bearer " + tokenDTO.getAccessToken())
                .post(Entity.json(bookingDTO));

        StgdrvResponseDTO responseDTO = response.readEntity(new GenericType<StgdrvResponseDTO>() {
        });

        LOGGER.info("Response: " + responseDTO.getMessage());

        assertThat(response.getStatus()).isEqualTo(200);

        return responseDTO;
    }

    public List<BookingDTO> getEventBookings(TokenDTO tokenDTO, String page, String limit, String eventId, String accountId) throws UnsupportedEncodingException {

        Response response = client
                .target("http://localhost:" + service.getLocalPort() + "/v1/events/"+eventId+"/bookings?page="+page+"&limit="+limit+"&accountId="+accountId)
                .request("application/json").header("Authorization", "Bearer " + tokenDTO.getAccessToken())
                .get(Response.class);

        assertThat(response.getStatus()).isEqualTo(200);

        List<BookingDTO> bookingDTOS = response.readEntity(new GenericType<List<BookingDTO>>() {
        });

        return bookingDTOS;
    }

    public StgdrvResponseDTO deleteEventBooking(TokenDTO tokenDTO, String eventId, String bookingId) throws JsonProcessingException {


        Response response = client
                .target("http://localhost:" + service.getLocalPort() + "/v1/events/"+eventId+"/bookings/"+bookingId)
                .request("application/json")
                .accept("application/json")
                .header("Authorization", "Bearer " + tokenDTO.getAccessToken())
                .delete();

        StgdrvResponseDTO responseDTO = response.readEntity(new GenericType<StgdrvResponseDTO>() {
        });

        LOGGER.info("Response: " + responseDTO.getMessage());

        assertThat(response.getStatus()).isEqualTo(200);

        return responseDTO;
    }

    public List<EventInterestDTO> getEventInterests(TokenDTO tokenDTO, String page, String limit, String eventId, String accountId) throws UnsupportedEncodingException {

        Response response = client
                .target("http://localhost:" + service.getLocalPort() + "/v1/events/"+eventId+"/interests?page="+page+"&limit="+limit+"&accountId="+accountId)
                .request("application/json").header("Authorization", "Bearer " + tokenDTO.getAccessToken())
                .get(Response.class);

        assertThat(response.getStatus()).isEqualTo(200);

        List<EventInterestDTO> interestDTOS = response.readEntity(new GenericType<List<EventInterestDTO>>() {
        });

        return interestDTOS;
    }

    public StgdrvResponseDTO postEventLike(TokenDTO tokenDTO, String eventId) throws JsonProcessingException {

        LikeDTO likeDTO = new LikeDTO();

        Response response = client
                .target("http://localhost:" + service.getLocalPort() + "/v1/events/"+eventId+"/likes")
                .request("application/json")
                .accept("application/json")
                .header("Authorization", "Bearer " + tokenDTO.getAccessToken())
                .post(Entity.json(likeDTO));

        StgdrvResponseDTO responseDTO = response.readEntity(new GenericType<StgdrvResponseDTO>() {
        });

        LOGGER.info("Response: " + responseDTO.getMessage());

        assertThat(response.getStatus()).isEqualTo(200);

        return responseDTO;
    }

    public List<LikeDTO> getEventLikes(TokenDTO tokenDTO, String page, String limit, String eventId, String accountId) throws UnsupportedEncodingException {

        Response response = client
                .target("http://localhost:" + service.getLocalPort() + "/v1/events/"+eventId+"/likes?page="+page+"&limit="+limit+"&accountId="+accountId)
                .request("application/json").header("Authorization", "Bearer " + tokenDTO.getAccessToken())
                .get(Response.class);

        assertThat(response.getStatus()).isEqualTo(200);

        List<LikeDTO> likeDTOS = response.readEntity(new GenericType<List<LikeDTO>>() {
        });

        return likeDTOS;
    }

    public StgdrvResponseDTO deleteEventLike(TokenDTO tokenDTO, String eventId, String likeId) throws JsonProcessingException {


        Response response = client
                .target("http://localhost:" + service.getLocalPort() + "/v1/events/"+eventId+"/likes/"+likeId)
                .request("application/json")
                .accept("application/json")
                .header("Authorization", "Bearer " + tokenDTO.getAccessToken())
                .delete();

        StgdrvResponseDTO responseDTO = response.readEntity(new GenericType<StgdrvResponseDTO>() {
        });

        LOGGER.info("Response: " + responseDTO.getMessage());

        assertThat(response.getStatus()).isEqualTo(200);

        return responseDTO;
    }

    public StgdrvResponseDTO postEventComment(TokenDTO tokenDTO, String eventId, String content) throws JsonProcessingException {

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setContent(content);

        Response response = client
                .target("http://localhost:" + service.getLocalPort() + "/v1/events/"+eventId+"/comments")
                .request("application/json")
                .accept("application/json")
                .header("Authorization", "Bearer " + tokenDTO.getAccessToken())
                .post(Entity.json(commentDTO));

        StgdrvResponseDTO responseDTO = response.readEntity(new GenericType<StgdrvResponseDTO>() {
        });

        LOGGER.info("Response: " + responseDTO.getMessage());

        assertThat(response.getStatus()).isEqualTo(200);

        return responseDTO;
    }

    public List<CommentDTO> getEventComments(TokenDTO tokenDTO, String page, String limit, String eventId, String accountId) throws UnsupportedEncodingException {

        Response response = client
                .target("http://localhost:" + service.getLocalPort() + "/v1/events/"+eventId+"/comments?page="+page+"&limit="+limit+"&accountId="+accountId)
                .request("application/json").header("Authorization", "Bearer " + tokenDTO.getAccessToken())
                .get(Response.class);

        assertThat(response.getStatus()).isEqualTo(200);

        List<CommentDTO> commentDTOS = response.readEntity(new GenericType<List<CommentDTO>>() {
        });

        return commentDTOS;
    }

    public StgdrvResponseDTO deleteEventComment(TokenDTO tokenDTO, String eventId, String likeId) throws JsonProcessingException {


        Response response = client
                .target("http://localhost:" + service.getLocalPort() + "/v1/events/"+eventId+"/comments/"+likeId)
                .request("application/json")
                .accept("application/json")
                .header("Authorization", "Bearer " + tokenDTO.getAccessToken())
                .delete();

        StgdrvResponseDTO responseDTO = response.readEntity(new GenericType<StgdrvResponseDTO>() {
        });

        LOGGER.info("Response: " + responseDTO.getMessage());

        assertThat(response.getStatus()).isEqualTo(200);

        return responseDTO;
    }
}
