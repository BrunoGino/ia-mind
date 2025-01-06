package com.iamind.students_ms.service;

import com.iamind.students_ms.dto.StudentRequestDTO;
import com.iamind.students_ms.dto.StudentResponseDTO;

import java.util.List;

public interface StudentService {
    List<StudentResponseDTO> getAllStudents();

    StudentResponseDTO getStudentById(Long id);

    StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO);

    StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO);

    void deleteStudent(Long id);
}
