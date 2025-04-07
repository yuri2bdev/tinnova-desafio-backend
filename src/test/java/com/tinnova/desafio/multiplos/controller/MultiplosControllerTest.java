package com.tinnova.desafio.multiplos.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MultiplosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deve retornar a soma dos múltiplos de 3 ou 5 até o limite fornecido")
    void deveRetornarSomaDosMultiplos() throws Exception {
        int limite = 10;
        int resultadoEsperado = 23; // 3 + 5 + 6 + 9

        mockMvc.perform(get("/desafios/multiplos/{limite}", limite)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(resultadoEsperado)));
    }
}