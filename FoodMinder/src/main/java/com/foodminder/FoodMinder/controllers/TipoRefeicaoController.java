package com.foodminder.FoodMinder.controllers;

import com.foodminder.FoodMinder.domain.tipoRefeicao.RequestTipoRefeicao;
import com.foodminder.FoodMinder.services.TipoRefeicaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipoRefeicao")
public class TipoRefeicaoController {
    @Autowired
    private TipoRefeicaoService tipoRefeicaoService;
    @GetMapping
    public ResponseEntity getAllTipoRefeicao() {
        return tipoRefeicaoService.obterTodosTipoRefeicao();
    }
    @GetMapping("/{id}")
    public ResponseEntity getTipoRefeicaoById(@PathVariable Integer id) {
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
