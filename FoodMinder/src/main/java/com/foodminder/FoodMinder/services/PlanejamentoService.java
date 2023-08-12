package com.foodminder.FoodMinder.services;

import com.foodminder.FoodMinder.domain.planejamento.Planejamento;
import com.foodminder.FoodMinder.domain.planejamento.PlanejamentoRepository;
import com.foodminder.FoodMinder.domain.planejamento.RequestPlanejamento;
import com.foodminder.FoodMinder.domain.rabbitmq.constantes.RabbitMQConstantes;
import com.foodminder.FoodMinder.domain.refeicao.Refeicao;
import com.foodminder.FoodMinder.domain.refeicao.RefeicaoRepository;
import com.foodminder.FoodMinder.domain.tipoRefeicao.TipoRefeicao;
import com.foodminder.FoodMinder.domain.tipoRefeicao.TipoRefeicaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PlanejamentoService {
    private RequestPlanejamento requestPlanejamento;
    @Autowired
    private RabbitMQService rabbitMQService;
    @Autowired
    private PlanejamentoRepository planejamentoRepository;
    @Autowired
    private RefeicaoRepository refeicaoRepository;
    @Autowired
    private TipoRefeicaoRepository tipoRefeicaoRepository;
    public ResponseEntity obterTodosPlanejamento() {
        this.rabbitMQService.enviaMensagem(RabbitMQConstantes.FILA_PLANEJAMENTO, planejamentoRepository.findAll());
        return ResponseEntity.ok(planejamentoRepository.findAll());
    }
    public ResponseEntity obterPlanejamentoPorId(Integer id) {
        Optional<Planejamento> planejamentoOptional = planejamentoRepository.findById(id);
        if (planejamentoOptional.isPresent()) {
            Planejamento planejamento = planejamentoOptional.get();
            return ResponseEntity.ok(planejamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity registrarPlanejamento(RequestPlanejamento requestPlanejamento) {
        Refeicao refeicao = refeicaoRepository.findById(requestPlanejamento.refeicao().getId()).orElseThrow(EntityNotFoundException::new);
        TipoRefeicao tipoRefeicao = tipoRefeicaoRepository.findById(requestPlanejamento.tipoRefeicao().getId()).orElseThrow(EntityNotFoundException::new);
        Planejamento planejamento = new Planejamento();
        planejamento.setData(requestPlanejamento.data());
        planejamento.setRefeicao(refeicao);
        planejamento.setTipoRefeicao(tipoRefeicao);
        planejamentoRepository.save(planejamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(planejamento);
    }
    public ResponseEntity deletarPlanejamentoPorId(Integer id) {
        Optional<Planejamento> planejamentoOptional = planejamentoRepository.findById(id);
        if (planejamentoOptional.isPresent()) {
            planejamentoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
