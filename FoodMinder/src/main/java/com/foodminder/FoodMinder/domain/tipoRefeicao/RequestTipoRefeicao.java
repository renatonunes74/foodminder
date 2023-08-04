package com.foodminder.FoodMinder.domain.TipoRefeicao;

import jakarta.validation.constraints.NotNull;

public record RequestTipoRefeicao(
		Integer id,
		@NotNull
		String tipo
) {
}