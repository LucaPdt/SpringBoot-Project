package com.lucapdt.challenge.model.dto;

import com.lucapdt.challenge.model.entity.Role;
import lombok.Data;

@Data
public class RegisterDTO {

    private String username;
    private String password;
    private Role role;
}
