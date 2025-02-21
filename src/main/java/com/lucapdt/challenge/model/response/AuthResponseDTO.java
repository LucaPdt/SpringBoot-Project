package com.lucapdt.challenge.model.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "DTO di risposta per l'autenticazione (dopo il login), contiene il token di accesso da utilizzare")
@Data
public class AuthResponseDTO {

    @Schema(description = "Token di accesso generato dopo il login", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String accessToken;

    @Schema(description = "Tipo di token (di default Bearer)", example = "Bearer")
    private String tokenType = "Bearer ";

    public AuthResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
