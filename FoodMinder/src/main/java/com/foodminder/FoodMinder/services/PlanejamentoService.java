package com.foodminder.FoodMinder.services;

import com.foodminder.FoodMinder.domain.planejamento.Planejamento;
import com.foodminder.FoodMinder.domain.planejamento.PlanejamentoRepository;
import com.foodminder.FoodMinder.domain.planejamento.RequestPlanejamento;
import com.foodminder.FoodMinder.domain.refeicao.Refeicao;
import com.foodminder.FoodMinder.domain.refeicao.RefeicaoRepository;
import com.foodminder.FoodMinder.domain.tipoRefeicao.TipoRefeicao;
import com.foodminder.FoodMinder.domain.tipoRefeicao.TipoRefeicaoRepository;
import com.foodminder.FoodMinder.exceptions.RecursoNaoEncontrado;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PlanejamentoService {
    @Autowired
    private PlanejamentoRepository planejamentoRepository;
    @Autowired
    private RefeicaoRepository refeicaoRepository;
    @Autowired
    private TipoRefeicaoRepository tipoRefeicaoRepository;
    public List<Planejamento> obterTodosPlanejamentos() {
        return planejamentoRepository.findAll();
    }
    public Planejamento obterPlanejamentoPorId(Integer id) {
        return planejamentoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Planejamento não encontrado"));
    }
    public Planejamento registrarPlanejamento(RequestPlanejamento requestData) {
        Refeicao refeicao = refeicaoRepository.findById(requestData.refeicao().getId()).orElseThrow(EntityNotFoundException::new);
        TipoRefeicao tipoRefeicao = tipoRefeicaoRepository.findById(requestData.tipoRefeicao().getId()).orElseThrow(EntityNotFoundException::new);
        Planejamento newPlanejamento = new Planejamento();
        newPlanejamento.setData(requestData.data());
        newPlanejamento.setRefeicao(refeicao);
        newPlanejamento.setTipoRefeicao(tipoRefeicao);
        return planejamentoRepository.save(newPlanejamento);
    }
    public Planejamento deletarPlanejamentoPorId(Integer id) {
        Planejamento planejamento = planejamentoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Planejamento não encontrado"));
        planejamentoRepository.deleteById(id);
        return planejamento;
    }

}
