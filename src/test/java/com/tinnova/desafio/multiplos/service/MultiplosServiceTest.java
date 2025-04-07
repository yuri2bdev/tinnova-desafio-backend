package com.tinnova.desafio.multiplos.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiplosServiceTest {

    private final MultiplosService service = new MultiplosService();

    @Test
    @DisplayName("Deve retornar 23 para o limite 10 (múltiplos de 3 ou 5: 3, 5, 6, 9)")
    void deveSomarMultiplosCorretamente() {
        int resultado = service.somarMultiplosDe3ou5(10);
        assertEquals(23, resultado);
    }

    @Test
    @DisplayName("Deve lançar exceção para limite negativo")
    void deveLancarExcecaoParaLimiteNegativo() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            service.somarMultiplosDe3ou5(-5);
        });
        assertEquals("O limite deve ser um número não negativo.", ex.getMessage());
    }

    @Test
    @DisplayName("Deve retornar 0 para limite 0 ou 2")
    void deveRetornarZeroParaLimitesBaixosValidos() {
        assertEquals(0, service.somarMultiplosDe3ou5(0));
        assertEquals(0, service.somarMultiplosDe3ou5(2));
    }

    @Test
    @DisplayName("Deve calcular corretamente para um limite maior")
    void deveCalcularCorretamenteParaLimiteMaior() {
        int resultado = service.somarMultiplosDe3ou5(20);
        int esperado = 78;
        assertEquals(esperado, resultado);
    }
}