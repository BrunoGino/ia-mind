package com.iamind.students_ms.repository;

import com.iamind.students_ms.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}

