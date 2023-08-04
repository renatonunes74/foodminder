package com.foodminder.FoodMinder.domain.tipoRefeicao;

import jakarta.validation.constraints.NotNull;

public record RequestTipoRefeicao(
		Integer id,
		@NotNull
		String tipo
) {
}