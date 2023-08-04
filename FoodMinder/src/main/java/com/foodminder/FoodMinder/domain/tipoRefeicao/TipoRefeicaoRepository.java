package com.foodminder.FoodMinder.domain.tipoRefeicao;

import com.foodminder.FoodMinder.domain.planejamento.Planejamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoRefeicaoRepository extends JpaRepository<TipoRefeicao, Integer> {
   // List<TipoRefeicao> findById(Integer id);
}
