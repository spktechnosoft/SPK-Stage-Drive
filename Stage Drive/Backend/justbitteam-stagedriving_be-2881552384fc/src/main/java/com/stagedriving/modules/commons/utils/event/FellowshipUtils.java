package com.stagedriving.modules.commons.utils.event;

import com.google.inject.Inject;
import com.stagedriving.modules.commons.ds.entities.Fellowship;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
public class FellowshipUtils {

    @Inject
    public FellowshipUtils() {
    }

    public Fellowship merge(Fellowship oldFellowship, Fellowship newFellowship) {

        oldFellowship.setName(newFellowship.getName() != null ? newFellowship.getName() : oldFellowship.getName());
        oldFellowship.setDescription(newFellowship.getDescription() != null ? newFellowship.getDescription() : oldFellowship.getDescription());
        oldFellowship.setStatus(newFellowship.getStatus() != null ? newFellowship.getStatus() : oldFellowship.getStatus());

        return oldFellowship;
    }
}


