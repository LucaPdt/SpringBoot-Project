package com.lucapdt.challenge.command;

import com.lucapdt.challenge.model.dto.AutomobileDTO;
import com.lucapdt.challenge.model.entity.Automobile;
import com.lucapdt.challenge.service.AutomobileService;
import org.springframework.beans.factory.annotation.Autowired;

public class AutomobileCommandImpl implements AutomobileCommand{

    @Autowired
    AutomobileService automobileService;

    @Override
    public AutomobileDTO findById(int id) {
        Automobile auto = automobileService.findById(id);

        return mapToDTO(auto);
    }

    @Override
    public AutomobileDTO save(AutomobileDTO input) {
        Automobile saved = automobileService.save(mapToEntity(input));
        return mapToDTO(saved);
    }

    @Override
    public void deleteById(int id) {
        automobileService.deleteById(id);
    }

    private AutomobileDTO mapToDTO(Automobile auto) {
        AutomobileDTO dto = new AutomobileDTO();

        dto.setId(auto.getId());
        dto.setMarca(auto.getMarca());
        dto.setModello(auto.getModello());
        dto.setAnno(auto.getAnno());
        dto.setMotorizzazione(auto.getMotorizzazione());
        dto.setPrezzo(auto.getPrezzo());
        dto.setStato(auto.getStato());

        return dto;
    }

    private Automobile mapToEntity(AutomobileDTO dto){
        Automobile entity = new Automobile();

        if (dto.getMarca() != null)
            entity.setMarca(dto.getMarca());
        if (dto.getModello() != null)
            entity.setModello(dto.getModello());
        if (dto.getAnno() != null)
            entity.setAnno(dto.getAnno());
        if (dto.getMotorizzazione() != null)
            entity.setMotorizzazione(dto.getMotorizzazione());
        if (dto.getPrezzo() != null)
            entity.setPrezzo(dto.getPrezzo());
        if (dto.getStato() != null)
            entity.setStato(dto.getStato());

        return entity;
    }
}
