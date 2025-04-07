package com.tinnova.desafio.fatorial.controller;

import com.tinnova.desafio.fatorial.service.FatorialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/desafios/fatorial")
public class FatorialController {

    private final FatorialService fatorialService;

    public FatorialController(FatorialService fatorialService) {
        this.fatorialService = fatorialService;
    }

    @GetMapping("/{numero}")
    public ResponseEntity<BigInteger> calcularFatorial(@PathVariable int numero) {
        try {
            return ResponseEntity.ok(fatorialService.calcularFatorial(numero));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}