package com.tinnova.desafio.veiculos.config;

import com.tinnova.desafio.veiculos.enums.Marca;
import com.tinnova.desafio.veiculos.model.Veiculo;
import com.tinnova.desafio.veiculos.repository.VeiculoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner carregarDadosIniciais(VeiculoRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                List<Veiculo> veiculos = List.of(
                        Veiculo.builder()
                                .veiculo("Civic")
                                .marca(Marca.HONDA)
                                .ano(2010)
                                .descricao("Sedan confortável")
                                .vendido(false)
                                .build(),
                        Veiculo.builder()
                                .veiculo("Corolla")
                                .marca(Marca.TOYOTA)
                                .ano(2012)
                                .descricao("Econômico e confiável")
                                .vendido(true)
                                .build(),
                        Veiculo.builder()
                                .veiculo("Gol")
                                .marca(Marca.VOLKSWAGEN)
                                .ano(2005)
                                .descricao("Popular e resistente")
                                .vendido(false)
                                .build(),
                        Veiculo.builder()
                                .veiculo("Fusca")
                                .marca(Marca.VOLKSWAGEN)
                                .ano(1975)
                                .descricao("Clássico e icônico")
                                .vendido(false)
                                .build(),
                        Veiculo.builder()
                                .veiculo("Marea 2.0 Turbo")
                                .marca(Marca.FIAT)
                                .ano(2000)
                                .descricao("Sedan bombástico")
                                .vendido(true)
                                .build()
                );

                repository.saveAll(veiculos);
                System.out.println("🚗 Veículos mockados inseridos com sucesso!");
            }
        };
    }
}
