package com.foodminder.FoodMinder.domain.planejamento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanejamentoRepository extends JpaRepository<Planejamento, Integer> {
    List<Planejamento> findAllById(Integer id);
}