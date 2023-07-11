package com.stagedriving.modules.commons.conf;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: simone
 * Date: 09/09/13
 * Time: 13:08
 * To change this template use File | Settings | File Templates.
 */
public class GMapsConfiguration {

    @Valid
    @NotNull
    private String key = "AIzaSyA7u7oBqdjgWp77CB1PpVtVajgUW8jA";


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
