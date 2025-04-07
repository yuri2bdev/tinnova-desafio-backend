package com.tinnova.desafio.veiculos.service;

import com.tinnova.desafio.veiculos.dto.VeiculoRequestDTO;
import com.tinnova.desafio.veiculos.dto.VeiculoResponseDTO;
import com.tinnova.desafio.veiculos.enums.Marca;
import com.tinnova.desafio.veiculos.model.Veiculo;
import com.tinnova.desafio.veiculos.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VeiculoServiceTest {

    @Mock
    private VeiculoRepository repository;

    @InjectMocks
    private VeiculoService service;

    private UUID id;
    private Veiculo veiculo;
    private VeiculoRequestDTO requestDTO;
    private List<Veiculo> veiculos;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        
        veiculo = Veiculo.builder()
                .id(id)
                .veiculo("Golf")
                .marca(Marca.VOLKSWAGEN)
                .ano(2020)
                .descricao("Veículo em ótimo estado")
                .vendido(false)
                .created(LocalDateTime.now())
                .updated(null)
                .build();
        
        requestDTO = new VeiculoRequestDTO(
                "Golf",
                Marca.VOLKSWAGEN,
                2020,
                "Veículo em ótimo estado",
                false
        );
        
        Veiculo veiculo2 = Veiculo.builder()
                .id(UUID.randomUUID())
                .veiculo("Uno")
                .marca(Marca.FIAT)
                .ano(2018)
                .descricao("Veículo econômico")
                .vendido(true)
                .created(LocalDateTime.now())
                .updated(null)
                .build();
        
        veiculos = Arrays.asList(veiculo, veiculo2);
    }

    @Test
    void listarTodos_DeveRetornarTodosVeiculos() {
        when(repository.findAll()).thenReturn(veiculos);
        
        List<VeiculoResponseDTO> result = service.listarTodos();
        
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void buscarPorId_QuandoVeiculoExiste_DeveRetornarVeiculo() {
        when(repository.findById(id)).thenReturn(Optional.of(veiculo));
        
        VeiculoResponseDTO result = service.buscarPorId(id);
        
        assertNotNull(result);
        assertEquals(id, result.id());
        assertEquals("Golf", result.veiculo());
        assertEquals(Marca.VOLKSWAGEN, result.marca());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void buscarPorId_QuandoVeiculoNaoExiste_DeveLancarExcecao() {
        when(repository.findById(id)).thenReturn(Optional.empty());
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.buscarPorId(id);
        });
        
        assertTrue(exception.getMessage().contains("não encontrado"));
        verify(repository, times(1)).findById(id);
    }

    @Test
    void criar_DeveSalvarERetornarVeiculo() {
        when(repository.save(any(Veiculo.class))).thenReturn(veiculo);
        
        VeiculoResponseDTO result = service.criar(requestDTO);
        
        assertNotNull(result);
        assertEquals("Golf", result.veiculo());
        assertEquals(Marca.VOLKSWAGEN, result.marca());
        verify(repository, times(1)).save(any(Veiculo.class));
    }

    @Test
    void atualizar_QuandoVeiculoExiste_DeveAtualizarERetornarVeiculo() {
        when(repository.findById(id)).thenReturn(Optional.of(veiculo));
        when(repository.save(any(Veiculo.class))).thenReturn(veiculo);
        
        // Criando nova instância com valores atualizados
        requestDTO = new VeiculoRequestDTO(
                "Golf GTI",
                Marca.VOLKSWAGEN,
                2020,
                "Veículo esportivo",
                false
        );
        
        VeiculoResponseDTO result = service.atualizar(id, requestDTO);
        
        assertNotNull(result);
        assertEquals("Golf GTI", veiculo.getVeiculo());
        assertEquals("Veículo esportivo", veiculo.getDescricao());
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(veiculo);
    }

    @Test
    void atualizar_QuandoVeiculoNaoExiste_DeveLancarExcecao() {
        when(repository.findById(id)).thenReturn(Optional.empty());
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.atualizar(id, requestDTO);
        });
        
        assertTrue(exception.getMessage().contains("não encontrado"));
        verify(repository, times(1)).findById(id);
        verify(repository, never()).save(any(Veiculo.class));
    }

    @Test
    void deletar_QuandoVeiculoExiste_DeveDeletar() {
        when(repository.existsById(id)).thenReturn(true);
        doNothing().when(repository).deleteById(id);
        
        assertDoesNotThrow(() -> service.deletar(id));
        
        verify(repository, times(1)).existsById(id);
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    void deletar_QuandoVeiculoNaoExiste_DeveLancarExcecao() {
        when(repository.existsById(id)).thenReturn(false);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.deletar(id);
        });
        
        assertTrue(exception.getMessage().contains("não encontrado"));
        verify(repository, times(1)).existsById(id);
        verify(repository, never()).deleteById(any());
    }

    @Test
    void buscarNaoVendidos_DeveRetornarApenasVeiculosNaoVendidos() {
        List<Veiculo> naoVendidos = Collections.singletonList(veiculo);
        when(repository.findByVendidoFalse()).thenReturn(naoVendidos);
        
        List<VeiculoResponseDTO> result = service.buscarNaoVendidos();
        
        assertNotNull(result);
        assertEquals(1, result.size());
        assertFalse(result.get(0).vendido());
        verify(repository, times(1)).findByVendidoFalse();
    }

    @Test
    void buscarPorMarca_DeveRetornarVeiculosDaMarca() {
        List<Veiculo> volkswagens = Collections.singletonList(veiculo);
        when(repository.findByMarca(Marca.VOLKSWAGEN)).thenReturn(volkswagens);
        
        List<VeiculoResponseDTO> result = service.buscarPorMarca(Marca.VOLKSWAGEN);
        
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(Marca.VOLKSWAGEN, result.get(0).marca());
        verify(repository, times(1)).findByMarca(Marca.VOLKSWAGEN);
    }
} 