package com.senai.suporte.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String projectDirectory = Paths.get(System.getProperty("user.dir"))
            .toAbsolutePath()
            .normalize()
            .toUri()
            .toString();
        registry.addResourceHandler("/senai-sp.svg")
            .addResourceLocations(projectDirectory);
        registry.addResourceHandler("/logo-small.png")
            .addResourceLocations(projectDirectory + "src/main/");
    }
}