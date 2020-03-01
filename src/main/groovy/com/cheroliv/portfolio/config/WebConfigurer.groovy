package com.cheroliv.portfolio.config

import groovy.util.logging.Slf4j
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

import java.time.Duration

import static com.cheroliv.portfolio.config.ApplicationConstants.*

@Slf4j
@Configuration
class WebConfigurer {
    @Bean
    CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource()
        CorsConfiguration config = new CorsConfiguration(
                allowedOrigins: [CORS_ALLOWED_ORIGINS],
                allowedMethods: [CORS_ALLOWED__METHODS],
                allowedHeaders: [CORS_ALLOWED_HEADERS],
                exposedHeaders: [CORS_EXPOSED_HEADERS],
                allowCredentials: true,
                maxAge: Duration.ofSeconds(1800)
        )
        if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
            log.debug("Registering CORS filter")
            source.registerCorsConfiguration(API_URL_PREFIX_ACCEPTED_EXPRESSION, config)
            source.registerCorsConfiguration(MANAGEMENT_URL_EXPRESSION, config)
            source.registerCorsConfiguration(API_DOC_URL, config)
        }
        new CorsFilter(source)
    }
}
