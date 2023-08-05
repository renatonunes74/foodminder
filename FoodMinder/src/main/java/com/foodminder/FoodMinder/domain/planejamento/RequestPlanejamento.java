package com.foodminder.FoodMinder.domain.planejamento;

import com.foodminder.FoodMinder.domain.refeicao.Refeicao;
import com.foodminder.FoodMinder.domain.tipoRefeicao.TipoRefeicao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestPlanejamento(
		Integer id,
		@NotBlank(message = "Data é obrigatório")
		String data,
		@NotNull(message = "Tipo de refeição é obrigatório")
		TipoRefeicao tipoRefeicao,
		@NotNull(message = "Refeição é obrigatório")
		Refeicao refeicao
) {
}
