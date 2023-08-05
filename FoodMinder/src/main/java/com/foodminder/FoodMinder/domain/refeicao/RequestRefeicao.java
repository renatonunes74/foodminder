package com.foodminder.FoodMinder.domain.refeicao;

import jakarta.validation.constraints.NotBlank;

public record RequestRefeicao(
		Integer id,
		@NotBlank(message = "Nome é obrigatório")
		String nome,
		@NotBlank(message = "Receita é obrigatório")
		String receita
) {
}