package com.iamind.user_ms.service.impl;

import com.iamind.user_ms.dto.PsychopedagogistRequestDTO;
import com.iamind.user_ms.dto.PsychopedagogistResponseDTO;
import com.iamind.user_ms.exception.ResourceNotFoundException;
import com.iamind.user_ms.model.Psychopedagogist;
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
class PsychopedagogistServiceImplTest {

    @Mock
    private PsychopedagogistRepository psychopedagogistRepository;

    @InjectMocks
    private PsychopedagogistServiceImpl psychopedagogistService;

    private Psychopedagogist psychopedagogist;
    private PsychopedagogistRequestDTO psychopedagogistRequestDTO;

    @BeforeEach
    void setUp() {
        psychopedagogist = Psychopedagogist.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(LocalDate.of(1985, 7, 12))
                .gender("Male")
                .fullAddress("456 Elm Street")
                .email("john.doe@example.com")
                .phone("+1-555-9876")
                .professionalRegistration("CRP-12345")
                .academicFormation("Psychology PhD")
                .specializations("Autism, ADHD")
                .experienceInYears(10)
                .build();

        psychopedagogistRequestDTO = new PsychopedagogistRequestDTO(
                "John",
                "Doe",
                LocalDate.of(1985, 7, 12),
                "Male",
                "456 Elm Street",
                "john.doe@example.com",
                "+1-555-9876",
                "CRP-12345",
                "Psychology PhD",
                "Autism, ADHD",
                10
        );
    }

    // ---------------------------------------
    // ✅ GET ALL PSYCHOPEDAGOGISTS
    // ---------------------------------------
    @Test
    void shouldReturnAllPsychopedagogistsSuccessfully() {
        // Arrange
        when(psychopedagogistRepository.findAll()).thenReturn(List.of(psychopedagogist));

        // Act
        List<PsychopedagogistResponseDTO> psychopedagogists = psychopedagogistService.getAllPsychopedagogists();

        // Assert
        assertFalse(psychopedagogists.isEmpty());
        assertEquals(1, psychopedagogists.size());
        assertEquals("John", psychopedagogists.getFirst().firstName());
        verify(psychopedagogistRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenNoPsychopedagogistsExist() {
        // Arrange
        when(psychopedagogistRepository.findAll()).thenReturn(List.of());

        // Act
        List<PsychopedagogistResponseDTO> psychopedagogists = psychopedagogistService.getAllPsychopedagogists();

        // Assert
        assertTrue(psychopedagogists.isEmpty());
        verify(psychopedagogistRepository, times(1)).findAll();
    }

    // ---------------------------------------
    // ✅ GET PSYCHOPEDAGOGIST BY ID
    // ---------------------------------------
    @Test
    void shouldReturnPsychopedagogistByIdSuccessfully() {
        // Arrange
        when(psychopedagogistRepository.findById(1L)).thenReturn(Optional.of(psychopedagogist));

        // Act
        PsychopedagogistResponseDTO response = psychopedagogistService.getPsychopedagogistById(1L);

        // Assert
        assertNotNull(response);
        assertEquals("John", response.firstName());
        verify(psychopedagogistRepository, times(1)).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenPsychopedagogistNotFoundById() {
        // Arrange
        when(psychopedagogistRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> psychopedagogistService.getPsychopedagogistById(1L));
        verify(psychopedagogistRepository, times(1)).findById(1L);
    }

    // ---------------------------------------
    // ✅ CREATE PSYCHOPEDAGOGIST
    // ---------------------------------------
    @Test
    void shouldCreatePsychopedagogistSuccessfully() {
        // Arrange
        when(psychopedagogistRepository.save(any(Psychopedagogist.class))).thenReturn(psychopedagogist);

        // Act
        PsychopedagogistResponseDTO response = psychopedagogistService.createPsychopedagogist(psychopedagogistRequestDTO);

        // Assert
        assertNotNull(response);
        assertEquals("John", response.firstName());
        verify(psychopedagogistRepository, times(1)).save(any(Psychopedagogist.class));
    }

    // ---------------------------------------
    // ✅ UPDATE PSYCHOPEDAGOGIST
    // ---------------------------------------
    @Test
    void shouldUpdatePsychopedagogistSuccessfully() {
        // Arrange
        when(psychopedagogistRepository.findById(1L)).thenReturn(Optional.of(psychopedagogist));
        when(psychopedagogistRepository.save(any(Psychopedagogist.class))).thenReturn(psychopedagogist);

        // Act
        PsychopedagogistResponseDTO response = psychopedagogistService.updatePsychopedagogist(1L, psychopedagogistRequestDTO);

        // Assert
        assertNotNull(response);
        assertEquals("John", response.firstName());
        verify(psychopedagogistRepository, times(1)).findById(1L);
        verify(psychopedagogistRepository, times(1)).save(any(Psychopedagogist.class));
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingPsychopedagogist() {
        // Arrange
        when(psychopedagogistRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> psychopedagogistService.updatePsychopedagogist(1L, psychopedagogistRequestDTO));
        verify(psychopedagogistRepository, times(1)).findById(1L);
    }

    // ---------------------------------------
    // ✅ DELETE PSYCHOPEDAGOGIST
    // ---------------------------------------
    @Test
    void shouldDeletePsychopedagogistSuccessfully() {
        when(psychopedagogistRepository.existsById(1L)).thenReturn(true);
        doNothing().when(psychopedagogistRepository).deleteById(1L);

        psychopedagogistService.deletePsychopedagogist(1L);

        verify(psychopedagogistRepository, times(1)).existsById(1L);
        verify(psychopedagogistRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingPsychopedagogist() {
        when(psychopedagogistRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> psychopedagogistService.deletePsychopedagogist(1L));
        verify(psychopedagogistRepository, times(1)).existsById(1L);
    }
}
