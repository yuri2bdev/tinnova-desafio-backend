package com.tinnova.desafio.bubble.service;

import org.springframework.stereotype.Service;

@Service
public class BubbleSortService {

    public int[] ordenar(int[] vetor) {
        int n = vetor.length;
        boolean trocou;

        //Poderíamos usar Arrays.sort(), mas o propósito é implementar Bubble Sort manualmente mesmo.
        for (int i = 0; i < n - 1; i++) {
            trocou = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (vetor[j] > vetor[j + 1]) {
                    // swap usando destructuring de Java 21
                    vetor[j] = vetor[j] + vetor[j + 1];
                    vetor[j + 1] = vetor[j] - vetor[j + 1];
                    vetor[j] = vetor[j] - vetor[j + 1];
                    trocou = true;
                }
            }
            if (!trocou) break;
        }

        return vetor;
    }
}
