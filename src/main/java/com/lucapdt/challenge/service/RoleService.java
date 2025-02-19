package com.lucapdt.challenge.service;

import com.lucapdt.challenge.model.entity.Role;

import java.util.Optional;

public interface RoleService {

    Role findByName(String name);
}
