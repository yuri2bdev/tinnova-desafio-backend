package com.tinnova.desafio.veiculos.model;

import com.tinnova.desafio.veiculos.enums.Marca;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "veiculos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Veiculo {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(updatable = false, nullable = false)
    private UUID id;

    private String veiculo;

    @Enumerated(EnumType.STRING)
    private Marca marca;

    private Integer ano;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private Boolean vendido;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;
}
