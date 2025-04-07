package com.tinnova.desafio.veiculos.service;

import com.tinnova.desafio.veiculos.dto.VeiculoRequestDTO;
import com.tinnova.desafio.veiculos.dto.VeiculoResponseDTO;
import com.tinnova.desafio.veiculos.enums.Marca;
import com.tinnova.desafio.veiculos.model.Veiculo;
import com.tinnova.desafio.veiculos.repository.VeiculoRepository;
import com.tinnova.desafio.veiculos.util.VeiculoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VeiculoService {

    private final VeiculoRepository repository;

    public VeiculoService(VeiculoRepository repository) {
        this.repository = repository;
    }

    public List<VeiculoResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(VeiculoMapper::toResponseDTO)
                .toList();
    }

    public VeiculoResponseDTO buscarPorId(UUID id) {
        return repository.findById(id)
                .map(VeiculoMapper::toResponseDTO)
                .orElseThrow(() -> new IllegalArgumentException("Veículo não encontrado com ID: " + id));
    }

    @Transactional
    public VeiculoResponseDTO criar(VeiculoRequestDTO dto) {
        Veiculo novoVeiculo = VeiculoMapper.toEntity(dto);
        Veiculo salvo = repository.save(novoVeiculo);
        return VeiculoMapper.toResponseDTO(salvo);
    }

    @Transactional
    public VeiculoResponseDTO atualizar(UUID id, VeiculoRequestDTO dto) {
        Veiculo veiculo = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Veículo não encontrado com ID: " + id));

        VeiculoMapper.updateEntityFromDto(veiculo, dto);
        Veiculo atualizado = repository.save(veiculo);

        return VeiculoMapper.toResponseDTO(atualizado);
    }

    public Veiculo atualizarParcial(UUID id, Map<String, Object> updates) {
        Veiculo veiculo = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Veículo não encontrado"));

        updates.forEach((chave, valor) -> {
            Field field = ReflectionUtils.findField(Veiculo.class, chave);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, veiculo, valor);
            }
        });

        veiculo.setUpdated(LocalDateTime.now());
        return repository.save(veiculo);
    }


    public List<VeiculoResponseDTO> buscarNaoVendidos() {
        return repository.findByVendidoFalse()
                .stream()
                .map(VeiculoMapper::toResponseDTO)
                .toList();
    }

    public long contarVeiculosNaoVendidos() {
        return repository.countByVendidoFalse();
    }

    public List<VeiculoResponseDTO> buscarPorMarca(Marca marca) {
        return repository.findByMarca(marca)
                .stream()
                .map(VeiculoMapper::toResponseDTO)
                .toList();
    }

    public List<VeiculoResponseDTO> buscarPorAno(int ano) {
        return repository.findByAno(ano)
                .stream()
                .map(VeiculoMapper::toResponseDTO)
                .toList();
    }

    public List<VeiculoResponseDTO> buscarPorDecada(int anoBase) {
        int inicioDecada = (anoBase / 10) * 10;
        int fimDecada = inicioDecada + 9;

        return repository.findByAnoBetween(inicioDecada, fimDecada)
                .stream()
                .map(VeiculoMapper::toResponseDTO)
                .toList();
    }

    public Map<String, Long> contarVeiculosPorDecada() {
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(
                        veiculo -> {
                            int ano = veiculo.getAno();
                            int decada = (ano / 10) * 10;
                            return "Década de " + decada;
                        },
                        Collectors.counting()
                ));
    }

    public List<VeiculoResponseDTO> buscarRegistradosUltimaSemana() {
        LocalDate seteDiasAtras = LocalDate.now().minusDays(7);
        LocalDateTime dataInicio = seteDiasAtras.atStartOfDay();
        
        return repository.findByCreatedAfter(dataInicio)
                .stream()
                .map(VeiculoMapper::toResponseDTO)
                .toList();
    }

    public Map<String, Long> contarVeiculosPorMarca() {
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(
                        veiculo -> veiculo.getMarca().name(),
                        Collectors.counting()
                ));
    }

    @Transactional
    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Veículo não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }
}
