package com.iamind.user_ms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iamind.user_ms.config.InitializeDynamoDb;
import com.iamind.user_ms.dto.PsychopedagogistRequestDTO;
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
@ContextConfiguration(classes = {com.iamind.user_ms.config.TestContainerConfig.class})
@InitializeDynamoDb
class PsychopedagogistControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private PsychopedagogistRequestDTO psychopedagogistRequest;

    @BeforeEach
    void setUp() {
        psychopedagogistRequest = new PsychopedagogistRequestDTO(
                "John",
                "Doe",
                LocalDate.of(1985, 3, 25),
                "Male",
                "456 Elm St",
                "john.doe@example.com",
                "+1-555-5678",
                "CRP-12345",
                "Psychology",
                "Autism, ADHD",
                10
        );
    }

    @Test
    void shouldReturnAllPsychopedagogists() throws Exception {
        mockMvc.perform(get("/api/users/psychopedagogists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        createPsychopedagogist(psychopedagogistRequest);

        mockMvc.perform(get("/api/users/psychopedagogists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void shouldCreatePsychopedagogist() throws Exception {
        mockMvc.perform(post("/api/users/psychopedagogists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(psychopedagogistRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void shouldGetPsychopedagogistById() throws Exception {
        long id = createPsychopedagogist(psychopedagogistRequest);

        mockMvc.perform(get("/api/users/psychopedagogists/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void shouldUpdatePsychopedagogist() throws Exception {
        long id = createPsychopedagogist(psychopedagogistRequest);

        PsychopedagogistRequestDTO updatedRequest = new PsychopedagogistRequestDTO(
                "Jane",
                "Doe",
                LocalDate.of(1990, 7, 15),
                "Female",
                "789 Maple St",
                "jane.doe@example.com",
                "+1-555-8765",
                "CRP-54321",
                "Child Psychology",
                "Autism, Dyslexia",
                5
        );

        mockMvc.perform(put("/api/users/psychopedagogists/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jane"));
    }

    @Test
    void shouldDeletePsychopedagogist() throws Exception {
        long id = createPsychopedagogist(psychopedagogistRequest);

        mockMvc.perform(delete("/api/users/psychopedagogists/" + id))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/users/psychopedagogists/" + id))
                .andExpect(status().isNotFound());
    }

    private long createPsychopedagogist(PsychopedagogistRequestDTO request) throws Exception {
        String responseBody = mockMvc.perform(post("/api/users/psychopedagogists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return objectMapper.readTree(responseBody).get("id").asLong();
    }
}
