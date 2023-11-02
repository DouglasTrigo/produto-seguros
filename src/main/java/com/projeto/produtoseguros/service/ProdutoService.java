package com.projeto.produtoseguros.service;

import com.projeto.produtoseguros.model.Produto;

public interface ProdutoService {

    Produto inserir(Produto produto);
    Produto atualizar(Long id, Produto produto);

}
