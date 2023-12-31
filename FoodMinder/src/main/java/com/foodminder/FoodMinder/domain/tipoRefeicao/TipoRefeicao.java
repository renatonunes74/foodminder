package com.foodminder.FoodMinder.domain.tipoRefeicao;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Builder
@Table(name="tipo_refeicao")
@Entity(name="tipo_refeicao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoRefeicao implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tipo;
    public TipoRefeicao(RequestTipoRefeicao requestTipoRefeicao) {
        this.tipo = requestTipoRefeicao.tipo();
    }
}
