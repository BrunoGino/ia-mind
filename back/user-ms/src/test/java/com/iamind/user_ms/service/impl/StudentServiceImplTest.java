package com.iamind.user_ms.service.impl;

import com.iamind.user_ms.dto.StudentRequestDTO;
import com.iamind.user_ms.dto.StudentResponseDTO;
import com.iamind.user_ms.exception.ResourceNotFoundException;
import com.iamind.user_ms.model.Student;
import com.iamind.user_ms.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
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
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student;
    private StudentRequestDTO studentRequestDTO;

    @BeforeEach
    void setUp() {
        student = Student.builder()
                .id(1L)
                .firstName("Alice")
                .lastName("Smith")
                .dateOfBirth(LocalDate.of(2005, 5, 15))
                .gender("Female")
                .fullAddress("123 Main St")
                .email("alice@example.com")
                .phone("+1-555-1234")
                .schoolYear("5th Grade")
                .classRoom("5A")
                .school("Springfield School")
                .shift("Morning")
                .guardianName("Emily Smith")
                .guardianPhone("+1-555-5678")
                .guardianEmail("emily@example.com")
                .build();

        studentRequestDTO = new StudentRequestDTO(
                "Alice",
                "Smith",
                LocalDate.of(2005, 5, 15),
                "Female",
                "123 Main St",
                "alice@example.com",
                "+1-555-1234",
                "5th Grade",
                "5A",
                "Springfield School",
                "Morning",
                null,
                null,
                null,
                null,
                "Emily Smith",
                "+1-555-5678",
                "emily@example.com"
        );
    }

    @Test
    void shouldReturnAllStudentsSuccessfully() {
        when(studentRepository.findAll()).thenReturn(List.of(student));

        List<StudentResponseDTO> students = studentService.getAllStudents();

        assertFalse(students.isEmpty());
        assertEquals(1, students.size());
        assertEquals("Alice", students.getFirst().firstName());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenNoStudentsExist() {
        when(studentRepository.findAll()).thenReturn(List.of());

        List<StudentResponseDTO> students = studentService.getAllStudents();

        assertTrue(students.isEmpty());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnStudentByIdSuccessfully() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        StudentResponseDTO response = studentService.getStudentById(1L);

        assertNotNull(response);
        assertEquals("Alice", response.firstName());
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenStudentNotFoundById() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> studentService.getStudentById(1L));
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void shouldCreateStudentSuccessfully() {
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentResponseDTO response = studentService.createStudent(studentRequestDTO);

        assertNotNull(response);
        assertEquals("Alice", response.firstName());
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void shouldUpdateStudentSuccessfully() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentResponseDTO response = studentService.updateStudent(1L, studentRequestDTO);

        assertNotNull(response);
        assertEquals("Alice", response.firstName());
        verify(studentRepository, times(1)).findById(1L);
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingStudent() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> studentService.updateStudent(1L, studentRequestDTO));
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void shouldDeleteStudentSuccessfully() {
        when(studentRepository.existsById(1L)).thenReturn(true);
        doNothing().when(studentRepository).deleteById(1L);

        studentService.deleteStudent(1L);

        verify(studentRepository, times(1)).existsById(1L);
        verify(studentRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingStudent() {
        when(studentRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> studentService.deleteStudent(1L));
        verify(studentRepository, times(1)).existsById(1L);
    }
}
