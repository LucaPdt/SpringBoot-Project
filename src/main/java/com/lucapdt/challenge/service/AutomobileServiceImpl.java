package com.lucapdt.challenge.service;

import com.lucapdt.challenge.model.entity.Automobile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutomobileServiceImpl implements AutomobileService{

    @Override
    public Optional<Automobile> findById(int id) {
        return Optional.empty();
    }
}
