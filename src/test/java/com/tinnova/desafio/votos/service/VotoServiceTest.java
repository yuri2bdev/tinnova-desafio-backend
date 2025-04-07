package com.tinnova.desafio.votos.service;

import com.tinnova.desafio.votos.dto.VotoRequestDTO;
import com.tinnova.desafio.votos.dto.VotoResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VotoServiceTest {

    private VotoService votoService;

    @BeforeEach
    void setUp() {
        votoService = new VotoService();
    }

    @Test
    void deveCalcularPercentuaisCorretamente() {
        // total: 1000, válidos: 800, brancos: 150, nulos: 50
        VotoRequestDTO dto = new VotoRequestDTO(1000, 800, 150, 50);

        VotoResponseDTO response = votoService.calcularPercentuais(dto);

        assertEquals("80,00%", response.getPercentValidos());
        assertEquals("15,00%", response.getPercentBrancos());
        assertEquals("5,00%", response.getPercentNulos());
    }

    @Test
    void deveLancarExcecaoQuandoTotalEleitoresForZero() {
        VotoRequestDTO dto = new VotoRequestDTO(0, 0, 0, 0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            votoService.calcularPercentuais(dto);
        });

        assertEquals("O total de eleitores não pode ser zero.", exception.getMessage());
    }
}