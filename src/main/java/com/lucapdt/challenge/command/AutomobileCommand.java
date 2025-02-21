package com.lucapdt.challenge.command;

import com.lucapdt.challenge.model.dto.AutomobileDTO;
import com.lucapdt.challenge.model.entity.Automobile;
import com.lucapdt.challenge.model.response.AutomobileResponse;

import java.util.List;

public interface AutomobileCommand {

    AutomobileDTO findById(int id);

    AutomobileDTO save(AutomobileDTO input);

    AutomobileDTO update(int id, AutomobileDTO input);

    void deleteById(int id);

    AutomobileResponse findAll(int page, int size);

    List<AutomobileDTO> findAll();

    AutomobileResponse findByMarca(String marca, int page, int size);

    AutomobileResponse findByPrezzoBetween(double prezzoMin, double prezzoMax,int page, int size);

    AutomobileResponse findByStato(Automobile.StatoAuto stato, int page, int size);
}
