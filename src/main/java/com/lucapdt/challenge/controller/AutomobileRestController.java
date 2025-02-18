package com.lucapdt.challenge.controller;

import com.lucapdt.challenge.command.AutomobileCommand;
import com.lucapdt.challenge.model.dto.AutomobileDTO;
import com.lucapdt.challenge.model.entity.Automobile;
import com.lucapdt.challenge.model.response.AutomobileResponse;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AutomobileRestController {

    @Autowired
    private AutomobileCommand automobileCommand;

    @GetMapping("/automobili/{id}")
    public ResponseEntity<AutomobileDTO> getAutomobileById(@PathVariable("id") int id) {
//        ResponseEntity<AutomobileDTO> response;
//
//        try{
//            response = ResponseEntity.ok(automobileCommand.findById(id));
//        } catch(Exception e){
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        return  response;
        return ResponseEntity.ok(automobileCommand.findById(id));
    }

    @PostMapping("/automobili")
    public ResponseEntity<AutomobileDTO> createAutomobile(@RequestBody AutomobileDTO automobileDTO){
        return new ResponseEntity<>(automobileCommand.save(automobileDTO), HttpStatus.CREATED);
    }

    @PutMapping("/automobili/{id}")
    public ResponseEntity<AutomobileDTO> updateAutomobile(@PathVariable("id") int id, @RequestBody AutomobileDTO automobileDTO){
        return ResponseEntity.ok(automobileCommand.update(id, automobileDTO));
    }

    @DeleteMapping("/automobili/{id}")
    public ResponseEntity<String> deleteAutomobile(@PathVariable("id") int id){
        automobileCommand.deleteById(id);
        return new ResponseEntity<>("Automobile delete", HttpStatus.OK);
    }

    @GetMapping("/automobili/list")
    public ResponseEntity<List<AutomobileDTO>> findAllList(){
        return ResponseEntity.ok(automobileCommand.findAll());
    }

    @GetMapping("/automobili")
    public ResponseEntity<AutomobileResponse> findAll(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ){
        return ResponseEntity.ok(automobileCommand.findAll(page,size));
    }

    @GetMapping("/automobili/marca")
    public ResponseEntity<AutomobileResponse> findByMarca(
            @RequestParam(value = "marca") String marca,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ){
        return ResponseEntity.ok(automobileCommand.findByMarca(marca, page, size));

    }

    @GetMapping("/automobili/prezzo/range")
    public ResponseEntity<AutomobileResponse> findByPrezzoBetween(
            @RequestParam(value = "prezzoMin") double prezzoMin,
            @RequestParam(value = "prezzoMax") double prezzoMax,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ){
        return ResponseEntity.ok(automobileCommand.findByPrezzoBetween(prezzoMin, prezzoMax, page, size));
    }

    @GetMapping("/automobili/stato")
    public ResponseEntity<AutomobileResponse> findByStato(
            @RequestParam(value = "stato") Automobile.StatoAuto stato,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ){
        return ResponseEntity.ok(automobileCommand.findByStato(stato, page, size));
    }
}
