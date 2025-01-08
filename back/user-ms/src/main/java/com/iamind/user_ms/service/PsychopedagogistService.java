package com.iamind.user_ms.service;

import com.iamind.user_ms.dto.PsychopedagogistRequestDTO;
import com.iamind.user_ms.dto.PsychopedagogistResponseDTO;

import java.util.List;

public interface PsychopedagogistService {
    List<PsychopedagogistResponseDTO> getAllPsychopedagogists();

    PsychopedagogistResponseDTO getPsychopedagogistById(Long id);

    PsychopedagogistResponseDTO createPsychopedagogist(PsychopedagogistRequestDTO dto);

    PsychopedagogistResponseDTO updatePsychopedagogist(Long id, PsychopedagogistRequestDTO dto);

    void deletePsychopedagogist(Long id);
}
