package com.lucapdt.challenge.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
@Data
public class LoginDTO {

    @NotEmpty(message = "L'username non puo' essere vuoto")
    private String username;
    @NotEmpty(message = "La password non puo' essere vuota")
    private String password;
}
