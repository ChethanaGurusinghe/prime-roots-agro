package edu.icet.service;

import edu.icet.model.dto.UserDTO;
import edu.icet.model.entity.RoleEntity;
import edu.icet.model.entity.UserEntity;
import edu.icet.model.request.LoginRequest;
import edu.icet.model.request.RegisterRequest;
import edu.icet.repository.RoleRepository;
import edu.icet.repository.UserRepository;
import edu.icet.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public UserDTO register(RegisterRequest request) {

        if (userRepo.existsByEmail(request.getEmail()))
            throw new RuntimeException("Email already exists");

        if (userRepo.existsByUsername(request.getUsername()))
            throw new RuntimeException("Username already exists");

        Set<RoleEntity> roles = request.getRoles()
                .stream()
                .map(r -> roleRepo.findByName(r)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + r)))
                .collect(Collectors.toSet());

        UserEntity user = UserEntity.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phoneNo(request.getPhoneNo())
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .roles(roles)
                .build();

        UserEntity saved = userRepo.save(user);

        return UserDTO.builder()
                .userId(saved.getUserId())
                .fullName(saved.getFullName())
                .email(saved.getEmail())
                .phoneNo(saved.getPhoneNo())
                .username(saved.getUsername())
                .roles(saved.getRoles().stream().map(RoleEntity::getName).collect(Collectors.toSet()))
                .createdAt(saved.getCreatedAt())
                .build();
    }

    public String login(LoginRequest request) {
        UserEntity user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid credentials");

        return jwtUtil.generateToken(user.getUsername());
    }
}
