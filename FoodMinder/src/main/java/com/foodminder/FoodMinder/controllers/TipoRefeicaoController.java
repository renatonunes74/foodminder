package com.foodminder.FoodMinder.controllers;

import com.foodminder.FoodMinder.domain.TipoRefeicao.TipoRefeicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tipoRefeicoes")
public class TipoRefeicaoController {
    @Autowired
    private TipoRefeicaoRepository repository;
    @GetMapping
    public ResponseEntity getAllTipoRefeicao() {
        return ResponseEntity.ok(repository.findAll());
    }
}
