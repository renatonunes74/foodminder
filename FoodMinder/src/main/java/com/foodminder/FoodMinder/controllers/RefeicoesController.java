package com.foodminder.FoodMinder.controllers;

import com.foodminder.FoodMinder.domain.refeicoes.RefeicoesRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/refeicoes")
public class RefeicoesController {
    @Autowired
    private RefeicoesRepository repository;
    @GetMapping
    public ResponseEntity getAllRefeicoes() {
      return(ResponseEntity.ok(repository.findAll()));
    }
}
