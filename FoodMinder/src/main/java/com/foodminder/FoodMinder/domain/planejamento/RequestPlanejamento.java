package com.foodminder.FoodMinder.domain.planejamento;

import com.foodminder.FoodMinder.domain.TipoRefeicao.TipoRefeicao;
import com.foodminder.FoodMinder.domain.refeicoes.Refeicoes;
import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestPlanejamento(
		String id,
		@NotNull
		String data,
		@NotNull
		TipoRefeicao tipoRefeicao, // Adicionando o atributo para representar o tipo de refeição
		@NotNull
		List<Refeicoes> refeicoes // Adicionando o atributo para representar as refeições associadas ao planejamento
) {
}