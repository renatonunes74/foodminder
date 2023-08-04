package com.foodminder.FoodMinder.domain.refeicoes;

import com.foodminder.FoodMinder.domain.TipoRefeicao.TipoRefeicao;
import com.foodminder.FoodMinder.domain.planejamento.Planejamento;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RequestRefeicoes(
		Integer id,
		@NotNull
		String nome,
		@NotNull
		String receita,
		@NotNull
		Planejamento planejamento // Adicionando o atributo para representar o tipo de refeição
) {
}