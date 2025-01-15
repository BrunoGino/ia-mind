package com.iamind.user_ms.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration
public class TestContainerConfig {

    private static final PostgreSQLContainer<?> postgresContainer =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:16.4"))
                    .withDatabaseName("test_db")
                    .withUsername("test_user")
                    .withPassword("test_pass");

    static {
        postgresContainer.start();
        System.setProperty("TESTCONTAINERS_JDBC_URL", postgresContainer.getJdbcUrl());
        System.setProperty("TESTCONTAINERS_USERNAME", postgresContainer.getUsername());
        System.setProperty("TESTCONTAINERS_PASSWORD", postgresContainer.getPassword());
    }
}
