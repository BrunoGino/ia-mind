package com.iamind.user_ms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iamind.user_ms.config.InitializeDynamoDb;
import com.iamind.user_ms.dto.StudentRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@InitializeDynamoDb
class StudentControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private StudentRequestDTO studentRequest;

    @BeforeEach
    void setUp() {
        studentRequest = new StudentRequestDTO(
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
    void shouldReturnAllStudents() throws Exception {
        mockMvc.perform(get("/api/users/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        createStudent(studentRequest);

        mockMvc.perform(get("/api/users/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void shouldCreateStudent() throws Exception {
        mockMvc.perform(post("/api/users/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Alice"));
    }

    @Test
    void shouldGetStudentById() throws Exception {
        String id = createStudent(studentRequest);

        mockMvc.perform(get("/api/users/students/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Alice"));
    }

    @Test
    void shouldUpdateStudent() throws Exception {
        String id = createStudent(studentRequest);

        StudentRequestDTO updatedRequest = new StudentRequestDTO(
                "Alice Updated",
                "Smith",
                LocalDate.of(2005, 5, 15),
                "Female",
                "456 Updated St",
                "updated@example.com",
                "+1-555-9876",
                "6th Grade",
                "6B",
                "Updated School",
                "Afternoon",
                null,
                null,
                null,
                null,
                "John Smith",
                "+1-555-1234",
                "john@example.com"
        );

        mockMvc.perform(put("/api/users/students/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Alice Updated"));
    }

    @Test
    void shouldDeleteStudent() throws Exception {
        String id = createStudent(studentRequest);

        mockMvc.perform(delete("/api/users/students/" + id))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/users/students/" + id))
                .andExpect(status().isNotFound());
    }

    private String createStudent(StudentRequestDTO request) throws Exception {
        String responseBody = mockMvc.perform(post("/api/users/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return objectMapper.readTree(responseBody).get("id").asText();
    }
}
