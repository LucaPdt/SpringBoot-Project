package com.lucapdt.challenge.service;

import com.lucapdt.challenge.model.entity.Automobile;

import java.util.Optional;

public interface AutomobileService {

    Optional<Automobile> findById(int id);

    Optional<Automobile> save(Automobile auto);

}
