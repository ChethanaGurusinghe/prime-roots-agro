package edu.icet.model.dto;

import lombok.*;

import java.time.Instant;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDTO {

    private Long userId;
    private String fullName;
    private String email;
    private String phoneNo;
    private String username;
    private Set<String> roles;
    private Instant createdAt;

}
