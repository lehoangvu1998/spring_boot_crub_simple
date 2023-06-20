package com.example.springboot;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.springboot.controller.student.StudentsController;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public GroupedOpenApi apiV1() {
        return GroupedOpenApi.builder()
                .group("v1")
                .packagesToScan(StudentsController.class.getPackageName())
                .build();
    }
}
