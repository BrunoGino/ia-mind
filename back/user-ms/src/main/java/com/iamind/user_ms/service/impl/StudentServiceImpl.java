package com.iamind.user_ms.service.impl;

import com.iamind.user_ms.dto.StudentRequestDTO;
import com.iamind.user_ms.dto.StudentResponseDTO;
import com.iamind.user_ms.exception.ResourceNotFoundException;
import com.iamind.user_ms.model.Student;
import com.iamind.user_ms.repository.StudentRepository;
import com.iamind.user_ms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
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
                        student.getGender(),
                        student.getFullAddress(),
                        student.getEmail(),
                        student.getPhone(),
                        student.getSchoolYear(),
                        student.getClassRoom(),
                        student.getSchool(),
                        student.getShift(),
                        student.getMentalHealthHistory(),
                        student.getPreviousDiagnoses(),
                        student.getMedicationsInUse(),
                        student.getAllergies(),
                        student.getGuardianName(),
                        student.getGuardianPhone(),
                        student.getGuardianEmail(),
                        student.getNotes()
                ))
                .toList();
    }

    @Override
    public StudentResponseDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));

        return mapToResponseDTO(student);
    }

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO dto) {
        Student student = Student.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .dateOfBirth(dto.dateOfBirth())
                .gender(dto.gender())
                .fullAddress(dto.fullAddress())
                .email(dto.email())
                .phone(dto.phone())
                .schoolYear(dto.schoolYear())
                .classRoom(dto.classRoom())
                .school(dto.school())
                .shift(dto.shift())
                .guardianName(dto.guardianName())
                .guardianPhone(dto.guardianPhone())
                .guardianEmail(dto.guardianEmail())
                .build();

        Student saved = studentRepository.save(student);
        return mapToResponseDTO(saved);
    }

    @Override
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));

        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setDateOfBirth(dto.dateOfBirth());
        student.setGender(dto.gender());
        student.setSchoolYear(dto.schoolYear());

        Student updated = studentRepository.save(student);
        return mapToResponseDTO(updated);
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }

    private StudentResponseDTO mapToResponseDTO(Student student) {
        return new StudentResponseDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getDateOfBirth(),
                student.getGender(),
                student.getFullAddress(),
                student.getEmail(),
                student.getPhone(),
                student.getSchoolYear(),
                student.getClassRoom(),
                student.getSchool(),
                student.getShift(),
                student.getMentalHealthHistory(),
                student.getPreviousDiagnoses(),
                student.getMedicationsInUse(),
                student.getAllergies(),
                student.getGuardianName(),
                student.getGuardianPhone(),
                student.getGuardianEmail(),
                student.getNotes()
        );
    }
}
