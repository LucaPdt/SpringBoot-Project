package com.lucapdt.challenge.command;

import com.lucapdt.challenge.model.dto.AutomobileDTO;
import com.lucapdt.challenge.model.entity.Automobile;
import com.lucapdt.challenge.repository.AutomobileRepository;
import com.lucapdt.challenge.service.AutomobileServiceImpl;
import com.lucapdt.challenge.service.AutomobileServiceTest;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AutomobileCommandTest {


    @Mock
    private AutomobileServiceImpl automobileService;

    @InjectMocks
    private AutomobileCommandImpl automobileCommand;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByIdTest(){
        Automobile auto = new Automobile("Fiat", "Panda", "2.0 JTDM", 2011, 7500.00, Automobile.StatoAuto.disponibile);

        AutomobileDTO excpected = new AutomobileDTO(0, "Fiat", "Panda", "2.0 JTDM", 2011, 7500.00, Automobile.StatoAuto.disponibile);

        when(automobileService.findById(0)).thenReturn(auto);

        assertThat(automobileCommand.findById(0)).isEqualTo(excpected);
    }

}
