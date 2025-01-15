package com.iamind.user_ms.service.impl;

import com.iamind.user_ms.converter.UserTableToStudentConverter;
import com.iamind.user_ms.dto.StudentRequestDTO;
import com.iamind.user_ms.dto.StudentResponseDTO;
import com.iamind.user_ms.exception.ResourceNotFoundException;
import com.iamind.user_ms.model.Student;
import com.iamind.user_ms.model.dynamodb.UserTable;
import com.iamind.user_ms.repository.impl.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private UserRepositoryImpl userRepository;

    @Mock
    private UserTableToStudentConverter converter;

    @InjectMocks
    private StudentServiceImpl service;

    @Test
    void testGetAllStudents() {
        // Arrange
        UserTable userTable = new UserTable();
        userTable.setId("1");
        userTable.setFirstName("John");
        userTable.setLastName("Doe");
        userTable.setDateOfBirth(LocalDate.of(2005, 1, 1));

        when(userRepository.findAll()).thenReturn(List.of(userTable));

        // Act
        List<StudentResponseDTO> result = service.getAllStudents();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John", result.getFirst().firstName());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetStudentById_Success() {
        // Arrange
        String id = "1";
        UserTable userTable = new UserTable();
        userTable.setId(id);
        userTable.setFirstName("John");
        userTable.setLastName("Doe");

        Student student = new Student();
        student.setId(id);
        student.setFirstName("John");
        student.setLastName("Doe");

        when(userRepository.findById(id)).thenReturn(Optional.of(userTable));
        when(converter.convert(userTable)).thenReturn(student);

        // Act
        StudentResponseDTO result = service.getStudentById(id);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.firstName());
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void testGetStudentById_NotFound() {
        // Arrange
        String id = "1";
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> service.getStudentById(id));
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void testCreateStudent() {
        // Arrange
        StudentRequestDTO requestDTO = new StudentRequestDTO(
                "John", "Doe", LocalDate.of(2005, 1, 1), "Male",
                "123 Main St", "john.doe@example.com", "555-1234",
                "2023", "10A", "Springfield High", "Morning",
                null, null, null, null,
                "Jane Doe", "555-5678", "jane.doe@example.com"
        );

        Student student = Student.builder()
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(LocalDate.of(2005, 1, 1))
                .gender("Male")
                .fullAddress("123 Main St")
                .email("john.doe@example.com")
                .phone("555-1234")
                .schoolYear("2023")
                .classRoom("10A")
                .school("Springfield High")
                .shift("Morning")
                .guardianName("Jane Doe")
                .guardianPhone("555-5678")
                .guardianEmail("jane.doe@example.com")
                .build();
        student.setId("1");

        UserTable userTable = new UserTable();

        when(converter.convert(any(Student.class))).thenReturn(userTable);
        when(userRepository.findById(anyString())).thenReturn(Optional.of(userTable));
        lenient().when(converter.convert(userTable)).thenReturn(student);

        // Act
        StudentResponseDTO result = service.createStudent(requestDTO);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.firstName());
        verify(userRepository, times(1)).save(userTable);
    }

    @Test
    void testUpdateStudent_Success() {
        // Arrange
        String id = "1";
        StudentRequestDTO requestDTO = new StudentRequestDTO(
                "John", "Doe", LocalDate.of(2005, 1, 1), "Male",
                "123 Main St", "john.doe@example.com", "555-1234",
                "2023", "10A", "Springfield High", "Morning",
                null, null, null, null,
                "Jane Doe", "555-5678", "jane.doe@example.com"
        );

        UserTable userTable = new UserTable();
        userTable.setId(id);

        Student student = new Student();
        student.setId(id);

        when(userRepository.findById(id)).thenReturn(Optional.of(userTable));
        when(converter.convert(userTable)).thenReturn(student);
        when(converter.convert(student)).thenReturn(userTable);

        // Act
        StudentResponseDTO result = service.updateStudent(id, requestDTO);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.firstName());
        verify(userRepository, times(1)).save(userTable);
    }

    @Test
    void testUpdateStudent_NotFound() {
        // Arrange
        String id = "1";
        StudentRequestDTO requestDTO = new StudentRequestDTO(
                "John", "Doe", LocalDate.of(2005, 1, 1), "Male",
                "123 Main St", "john.doe@example.com", "555-1234",
                "2023", "10A", "Springfield High", "Morning",
                null, null, null, null,
                "Jane Doe", "555-5678", "jane.doe@example.com"
        );

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> service.updateStudent(id, requestDTO));
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void testDeleteStudent_Success() {
        // Arrange
        String id = "1";
        UserTable userTable = new UserTable();
        when(userRepository.findById(id)).thenReturn(Optional.of(userTable));

        // Act
        service.deleteStudent(id);

        // Assert
        verify(userRepository, times(1)).delete(userTable);
    }

    @Test
    void testDeleteStudent_NotFound() {
        // Arrange
        String id = "1";
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> service.deleteStudent(id));
        verify(userRepository, times(1)).findById(id);
    }
}
