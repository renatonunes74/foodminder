package com.foodminder.FoodMinder.controllers;

import com.foodminder.FoodMinder.domain.tipoRefeicao.RequestTipoRefeicao;
import com.foodminder.FoodMinder.domain.tipoRefeicao.TipoRefeicao;
import com.foodminder.FoodMinder.domain.tipoRefeicao.TipoRefeicaoRepository;
import com.foodminder.FoodMinder.services.TipoRefeicaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipoRefeicao")
public class TipoRefeicaoController {
    @Autowired
    private TipoRefeicaoService tipoRefeicaoService;
    @Autowired
    private TipoRefeicaoRepository tipoRefeicaoRepository;
    @GetMapping
    public ResponseEntity getAllTipoRefeicao() {
        return tipoRefeicaoService.obterTodosTipoRefeicao();
    }
    @GetMapping("/{id}")
    @Cacheable(value = "tipoRefeicao", key="#id")
    public TipoRefeicao getTipoRefeicaoById(@PathVariable Integer id) {
        return tipoRefeicaoService.obterTipoRefeicaoPorId(id);
    }
    @PostMapping
    public ResponseEntity registerTipoRefeicao(@RequestBody @Valid RequestTipoRefeicao data) {
       return tipoRefeicaoService.registrarTipoRefeicao(data);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTipoRefeicao(@PathVariable Integer id){
        return tipoRefeicaoService.deletarTipoRefeicaoPorId(id);
    }
}
