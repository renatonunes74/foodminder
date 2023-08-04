package com.foodminder.FoodMinder.controllers;

import com.foodminder.FoodMinder.domain.refeicao.Refeicao;
import com.foodminder.FoodMinder.domain.refeicao.RefeicaoRepository;
import com.foodminder.FoodMinder.domain.refeicao.RequestRefeicao;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/refeicao")
public class RefeicaoController {
    @Autowired
    private RefeicaoRepository repository;
    @GetMapping
    public ResponseEntity getAllRefeicao() {
      return(ResponseEntity.ok(repository.findAll()));
    }
    @PostMapping
    public ResponseEntity registerRefeicao(@Valid @RequestBody RequestRefeicao data) {
        Refeicao newRefeicao = new Refeicao(data);
        repository.save(newRefeicao);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteRefeicao(@PathVariable Integer id){
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new EntityNotFoundException();
        }
    }
}
