package com.tinnova.desafio.veiculos.util;

import com.tinnova.desafio.veiculos.dto.VeiculoRequestDTO;
import com.tinnova.desafio.veiculos.dto.VeiculoResponseDTO;
import com.tinnova.desafio.veiculos.model.Veiculo;

import java.time.LocalDateTime;

public class VeiculoMapper {

    private VeiculoMapper() {
        throw new UnsupportedOperationException("Classe utilitária, não pode ser instanciada.");
    }

    public static VeiculoResponseDTO toResponseDTO(Veiculo veiculo) {
        return new VeiculoResponseDTO(
                veiculo.getId(),
                veiculo.getVeiculo(),
                veiculo.getMarca(),
                veiculo.getAno(),
                veiculo.getDescricao(),
                veiculo.getVendido(),
                veiculo.getCreated(),
                veiculo.getUpdated()
        );
    }

    public static Veiculo toEntity(VeiculoRequestDTO dto) {
        return new Veiculo(
                null, // O ID será gerado pelo banco
                dto.veiculo(),
                dto.marca(),
                dto.ano(),
                dto.descricao(),
                dto.vendido(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public static void updateEntityFromDto(Veiculo veiculo, VeiculoRequestDTO dto) {
        veiculo.setVeiculo(dto.veiculo());
        veiculo.setMarca(dto.marca());
        veiculo.setAno(dto.ano());
        veiculo.setDescricao(dto.descricao());
        veiculo.setVendido(dto.vendido());
        veiculo.setUpdated(LocalDateTime.now());
    }
}
