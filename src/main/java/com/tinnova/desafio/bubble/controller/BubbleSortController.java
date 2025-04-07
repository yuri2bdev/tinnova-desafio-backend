package com.tinnova.desafio.bubble.controller;

import com.tinnova.desafio.bubble.service.BubbleSortService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/desafios/bubble-sort")
public class BubbleSortController {

    private final BubbleSortService bubbleSortService;

    public BubbleSortController(BubbleSortService bubbleSortService) {
        this.bubbleSortService = bubbleSortService;
    }

    @PostMapping
    public ResponseEntity<int[]> ordenar(@RequestBody int[] vetor) {
        return ResponseEntity.ok(bubbleSortService.ordenar(vetor));
    }
}
