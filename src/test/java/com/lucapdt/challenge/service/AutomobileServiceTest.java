package com.lucapdt.challenge.service;

import com.lucapdt.challenge.exception.AutomobileNotFoundException;
import com.lucapdt.challenge.model.entity.Automobile;
import com.lucapdt.challenge.repository.AutomobileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Year;
import java.util.List;
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
        Automobile auto = new Automobile("Fiat", "Panda", "2.0 JTDM", Year.of(2011), 7500.00, Automobile.StatoAuto.disponibile);
        Optional<Automobile> optionalAutomobile = Optional.of(auto);

        when(automobileRepository.findById(1)).thenReturn(optionalAutomobile);
        when(automobileRepository.findById(2)).thenReturn(Optional.empty());

        assertThat(automobileService.findById(1)).isEqualTo(auto);
        assertThatThrownBy(() -> automobileService.findById(2))
                .isInstanceOf(AutomobileNotFoundException.class)
                .hasMessage("Non e' stata trovata una automobile per l'id inserito");

    }

    @Test
    void saveTest(){

        Automobile auto = new Automobile("Fiat", "Panda", "2.0 JTDM", Year.of(2011), 7500.00, Automobile.StatoAuto.disponibile);

        when(automobileRepository.save(auto)).thenReturn(auto);
        assertThat(automobileService.save(auto)).isEqualTo(auto);
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

    @Test
    void findAllListTest(){
        List<Automobile> automobileList = List.of(
                new Automobile("Alfa Romeo", "Giulietta", "2.0 JTDM", Year.of(2011), 7500.00, Automobile.StatoAuto.disponibile),
                new Automobile("Fiat", "Panda", "2.0 JTDM", Year.of(2011), 7500.00, Automobile.StatoAuto.disponibile)
        );

        when(automobileRepository.findAll()).thenReturn(automobileList);

        assertThat(automobileService.findAll()).containsExactlyElementsOf(automobileList);
    }

    @Test
    void findAllPageTest(){
        List<Automobile> automobileList = List.of(
                new Automobile("Alfa Romeo", "Giulietta", "2.0 JTDM", Year.of(2011), 7500.00, Automobile.StatoAuto.disponibile),
                new Automobile("Fiat", "Panda", "2.0 JTDM", Year.of(2011), 7500.00, Automobile.StatoAuto.disponibile)
        );

        Pageable pageable = PageRequest.of(0,2);
        Page<Automobile> page = new PageImpl<>(automobileList, pageable, automobileList.size());
        when(automobileRepository.findAll(pageable)).thenReturn(page);

        Page<Automobile> result = automobileService.findAll(0,2);

        assertThat(result).isNotNull();
        assertThat(result.get()).hasSize(2);
        assertThat(result.get()).containsExactlyElementsOf(automobileList);
    }

    @Test
    void findByMarcaTest(){
        List<Automobile> automobileList = List.of(
                new Automobile("Alfa Romeo", "Giulietta", "2.0 JTDM", Year.of(2011), 7500.00, Automobile.StatoAuto.disponibile),
                new Automobile("Alfa Romeo", "Giulia", "Giulia 2.2 Turbodiesel 160 CV AT8", Year.of(2023), 17500.00, Automobile.StatoAuto.disponibile)
        );

        Pageable pageable = PageRequest.of(0,2);
        Page<Automobile> page = new PageImpl<>(automobileList, pageable, automobileList.size());
        when(automobileRepository.findByMarca("Alfa Romeo", pageable)).thenReturn(page);

        Page<Automobile> result = automobileService.findByMarca("Alfa Romeo", 0, 2);
        assertThat(result).isNotNull();
        assertThat(result.get()).hasSize(2);
        assertThat(result.get()).containsExactlyElementsOf(automobileList);
    }

    @Test
    void findAutomobiliByPrezzoRangeTest(){
        List<Automobile> automobileList = List.of(
                new Automobile("Alfa Romeo", "Giulietta", "2.0 JTDM", Year.of(2011), 7500.00, Automobile.StatoAuto.disponibile),
                new Automobile("Alfa Romeo", "Giulia", "Giulia 2.2 Turbodiesel 160 CV AT8", Year.of(2023), 17500.00, Automobile.StatoAuto.disponibile)
        );

        Pageable pageable = PageRequest.of(0,2);
        Page<Automobile> page = new PageImpl<>(automobileList, pageable, automobileList.size());
        when(automobileRepository.findByPrezzoBetween(1000, 20000, pageable)).thenReturn(page);

        Page<Automobile> result = automobileService.findByPrezzoBetween(1000, 20000, 0, 2);
        assertThat(result).isNotNull();
        assertThat(result.get()).hasSize(2);
        assertThat(result.get()).containsExactlyElementsOf(automobileList);
    }

    @Test
    void findAutomobiliByStatoTest(){
        List<Automobile> automobileList = List.of(
                new Automobile("Alfa Romeo", "Giulietta", "2.0 JTDM", Year.of(2011), 7500.00, Automobile.StatoAuto.disponibile),
                new Automobile("Alfa Romeo", "Giulia", "Giulia 2.2 Turbodiesel 160 CV AT8", Year.of(2023), 17500.00, Automobile.StatoAuto.disponibile)
        );

        Pageable pageable = PageRequest.of(0,2);
        Page<Automobile> page = new PageImpl<>(automobileList, pageable, automobileList.size());
        when(automobileRepository.findByStato(Automobile.StatoAuto.disponibile, pageable)).thenReturn(page);

        Page<Automobile> result = automobileService.findByStato(Automobile.StatoAuto.disponibile, 0, 2);
        assertThat(result).isNotNull();
        assertThat(result.get()).hasSize(2);
        assertThat(result.get()).containsExactlyElementsOf(automobileList);
    }
}
