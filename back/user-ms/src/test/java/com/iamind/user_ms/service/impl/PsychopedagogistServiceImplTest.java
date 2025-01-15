package com.iamind.user_ms.service.impl;

import com.iamind.user_ms.converter.UserTableToPsychopedagogistConverter;
import com.iamind.user_ms.dto.PsychopedagogistRequestDTO;
import com.iamind.user_ms.dto.PsychopedagogistResponseDTO;
import com.iamind.user_ms.exception.ResourceNotFoundException;
import com.iamind.user_ms.model.Psychopedagogist;
import com.iamind.user_ms.model.dynamodb.DynamoDbRepository;
import com.iamind.user_ms.model.dynamodb.UserTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PsychopedagogistServiceImplTest {

    @Mock
    private DynamoDbRepository<UserTable> userRepository;

    @Mock
    private UserTableToPsychopedagogistConverter converter;

    @InjectMocks
    private PsychopedagogistServiceImpl service;

    @Test
    void testGetAllPsychopedagogists() {
        // Arrange
        UserTable userTable = new UserTable();
        Psychopedagogist psychopedagogist = new Psychopedagogist();
        PsychopedagogistResponseDTO responseDTO = new PsychopedagogistResponseDTO("1", "John", "Doe", null, "Male", "Address", "email", "phone", "reg", "edu", "spec", 5, "notes");

        when(userRepository.findAll()).thenReturn(List.of(userTable));
        when(converter.convert(userTable)).thenReturn(psychopedagogist);

        // Act
        List<PsychopedagogistResponseDTO> result = service.getAllPsychopedagogists();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).firstName());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetPsychopedagogistById_Success() {
        // Arrange
        String id = "1";
        UserTable userTable = new UserTable();
        Psychopedagogist psychopedagogist = new Psychopedagogist();
        PsychopedagogistResponseDTO responseDTO = new PsychopedagogistResponseDTO("1", "John", "Doe", null, "Male", "Address", "email", "phone", "reg", "edu", "spec", 5, "notes");

        when(userRepository.findById(id)).thenReturn(Optional.of(userTable));
        when(converter.convert(userTable)).thenReturn(psychopedagogist);

        // Act
        PsychopedagogistResponseDTO result = service.getPsychopedagogistById(id);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.firstName());
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void testGetPsychopedagogistById_NotFound() {
        // Arrange
        String id = "1";
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> service.getPsychopedagogistById(id));
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void testCreatePsychopedagogist() {
        // Arrange
        PsychopedagogistRequestDTO requestDTO = new PsychopedagogistRequestDTO("John", "Doe", LocalDate.now(), "Male", "Address", "email", "phone", "reg", "edu", "spec", 5);
        Psychopedagogist psychopedagogist = new Psychopedagogist();
        psychopedagogist.setId("1");
        UserTable userTable = new UserTable();

        when(converter.convert(psychopedagogist)).thenReturn(userTable);
        when(userRepository.findById("1")).thenReturn(Optional.of(userTable));
        when(converter.convert(userTable)).thenReturn(psychopedagogist);

        // Act
        PsychopedagogistResponseDTO result = service.createPsychopedagogist(requestDTO);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.firstName());
        verify(userRepository, times(1)).save(userTable);
    }

    @Test
    void testUpdatePsychopedagogist_Success() {
        // Arrange
        String id = "1";
        PsychopedagogistRequestDTO requestDTO = new PsychopedagogistRequestDTO("John", "Doe", LocalDate.now(), "Male", "New Address", "newemail", "newphone", "newreg", "newedu", "newspec", 10);
        UserTable userTable = new UserTable();
        Psychopedagogist psychopedagogist = new Psychopedagogist();

        when(userRepository.findById(id)).thenReturn(Optional.of(userTable));
        when(converter.convert(userTable)).thenReturn(psychopedagogist);
        when(converter.convert(psychopedagogist)).thenReturn(userTable);

        // Act
        PsychopedagogistResponseDTO result = service.updatePsychopedagogist(id, requestDTO);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.firstName());
        assertEquals("New Address", result.fullAddress());
        verify(userRepository, times(1)).save(userTable);
    }

    @Test
    void testUpdatePsychopedagogist_NotFound() {
        // Arrange
        String id = "1";
        PsychopedagogistRequestDTO requestDTO = new PsychopedagogistRequestDTO("John", "Doe", LocalDate.now(), "Male", "New Address", "newemail", "newphone", "newreg", "newedu", "newspec", 10);

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> service.updatePsychopedagogist(id, requestDTO));
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void testDeletePsychopedagogist_Success() {
        // Arrange
        String id = "1";
        UserTable userTable = new UserTable();
        when(userRepository.findById(id)).thenReturn(Optional.of(userTable));

        // Act
        service.deletePsychopedagogist(id);

        // Assert
        verify(userRepository, times(1)).delete(userTable);
    }

    @Test
    void testDeletePsychopedagogist_NotFound() {
        // Arrange
        String id = "1";
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> service.deletePsychopedagogist(id));
        verify(userRepository, times(1)).findById(id);
    }
}
