package com.iamind.user_ms.repository.impl;

import com.iamind.user_ms.model.dynamodb.DynamoDbRepository;
import com.iamind.user_ms.model.dynamodb.UserTable;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Component
public class UserRepositoryImpl extends DynamoDbRepository<UserTable> {


    public UserRepositoryImpl(DynamoDbClient dynamoDbClient) {
        super(dynamoDbClient, UserTable.class, "iamind_users_table");
    }

}
