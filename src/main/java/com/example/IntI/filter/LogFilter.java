package com.example.IntI.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.UUID;

@Log4j2
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
    throws IOException,ServletException {
        log.info("log filter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();
        String uuid = UUID.randomUUID().toString();

        try{
            log.info("REQUEST [{}][{}]",uuid,requestURI);
            filterChain.doFilter(request,response);
        }catch (Exception e){
            throw e;
        }finally {
            log.info("RESPONSE [{}][{}]", uuid, requestURI);
        }

    }

}
