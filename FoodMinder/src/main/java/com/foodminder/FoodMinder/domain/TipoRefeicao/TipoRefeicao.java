package com.foodminder.FoodMinder.domain.TipoRefeicao;

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
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String tipo;
}
