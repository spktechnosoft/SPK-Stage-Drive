package com.stagedriving.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.justbit.commons.DateUtils;
import com.justbit.commons.TokenUtils;
import com.stagedriving.AppTestSuite;
import com.stagedriving.commons.v1.auth.TokenDTO;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.*;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.utils.AccountUtils;
import com.stagedriving.utils.EventUtils;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.joda.time.DateTime;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventResourceRestTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventResourceRestTest.class);

    private static DropwizardAppRule<AppConfiguration> service = AppTestSuite.service;
    private static AccountUtils userUtils = new AccountUtils();
    private static EventUtils eventUtils = new EventUtils();

    @Test
    public void AnewEvent() throws JsonProcessingException, InterruptedException, UnsupportedEncodingException {
        AccountDTO userDTO = userUtils.newUser("email"+ TokenUtils.generateUid()+"@stagedriving.com", TokenUtils.generateUid()
        );
        TokenDTO tokenDTO = userUtils.authUser(userDTO.getEmail(), userDTO.getPassword());

        StgdrvResponseDTO responseDTO = eventUtils.postEvent(tokenDTO, "Event name", 0.0, 0.0);

        EventDTO eventDTO = eventUtils.getEvent(tokenDTO, responseDTO.getId());
        assertThat(eventDTO.getImages().size()).isGreaterThanOrEqualTo(1);
        assertThat(eventDTO.getImages().get(0).getUri()).isNotEmpty();
        assertThat(eventDTO.getCoordinate().getLatitude()).isNotNull();
        assertThat(eventDTO.getCoordinate().getLongitude()).isNotNull();
        assertThat(eventDTO.getName()).isNotEmpty();
        assertThat(eventDTO.getDescription()).isNotEmpty();

        List<EventDTO> eventDTOs = eventUtils.getEvents(tokenDTO, "0", "20", "", DateUtils.dateToString(DateTime.now().minusSeconds(10).toDate()), "", "", null, null, null);
        assertThat(eventDTOs.size()).isEqualTo(1);
    }

    @Test
    public void BfetchEvents() throws JsonProcessingException, InterruptedException, UnsupportedEncodingException {
        AccountDTO userDTO = userUtils.newUser("email"+ TokenUtils.generateUid()+"@stagedriving.com", TokenUtils.generateUid()
        );
        TokenDTO tokenDTO = userUtils.authUser(userDTO.getEmail(), userDTO.getPassword());

        Double lat = 42.0;
        Double lon = -72.0;
        Double distance = 10.0;

        StgdrvResponseDTO responseDTO = eventUtils.postEvent(tokenDTO, "Event name near", 42.0, -72.0);
        responseDTO = eventUtils.postEvent(tokenDTO, "Event name far", -42.0, 72.0);

        List<EventDTO> eventDTOs = eventUtils.getEvents(tokenDTO, "0", "20", "", DateUtils.dateToString(DateTime.now().minusSeconds(30).toDate()), "", "", lat, lon, distance);
        assertThat(eventDTOs.size()).isEqualTo(1);

        distance = 400000.0;
        eventDTOs = eventUtils.getEvents(new TokenDTO(), "0", "20", "", DateUtils.dateToString(DateTime.now().minusSeconds(60).toDate()), "", "", lat, lon, distance);
        assertThat(eventDTOs.size()).isGreaterThanOrEqualTo(2);


        // Fetch by name
        responseDTO = eventUtils.postEvent(tokenDTO, "1 Eventone 1", -42.0, 72.0);
        responseDTO = eventUtils.postEvent(tokenDTO, "3 Eventone 1", -42.0, 72.0);
        eventDTOs = eventUtils.getEvents(tokenDTO, "0", "20", "eventone", DateUtils.dateToString(DateTime.now().minusSeconds(30).toDate()), "", "", null, null, null);
        assertThat(eventDTOs.size()).isEqualTo(2);
    }

    @Test
    public void CeventBooking() throws JsonProcessingException, InterruptedException, UnsupportedEncodingException {
        AccountDTO userDTO = userUtils.newUser("email"+ TokenUtils.generateUid()+"@stagedriving.com", TokenUtils.generateUid()
        );
        AccountDTO secondUserDTO = userUtils.newUser("email"+ TokenUtils.generateUid()+"@stagedriving.com", TokenUtils.generateUid()
        );
        TokenDTO tokenDTO = userUtils.authUser(userDTO.getEmail(), userDTO.getPassword());
        TokenDTO secondTokenDTO = userUtils.authUser(secondUserDTO.getEmail(), secondUserDTO.getPassword());

        StgdrvResponseDTO responseDTO = eventUtils.postEvent(tokenDTO, "Event name", 0.0, 0.0);
        String eventId = responseDTO.getId();
        StgdrvResponseDTO bookingResponseDTO = eventUtils.postEventBooking(tokenDTO, responseDTO.getId(), true);

        // Get bookings of requester account
        List<BookingDTO> bookingDTOS = eventUtils.getEventBookings(tokenDTO, "0", "20", responseDTO.getId(), "");
        assertThat(bookingDTOS.size()).isEqualTo(1);
        List<EventInterestDTO> eventInterests = eventUtils.getEventInterests(tokenDTO, "0", "20", responseDTO.getId(), "");
        assertThat(eventInterests.size()).isEqualTo(1);

        // Get bookings of other account
        bookingDTOS = eventUtils.getEventBookings(secondTokenDTO, "0", "20", responseDTO.getId(), userDTO.getId());
        assertThat(bookingDTOS.size()).isEqualTo(1);
        bookingDTOS = eventUtils.getEventBookings(secondTokenDTO, "0", "20", responseDTO.getId(), "");
        assertThat(bookingDTOS.size()).isEqualTo(0);


        // Now delete requester booking
        responseDTO = eventUtils.deleteEventBooking(tokenDTO, eventId, bookingResponseDTO.getId());
        // Getting again bookings of requester account
        bookingDTOS = eventUtils.getEventBookings(tokenDTO, "0", "20", responseDTO.getId(), "");
        assertThat(bookingDTOS.size()).isEqualTo(0);
        eventInterests = eventUtils.getEventInterests(tokenDTO, "0", "20", responseDTO.getId(), "");
        assertThat(eventInterests.size()).isEqualTo(1);
    }

    @Test
    public void DeventLike() throws JsonProcessingException, InterruptedException, UnsupportedEncodingException {
        AccountDTO userDTO = userUtils.newUser("email"+ TokenUtils.generateUid()+"@stagedriving.com", TokenUtils.generateUid());
        TokenDTO tokenDTO = userUtils.authUser(userDTO.getEmail(), userDTO.getPassword());

        StgdrvResponseDTO responseDTO = eventUtils.postEvent(tokenDTO, "Event name", 0.0, 0.0);
        String eventId = responseDTO.getId();
        StgdrvResponseDTO postEventLike = eventUtils.postEventLike(tokenDTO, responseDTO.getId());

        List<LikeDTO> likeDTOS = eventUtils.getEventLikes(tokenDTO, "0", "20", eventId, "");
        assertThat(likeDTOS.size()).isEqualTo(1);
        assertThat(likeDTOS.get(0).getId()).isEqualTo(postEventLike.getId());
        assertThat(likeDTOS.get(0).getAccount()).isNotNull();

        eventUtils.deleteEventLike(tokenDTO, eventId, postEventLike.getId());
        likeDTOS = eventUtils.getEventLikes(tokenDTO, "0", "20", eventId, "");
        assertThat(likeDTOS.size()).isEqualTo(0);
    }

    @Test
    public void EeventComment() throws JsonProcessingException, InterruptedException, UnsupportedEncodingException {
        AccountDTO userDTO = userUtils.newUser("email"+ TokenUtils.generateUid()+"@stagedriving.com", TokenUtils.generateUid());
        AccountDTO secondUserDTO = userUtils.newUser("email"+ TokenUtils.generateUid()+"@stagedriving.com", TokenUtils.generateUid()
        );
        TokenDTO tokenDTO = userUtils.authUser(userDTO.getEmail(), userDTO.getPassword());
        TokenDTO secondTokenDTO = userUtils.authUser(secondUserDTO.getEmail(), secondUserDTO.getPassword());

        StgdrvResponseDTO responseDTO = eventUtils.postEvent(tokenDTO, "Event name", 0.0, 0.0);
        String eventId = responseDTO.getId();
        StgdrvResponseDTO postEventComment = eventUtils.postEventComment(secondTokenDTO, responseDTO.getId(), "awesome comment");
        postEventComment = eventUtils.postEventComment(tokenDTO, responseDTO.getId(), "awesome comment");

        List<CommentDTO> commentDTOS = eventUtils.getEventComments(tokenDTO, "0", "20", eventId, "");
        assertThat(commentDTOS.size()).isEqualTo(2);

        eventUtils.deleteEventComment(tokenDTO, eventId, postEventComment.getId());
        commentDTOS = eventUtils.getEventComments(tokenDTO, "0", "20", eventId, "");
        assertThat(commentDTOS.size()).isEqualTo(1);
        assertThat(commentDTOS.get(0).getContent()).isEqualTo("awesome comment");
        assertThat(commentDTOS.get(0).getAccount()).isNotNull();
    }

}
