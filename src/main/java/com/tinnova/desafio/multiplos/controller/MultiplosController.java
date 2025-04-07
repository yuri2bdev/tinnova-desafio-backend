package com.tinnova.desafio.multiplos.controller;

import com.tinnova.desafio.multiplos.service.MultiplosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/desafios/multiplos")
public class MultiplosController {

    private final MultiplosService multiplosService;

    public MultiplosController(MultiplosService multiplosService) {
        this.multiplosService = multiplosService;
    }

    @GetMapping("/{limite}")
    public ResponseEntity<Integer> somarMultiplos(@PathVariable int limite) {
        return ResponseEntity.ok(multiplosService.somarMultiplosDe3ou5(limite));
    }
}
