package com.foodminder.FoodMinder.domain.planejamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanejamentoRepository extends JpaRepository<Planejamento, Integer> {}