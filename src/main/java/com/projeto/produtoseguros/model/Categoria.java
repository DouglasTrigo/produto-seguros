package com.projeto.produtoseguros.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String descricao;
    private BigDecimal iof;
    private BigDecimal pis;
    private BigDecimal cofins;

}

/*import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum Categoria {

    VIDA(1, 2, 3),
    AUTO(5.5f, 4, 1),
    VIAGEM(2, 4, 1),
    RESIDENCIAL(4, 2.5f, 3),
    PATRIMONIAL(5, 3, 2);

    Categoria(float percentualIof, float percentualPis, float percentualConfins){
        this.percentualIof = new BigDecimal(percentualIof);
        this.percentualPis = new BigDecimal(percentualPis);
        this.percentualConfins = new BigDecimal(percentualConfins);
    }

    private BigDecimal percentualIof;
    private BigDecimal percentualPis;
    private BigDecimal percentualConfins;

}*/