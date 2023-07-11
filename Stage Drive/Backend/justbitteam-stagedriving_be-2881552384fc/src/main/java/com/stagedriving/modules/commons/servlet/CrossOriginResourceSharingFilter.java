/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.commons.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.exception.StgdrvInvalidRequestException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CrossOriginResourceSharingFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrossOriginResourceSharingFilter.class);

    private AppConfiguration configuration;

    @Inject
    private SessionFactory sessionFactory;

    @Inject
    ObjectMapper objectMapper;

    @Inject
    public CrossOriginResourceSharingFilter(AppConfiguration configuration) {
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

        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        httpServletResponse.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");

        chain.doFilter(request, response);

    }
}