package com.foodminder.FoodMinder.domain.planejamento;

import com.foodminder.FoodMinder.domain.refeicao.Refeicao;
import com.foodminder.FoodMinder.domain.tipoRefeicao.TipoRefeicao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Entity
@Table(name = "planejamento")
@Entity(name = "planejamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Planejamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String data;

	@ManyToOne
	@JoinColumn(name = "tipo_refeicao_id")
	private TipoRefeicao tipoRefeicao;

	@ManyToOne
	@JoinColumn(name = "refeicao_id")
	private Refeicao refeicao;
	public Planejamento(RequestPlanejamento requestPlanejamento) {
		this.data = requestPlanejamento.data();
		this.tipoRefeicao = requestPlanejamento.tipoRefeicao();
		this.refeicao = requestPlanejamento.refeicao();
	}
}
