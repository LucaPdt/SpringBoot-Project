package com.lucapdt.challenge.command;

import com.lucapdt.challenge.model.response.AuthResponseDTO;
import com.lucapdt.challenge.model.dto.LoginDTO;
import com.lucapdt.challenge.model.dto.RegisterDTO;
import com.lucapdt.challenge.model.entity.Role;
import com.lucapdt.challenge.model.entity.UserEntity;
import com.lucapdt.challenge.security.JWTGenerator;
import com.lucapdt.challenge.service.RoleService;
import com.lucapdt.challenge.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthenticationCommandImpl implements AuthenticationCommand{

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTGenerator jwtGenerator;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserEntityService userEntityService;


    @Override
    public AuthResponseDTO login(LoginDTO loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new AuthResponseDTO(token);
    }

    @Override
    public ResponseEntity<String> register(RegisterDTO registerDto) {
        if(userEntityService.existsByUsername(registerDto.getUsername()))
            return new ResponseEntity<String>("Username non disponibile", HttpStatus.BAD_REQUEST);

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword((passwordEncoder.encode(registerDto.getPassword())));

        Role role = roleService.findByName("ROLE_USER");
        user.setRoles(Collections.singletonList(role));

        userEntityService.save(user);
        return new ResponseEntity<>("Utente registrato con successo", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> registerRole(RegisterDTO registerDto) {
        if(userEntityService.existsByUsername(registerDto.getUsername()))
            return new ResponseEntity<String>("Username non disponibile", HttpStatus.BAD_REQUEST);

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role role = roleService.findByName(registerDto.getRole().getName());
        user.setRoles(Collections.singletonList(role));

        userEntityService.save(user);
        return new ResponseEntity<>("Utenza registrato con successo", HttpStatus.CREATED);
    }
}
