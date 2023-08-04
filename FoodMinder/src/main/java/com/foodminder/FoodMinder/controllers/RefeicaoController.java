package com.foodminder.FoodMinder.controllers;

import com.foodminder.FoodMinder.domain.planejamento.Planejamento;
import com.foodminder.FoodMinder.domain.refeicao.Refeicao;
import com.foodminder.FoodMinder.domain.refeicao.RefeicaoRepository;
import com.foodminder.FoodMinder.domain.refeicao.RequestRefeicao;
import com.foodminder.FoodMinder.entities.RefeicaoService;
import com.foodminder.FoodMinder.exceptions.RecursoNaoEncontrado;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Ref;
import java.util.Optional;

@RestController
@RequestMapping("/refeicao")
public class RefeicaoController {
    @Autowired
    private RefeicaoRepository repository;
    @Autowired
    private RefeicaoService refeicaoService;
    @GetMapping
    public ResponseEntity getAllRefeicao() {
      return ResponseEntity.ok(refeicaoService.obterTodasRefeicoes());
    }
    @GetMapping("/{id}")
    public ResponseEntity getRefeicaoById(@PathVariable Integer id) {
            try {
                Refeicao refeicao = refeicaoService.obterRefeicaoPorId(id);
                return ResponseEntity.ok(refeicao);
            } catch (RecursoNaoEncontrado e) {
                return ResponseEntity.notFound().build();
            }
    }
    @PostMapping
    public ResponseEntity registerRefeicao(@Valid @RequestBody RequestRefeicao data) {
        Refeicao newRefeicao = new Refeicao(data);
        repository.save(newRefeicao);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteRefeicao(@PathVariable Integer id) {
        try {
            Refeicao refeicao = refeicaoService.deletarRefeicaoPorId(id);
            return ResponseEntity.noContent().build();
        } catch (RecursoNaoEncontrado e) {
            return ResponseEntity.notFound().build();
        }
    }
}
