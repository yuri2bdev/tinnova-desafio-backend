package com.tinnova.desafio.veiculos.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record VeiculoResponseDTO(
        UUID id,
        String veiculo,
        com.tinnova.desafio.veiculos.enums.Marca marca,
        Integer ano,
        String descricao,
        Boolean vendido,
        LocalDateTime created,
        LocalDateTime updated
) {}
