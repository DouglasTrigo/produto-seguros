package com.projeto.produtoseguros.mock;

import com.projeto.produtoseguros.model.Categoria;
import lombok.Getter;

import java.math.BigDecimal;

public class CategoriaMock {

    private CategoriaMock(){}

    public static Categoria mock(CategoriaEnum categoriaEnum){
        return Categoria.builder()
                .descricao(categoriaEnum.name())
                .cofins(categoriaEnum.getPercentualConfins())
                .pis(categoriaEnum.getPercentualPis())
                .iof(categoriaEnum.getPercentualIof())
                .build();
    }

    @Getter
    public enum CategoriaEnum {

        VIDA(1, 2, 3),
        AUTO(5.5f, 4, 1),
        VIAGEM(2, 4, 1),
        RESIDENCIAL(4, 2.5f, 3),
        PATRIMONIAL(5, 3, 2);

        CategoriaEnum(float percentualIof, float percentualPis, float percentualConfins){
            this.percentualIof = new BigDecimal(percentualIof);
            this.percentualPis = new BigDecimal(percentualPis);
            this.percentualConfins = new BigDecimal(percentualConfins);
        }

        private BigDecimal percentualIof;
        private BigDecimal percentualPis;
        private BigDecimal percentualConfins;

    }

}
