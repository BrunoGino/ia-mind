package com.iamind.students_ms.service;

import com.iamind.students_ms.dto.StudentRequestDTO;
import com.iamind.students_ms.dto.StudentResponseDTO;
import com.iamind.students_ms.exception.ResourceNotFoundException;
import com.iamind.students_ms.model.Student;
import com.iamind.students_ms.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(student -> new StudentResponseDTO(
                        student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getDateOfBirth(),
                        student.getSchool(),
                        student.getRegistrationNumber(),
                        student.getEmail()
                ))
                .toList();
    }

    @Override
    public StudentResponseDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));
        return new StudentResponseDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getDateOfBirth(),
                student.getSchool(),
                student.getRegistrationNumber(),
                student.getEmail()
        );
    }

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO dto) {
        Student student = Student.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .dateOfBirth(dto.dateOfBirth())
                .school(dto.school())
                .registrationNumber(dto.registrationNumber())
                .email(dto.email())
                .build();
        Student saved = studentRepository.save(student);
        return new StudentResponseDTO(
                saved.getId(),
                saved.getFirstName(),
                saved.getLastName(),
                saved.getDateOfBirth(),
                saved.getSchool(),
                saved.getRegistrationNumber(),
                saved.getEmail()
        );
    }

    @Override
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));

        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setDateOfBirth(dto.dateOfBirth());
        student.setSchool(dto.school());
        student.setRegistrationNumber(dto.registrationNumber());
        student.setEmail(dto.email());

        Student updated = studentRepository.save(student);
        return new StudentResponseDTO(
                updated.getId(),
                updated.getFirstName(),
                updated.getLastName(),
                updated.getDateOfBirth(),
                updated.getSchool(),
                updated.getRegistrationNumber(),
                updated.getEmail()
        );
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }
}
