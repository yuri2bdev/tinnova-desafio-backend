package com.tinnova.desafio.bubble.controller;

import com.tinnova.desafio.bubble.service.BubbleSortService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BubbleSortController.class)
@Import(BubbleSortControllerTest.Config.class)
class BubbleSortControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BubbleSortService bubbleSortService;

    @Test
    void ordenar_DeveRetornarVetorOrdenado() throws Exception {
        int[] entrada = {5, 1, 4, 2, 8};
        int[] esperado = {1, 2, 4, 5, 8};

        when(bubbleSortService.ordenar(entrada)).thenReturn(esperado);

        String jsonEntrada = "[5,1,4,2,8]";
        String jsonEsperado = "[1,2,4,5,8]";

        mockMvc.perform(post("/desafios/bubble-sort")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonEntrada))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonEsperado));

        verify(bubbleSortService).ordenar(entrada);
    }

    @TestConfiguration
    static class Config {
        @Bean
        public BubbleSortService bubbleSortService() {
            return mock(BubbleSortService.class);
        }
    }
}