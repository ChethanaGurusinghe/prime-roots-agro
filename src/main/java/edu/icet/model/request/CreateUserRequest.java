package edu.icet.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {
    private String fullName;
    private String email;
    private String phoneNo;
    private String username;
    private String password;
    private Set<String> roles;
}
