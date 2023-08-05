package com.foodminder.FoodMinder.services;

import com.foodminder.FoodMinder.domain.refeicao.Refeicao;
import com.foodminder.FoodMinder.domain.refeicao.RefeicaoRepository;
import com.foodminder.FoodMinder.domain.refeicao.RequestRefeicao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class RefeicaoService {
    @Autowired
    private RefeicaoRepository refeicaoRepository;
    public ResponseEntity obterTodasRefeicoes() {
        return ResponseEntity.ok(refeicaoRepository.findAll());
    }
    public ResponseEntity obterRefeicaoPorId(Integer id) {
        Optional<Refeicao> refeicaoOptional = refeicaoRepository.findById(id);
        if (refeicaoOptional.isPresent()) {
            Refeicao refeicao = refeicaoOptional.get();
            return ResponseEntity.ok(refeicao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity registrarRefeicao(RequestRefeicao requestRefeicao) {
        Refeicao refeicao = new Refeicao(requestRefeicao);
        refeicaoRepository.save(refeicao);
        return ResponseEntity.status(HttpStatus.CREATED).body(refeicao);
    }
    public ResponseEntity deletarRefeicaoPorId(Integer id) {
        Optional<Refeicao> refeicaoOptional = refeicaoRepository.findById(id);
        if (refeicaoOptional.isPresent()) {
            refeicaoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
