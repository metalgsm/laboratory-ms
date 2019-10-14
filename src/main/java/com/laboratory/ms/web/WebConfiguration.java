package com.laboratory.ms.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import com.laboratory.ms.base.web.DefaultControllerAdvice;
import com.laboratory.ms.base.web.LoggingProperties;
import com.laboratory.ms.base.web.MdcFilter;
import com.laboratory.ms.base.web.RequestLoggingFilter;
import com.laboratory.ms.base.web.ResponseLoggingFilter;
import com.laboratory.ms.base.web.ShouldLogFilter;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class WebConfiguration {

    @Bean
    DefaultControllerAdvice defaultControllerAdvice(ObjectMapper objectMapper) {
        return new DefaultControllerAdvice(objectMapper);
    }

    @Bean
    LoggingProperties loggingProperties() {
        return new LoggingProperties();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE + 100)
    ShouldLogFilter shouldLogFilter() {
        return new ShouldLogFilter(loggingProperties());
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE + 200)
    MdcFilter mdcFilter() {
        return new MdcFilter();
    }

    @Bean
    // XXX Must be before RequestLoggingFilter, as logging happens after doFilter()
    @Order(Ordered.HIGHEST_PRECEDENCE + 300)
    ResponseLoggingFilter responseLoggingFilter() {
        return new ResponseLoggingFilter();
    }

    @Bean
    // XXX Must be after ResponseLoggingFilter, as logging happens after doFilter()
    @Order(Ordered.HIGHEST_PRECEDENCE + 400)
    RequestLoggingFilter requestLoggingFilter() {
        return new RequestLoggingFilter();
    }

}
