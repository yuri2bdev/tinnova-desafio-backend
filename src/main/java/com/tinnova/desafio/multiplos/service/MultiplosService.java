package com.tinnova.desafio.multiplos.service;

import org.springframework.stereotype.Service;
import java.util.stream.IntStream;

@Service
public class MultiplosService {

    public int somarMultiplosDe3ou5(int limite) {
        validarLimite(limite);

        return IntStream.range(1, limite)
                .filter(this::multiploDe3ou5)
                .sum();
    }

    private boolean multiploDe3ou5(int numero) {
        return numero % 3 == 0 || numero % 5 == 0;
    }

    private void validarLimite(int limite) {
        if (limite < 0) {
            throw new IllegalArgumentException("O limite deve ser um número não negativo.");
        }
    }
}

