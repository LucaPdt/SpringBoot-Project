package com.lucapdt.challenge.command;

import com.lucapdt.challenge.model.dto.AutomobileDTO;
import com.lucapdt.challenge.model.entity.Automobile;

public interface AutomobileCommand {

    AutomobileDTO findById(int id);

    AutomobileDTO save(AutomobileDTO input);

    void deleteById(int id);
}
