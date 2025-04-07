package com.tinnova.desafio.fatorial.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FatorialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deve retornar o fatorial de 5 como 120")
    void calcularFatorial_comNumeroValido_deveRetornarResultadoEsperado() throws Exception {
        mockMvc.perform(get("/desafios/fatorial/5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("120"));
    }

    @Test
    @DisplayName("Deve retornar 400 para número negativo")
    void calcularFatorial_comNumeroNegativo_deveRetornarErro() throws Exception {
        mockMvc.perform(get("/desafios/fatorial/-3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}