package com.tinnova.desafio.fatorial.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class FatorialService {

    public BigInteger calcularFatorial(int numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("Número não pode ser negativo");
        }

        BigInteger resultado = BigInteger.ONE;
        for (int i = 2; i <= numero; i++) {
            resultado = resultado.multiply(BigInteger.valueOf(i));
        }
        return resultado;
    }
}