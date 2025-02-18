package com.lucapdt.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucapdt.challenge.command.AutomobileCommand;
import com.lucapdt.challenge.model.dto.AutomobileDTO;
import com.lucapdt.challenge.model.entity.Automobile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest(AutomobileRestController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AutomobileControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @MockitoBean
    private AutomobileCommand automobileCommand;

    @Autowired
    private ObjectMapper objectMapper;

    private Automobile automobile;
    private AutomobileDTO automobileDTO;

    @BeforeEach
    void setup() {
        automobile = new Automobile("Fiat", "Panda", "2.0 JTDM", Year.of(2011), 7500.00, Automobile.StatoAuto.disponibile);
        automobileDTO = new AutomobileDTO(0, "Fiat", "Panda", "2.0 JTDM", Year.of(2011), 7500.00, Automobile.StatoAuto.disponibile);
    }

    @Test
    void findByIdTest() throws Exception {
        int id = 1;

        when(automobileCommand.findById(1)).thenReturn(automobileDTO);

        MockHttpServletResponse response = mockmvc.perform(
                        get("/api/automobili/1")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(objectMapper.writeValueAsString(automobileDTO));
    }
}