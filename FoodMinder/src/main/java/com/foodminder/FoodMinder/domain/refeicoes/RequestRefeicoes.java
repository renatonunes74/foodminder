package com.foodminder.FoodMinder.domain.refeicoes;

import com.foodminder.FoodMinder.domain.TipoRefeicao.TipoRefeicao;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RequestRefeicoes(
		String id,
		@NotNull
		String nome,
		@NotNull
		String receita,
		@NotNull
		TipoRefeicao tipoRefeicao // Adicionando o atributo para representar o tipo de refeição
) {
}