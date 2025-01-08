package com.iamind.user_ms.controller;

import com.iamind.user_ms.dto.PsychopedagogistRequestDTO;
import com.iamind.user_ms.dto.PsychopedagogistResponseDTO;
import com.iamind.user_ms.service.PsychopedagogistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/psychopedagogists")
@RequiredArgsConstructor
public class PsychopedagogistController {

    private final PsychopedagogistService psychopedagogistService;

    @GetMapping
    public ResponseEntity<List<PsychopedagogistResponseDTO>> getAllPsychopedagogists() {
        List<PsychopedagogistResponseDTO> psychopedagogists = psychopedagogistService.getAllPsychopedagogists();
        return ResponseEntity.ok(psychopedagogists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PsychopedagogistResponseDTO> getPsychopedagogistById(@PathVariable Long id) {
        PsychopedagogistResponseDTO psychopedagogist = psychopedagogistService.getPsychopedagogistById(id);
        return ResponseEntity.ok(psychopedagogist);
    }

    @PostMapping
    public ResponseEntity<PsychopedagogistResponseDTO> createPsychopedagogist(@Valid @RequestBody PsychopedagogistRequestDTO dto) {
        PsychopedagogistResponseDTO psychopedagogist = psychopedagogistService.createPsychopedagogist(dto);
        return ResponseEntity.ok(psychopedagogist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PsychopedagogistResponseDTO> updatePsychopedagogist(
            @PathVariable Long id,
            @Valid @RequestBody PsychopedagogistRequestDTO dto) {
        PsychopedagogistResponseDTO updatedPsychopedagogist = psychopedagogistService.updatePsychopedagogist(id, dto);
        return ResponseEntity.ok(updatedPsychopedagogist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePsychopedagogist(@PathVariable Long id) {
        psychopedagogistService.deletePsychopedagogist(id);
        return ResponseEntity.noContent().build();
    }
}
