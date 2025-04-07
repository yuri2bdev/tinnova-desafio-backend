package com.tinnova.desafio.bubble.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BubbleSortServiceTest {

    private final BubbleSortService service = new BubbleSortService();

    @Test
    void ordenar_DeveOrdenarVetorComNumerosAleatorios() {
        int[] entrada = {5, 1, 4, 2, 8};
        int[] esperado = {1, 2, 4, 5, 8};
        assertArrayEquals(esperado, service.ordenar(entrada));
    }

    @Test
    void ordenar_DeveManterVetorJaOrdenado() {
        int[] entrada = {1, 2, 3, 4, 5};
        int[] esperado = {1, 2, 3, 4, 5};
        assertArrayEquals(esperado, service.ordenar(entrada));
    }

    @Test
    void ordenar_DeveOrdenarVetorComNumerosNegativos() {
        int[] entrada = {0, -3, 2, -1, 5};
        int[] esperado = {-3, -1, 0, 2, 5};
        assertArrayEquals(esperado, service.ordenar(entrada));
    }

    @Test
    void ordenar_DeveOrdenarVetorComElementosRepetidos() {
        int[] entrada = {4, 2, 4, 3, 2};
        int[] esperado = {2, 2, 3, 4, 4};
        assertArrayEquals(esperado, service.ordenar(entrada));
    }

    @Test
    void ordenar_DeveRetornarVetorVazioSeEntradaForVazia() {
        int[] entrada = {};
        int[] esperado = {};
        assertArrayEquals(esperado, service.ordenar(entrada));
    }

    @Test
    void ordenar_DeveRetornarMesmoElementoSeVetorTiverUmElemento() {
        int[] entrada = {42};
        int[] esperado = {42};
        assertArrayEquals(esperado, service.ordenar(entrada));
    }
}
