/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.commons.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.exception.StgdrvInvalidRequestException;
import com.stagedriving.modules.commons.utils.SessionUtils;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QueryParamsFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryParamsFilter.class);

    private AppConfiguration configuration;

    @Inject
    private SessionFactory sessionFactory;

    @Inject
    ObjectMapper objectMapper;

    @Inject
    public QueryParamsFilter(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(
            ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException, StgdrvInvalidRequestException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if (httpServletRequest.getParameter("size") != null && httpServletRequest.getParameter("size").equals("1")) {
            String key = Thread.currentThread().getName();
            SessionUtils.sessions.put(key, "size");
        }

        chain.doFilter(request, response);

    }
}