package com.projeto.produtoseguros.controller.impl;

import com.projeto.produtoseguros.controller.ProdutoController;
import com.projeto.produtoseguros.dto.ProdutoDto;
import com.projeto.produtoseguros.mapper.ProdutoMapper;
import com.projeto.produtoseguros.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/v1/produtos")
public class ProdutoControllerImpl implements ProdutoController {

    private ProdutoMapper produtoMapper;
    private ProdutoService produtoService;

    @Override
    @PostMapping
    public ResponseEntity<ProdutoDto> criar(@RequestBody @Valid ProdutoDto produtoDto){
        log.info("..: ProdutoControllerImpl.criar");
        var produtoInserido = produtoService.inserir(produtoMapper.toProduto(produtoDto));
        log.info("..: ProdutoControllerImpl.criar - produto inserido com sucesso!");
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoMapper.toProdutoDto(produtoInserido));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id, @RequestBody @Valid ProdutoDto produtoDto){
        log.info("..: ProdutoControllerImpl.atualizar");
        var produtoAtualizado = produtoService.atualizar(id, produtoMapper.toProduto(produtoDto));
        log.info("..: ProdutoControllerImpl.atualizar - produto atualizado com sucesso!");
        return ResponseEntity.status(HttpStatus.OK).body(produtoMapper.toProdutoDto(produtoAtualizado));

    }
}
