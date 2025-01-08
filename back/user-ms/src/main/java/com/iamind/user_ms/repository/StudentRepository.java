package com.iamind.user_ms.repository;

import com.iamind.user_ms.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
