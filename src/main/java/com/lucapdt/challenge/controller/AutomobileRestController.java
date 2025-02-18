package com.lucapdt.challenge.controller;

import com.lucapdt.challenge.command.AutomobileCommand;
import com.lucapdt.challenge.model.dto.AutomobileDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AutomobileRestController {

    @Autowired
    private AutomobileCommand automobileCommand;

    @GetMapping("automobili/{id}")
    public ResponseEntity<AutomobileDTO> getAutomobileById(@PathVariable int id) {
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
}
