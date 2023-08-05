package com.foodminder.FoodMinder.domain.tipoRefeicao;

import jakarta.validation.constraints.NotBlank;

public record RequestTipoRefeicao(
		Integer id,
		@NotBlank(message = "Tipo de refeição é obrigatório")
		String tipo
) {
}