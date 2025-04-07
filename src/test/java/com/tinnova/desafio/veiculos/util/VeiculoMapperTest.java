package com.tinnova.desafio.veiculos.util;

import com.tinnova.desafio.veiculos.dto.VeiculoRequestDTO;
import com.tinnova.desafio.veiculos.dto.VeiculoResponseDTO;
import com.tinnova.desafio.veiculos.enums.Marca;
import com.tinnova.desafio.veiculos.model.Veiculo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class VeiculoMapperTest {

    @Test
    void toResponseDTO_DeveMapearCorretamente() {
        LocalDateTime now = LocalDateTime.now();
        UUID id = UUID.randomUUID();
        Veiculo veiculo = new Veiculo(
                id,
                "Gol",
                Marca.VOLKSWAGEN,
                2010,
                "Compacto urbano",
                false,
                now,
                now
        );

        VeiculoResponseDTO dto = VeiculoMapper.toResponseDTO(veiculo);

        assertEquals(veiculo.getId(), dto.id());
        assertEquals(veiculo.getVeiculo(), dto.veiculo());
        assertEquals(veiculo.getMarca(), dto.marca());
        assertEquals(veiculo.getAno(), dto.ano());
        assertEquals(veiculo.getDescricao(), dto.descricao());
        assertEquals(veiculo.getVendido(), dto.vendido());
        assertEquals(veiculo.getCreated(), dto.created());
        assertEquals(veiculo.getUpdated(), dto.updated());
    }

    @Test
    void toEntity_DeveMapearCorretamente() {
        VeiculoRequestDTO request = new VeiculoRequestDTO(
                "Civic",
                Marca.HONDA,
                2020,
                "Sedan confortável",
                true
        );

        Veiculo veiculo = VeiculoMapper.toEntity(request);

        assertNull(veiculo.getId());
        assertEquals(request.veiculo(), veiculo.getVeiculo());
        assertEquals(request.marca(), veiculo.getMarca());
        assertEquals(request.ano(), veiculo.getAno());
        assertEquals(request.descricao(), veiculo.getDescricao());
        assertEquals(request.vendido(), veiculo.getVendido());
        assertNotNull(veiculo.getCreated());
        assertNotNull(veiculo.getUpdated());
    }

    @Test
    void updateEntityFromDto_DeveAtualizarCampos() {
        UUID id = UUID.randomUUID();
        Veiculo veiculo = new Veiculo(
                id,
                "Uno",
                Marca.FIAT,
                2005,
                "Carro econômico",
                false,
                LocalDateTime.now().minusDays(5),
                LocalDateTime.now().minusDays(5)
        );

        VeiculoRequestDTO novoDto = new VeiculoRequestDTO(
                "Strada",
                Marca.FIAT,
                2023,
                "Pickup leve",
                true
        );

        LocalDateTime updatedAntes = veiculo.getUpdated();

        VeiculoMapper.updateEntityFromDto(veiculo, novoDto);

        assertEquals(novoDto.veiculo(), veiculo.getVeiculo());
        assertEquals(novoDto.marca(), veiculo.getMarca());
        assertEquals(novoDto.ano(), veiculo.getAno());
        assertEquals(novoDto.descricao(), veiculo.getDescricao());
        assertEquals(novoDto.vendido(), veiculo.getVendido());
        assertTrue(veiculo.getUpdated().isAfter(updatedAntes));
    }
}
