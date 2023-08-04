package com.foodminder.FoodMinder.domain.refeicao;

import com.foodminder.FoodMinder.domain.planejamento.Planejamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RefeicaoRepository extends JpaRepository<Refeicao, Integer> {
    //List<Refeicao> findById(Integer id);

}
