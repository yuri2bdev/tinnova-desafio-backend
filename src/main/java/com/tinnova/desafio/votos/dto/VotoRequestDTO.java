package com.tinnova.desafio.votos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotoRequestDTO {
    private int totalEleitores;
    private int votosValidos;
    private int votosBrancos;
    private int votosNulos;
}
