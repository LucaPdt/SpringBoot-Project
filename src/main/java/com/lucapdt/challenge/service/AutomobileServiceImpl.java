package com.lucapdt.challenge.service;

import com.lucapdt.challenge.exception.AutomobileNotFoundException;
import com.lucapdt.challenge.model.entity.Automobile;
import com.lucapdt.challenge.repository.AutomobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return Optional.of(automobileRepository.save(auto));
    }

    @Override
    public void deleteById(int id) {
        if(!automobileRepository.existsById(id))
            throw new AutomobileNotFoundException("Non e' stata trovata una automobile per l'id inserito");

        automobileRepository.deleteById(id);
    }

    @Override
    public List<Automobile> findAll() {
        return automobileRepository.findAll();
    }

    @Override
    public Page<Automobile> findAll(int page, int size) {
        return automobileRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<Automobile> findByMarca(String marca, int page, int size) {
        return automobileRepository.findByMarca(marca, PageRequest.of(page, size));
    }

    @Override
    public Page<Automobile> findByPrezzoBetween(double prezzoMin, double prezzoMax, int page, int size) {
        return automobileRepository.findByPrezzoBetween(prezzoMin, prezzoMax, PageRequest.of(page, size));
    }

    @Override
    public Page<Automobile> findByStato(Automobile.StatoAuto stato, int page, int size) {
        return automobileRepository.findByStato(stato, PageRequest.of(page, size));
    }

}
