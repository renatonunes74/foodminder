package com.foodminder.FoodMinder.controllers;

import com.foodminder.FoodMinder.domain.tipoRefeicao.RequestTipoRefeicao;
import com.foodminder.FoodMinder.domain.tipoRefeicao.TipoRefeicao;
import com.foodminder.FoodMinder.domain.tipoRefeicao.TipoRefeicaoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipoRefeicao")
public class TipoRefeicaoController {
    @Autowired
    private TipoRefeicaoRepository repository;
    @GetMapping
    public ResponseEntity getAllTipoRefeicao() {
        return ResponseEntity.ok(repository.findAll());
    }
    @PostMapping
    public ResponseEntity registerTipoRefeicao(@RequestBody @Valid RequestTipoRefeicao data) {
        TipoRefeicao newTipoRefeicao = new TipoRefeicao(data);
        repository.save(newTipoRefeicao);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTipoRefeicao(@PathVariable Integer id){
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new EntityNotFoundException();
        }
    }
}
