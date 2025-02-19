package com.lucapdt.challenge.service;

import com.lucapdt.challenge.exception.RoleNameNotFoundException;
import com.lucapdt.challenge.model.entity.UserEntity;
import com.lucapdt.challenge.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserEntityServiceImpl implements UserEntityService{

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public UserEntity findByUsername(String username) {
        return userEntityRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username non trovato"));
    }

    @Override
    public Boolean existByUsername(String username) {

        return userEntityRepository.existByUsername(username);
    }

    @Override
    public void save(UserEntity user) {
        userEntityRepository.save(user);
    }
}
