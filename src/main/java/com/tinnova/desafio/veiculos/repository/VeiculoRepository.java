package com.tinnova.desafio.veiculos.repository;

import com.tinnova.desafio.veiculos.enums.Marca;
import com.tinnova.desafio.veiculos.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, UUID> {

    List<Veiculo> findByMarca(Marca marca);

    List<Veiculo> findByAno(int ano);

    List<Veiculo> findByAnoBetween(@Param("inicio") int inicio, @Param("fim") int fim);

    List<Veiculo> findByVendidoFalse();

    long countByVendidoFalse();

    @Query("SELECT v FROM Veiculo v WHERE v.created >= :dataInicio")
    List<Veiculo> findByCreatedAfter(@Param("dataInicio") LocalDateTime dataInicio);
}
