package com.soft_arex.user.model;

import com.soft_arex.enums.UserRole;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserResponse {
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private UserRole role;
}
