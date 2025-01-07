package com.iamind.user_ms.repository;

import com.iamind.user_ms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

