package com.tinnova.desafio.veiculos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinnova.desafio.veiculos.dto.VeiculoRequestDTO;
import com.tinnova.desafio.veiculos.dto.VeiculoResponseDTO;
import com.tinnova.desafio.veiculos.enums.Marca;
import com.tinnova.desafio.veiculos.service.VeiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VeiculoController.class)
@Import(VeiculoControllerTest.TestConfig.class)
class VeiculoControllerTest {

    @TestConfiguration
    static class TestConfig {
        @Bean
        public VeiculoService veiculoService() {
            return Mockito.mock(VeiculoService.class);
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private VeiculoService service;

    private VeiculoResponseDTO responseDTO;
    private VeiculoRequestDTO requestDTO;
    private UUID id;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        responseDTO = new VeiculoResponseDTO(
                id,
                "Golf",
                Marca.VOLKSWAGEN,
                2020,
                "Veículo em ótimo estado",
                false,
                now,
                null
        );

        requestDTO = new VeiculoRequestDTO(
                "Golf",
                Marca.VOLKSWAGEN,
                2020,
                "Veículo em ótimo estado",
                false
        );
    }

    @Test
    void listarTodos_DeveRetornarListaDeVeiculos() throws Exception {
        when(service.listarTodos()).thenReturn(List.of(responseDTO));

        mockMvc.perform(get("/veiculos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(id.toString()))
                .andExpect(jsonPath("$[0].veiculo").value("Golf"))
                .andExpect(jsonPath("$[0].marca").value("Volkswagen"));
    }

    @Test
    void buscarPorId_QuandoVeiculoExiste_DeveRetornarVeiculo() throws Exception {
        when(service.buscarPorId(id)).thenReturn(responseDTO);

        mockMvc.perform(get("/veiculos/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.veiculo").value("Golf"))
                .andExpect(jsonPath("$.marca").value("Volkswagen"));
    }

    @Test
    void criar_DeveRetornarVeiculoCriado() throws Exception {
        when(service.criar(any(VeiculoRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/veiculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.veiculo").value("Golf"))
                .andExpect(jsonPath("$.marca").value("Volkswagen"));
    }

    @Test
    void atualizar_DeveRetornarVeiculoAtualizado() throws Exception {
        when(service.atualizar(eq(id), any(VeiculoRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(put("/veiculos/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.veiculo").value("Golf"))
                .andExpect(jsonPath("$.marca").value("Volkswagen"));
    }

    @Test
    void deletar_DeveRetornarStatusOk() throws Exception {
        mockMvc.perform(delete("/veiculos/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    void buscarPorMarca_DeveRetornarVeiculosDaMarca() throws Exception {
        when(service.buscarPorMarca(Marca.VOLKSWAGEN)).thenReturn(List.of(responseDTO));

        mockMvc.perform(get("/veiculos/marca/{marca}", "Volkswagen"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].marca").value("Volkswagen"));
    }

    @Test
    void buscarNaoVendidos_DeveRetornarVeiculosNaoVendidos() throws Exception {
        when(service.buscarNaoVendidos()).thenReturn(List.of(responseDTO));

        mockMvc.perform(get("/veiculos/nao-vendidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].vendido").value(false));
    }
}