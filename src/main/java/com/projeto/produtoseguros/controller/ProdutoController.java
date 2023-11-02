package com.projeto.produtoseguros.controller;

import com.projeto.produtoseguros.dto.ProdutoDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProdutoController {

    ResponseEntity<ProdutoDto> criar(@RequestBody @Valid ProdutoDto produtoDto);

}
