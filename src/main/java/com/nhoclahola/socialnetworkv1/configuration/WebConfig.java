package com.nhoclahola.socialnetworkv1.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer
{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Đường dẫn URL cho các tệp tĩnh
        registry.addResourceHandler("/uploads/**")
                // Đường dẫn thư mục chứa các tệp tĩnh
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/uploads/");
    }
}
