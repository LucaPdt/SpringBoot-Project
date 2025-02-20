package com.lucapdt.challenge.service;

import com.lucapdt.challenge.exception.RoleNameNotFoundException;
import com.lucapdt.challenge.model.entity.Role;
import com.lucapdt.challenge.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RoleNameNotFoundException("Ruolo non trovato"));
    }
}
