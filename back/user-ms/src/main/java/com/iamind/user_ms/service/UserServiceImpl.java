package com.iamind.user_ms.service;

import com.iamind.user_ms.dto.UserRequestDTO;
import com.iamind.user_ms.dto.UserResponseDTO;
import com.iamind.user_ms.exception.ResourceNotFoundException;
import com.iamind.user_ms.model.User;
import com.iamind.user_ms.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getDateOfBirth(),
                        user.getSchool(),
                        user.getRegistrationNumber(),
                        user.getEmail()
                ))
                .toList();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        return new UserResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getDateOfBirth(),
                user.getSchool(),
                user.getRegistrationNumber(),
                user.getEmail()
        );
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = User.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .dateOfBirth(dto.dateOfBirth())
                .school(dto.school())
                .registrationNumber(dto.registrationNumber())
                .email(dto.email())
                .build();
        User saved = userRepository.save(user);
        return new UserResponseDTO(
                saved.getId(),
                saved.getFirstName(),
                saved.getLastName(),
                saved.getDateOfBirth(),
                saved.getSchool(),
                saved.getRegistrationNumber(),
                saved.getEmail()
        );
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setDateOfBirth(dto.dateOfBirth());
        user.setSchool(dto.school());
        user.setRegistrationNumber(dto.registrationNumber());
        user.setEmail(dto.email());

        User updated = userRepository.save(user);
        return new UserResponseDTO(
                updated.getId(),
                updated.getFirstName(),
                updated.getLastName(),
                updated.getDateOfBirth(),
                updated.getSchool(),
                updated.getRegistrationNumber(),
                updated.getEmail()
        );
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }
}
