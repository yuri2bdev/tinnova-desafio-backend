package com.tinnova.desafio.votos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinnova.desafio.votos.dto.VotoRequestDTO;
import com.tinnova.desafio.votos.service.VotoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VotoController.class)
@Import(VotoControllerTest.Config.class) // Importando nossa config custom
class VotoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class Config {
        @Bean
        public VotoService votoService() {
            return new VotoService(); // Instância real
        }
    }

    @Test
    @DisplayName("Deve retornar os percentuais de votos como String e double")
    void deveRetornarPercentuaisDeVotos() throws Exception {
        VotoRequestDTO request = new VotoRequestDTO(1000, 800, 150, 50);

        mockMvc.perform(post("/desafios/votos/calcular")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"totalEleitores\":1000,\"votosValidos\":800,\"votosBrancos\":150,\"votosNulos\":50}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.percentValidos").value("80,00%"))
                .andExpect(jsonPath("$.percentBrancos").value("15,00%"))
                .andExpect(jsonPath("$.percentNulos").value("5,00%"));
    }
}