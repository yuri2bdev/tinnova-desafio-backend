package com.tinnova.desafio.votos.service;

import com.tinnova.desafio.votos.dto.VotoRequestDTO;
import com.tinnova.desafio.votos.dto.VotoResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class VotoService {

    public VotoResponseDTO calcularPercentuais(VotoRequestDTO dto) {
        int total = dto.getTotalEleitores();

        if (total == 0) {
            throw new IllegalArgumentException("O total de eleitores não pode ser zero.");
        }

        double percentValidos = (dto.getVotosValidos() * 100.0) / total;
        double percentBrancos = (dto.getVotosBrancos() * 100.0) / total;
        double percentNulos = (dto.getVotosNulos() * 100.0) / total;

        return new VotoResponseDTO(
                String.format("%.2f%%", percentValidos),
                String.format("%.2f%%", percentBrancos),
                String.format("%.2f%%", percentNulos)
        );
    }
}
