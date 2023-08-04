package com.foodminder.FoodMinder.domain.tipoRefeicao;

import jakarta.persistence.*;
import lombok.*;

@Table(name="tipo_refeicao")
@Entity(name="tipo_refeicao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class TipoRefeicao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tipo;
    public TipoRefeicao(RequestTipoRefeicao requestTipoRefeicao) {
        this.tipo = requestTipoRefeicao.tipo();
    }
}
