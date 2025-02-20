package com.lucapdt.challenge.model.dto;

import com.lucapdt.challenge.model.entity.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDTO {

    @NotEmpty(message = "L'username non puo' essere vuoto")
    private String username;

    @NotEmpty(message = "La password non puo' essere vuota")
    @Size(min = 6, message = "La password deve contenere almeno 6 caratteri")
    private String password;
    private Role role;
}
