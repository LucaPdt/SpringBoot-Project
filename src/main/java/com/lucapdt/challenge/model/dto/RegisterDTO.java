package com.lucapdt.challenge.model.dto;

import com.lucapdt.challenge.model.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Dto per il trasferimento dei dati di registrazione di nuovi utenti")
public class RegisterDTO {

    @Schema(description = "Username dell'utente")
    @NotEmpty(message = "L'username non puo' essere vuoto")
    private String username;

    @Schema(description = "Password dell'utente, deve contenere almeno 6 caratteri")
    @NotEmpty(message = "La password non puo' essere vuota")
    @Size(min = 6, message = "La password deve contenere almeno 6 caratteri")
    private String password;

    @Schema(description = "Ruolo dell'utente", example = "ROLE_USER")
    private Role role;
}
