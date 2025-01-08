package com.iamind.user_ms.service.impl;

import com.iamind.user_ms.dto.PsychopedagogistRequestDTO;
import com.iamind.user_ms.dto.PsychopedagogistResponseDTO;
import com.iamind.user_ms.exception.ResourceNotFoundException;
import com.iamind.user_ms.model.Psychopedagogist;
import com.iamind.user_ms.repository.PsychopedagogistRepository;
import com.iamind.user_ms.service.PsychopedagogistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PsychopedagogistServiceImpl implements PsychopedagogistService {

    private final PsychopedagogistRepository psychopedagogistRepository;

    @Override
    public List<PsychopedagogistResponseDTO> getAllPsychopedagogists() {
        return psychopedagogistRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Override
    public PsychopedagogistResponseDTO getPsychopedagogistById(Long id) {
        Psychopedagogist psychopedagogist = psychopedagogistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Psychopedagogist not found with ID: " + id));

        return mapToResponseDTO(psychopedagogist);
    }

    @Override
    public PsychopedagogistResponseDTO createPsychopedagogist(PsychopedagogistRequestDTO dto) {
        Psychopedagogist psychopedagogist = Psychopedagogist.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .dateOfBirth(dto.dateOfBirth())
                .gender(dto.gender())
                .fullAddress(dto.fullAddress())
                .email(dto.email())
                .phone(dto.phone())
                .professionalRegistration(dto.professionalRegistration())
                .academicFormation(dto.academicFormation())
                .specializations(dto.specializations())
                .experienceInYears(dto.experienceInYears())
                .build();

        Psychopedagogist saved = psychopedagogistRepository.save(psychopedagogist);
        return mapToResponseDTO(saved);
    }

    @Override
    public PsychopedagogistResponseDTO updatePsychopedagogist(Long id, PsychopedagogistRequestDTO dto) {
        Psychopedagogist psychopedagogist = psychopedagogistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Psychopedagogist not found with ID: " + id));

        psychopedagogist.setFirstName(dto.firstName());
        psychopedagogist.setLastName(dto.lastName());
        psychopedagogist.setDateOfBirth(dto.dateOfBirth());
        psychopedagogist.setGender(dto.gender());
        psychopedagogist.setFullAddress(dto.fullAddress());
        psychopedagogist.setPhone(dto.phone());
        psychopedagogist.setProfessionalRegistration(dto.professionalRegistration());
        psychopedagogist.setAcademicFormation(dto.academicFormation());
        psychopedagogist.setSpecializations(dto.specializations());
        psychopedagogist.setExperienceInYears(dto.experienceInYears());

        Psychopedagogist updated = psychopedagogistRepository.save(psychopedagogist);
        return mapToResponseDTO(updated);
    }

    @Override
    public void deletePsychopedagogist(Long id) {
        if (!psychopedagogistRepository.existsById(id)) {
            throw new ResourceNotFoundException("Psychopedagogist not found with ID: " + id);
        }
        psychopedagogistRepository.deleteById(id);
    }

    private PsychopedagogistResponseDTO mapToResponseDTO(Psychopedagogist psychopedagogist) {
        return new PsychopedagogistResponseDTO(
                psychopedagogist.getId(),
                psychopedagogist.getFirstName(),
                psychopedagogist.getLastName(),
                psychopedagogist.getDateOfBirth(),
                psychopedagogist.getGender(),
                psychopedagogist.getFullAddress(),
                psychopedagogist.getEmail(),
                psychopedagogist.getPhone(),
                psychopedagogist.getProfessionalRegistration(),
                psychopedagogist.getAcademicFormation(),
                psychopedagogist.getSpecializations(),
                psychopedagogist.getExperienceInYears(),
                psychopedagogist.getNotes()
        );
    }
}
