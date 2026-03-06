package com.relay.config.logging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.util.Set;

@Configuration
public class RequestLoggingFilterConfig {

    private static final Set<String> SENSITIVE_HEADERS = Set.of(
            "authorization", "cookie", "set-cookie", "x-api-key"
    );

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(true);
        filter.setHeaderPredicate(name -> !SENSITIVE_HEADERS.contains(name.toLowerCase()));
        filter.setAfterMessagePrefix("REQUEST DATA: ");
        return filter;
    }
}
