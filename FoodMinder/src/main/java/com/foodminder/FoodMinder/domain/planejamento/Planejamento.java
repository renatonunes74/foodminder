package com.foodminder.FoodMinder.domain.planejamento;

import jakarta.persistence.*;
import lombok.*;
import com.foodminder.FoodMinder.domain.refeicoes.Refeicoes;
import com.foodminder.FoodMinder.domain.TipoRefeicao.TipoRefeicao;
import java.util.List;

@Table(name="planejamento")
@Entity(name="planejamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Planejamento {
	@Id @GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String data;
	@ManyToOne
	@JoinColumn(name = "tipo_refeicao_id")
	private TipoRefeicao tipoRefeicao;
	@OneToMany(mappedBy = "planejamento", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Refeicoes> refeicoes;
}
