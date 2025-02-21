package com.lucapdt.challenge.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Schema(description = "DTO per il trasferimento dei dati di login")
public class LoginDTO {

    @Schema(description = "Username dell'utente")
    @NotEmpty(message = "L'username non puo' essere vuoto")
    private String username;

    @Schema(description = "Password di login")
    @NotEmpty(message = "La password non puo' essere vuota")
    private String password;
}
