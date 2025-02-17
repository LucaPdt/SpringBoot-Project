package com.lucapdt.challenge.service;

import com.lucapdt.challenge.exception.AutomobileNotFoundException;
import com.lucapdt.challenge.model.entity.Automobile;
import com.lucapdt.challenge.repository.AutomobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutomobileServiceImpl implements AutomobileService{

    @Autowired
    private AutomobileRepository automobileRepository;

    @Override
    public Optional<Automobile> findById(int id) {

        Optional<Automobile> optionalAutomobile = automobileRepository.findById(id);
        if(optionalAutomobile.isEmpty())
            throw new AutomobileNotFoundException("Non e' stata trovata una automobile per l'id inserito");

        return optionalAutomobile;
    }

    @Override
    public Optional<Automobile> save(Automobile auto) {
        return Optional.empty();
    }
}
