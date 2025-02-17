package com.lucapdt.challenge.service;

import com.lucapdt.challenge.exception.AutomobileNotFoundException;
import com.lucapdt.challenge.model.entity.Automobile;
import com.lucapdt.challenge.repository.AutomobileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AutomobileServiceTest {

    @Mock
    private AutomobileRepository automobileRepository;

    @InjectMocks
    private AutomobileServiceImpl automobileService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByIdTest(){
        Automobile auto = new Automobile("Fiat", "Panda", "2.0 JTDM", 2011, 7500.00, Automobile.StatoAuto.disponibile);
        Optional<Automobile> expected = Optional.of(auto);

        when(automobileRepository.findById(1)).thenReturn(expected);
        when(automobileRepository.findById(2)).thenReturn(Optional.empty());

        assertThat(automobileService.findById(1)).isEqualTo(expected);
        assertThatThrownBy(() -> automobileService.findById(2))
                .isInstanceOf(AutomobileNotFoundException.class)
                .hasMessage("Non e' stata trovata una automobile per l'id inserito");

    }

    @Test
    void saveTest(){

        Automobile auto = new Automobile("Fiat", "Panda", "2.0 JTDM", 2011, 7500.00, Automobile.StatoAuto.disponibile);
        Optional<Automobile> expected = Optional.of(auto);

        when(automobileRepository.save(auto)).thenReturn(auto);
        assertThat(automobileService.save(auto)).isEqualTo(expected);
    }

    @Test
    void deleteTest(){
        int id = 1;
        when(automobileRepository.existsById(id)).thenReturn(true);
        automobileService.deleteById(id);
        verify(automobileRepository).deleteById(id);
        assertThatThrownBy(() -> automobileService.deleteById(2))
                .isInstanceOf(AutomobileNotFoundException.class)
                .hasMessage("Non e' stata trovata una automobile per l'id inserito");

    }
}
