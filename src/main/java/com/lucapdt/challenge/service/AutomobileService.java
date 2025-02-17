package com.lucapdt.challenge.service;

import com.lucapdt.challenge.model.entity.Automobile;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface AutomobileService {

    Automobile findById(int id);

    Automobile save(Automobile auto);

    void deleteById(int id);

    List<Automobile> findAll();

    Page<Automobile> findAll(int page, int size);

    Page<Automobile> findByMarca(String marca, int page, int size);

    Page<Automobile> findByPrezzoBetween(double prezzoMin, double prezzoMax,int page, int size);

    Page<Automobile> findByStato(Automobile.StatoAuto stato, int page, int size);

}
