package com.tinnova.desafio.veiculos.enums;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class MarcaTest {

    @Test
    void getNome_DeveRetornarNomeFormatado() {
        assertEquals("Volkswagen", Marca.VOLKSWAGEN.getNome());
        assertEquals("Fiat", Marca.FIAT.getNome());
        assertEquals("Mercedes-Benz", Marca.MERCEDES.getNome());
    }

    @ParameterizedTest
    @CsvSource({
            "Volkswagen, VOLKSWAGEN",
            "volkswagen, VOLKSWAGEN",
            "VOLKSWAGEN, VOLKSWAGEN",
            "Fiat, FIAT",
            "FIAT, FIAT",
            "Mercedes-Benz, MERCEDES",
            "mercedes, MERCEDES"
    })
    void fromString_DeveConverterCorretamente(String texto, Marca marcaEsperada) {
        assertEquals(marcaEsperada, Marca.fromString(texto));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void fromString_DeveGerarExcecaoParaValoresNulosOuVazios(String texto) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Marca.fromString(texto);
        });
        
        assertTrue(exception.getMessage().contains("nula ou vazia"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"InvalidBrand", "XYZ", "123"})
    void fromString_DeveGerarExcecaoParaMarcasInvalidas(String marcaInvalida) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Marca.fromString(marcaInvalida);
        });
        
        assertTrue(exception.getMessage().contains("não encontrada"));
    }

    @ParameterizedTest
    @CsvSource({
            "' Volkswagen ', VOLKSWAGEN",
            "'  fiat', FIAT",
            "'Mercedes-Benz  ', MERCEDES",
            "'  MERCEDES-BENZ', MERCEDES",
            "'  VoLksWaGeN  ', VOLKSWAGEN"
    })
    void fromString_DeveIgnorarEspacosECase(String texto, Marca marcaEsperada) {
        assertEquals(marcaEsperada, Marca.fromString(texto));
    }

    @ParameterizedTest
    @CsvSource({
            "chev, CHEVROLET",
            "benz, MERCEDES",
            "land, LAND_ROVER",
            "kia, KIA"
    })
    void fromString_DeveRealizarCorrespondenciaParcial(String texto, Marca marcaEsperada) {
        assertEquals(marcaEsperada, Marca.fromString(texto));
    }
} 