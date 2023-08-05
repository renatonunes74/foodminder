package com.foodminder.FoodMinder.services;

import com.foodminder.FoodMinder.domain.planejamento.PlanejamentoRepository;
import com.foodminder.FoodMinder.domain.refeicao.Refeicao;
import com.foodminder.FoodMinder.domain.refeicao.RefeicaoRepository;
import com.foodminder.FoodMinder.domain.refeicao.RequestRefeicao;
import com.foodminder.FoodMinder.exceptions.RecursoNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RefeicaoService {
    @Autowired
    private PlanejamentoRepository planejamentoRepository;
    @Autowired
    private RefeicaoRepository refeicaoRepository;
    public List<Refeicao> obterTodasRefeicoes() {
        return refeicaoRepository.findAll();
    }
    public Refeicao obterRefeicaoPorId(Integer id) {
        return refeicaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Planejamento não encontrado"));
    }
    public Refeicao registrarRefeicao(RequestRefeicao data) {
        Refeicao newRefeicao = new Refeicao(data);
        return(refeicaoRepository.save(newRefeicao));
    }
    public Refeicao deletarRefeicaoPorId(Integer id) {
        Refeicao refeicao = refeicaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Planejamento não encontrado"));
        refeicaoRepository.deleteById(id);
        return refeicao;
    }
}
