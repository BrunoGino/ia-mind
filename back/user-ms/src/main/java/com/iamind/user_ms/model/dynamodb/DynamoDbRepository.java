package com.iamind.user_ms.model.dynamodb;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.List;
import java.util.Optional;

public abstract class DynamoDbRepository<T> {

    private final DynamoDbTable<T> table;

    public DynamoDbRepository(DynamoDbClient dynamoDbClient, Class<T> clazz, String tableName) {
        this.table = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build()
                .table(tableName, TableSchema.fromBean(clazz));
    }

    public List<T> findAll() {
        return this.table.scan().items().stream().toList();
    }

    public void save(T item) {
        table.putItem(item);
    }

    public Optional<T> findById(String id) {
        return Optional.ofNullable(table.getItem(r -> r.key(k -> k.partitionValue(id))));
    }

    public void delete(T item) {
        table.deleteItem(item);
    }
}