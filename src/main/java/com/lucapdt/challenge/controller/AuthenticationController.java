package com.lucapdt.challenge.controller;

import com.lucapdt.challenge.command.AuthenticationCommand;
import com.lucapdt.challenge.model.response.AuthResponseDTO;
import com.lucapdt.challenge.model.dto.LoginDTO;
import com.lucapdt.challenge.model.dto.RegisterDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationCommand authenticationCommand;

    @Operation(
            summary = "Esegui il login",
            description = "Endpoint di login per ottenere il token JWT necessario per accedere alla maggior parte degli endpoint",
            responses = {
                    @ApiResponse(
                            description = "Login riuscito, token restituito",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Credenziali errate o parametro mancante",
                            responseCode = "400"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dettagli di login contenenti Username e Password",
                    required = true
            )
    )
    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@Validated @RequestBody LoginDTO loginDto){
        return new ResponseEntity<>(authenticationCommand.login(loginDto), HttpStatus.OK);
    }

    @Operation(
            summary = "Registra un utente con permessi di base",
            description = "Endpoint che permette di registrare nuove utente con ruolo di default = 'ROLE_USER, ha permessi di base",
            responses = {
                    @ApiResponse(
                            description = "Registrazione avvennuta con successo",
                            responseCode = "201"
                    ),
                    @ApiResponse(
                            description = "Richiesta malformata o Username non disponibile, controlla che tutti i campi siano corretti",
                            responseCode = "400"
                    )

            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dettagli dell'utente da registrare",
                    required = true
            )
    )
    @PostMapping("register")
    public ResponseEntity<String> register(@Validated @RequestBody RegisterDTO registerDto){
        return authenticationCommand.register(registerDto);
    }

    @Operation(
            summary = "Registra un utente con permessi a scelta",
            description = "Endpoint che permette di registrare nuove utente con ruolo a scelta, utilizzabile solo dagli utenti con ruolo 'ROLE_ADMIN'",
            responses = {
                    @ApiResponse(
                            description = "Registrazione avvennuta con successo",
                            responseCode = "201"
                    ),
                    @ApiResponse(
                            description = "Accesso negato",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Accesso negato, ruolo insufficiente",
                            responseCode = "403"
                    ),
                    @ApiResponse(
                            description = "Richiesta malformata o Username non disponibile, controlla che tutti i campi siano corretti",
                            responseCode = "400"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dettagli dell'utente da registrare",
                    required = true
            )
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("register/role")
    public ResponseEntity<String> registerRole(@Validated @RequestBody RegisterDTO registerDto) {
        return authenticationCommand.registerRole(registerDto);
    }
}
