package com.foodminder.FoodMinder.domain.refeicao;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Builder
@Entity
@Table(name = "refeicao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Refeicao implements Serializable {
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