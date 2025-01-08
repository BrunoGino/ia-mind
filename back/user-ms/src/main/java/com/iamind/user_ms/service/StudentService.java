package com.iamind.user_ms.service;

import com.iamind.user_ms.dto.StudentRequestDTO;
import com.iamind.user_ms.dto.StudentResponseDTO;

import java.util.List;

public interface StudentService {
    List<StudentResponseDTO> getAllStudents();

    StudentResponseDTO getStudentById(Long id);

    StudentResponseDTO createStudent(StudentRequestDTO dto);

    StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto);

    void deleteStudent(Long id);
}
