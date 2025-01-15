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

    @NotBlank(message = "AWS access key must be configured")
    private String accessKey;

    @NotBlank(message = "AWS secret access key must be configured")
    private String secretAccessKey;

    @NotBlank(message = "AWS region must be configured")
    private String region;

    private String endpoint;

}