package com.foodminder.FoodMinder.domain.refeicoes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.foodminder.FoodMinder.domain.TipoRefeicao.TipoRefeicao;
import com.foodminder.FoodMinder.domain.planejamento.Planejamento;
import com.foodminder.FoodMinder.domain.planejamento.RequestPlanejamento;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "refeicoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Refeicoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String receita;

    @ManyToOne
    @JoinColumn(name = "planejamento_id")
    private Planejamento planejamento;
    public Refeicoes(RequestRefeicoes requestRefeicoes) {
        this.nome = requestRefeicoes.nome();
        this.receita = requestRefeicoes.receita();
    }
}