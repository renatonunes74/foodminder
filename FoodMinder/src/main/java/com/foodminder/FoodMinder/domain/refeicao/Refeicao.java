package com.foodminder.FoodMinder.domain.refeicao;

import com.foodminder.FoodMinder.domain.planejamento.Planejamento;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "refeicao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Refeicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String receita;

    public Refeicao(RequestRefeicao requestRefeicao) {
        this.nome = requestRefeicao.nome();
        this.receita = requestRefeicao.receita();
    }
}