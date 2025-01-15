package com.iamind.user_ms;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = com.iamind.user_ms.config.TestContainerConfig.class)
class UserMsApplicationTests {

    @Test
    void contextLoads() {
    }
}