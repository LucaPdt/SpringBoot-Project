package com.lucapdt.challenge.command;

import com.lucapdt.challenge.model.dto.AutomobileDTO;
import com.lucapdt.challenge.model.entity.Automobile;
import com.lucapdt.challenge.model.response.AutomobileResponse;
import com.lucapdt.challenge.repository.AutomobileRepository;
import com.lucapdt.challenge.service.AutomobileServiceImpl;
import com.lucapdt.challenge.service.AutomobileServiceTest;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import javax.swing.text.html.Option;
import java.time.Year;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        Automobile auto = new Automobile("Fiat", "Panda", "2.0 JTDM", Year.of(2011), 7500.00, Automobile.StatoAuto.disponibile);

        AutomobileDTO excpected = new AutomobileDTO(0, "Fiat", "Panda", "2.0 JTDM", Year.of(2011), 7500.00, Automobile.StatoAuto.disponibile);

        when(automobileService.findById(0)).thenReturn(auto);

        assertThat(automobileCommand.findById(0)).isEqualTo(excpected);
    }

    @Test
    void saveTest(){
        Automobile auto = new Automobile("Fiat", "Panda", "2.0 JTDM", Year.of(2011), 7500.00, Automobile.StatoAuto.disponibile);
        AutomobileDTO input = new AutomobileDTO(0, "Fiat", "Panda", "2.0 JTDM", Year.of(2011), 7500.00, Automobile.StatoAuto.disponibile);

        AutomobileDTO expected = new AutomobileDTO(0, "Fiat", "Panda", "2.0 JTDM", Year.of(2011), 7500.00, Automobile.StatoAuto.disponibile);
        when(automobileService.save(any())).thenReturn(auto);
        assertThat(automobileCommand.save(input)).isEqualTo(expected);
    }

    @Test
    void deleteByIDTest(){
        int id = 1;
        automobileCommand.deleteById(id);
        verify(automobileService).deleteById(id);
    }

    @Test
    void findAllTest(){
        Page<Automobile> automobilePage = mock();
        List<Automobile> automobileList = mock();

        when(automobileService.findAll(1, 2)).thenReturn(automobilePage);
        when(automobileService.findAll()).thenReturn(automobileList);

        AutomobileResponse expected = automobileCommand.findAll(1, 2);
        assertThat(expected).isNotNull();
        List<AutomobileDTO> expected2 = automobileCommand.findAll();
        assertThat(expected).isNotNull();
    }

    @Test
    void findByMarcaTest(){
        Page<Automobile> automobilePage = mock();
        when(automobileService.findByMarca("fiat", 1, 2)).thenReturn(automobilePage);

        AutomobileResponse expected = automobileCommand.findByMarca("fiat", 1, 2);
        assertThat(expected).isNotNull();
    }

    @Test
    void findByPrezzoBetweenTest(){
        Page<Automobile> automobilePage = mock();
        when(automobileService.findByPrezzoBetween(0, 10000, 1, 2)).thenReturn(automobilePage);

        AutomobileResponse expected = automobileCommand.findByPrezzoBetween(0, 10000, 1, 2);
        assertThat(expected).isNotNull();
    }

    @Test
    void findByStatoTest(){
        Page<Automobile> automobilePage = mock();
        when(automobileService.findByStato(Automobile.StatoAuto.disponibile, 1, 2)).thenReturn(automobilePage);

        AutomobileResponse expected = automobileCommand.findByStato(Automobile.StatoAuto.disponibile, 1, 2);
        assertThat(expected).isNotNull();
    }

    @Test
    void updateTest() {
        int id = 1;
        Automobile auto = new Automobile("Fiat", "Panda", "2.0 JTDM", Year.of(2011), 7500.00, Automobile.StatoAuto.disponibile);
        AutomobileDTO expected = new AutomobileDTO(0, "Fiat", "Panda", "2.0 JTDM", Year.of(2011), 7500.00, Automobile.StatoAuto.disponibile);

        when(automobileService.findById(id)).thenReturn(auto);
        when(automobileService.save(auto)).thenReturn(auto);

        AutomobileDTO result = automobileCommand.update(id, expected);
        assertThat(result).isEqualTo(expected);

    }

}
