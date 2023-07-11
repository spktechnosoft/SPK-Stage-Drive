/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.commons.ds;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 *
 * @author simone
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagedResults<T> {

    Integer size;
    Integer page;
    Integer limit;
    List<T> data;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
    
}
