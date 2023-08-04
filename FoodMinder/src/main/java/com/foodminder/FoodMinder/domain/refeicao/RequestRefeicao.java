package com.foodminder.FoodMinder.domain.refeicao;

import com.foodminder.FoodMinder.domain.planejamento.Planejamento;
import jakarta.validation.constraints.NotNull;

public record RequestRefeicao(
		Integer id,
		@NotNull
		String nome,
		@NotNull
		String receita
) {
}