package com.iamind.user_ms.service.impl;

import com.iamind.user_ms.converter.UserTableToPsychopedagogistConverter;
import com.iamind.user_ms.dto.PsychopedagogistRequestDTO;
import com.iamind.user_ms.dto.PsychopedagogistResponseDTO;
import com.iamind.user_ms.exception.ResourceNotFoundException;
import com.iamind.user_ms.model.Psychopedagogist;
import com.iamind.user_ms.model.dynamodb.DynamoDbRepository;
import com.iamind.user_ms.model.dynamodb.UserTable;
import com.iamind.user_ms.repository.impl.UserRepositoryImpl;
import com.iamind.user_ms.service.PsychopedagogistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PsychopedagogistServiceImpl implements PsychopedagogistService {

    private final DynamoDbRepository<UserTable> userRepository;
    private final UserTableToPsychopedagogistConverter userTableToPsychopedagogistConverter;

    public PsychopedagogistServiceImpl(UserRepositoryImpl userRepository, UserTableToPsychopedagogistConverter userTableToPsychopedagogistConverter) {
        this.userRepository = userRepository;
        this.userTableToPsychopedagogistConverter = userTableToPsychopedagogistConverter;
    }

    @Override
    public List<PsychopedagogistResponseDTO> getAllPsychopedagogists() {
        return userRepository.findAll()
                .stream()
                .map(userTableToPsychopedagogistConverter::convert)
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Override
    public PsychopedagogistResponseDTO getPsychopedagogistById(String id) {
        Psychopedagogist psychopedagogist = userRepository.findById(id)
                .map(userTableToPsychopedagogistConverter::convert)
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
        psychopedagogist.setId(psychopedagogist.generateHashKey());

        userRepository.save(userTableToPsychopedagogistConverter.convert(psychopedagogist));


        Psychopedagogist saved = userTableToPsychopedagogistConverter.convert(this.userRepository.findById(psychopedagogist.getId()).get());
        return mapToResponseDTO(saved);
    }

    @Override
    public PsychopedagogistResponseDTO updatePsychopedagogist(String id, PsychopedagogistRequestDTO dto) {
        Psychopedagogist psychopedagogist = userRepository.findById(id)
                .map(userTableToPsychopedagogistConverter::convert)
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

        userRepository.save(userTableToPsychopedagogistConverter.convert(psychopedagogist));


        Psychopedagogist updated = userTableToPsychopedagogistConverter.convert(this.userRepository.findById(psychopedagogist.getId()).get());
        return mapToResponseDTO(updated);
    }

    @Override
    public void deletePsychopedagogist(String id) {
        Optional<UserTable> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("Psychopedagogist not found with ID: " + id);
        }
        userRepository.delete(user.get());
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
