package com.iamind.user_ms.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

@TestConfiguration
public class TestContainerConfig {

    private static LocalStackContainer localStackContainer;

    static {
        localStackContainer = new LocalStackContainer(DockerImageName.parse("localstack/localstack:3.2"))
                .withCopyFileToContainer(MountableFile.forClasspathResource("init-dynamodb.sh", 0744), "/etc/localstack/init/ready.d/init-dynamodb.sh")
                .withServices(LocalStackContainer.Service.DYNAMODB)
                .waitingFor(Wait.forLogMessage(".*Executed init-dynamodb.sh.*", 1));
        localStackContainer.start();
    }

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("aws.access-key", localStackContainer::getAccessKey);
        registry.add("aws.secret-access-key", localStackContainer::getSecretKey);
        registry.add("aws.region", localStackContainer::getRegion);
        registry.add("aws.endpoint", localStackContainer::getEndpoint);
    }

}
