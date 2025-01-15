package com.iamind.user_ms.repository;

import com.iamind.user_ms.model.dynamodb.UserTable;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<UserTable> findAll();

    Optional<UserTable> findById(String id);

    UserTable save(UserTable userTableRow);

    void delete(UserTable userTableRow);
}
