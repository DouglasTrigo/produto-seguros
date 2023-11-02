package com.projeto.produtoseguros.mapper;

import com.projeto.produtoseguros.dto.ProdutoDto;
import com.projeto.produtoseguros.model.Categoria;
import com.projeto.produtoseguros.model.Produto;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-01T21:13:54-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class ProdutoMapperImpl implements ProdutoMapper {

    @Override
    public Produto toProduto(ProdutoDto produtoDto) {
        if ( produtoDto == null ) {
            return null;
        }

        Produto.ProdutoBuilder produto = Produto.builder();

        produto.categoria( produtoDtoToCategoria( produtoDto ) );
        produto.id( produtoDto.id() );
        produto.nome( produtoDto.nome() );
        produto.precoBase( produtoDto.precoBase() );
        produto.precoTarifado( produtoDto.precoTarifado() );

        return produto.build();
    }

    @Override
    public ProdutoDto toProdutoDto(Produto produto) {
        if ( produto == null ) {
            return null;
        }

        String categoria = null;
        Long id = null;
        String nome = null;
        BigDecimal precoBase = null;
        BigDecimal precoTarifado = null;

        categoria = produtoCategoriaDescricao( produto );
        id = produto.getId();
        nome = produto.getNome();
        precoBase = produto.getPrecoBase();
        precoTarifado = produto.getPrecoTarifado();

        ProdutoDto produtoDto = new ProdutoDto( id, nome, categoria, precoBase, precoTarifado );

        return produtoDto;
    }

    protected Categoria produtoDtoToCategoria(ProdutoDto produtoDto) {
        if ( produtoDto == null ) {
            return null;
        }

        Categoria.CategoriaBuilder categoria = Categoria.builder();

        categoria.descricao( produtoDto.categoria() );

        return categoria.build();
    }

    private String produtoCategoriaDescricao(Produto produto) {
        if ( produto == null ) {
            return null;
        }
        Categoria categoria = produto.getCategoria();
        if ( categoria == null ) {
            return null;
        }
        String descricao = categoria.getDescricao();
        if ( descricao == null ) {
            return null;
        }
        return descricao;
    }
}
