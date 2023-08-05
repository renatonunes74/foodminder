package com.foodminder.FoodMinder.controllers;

import com.foodminder.FoodMinder.domain.refeicao.RefeicaoRepository;
import com.foodminder.FoodMinder.domain.refeicao.RequestRefeicao;
import com.foodminder.FoodMinder.services.RefeicaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/refeicao")
public class RefeicaoController {
    @Autowired
    private RefeicaoRepository refeicaoRepository;
    @Autowired
    private RefeicaoService refeicaoService;
    @GetMapping
    public ResponseEntity getAllRefeicao() {
        return refeicaoService.obterTodasRefeicoes();
    }
    @GetMapping("/{id}")
    public ResponseEntity getRefeicaoById(@PathVariable Integer id) {
        return refeicaoService.obterRefeicaoPorId(id);
    }
    @PostMapping
    public ResponseEntity registerRefeicao(@Valid @RequestBody RequestRefeicao requestRefeicao) {
        return refeicaoService.registrarRefeicao(requestRefeicao);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteRefeicao(@PathVariable Integer id) {
        return refeicaoService.deletarRefeicaoPorId(id);
    }
}
