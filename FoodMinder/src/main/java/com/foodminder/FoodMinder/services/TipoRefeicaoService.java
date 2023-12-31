package com.foodminder.FoodMinder.services;

import com.foodminder.FoodMinder.domain.tipoRefeicao.RequestTipoRefeicao;
import com.foodminder.FoodMinder.domain.tipoRefeicao.TipoRefeicao;
import com.foodminder.FoodMinder.domain.tipoRefeicao.TipoRefeicaoRepository;
import com.foodminder.FoodMinder.exceptions.RecursoNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoRefeicaoService {
    @Autowired
    private TipoRefeicaoRepository tipoRefeicaoRepository;
    public ResponseEntity obterTodosTipoRefeicao() {
        return ResponseEntity.ok(tipoRefeicaoRepository.findAll());
    }
    public TipoRefeicao obterTipoRefeicaoPorId(Integer id) {
        Optional<TipoRefeicao> tipoRefeicaoOptional = tipoRefeicaoRepository.findById(id);
        if (tipoRefeicaoOptional.isPresent()) {
            TipoRefeicao tipoRefeicao = tipoRefeicaoOptional.get();
            return tipoRefeicao;
        } else {
            throw new RecursoNaoEncontrado("TipoRefeicao not found with id: " + id);
        }
    }
    public ResponseEntity registrarTipoRefeicao(RequestTipoRefeicao requestTipoRefeicao) {
            TipoRefeicao tipoRefeicao = new TipoRefeicao(requestTipoRefeicao);
            tipoRefeicaoRepository.save(tipoRefeicao);
            return ResponseEntity.status(HttpStatus.CREATED).body(tipoRefeicao);
    }
    public ResponseEntity deletarTipoRefeicaoPorId(Integer id) {
        Optional<TipoRefeicao> tipoRefeicaoOptional = tipoRefeicaoRepository.findById(id);
        if (tipoRefeicaoOptional.isPresent()) {
            tipoRefeicaoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
