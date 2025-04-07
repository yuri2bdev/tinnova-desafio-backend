package com.tinnova.desafio.votos.controller;

import com.tinnova.desafio.votos.dto.VotoRequestDTO;
import com.tinnova.desafio.votos.dto.VotoResponseDTO;
import com.tinnova.desafio.votos.service.VotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/desafios/votos")
public class VotoController {

    private final VotoService votoService;

    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @PostMapping("/calcular")
    public ResponseEntity<VotoResponseDTO> calcular(@RequestBody VotoRequestDTO dto) {
        return ResponseEntity.ok(votoService.calcularPercentuais(dto));
    }
}
