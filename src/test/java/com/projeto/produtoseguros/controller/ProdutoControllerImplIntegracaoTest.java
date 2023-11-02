package com.projeto.produtoseguros.controller;

import com.projeto.produtoseguros.controller.impl.ProdutoControllerImpl;
import com.projeto.produtoseguros.exception.RegistroNaoEncontradoException;
import com.projeto.produtoseguros.mock.ProdutoMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class ProdutoControllerImplIntegracaoTest {

    @Autowired
    private ProdutoControllerImpl controller;

    @Test
    void deveCriarUmProdutoComPrecoTarifado(){

        var produtoRequest = ProdutoMock.produtoDtoBuilder()
                .categoria("VIDA")
                .precoBase(new BigDecimal("100"))
                .nome("Teste")
                .build();

        var responseEntity = controller.criar(produtoRequest);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCode().value());

        var produtoInserido = responseEntity.getBody();

        assertNotNull(produtoInserido);
        assertNotNull(produtoInserido.id());
        assertEquals(106.00, produtoInserido.precoTarifado().doubleValue());

    }

    @Test
    void deveLancarErroQuandoForCategoriaInvalida(){

        var produtoRequest = ProdutoMock.produtoDtoBuilder()
                .categoria("OUTRA")
                .precoBase(new BigDecimal("100"))
                .nome("Teste")
                .build();

        assertThrows(RegistroNaoEncontradoException.class, () -> controller.criar(produtoRequest));

    }

    @Test
    void deveAtualizarUmProduto(){
        var produtoRequest = ProdutoMock.produtoDtoBuilder()
                .categoria("VIDA")
                .precoBase(new BigDecimal("100"))
                .nome("Teste")
                .build();

        var responseEntity = controller.criar(produtoRequest);
        var produtoInserido = responseEntity.getBody();

        var protudoParaAtualizar = ProdutoMock.produtoDtoBuilder()
                .id(produtoInserido.id())
                .categoria(produtoInserido.categoria())
                .nome("Outro nome")
                .precoBase(new BigDecimal(200))
                .build();

        var reponseEntityDaAtualizacao =
                controller.atualizar(protudoParaAtualizar.id(), protudoParaAtualizar);

        var produtoAtualizado = reponseEntityDaAtualizacao.getBody();

        assertNotNull(reponseEntityDaAtualizacao);

        assertEquals(protudoParaAtualizar.id(), produtoAtualizado.id());
        assertEquals(protudoParaAtualizar.nome(), produtoAtualizado.nome());

        assertNotEquals(produtoAtualizado.precoBase(), produtoInserido.precoBase());
        assertNotEquals(produtoAtualizado.precoTarifado(), produtoInserido.precoTarifado());

    }

}
