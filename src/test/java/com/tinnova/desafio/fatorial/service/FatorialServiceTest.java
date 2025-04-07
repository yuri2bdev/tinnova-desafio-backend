package com.tinnova.desafio.fatorial.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class FatorialServiceTest {

    private final FatorialService service = new FatorialService();

    @Test
    @DisplayName("Deve calcular o fatorial de 0 corretamente")
    void deveCalcularFatorialDeZero() {
        BigInteger resultado = service.calcularFatorial(0);
        assertEquals(BigInteger.ONE, resultado);
    }

    @Test
    @DisplayName("Deve calcular o fatorial de 1 corretamente")
    void deveCalcularFatorialDeUm() {
        BigInteger resultado = service.calcularFatorial(1);
        assertEquals(BigInteger.ONE, resultado);
    }

    @Test
    @DisplayName("Deve calcular o fatorial de 5 corretamente")
    void deveCalcularFatorialDeCinco() {
        BigInteger resultado = service.calcularFatorial(5);
        assertEquals(BigInteger.valueOf(120), resultado);
    }

    @Test
    @DisplayName("Deve calcular o fatorial de 10 corretamente")
    void deveCalcularFatorialDeDez() {
        BigInteger resultado = service.calcularFatorial(10);
        assertEquals(BigInteger.valueOf(3628800), resultado);
    }

    @Test
    @DisplayName("Deve lançar exceção ao receber número negativo")
    void deveLancarExcecaoParaNumeroNegativo() {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> service.calcularFatorial(-3)
        );
        assertEquals("Número não pode ser negativo", excecao.getMessage());
    }
}