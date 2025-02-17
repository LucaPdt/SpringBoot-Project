package com.lucapdt.challenge.service;

import com.lucapdt.challenge.model.entity.Automobile;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface AutomobileService {

    Optional<Automobile> findById(int id);

    Optional<Automobile> save(Automobile auto);

    void deleteById(int id);

    Optional<List<Automobile>> findAll();

    Optional<Page<Automobile>> findAll(int page, int size);

}
