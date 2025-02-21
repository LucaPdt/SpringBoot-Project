package com.lucapdt.challenge.service;

import com.lucapdt.challenge.model.entity.Role;

public interface RoleService {

    Role findByName(String name);
}
