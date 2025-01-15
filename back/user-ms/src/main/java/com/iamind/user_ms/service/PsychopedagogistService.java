package com.iamind.user_ms.service;

import com.iamind.user_ms.dto.PsychopedagogistRequestDTO;
import com.iamind.user_ms.dto.PsychopedagogistResponseDTO;

import java.util.List;

public interface PsychopedagogistService {
    List<PsychopedagogistResponseDTO> getAllPsychopedagogists();

    PsychopedagogistResponseDTO getPsychopedagogistById(String id);

    PsychopedagogistResponseDTO createPsychopedagogist(PsychopedagogistRequestDTO dto);

    PsychopedagogistResponseDTO updatePsychopedagogist(String id, PsychopedagogistRequestDTO dto);

    void deletePsychopedagogist(String id);
}
