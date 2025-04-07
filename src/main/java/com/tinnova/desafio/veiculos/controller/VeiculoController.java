package com.tinnova.desafio.veiculos.controller;

import com.tinnova.desafio.veiculos.dto.VeiculoRequestDTO;
import com.tinnova.desafio.veiculos.dto.VeiculoResponseDTO;
import com.tinnova.desafio.veiculos.enums.Marca;
import com.tinnova.desafio.veiculos.model.Veiculo;
import com.tinnova.desafio.veiculos.service.VeiculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/veiculos")
@RequiredArgsConstructor
public class VeiculoController {

    private final VeiculoService service;

    @GetMapping
    public List<VeiculoResponseDTO> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public VeiculoResponseDTO buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public VeiculoResponseDTO criar(@RequestBody @Valid VeiculoRequestDTO dto) {
        return service.criar(dto);
    }

    @PutMapping("/{id}")
    public VeiculoResponseDTO atualizar(@PathVariable UUID id, @RequestBody VeiculoRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Veiculo> atualizarParcialmente(@PathVariable UUID id, @RequestBody Map<String, Object> updates) {
        Veiculo atualizado = service.atualizarParcial(id, updates);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }

    // Endpoints de filtro
    @GetMapping("/nao-vendidos")
    public List<VeiculoResponseDTO> buscarNaoVendidos() {
        return service.buscarNaoVendidos();
    }

    @GetMapping("/nao-vendidos/contagem")
    public ResponseEntity<Long> contarNaoVendidos() {
        return ResponseEntity.ok(service.contarVeiculosNaoVendidos());
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<VeiculoResponseDTO>> buscarPorMarca(@PathVariable String marca) {
        try {
            Marca marcaEnum = Marca.fromString(marca);
            return ResponseEntity.ok(service.buscarPorMarca(marcaEnum));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok(List.of());
        }
    }

    @GetMapping("/contagem-por-marca")
    public ResponseEntity<Map<String, Long>> contarPorMarca() {
        return ResponseEntity.ok(service.contarVeiculosPorMarca());
    }

    @GetMapping("/ano/{ano}")
    public List<VeiculoResponseDTO> buscarPorAno(@PathVariable int ano) {
        return service.buscarPorAno(ano);
    }

    @GetMapping("/decada/{anoBase}")
    public List<VeiculoResponseDTO> buscarPorDecada(@PathVariable int anoBase) {
        return service.buscarPorDecada(anoBase);
    }

    @GetMapping("/contagem-por-decada")
    public ResponseEntity<Map<String, Long>> contarPorDecada() {
        return ResponseEntity.ok(service.contarVeiculosPorDecada());
    }

    @GetMapping("/ultima-semana")
    public List<VeiculoResponseDTO> buscarRegistradosUltimaSemana() {
        return service.buscarRegistradosUltimaSemana();
    }
}