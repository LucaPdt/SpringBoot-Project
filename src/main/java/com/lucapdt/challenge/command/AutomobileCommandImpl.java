package com.lucapdt.challenge.command;

import com.lucapdt.challenge.model.dto.AutomobileDTO;
import com.lucapdt.challenge.model.entity.Automobile;
import com.lucapdt.challenge.model.response.AutomobileResponse;
import com.lucapdt.challenge.service.AutomobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AutomobileCommandImpl implements AutomobileCommand{

    @Autowired
    private AutomobileService automobileService;

    @Override
    public AutomobileDTO findById(int id) {
        Automobile auto = automobileService.findById(id);

        return mapToDTO(auto);
    }

    @Override
    public AutomobileDTO save(AutomobileDTO input) {

        Automobile saved = new Automobile();
        mapToEntity(saved, input);
        automobileService.save(saved);
        return mapToDTO(saved);
    }

    @Override
    public AutomobileDTO update(int id, AutomobileDTO input) {
        Automobile auto = automobileService.findById(id);

        mapToEntity(auto, input);
        Automobile automobileAggiornata = automobileService.save(auto);

        return mapToDTO(automobileAggiornata);

    }

    @Override
    public void deleteById(int id) {
        automobileService.deleteById(id);
    }

    @Override
    public AutomobileResponse findAll(int page, int size) {
        Page<Automobile> automobili = automobileService.findAll(page, size);

        return mapToAutomobileResponse(automobili);
    }

    @Override
    public List<AutomobileDTO> findAll() {
        List<Automobile> automobili = automobileService.findAll();
        return automobili.stream().map(this::mapToDTO)
                .toList();
    }

    @Override
    public AutomobileResponse findByMarca(String marca, int page, int size) {
        Page<Automobile> automobili = automobileService.findByMarca(marca, page, size);

        return mapToAutomobileResponse(automobili);
    }

    @Override
    public AutomobileResponse findByPrezzoBetween(double prezzoMin, double prezzoMax, int page, int size) {
        Page<Automobile> automobili = automobileService.findByPrezzoBetween(prezzoMin, prezzoMax, page, size);

        return mapToAutomobileResponse(automobili);
    }

    @Override
    public AutomobileResponse findByStato(Automobile.StatoAuto stato, int page, int size) {
        Page<Automobile> automobili = automobileService.findByStato(stato, page, size);

        return mapToAutomobileResponse(automobili);
    }

    private AutomobileResponse mapToAutomobileResponse(Page<Automobile> automobili) {
        List<AutomobileDTO> content = automobili.getContent()
                .stream().map(this::mapToDTO)
                .toList();

        AutomobileResponse response = new AutomobileResponse();

        response.setContent(content);
        response.setPageNo(automobili.getNumber());
        response.setPageSize(automobili.getSize());
        response.setTotalElements(automobili.getTotalElements());
        response.setTotalPages(automobili.getTotalPages());
        response.setLast(automobili.isLast());

        return response;
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

    private void mapToEntity(Automobile entity, AutomobileDTO dto){

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
    }
}
