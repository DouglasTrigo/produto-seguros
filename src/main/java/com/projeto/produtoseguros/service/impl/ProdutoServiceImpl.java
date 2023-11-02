package com.projeto.produtoseguros.service.impl;

import com.projeto.produtoseguros.component.CalculadoraDePrecoTarifado;
import com.projeto.produtoseguros.exception.RegistroNaoEncontradoException;
import com.projeto.produtoseguros.model.Produto;
import com.projeto.produtoseguros.repository.ProdutoRepository;
import com.projeto.produtoseguros.service.CategoriaService;
import com.projeto.produtoseguros.service.ProdutoService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Log4j2
@Service
@AllArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private ProdutoRepository produtoRepository;
    private CategoriaService categoriaService;
    private CalculadoraDePrecoTarifado calculadoraDePrecoTarifado;

    @Override
    public Produto inserir(Produto produto){
        log.info("..: ProdutoServiceImpl.inserir {} ", produto);
        produto.setCategoria(categoriaService.buscarPorDescricao(produto.getCategoria().getDescricao()));
        produto.setId(null);
        produto.setPrecoTarifado(getPrecoTarifado(produto));

        log.info("..: ProdutoServiceImpl.inserir produto que será inserido {} ", produto);
        return produtoRepository.save(produto);
    }

    @Override
    public Produto atualizar(Long id, Produto produto) {

        log.info("..: ProdutoServiceImpl.atualizar {} ", produto);
        validaSeProdutoExiste(id);
        produto.setCategoria(categoriaService.buscarPorDescricao(produto.getCategoria().getDescricao()));
        produto.setId(id);
        produto.setPrecoTarifado(getPrecoTarifado(produto));

        log.info("..: ProdutoServiceImpl.atualizar que será atualizado {} ", produto);
        return produtoRepository.save(produto);
    }

    private BigDecimal getPrecoTarifado(Produto produto){
        return calculadoraDePrecoTarifado.calcular(produto);
    }

    private void validaSeProdutoExiste(Long id){
        produtoRepository.findById(id).orElseThrow(() ->
                new RegistroNaoEncontradoException("O produto não está cadastrado"));
    }
}
