package com.lucapdt.challenge.controller;

import com.lucapdt.challenge.command.AuthenticationCommand;
import com.lucapdt.challenge.model.dto.AuthResponseDTO;
import com.lucapdt.challenge.model.dto.LoginDTO;
import com.lucapdt.challenge.model.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationCommand authenticationCommand;

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDto){
        return new ResponseEntity<>(authenticationCommand.login(loginDto), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDto){
        return authenticationCommand.register(registerDto);
    }

    @PostMapping("register/role")
    public ResponseEntity<String> registerRole(@RequestBody RegisterDTO registerDto) {
        return authenticationCommand.registerRole(registerDto);
    }
}
