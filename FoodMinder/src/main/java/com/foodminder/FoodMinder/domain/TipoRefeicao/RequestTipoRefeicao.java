package com.foodminder.FoodMinder.domain.TipoRefeicao;

import com.foodminder.FoodMinder.domain.refeicoes.Refeicoes;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RequestTipoRefeicao(
		String id,
		@NotNull
		String tipo
) {
}