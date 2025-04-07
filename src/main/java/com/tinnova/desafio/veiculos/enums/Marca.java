package com.tinnova.desafio.veiculos.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Marca {
    VOLKSWAGEN("Volkswagen"),
    FIAT("Fiat"),
    CHEVROLET("Chevrolet"),
    FORD("Ford"),
    RENAULT("Renault"),
    TOYOTA("Toyota"),
    HONDA("Honda"),
    HYUNDAI("Hyundai"),
    NISSAN("Nissan"),
    MERCEDES("Mercedes-Benz"),
    BMW("BMW"),
    AUDI("Audi"),
    JEEP("Jeep"),
    MITSUBISHI("Mitsubishi"),
    SUBARU("Subaru"),
    VOLVO("Volvo"),
    PEUGEOT("Peugeot"),
    CITROEN("Citroën"),
    KIA("Kia"),
    LAND_ROVER("Land Rover"),
    PORSCHE("Porsche"),
    FERRARI("Ferrari"),
    LAMBORGHINI("Lamborghini"),
    MASERATI("Maserati"),
    JAGUAR("Jaguar"),
    LEXUS("Lexus"),
    SUZUKI("Suzuki"),
    TESLA("Tesla"),
    BYD("BYD"),
    CHERY("Chery"),
    JAC("JAC"),
    RAM("RAM"),
    DODGE("Dodge"),
    MINI("MINI");

    private final String nome;
    private static final Map<String, Marca> porNome;

    static {
        porNome = Arrays.stream(values())
                .collect(Collectors.toMap(
                        marca -> marca.nome.toLowerCase(),
                        Function.identity()
                ));
    }

    Marca(String nome) {
        this.nome = nome;
    }

    @JsonValue
    public String getNome() {
        return nome;
    }

    @JsonCreator
    public static Marca fromString(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Marca não pode ser nula ou vazia");
        }

        String chave = text.trim().toLowerCase();
        Marca marca = porNome.get(chave);

        if (marca == null) {
            for (Map.Entry<String, Marca> entry : porNome.entrySet()) {
                if (entry.getKey().contains(chave) || chave.contains(entry.getKey())) {
                    return entry.getValue();
                }
            }
            throw new IllegalArgumentException("Marca não encontrada: " + text);
        }
        return marca;
    }
}