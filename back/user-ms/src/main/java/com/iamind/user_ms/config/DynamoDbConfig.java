package com.iamind.user_ms.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(value = AwsConfigurationProperties.class)
public class DynamoDbConfig {

    private final AwsConfigurationProperties awsConfigurationProperties;

    @Bean
    @Profile("!test && !local")
    public DynamoDbClient dynamoDbClient() {
        return DynamoDbClient.builder()
                .region(Region.of("eu-west-1"))
                .build();
    }

    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }

    @Bean
    @Profile("test | local")
    public DynamoDbClient testDynamoDbClient() {
        var credentials = constructCredentials();
        var region = Region.of(awsConfigurationProperties.getRegion());
        return DynamoDbClient.builder()
                .endpointOverride(URI.create(awsConfigurationProperties.getEndpoint())) // Custom endpoint for local testing
                .region(region)
                .credentialsProvider(credentials)
                .build();
    }

    private StaticCredentialsProvider constructCredentials() {
        var accessKey = awsConfigurationProperties.getAccessKey();
        var secretAccessKey = awsConfigurationProperties.getSecretAccessKey();
        return StaticCredentialsProvider.create(
                AwsBasicCredentials.create(accessKey, secretAccessKey)
        );
    }


}
