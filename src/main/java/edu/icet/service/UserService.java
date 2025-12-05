package edu.icet.service;

import edu.icet.model.dto.UserDTO;
import edu.icet.model.entity.RoleEntity;
import edu.icet.model.entity.UserEntity;
import edu.icet.model.request.CreateUserRequest;
import edu.icet.model.request.UpdateUserRequest;
import edu.icet.repository.RoleRepository;
import edu.icet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;

    public UserDTO createUser(CreateUserRequest req) {

        UserEntity user = UserEntity.builder()
                .fullName(req.getFullName())
                .email(req.getEmail())
                .phoneNo(req.getPhoneNo())
                .username(req.getUsername())
                .password(encoder.encode(req.getPassword()))
                .roles(req.getRoles().stream()
                        .map(r -> roleRepo.findByName(r)
                                .orElseThrow(() -> new RuntimeException("Role not found: " + r)))
                        .collect(Collectors.toSet()))
                .build();

        return toDTO(userRepo.save(user));
    }

    public UserDTO updateUser(Long id, UpdateUserRequest req) {
        UserEntity user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFullName(req.getFullName());
        user.setPhoneNo(req.getPhoneNo());

        return toDTO(userRepo.save(user));
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public List<UserDTO> getAllUsers() {
        return userRepo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    private UserDTO toDTO(UserEntity u) {
        return UserDTO.builder()
                .userId(u.getUserId())
                .fullName(u.getFullName())
                .email(u.getEmail())
                .phoneNo(u.getPhoneNo())
                .username(u.getUsername())
                .roles(u.getRoles().stream().map(RoleEntity::getName).collect(Collectors.toSet()))
                .createdAt(u.getCreatedAt())
                .build();
    }
}
