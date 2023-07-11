package com.stagedriving.modules.commons.ds;

import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostUpdateEvent;

import java.util.Arrays;
import java.util.List;

public class AbstractEventListener {

    protected boolean checkChanges(PostUpdateEvent postUpdateEvent, String... names) {
        if (postUpdateEvent == null) {
            return false;
        }

        List<String> nms = Arrays.asList(names);
        for (int i = 0; i < postUpdateEvent.getDirtyProperties().length; i++) {
            int currentIndex = postUpdateEvent.getDirtyProperties()[i];
            String key = postUpdateEvent.getPersister().getPropertyNames()[currentIndex];
            if (nms.indexOf(key) != -1 && !postUpdateEvent.getState()[currentIndex].equals(postUpdateEvent.getOldState()[currentIndex])) {
                return true;
            }
        }
        return false;
    }

    protected boolean checkChanges(PostUpdateEvent postUpdateEvent, String name) {
        if (postUpdateEvent == null) {
            return false;
        }
        for (int i = 0; i < postUpdateEvent.getDirtyProperties().length; i++) {
            int currentIndex = postUpdateEvent.getDirtyProperties()[i];
            String key = postUpdateEvent.getPersister().getPropertyNames()[currentIndex];
            if (key.equals(name) && !postUpdateEvent.getState()[currentIndex].equals(postUpdateEvent.getOldState()[currentIndex])) {
                return true;
            }
        }
        return false;
    }

    protected boolean checkChanges(PostUpdateEvent postUpdateEvent, String name, String state) {
        if (postUpdateEvent == null) {
            return false;
        }
        for (int i = 0; i < postUpdateEvent.getDirtyProperties().length; i++) {
            int currentIndex = postUpdateEvent.getDirtyProperties()[i];
            String key = postUpdateEvent.getPersister().getPropertyNames()[currentIndex];
            if (key.equals(name) && !postUpdateEvent.getOldState()[currentIndex].equals(postUpdateEvent.getState()[currentIndex])) {
                if (postUpdateEvent.getState()[currentIndex].equals(state)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected Object getValue(PostInsertEvent postUpdateEvent, String name) {
        if (postUpdateEvent == null) {
            return false;
        }
        for (int i = 0; i < postUpdateEvent.getPersister().getPropertyNames().length; i++) {
            String key = postUpdateEvent.getPersister().getPropertyNames()[i];
            if (key.equals(name)) {
                return postUpdateEvent.getState()[i];
            }
        }
        return false;
    }

    protected String getChangesResult(PostUpdateEvent postUpdateEvent, String name, String state) {
        if (postUpdateEvent == null) {
            return null;
        }
        for (int i : postUpdateEvent.getDirtyProperties()) {
            int currentIndex = postUpdateEvent.getDirtyProperties()[i];
            String key = postUpdateEvent.getPersister().getPropertyNames()[currentIndex];
            if (key.equals(name) && !postUpdateEvent.getOldState()[currentIndex].equals(postUpdateEvent.getState()[currentIndex])) {
                if (postUpdateEvent.getState()[currentIndex].equals(state)) {
                    return (String) postUpdateEvent.getOldState()[currentIndex];
                }
            }
        }

        return null;
    }

    protected String getChangesResult(PostUpdateEvent postUpdateEvent, String name) {
        if (postUpdateEvent == null) {
            return null;
        }
        for (int i : postUpdateEvent.getDirtyProperties()) {
            int currentIndex = postUpdateEvent.getDirtyProperties()[i];
            String key = postUpdateEvent.getPersister().getPropertyNames()[currentIndex];
            if (key.equals(name) && !postUpdateEvent.getOldState()[currentIndex].equals(postUpdateEvent.getState()[currentIndex])) {
                return (String) postUpdateEvent.getOldState()[currentIndex];
            }
        }

        return null;
    }

    protected Object getValue(PostUpdateEvent postUpdateEvent, String name) {
        for (int i = 0; i < postUpdateEvent.getPersister().getPropertyNames().length; i++) {
            String key = postUpdateEvent.getPersister().getPropertyNames()[i];
            if (key.equals(name)) {
                return postUpdateEvent.getState()[i];
            }
        }
        return false;
    }

    protected Object getOldValue(PostUpdateEvent postUpdateEvent, String name) {
        for (int i = 0; i < postUpdateEvent.getPersister().getPropertyNames().length; i++) {
            String key = postUpdateEvent.getPersister().getPropertyNames()[i];
            if (key.equals(name)) {
                return postUpdateEvent.getOldState()[i];
            }
        }
        return false;
    }
}
