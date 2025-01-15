package com.iamind.user_ms.service.impl;

import com.iamind.user_ms.converter.UserTableToStudentConverter;
import com.iamind.user_ms.dto.StudentRequestDTO;
import com.iamind.user_ms.dto.StudentResponseDTO;
import com.iamind.user_ms.exception.ResourceNotFoundException;
import com.iamind.user_ms.model.Student;
import com.iamind.user_ms.model.dynamodb.DynamoDbRepository;
import com.iamind.user_ms.model.dynamodb.UserTable;
import com.iamind.user_ms.repository.impl.UserRepositoryImpl;
import com.iamind.user_ms.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final DynamoDbRepository<UserTable> userRepository;
    private final UserTableToStudentConverter userTableToStudentConverter;

    public StudentServiceImpl(UserRepositoryImpl userRepository, UserTableToStudentConverter userTableToStudentConverter) {
        this.userRepository = userRepository;
        this.userTableToStudentConverter = userTableToStudentConverter;
    }

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        return userRepository.findAll()
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
    public StudentResponseDTO getStudentById(String id) {
        Student student = userRepository.findById(id)
                .map(userTableToStudentConverter::convert)
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

        student.setId(student.generateHashKey());
        userRepository.save(userTableToStudentConverter.convert(student));


        Student saved = userTableToStudentConverter.convert(this.userRepository.findById(student.getId()).get());
        return mapToResponseDTO(saved);
    }

    @Override
    public StudentResponseDTO updateStudent(String id, StudentRequestDTO dto) {
        Student student = userRepository.findById(id)
                .map(userTableToStudentConverter::convert)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));

        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setDateOfBirth(dto.dateOfBirth());
        student.setGender(dto.gender());
        student.setSchoolYear(dto.schoolYear());

        this.userRepository.save(userTableToStudentConverter.convert(student));
        Student updated = userTableToStudentConverter.convert(this.userRepository.findById(student.getId()).get());
        return mapToResponseDTO(updated);
    }

    @Override
    public void deleteStudent(String id) {
        Optional<UserTable> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("Student not found with ID: " + id);
        }
        userRepository.delete(user.get());
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
