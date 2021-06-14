package com.studentmanager.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YAMLConfig {

    @Value("${student.address}")
    private String address;

    @Value("${student.gender}")
    private String gender;

    @Value("${student.environment}")
    private String environment;
}
