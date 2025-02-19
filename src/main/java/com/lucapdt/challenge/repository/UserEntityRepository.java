package com.lucapdt.challenge.repository;

import com.lucapdt.challenge.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
}
