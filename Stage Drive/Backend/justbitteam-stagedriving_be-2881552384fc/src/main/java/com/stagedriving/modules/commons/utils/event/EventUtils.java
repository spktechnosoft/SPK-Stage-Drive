package com.stagedriving.modules.commons.utils.event;

import com.google.inject.Inject;
import com.stagedriving.modules.commons.ds.entities.Event;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
public class EventUtils {

    @Inject
    public EventUtils() {
    }

    public Event merge(Event oldEvent, Event newEvent) {

        oldEvent.setName(newEvent.getName() != null ? newEvent.getName() : oldEvent.getName());
        oldEvent.setDescription(newEvent.getDescription() != null ? newEvent.getDescription() : oldEvent.getDescription());
        oldEvent.setLatitude(newEvent.getLatitude() != null ? newEvent.getLatitude() : oldEvent.getLatitude());
        oldEvent.setLongitude(newEvent.getLongitude() != null ? newEvent.getLongitude() : oldEvent.getLongitude());
        oldEvent.setCapacity(newEvent.getCapacity() != null ? newEvent.getCapacity() : oldEvent.getCapacity());
        oldEvent.setStatus(newEvent.getStatus() != null ? newEvent.getStatus() : oldEvent.getStatus());
        oldEvent.setCategory(newEvent.getCategory() != null ? newEvent.getCategory() : oldEvent.getCategory());
        oldEvent.setVisible(newEvent.getVisible() != null ? newEvent.getVisible() : oldEvent.getVisible());
        oldEvent.setCode(newEvent.getCode() != null ? newEvent.getCode() : oldEvent.getCode());
        oldEvent.setLevel(newEvent.getLevel() != null ? newEvent.getLevel() : oldEvent.getLevel());
        oldEvent.setPiva(newEvent.getPiva() != null ? newEvent.getPiva() : oldEvent.getPiva());
        oldEvent.setMobile(newEvent.getMobile() != null ? newEvent.getMobile() : oldEvent.getMobile());
        oldEvent.setPhone(newEvent.getPhone() != null ? newEvent.getPhone() : oldEvent.getPhone());
        oldEvent.setCity(newEvent.getCity() != null ? newEvent.getCity() : oldEvent.getCity());
        oldEvent.setTown(newEvent.getTown() != null ? newEvent.getTown() : oldEvent.getTown());
        oldEvent.setAddress(newEvent.getAddress() != null ? newEvent.getAddress() : oldEvent.getAddress());
        oldEvent.setZipcode(newEvent.getZipcode() != null ? newEvent.getZipcode() : oldEvent.getZipcode());
        oldEvent.setPass(newEvent.getPass() != null ? newEvent.getPass() : oldEvent.getPass());
        oldEvent.setCover(newEvent.getCover() != null ? newEvent.getCover() : oldEvent.getCover());
        oldEvent.setAvatar(newEvent.getAvatar() != null ? newEvent.getAvatar() : oldEvent.getAvatar());
        oldEvent.setRank(newEvent.getRank() != null ? newEvent.getRank() : oldEvent.getRank());
        oldEvent.setStart(newEvent.getStart() != null ? newEvent.getStart() : oldEvent.getStart());
        oldEvent.setFinish(newEvent.getFinish() != null ? newEvent.getFinish() : oldEvent.getFinish());
        oldEvent.setOrganizer(newEvent.getOrganizer() != null ? newEvent.getOrganizer() : oldEvent.getOrganizer());
        oldEvent.setWebsite(newEvent.getWebsite() != null ? newEvent.getWebsite() : oldEvent.getWebsite());
        oldEvent.setParking(newEvent.getParking() != null ? newEvent.getParking() : oldEvent.getParking());

        return oldEvent;
    }
}


