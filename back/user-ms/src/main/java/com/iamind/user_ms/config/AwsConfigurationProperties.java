package com.iamind.user_ms.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "aws")
public class AwsConfigurationProperties {

    private String accessKey;

    private String secretAccessKey;

    private String region;

    private String endpoint;

}