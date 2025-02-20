package com.lucapdt.challenge.service;

import com.lucapdt.challenge.model.entity.UserEntity;

import java.util.Optional;

public interface UserEntityService {

    UserEntity findByUsername(String username);
    Boolean existsByUsername(String username);
    void save(UserEntity user);
}
