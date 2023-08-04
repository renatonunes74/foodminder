package com.foodminder.FoodMinder.domain.planejamento;

import com.foodminder.FoodMinder.domain.refeicao.Refeicao;
import com.foodminder.FoodMinder.domain.tipoRefeicao.TipoRefeicao;

import jakarta.validation.constraints.NotNull;

import java.sql.Ref;

public record RequestPlanejamento(
		Integer id,
		@NotNull
		String data,
		@NotNull
		TipoRefeicao tipoRefeicao, // Adicionando o atributo para representar o tipo de refeição
		@NotNull
		Refeicao refeicao // Adicionando o atributo para representar as refeições associadas ao planejamento
) {
}
