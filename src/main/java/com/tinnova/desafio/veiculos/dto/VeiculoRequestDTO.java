package com.tinnova.desafio.veiculos.dto;

import com.tinnova.desafio.veiculos.enums.Marca;
import jakarta.validation.constraints.*;

public record VeiculoRequestDTO(

        @NotBlank(message = "O campo 'veiculo' é obrigatório.")
        String veiculo,

        @NotNull(message = "O campo 'marca' é obrigatório.")
        Marca marca,

        @Min(value = 1886, message = "O ano deve ser maior ou igual a 1886.")
        @Max(value = 2100, message = "O ano deve ser menor ou igual a 2100.")
        int ano,

        @NotBlank(message = "O campo 'descricao' é obrigatório.")
        @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres.")
        String descricao,

        @NotNull(message = "O campo 'vendido' é obrigatório.")
        Boolean vendido
) {}
