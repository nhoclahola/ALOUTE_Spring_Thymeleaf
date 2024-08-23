package com.nhoclahola.socialnetworkv1.configuration;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@AllArgsConstructor
public class WebConfig implements WebMvcConfigurer
{
    private final ServerProperties serverProperties;
    private static String address;
    private static int port;
    public static String serverAdress;
    @PostConstruct
    private void init() throws UnknownHostException
    {
        address = serverProperties.getAddress() != null && !"0.0.0.0".equals(serverProperties.getAddress().getHostAddress())
                ? serverProperties.getAddress().getHostAddress()
                : InetAddress.getLocalHost().getHostAddress();
        port = serverProperties.getPort() != null ? serverProperties.getPort() : 8080;
        serverAdress = "http://" + address + ":" + port;
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/uploads/");
    }
}
