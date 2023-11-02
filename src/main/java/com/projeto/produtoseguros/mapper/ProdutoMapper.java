package com.projeto.produtoseguros.mapper;

import com.projeto.produtoseguros.dto.ProdutoDto;
import com.projeto.produtoseguros.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    @Mapping(target = "categoria.descricao", source = "categoria")
    Produto toProduto(ProdutoDto produtoDto);

    @Mapping(target = "categoria", source = "categoria.descricao")
    ProdutoDto toProdutoDto(Produto produto);
}
