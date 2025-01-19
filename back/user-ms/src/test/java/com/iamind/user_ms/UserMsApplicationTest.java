package com.iamind.user_ms;

import com.iamind.user_ms.config.InitializeDynamoDb;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@InitializeDynamoDb
class UserMsApplicationTests {

    @Test
    void contextLoads() {
    }
}