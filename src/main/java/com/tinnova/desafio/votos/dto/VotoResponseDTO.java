package com.tinnova.desafio.votos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotoResponseDTO {
    private String percentValidos;
    private String percentBrancos;
    private String percentNulos;
}
