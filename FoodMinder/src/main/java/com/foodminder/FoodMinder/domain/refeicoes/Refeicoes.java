package com.foodminder.FoodMinder.domain.refeicoes;

import com.foodminder.FoodMinder.domain.planejamento.Planejamento;
import jakarta.persistence.*;
import lombok.*;

@Table(name="refeicoes")
@Entity(name="refeicoes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Refeicoes {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private String receita;
    @ManyToOne
    @JoinColumn(name = "planejamento_id")
    private Planejamento planejamento;
}
