package com.lucapdt.challenge.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucapdt.challenge.command.AutomobileCommand;
import com.lucapdt.challenge.model.dto.AutomobileDTO;
import com.lucapdt.challenge.model.entity.Automobile;
import com.lucapdt.challenge.model.response.AutomobileResponse;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


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

    @Test
    void saveTest() throws Exception {
        when(automobileCommand.save(automobileDTO)).thenReturn(automobileDTO);

        MockHttpServletResponse response = mockmvc.perform(
                        post("/api/automobili")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(automobileDTO)))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString())
                .isEqualTo(objectMapper.writeValueAsString(automobileDTO));
    }

    @Test
    void updateTest() throws Exception {
        when(automobileCommand.update(1, automobileDTO)).thenReturn(automobileDTO);
        MockHttpServletResponse response = mockmvc.perform(
                        put("/api/automobili/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(automobileDTO)))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(objectMapper.writeValueAsString(automobileDTO));
    }

    @Test
    void deleteById() throws Exception {
        doNothing().when(automobileCommand).deleteById(0);

        MockHttpServletResponse response = mockmvc.perform(
                        delete("/api/automobili/1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("Automobile delete");
    }

    @Test
    void findAllListTest() throws Exception {
        List<AutomobileDTO> automobileList = List.of(automobileDTO, automobileDTO);

        when(automobileCommand.findAll()).thenReturn(automobileList);

        MockHttpServletResponse response = mockmvc.perform(
                        get("/api/automobili/list")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(objectMapper.writeValueAsString(automobileList));
    }

    @Test
    void findAllPagingTest() throws Exception {
        AutomobileResponse expected = AutomobileResponse.builder().pageSize(10).last(true).pageNo(1).content(List.of(automobileDTO)).build();

        when(automobileCommand.findAll(1,10)).thenReturn(expected);

        MockHttpServletResponse response = mockmvc.perform(
                        get("/api/automobili")
                                .accept(MediaType.APPLICATION_JSON)
                                .param("page", "1")
                                .param("size", "10"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(objectMapper.writeValueAsString(expected));
    }

    @Test
    void findByMarcaTest() throws Exception {
        AutomobileResponse expected = AutomobileResponse.builder().pageSize(10).last(true).pageNo(1).content(List.of(automobileDTO)).build();

        when(automobileCommand.findByMarca("Fiat", 1,10)).thenReturn(expected);

        MockHttpServletResponse response = mockmvc.perform(
                        get("/api/automobili/marca")
                                .accept(MediaType.APPLICATION_JSON)
                                .param("marca", "Fiat")
                                .param("page", "1")
                                .param("size", "10"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(objectMapper.writeValueAsString(expected));
    }

    @Test
    void findByPrezzoBetweenTest() throws Exception {
        AutomobileResponse expected = AutomobileResponse.builder().pageSize(10).last(true).pageNo(1).content(List.of(automobileDTO)).build();

        when(automobileCommand.findByPrezzoBetween(0, 10000, 1,10)).thenReturn(expected);

        MockHttpServletResponse response = mockmvc.perform(
                        get("/api/automobili/prezzo/range")
                                .accept(MediaType.APPLICATION_JSON)
                                .param("prezzoMin", "0")
                                .param("prezzoMax", "10000")
                                .param("page", "1")
                                .param("size", "10"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(objectMapper.writeValueAsString(expected));
    }


}