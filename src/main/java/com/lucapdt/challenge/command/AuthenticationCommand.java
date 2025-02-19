package com.lucapdt.challenge.command;

import com.lucapdt.challenge.model.dto.AuthResponseDTO;
import com.lucapdt.challenge.model.dto.LoginDTO;
import com.lucapdt.challenge.model.dto.RegisterDTO;
import org.springframework.http.ResponseEntity;

public interface AuthenticationCommand {

    AuthResponseDTO login(LoginDTO loginDto);

    ResponseEntity<String> register(RegisterDTO registerDto);

    ResponseEntity<String> registerRole(RegisterDTO registerDto);
}
