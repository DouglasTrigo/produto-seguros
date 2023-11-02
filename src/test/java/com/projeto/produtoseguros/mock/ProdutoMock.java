package com.projeto.produtoseguros.mock;

import com.projeto.produtoseguros.dto.ProdutoDto;
import com.projeto.produtoseguros.model.Produto;

import java.math.BigDecimal;

public class ProdutoMock {

    private ProdutoMock(){}

    public static Produto mock(BigDecimal precoBase, CategoriaMock.CategoriaEnum categoriaEnum){
        return Produto.builder()
                .id(1l)
                .categoria(CategoriaMock.mock(categoriaEnum))
                .nome("Teste")
                .precoBase(precoBase)
                .build();

    }

    public static ProdutoDtoBuilder produtoDtoBuilder(){
        return new ProdutoDtoBuilder();
    }

    public static class ProdutoDtoBuilder {

        private Long id;
        private String nome;
        private String categoria;
        private BigDecimal precoBase;
        private BigDecimal precoTarifado;

        public ProdutoDtoBuilder id(Long id){
            this.id = id;
            return this;
        }

        public ProdutoDtoBuilder nome(String nome){
            this.nome = nome;
            return this;
        }

        public ProdutoDtoBuilder categoria(String categoria){
            this.categoria = categoria;
            return this;
        }

        public ProdutoDtoBuilder precoBase(BigDecimal precoBase){
            this.precoBase = precoBase;
            return this;
        }

        public ProdutoDtoBuilder precoTarifado(BigDecimal precoTarifado){
            this.precoTarifado = precoTarifado;
            return this;
        }

        public ProdutoDto build(){
            return new ProdutoDto(id, nome, categoria, precoBase, precoTarifado);
        }
    }

}
