package com.iamind.user_ms.config;

import lombok.RequiredArgsConstructor;
import org.springdoc.webmvc.ui.SwaggerIndexTransformer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig extends WebMvcConfigurationSupport {
    private final SwaggerIndexTransformer swaggerIndexTransformer;

    final String[] swaggerLocations = {
            "/users-swagger-ui.html",
            "/swagger-ui*/**"
    };

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {
        resourceHandlerRegistry.addResourceHandler(swaggerLocations)
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
                .resourceChain(false)
                .addTransformer(swaggerIndexTransformer);
    }
}
