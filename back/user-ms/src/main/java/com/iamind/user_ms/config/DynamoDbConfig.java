package com.iamind.user_ms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDbConfig {

    @Bean
    @Primary
    public DynamoDbClient dynamoDbClient() {
//        String accessKey = System.getenv("AWS_ACCESS_KEY_ID");
//        String secretKey = System.getenv("AWS_SECRET_ACCESS_KEY");
//        String region = System.getenv("AWS_REGION");
//
//        if (accessKey == null || secretKey == null || region == null) {
//            throw new IllegalStateException("AWS credentials or region not configured in environment variables.");
//        }
//
//        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);

        return DynamoDbClient.builder()
//                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .region(Region.of("eu-west-1"))
                .build();
    }

    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }

//    @Bean
//    public DynamoDBMapperConfig dynamoDBMapperConfig() {
//        return new DynamoDBMapperConfig.Builder()
//                .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE)
//                .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.EVENTUAL)
//                .withPaginationLoadingStrategy(DynamoDBMapperConfig.PaginationLoadingStrategy.LAZY_LOADING)
//                .build();
//
//    }
//
//    @Bean
//    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB, DynamoDBMapperConfig dynamoDBMapperConfig) {
//        return new DynamoDBMapper(amazonDynamoDB, dynamoDBMapperConfig);
//    }
}
