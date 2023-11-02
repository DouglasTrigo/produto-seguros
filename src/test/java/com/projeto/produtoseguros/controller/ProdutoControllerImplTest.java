package com.projeto.produtoseguros.controller;

import com.projeto.produtoseguros.controller.impl.ProdutoControllerImpl;
import com.projeto.produtoseguros.dto.ProdutoDto;
import com.projeto.produtoseguros.mapper.ProdutoMapper;
import com.projeto.produtoseguros.mock.ProdutoMock;
import com.projeto.produtoseguros.service.ProdutoService;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Tag("unit")
class ProdutoControllerImplTest {

    @InjectMocks
    private ProdutoControllerImpl controller;

    @Mock
    private ProdutoMapper produtoMapper;

    @Mock
    private ProdutoService produtoService;

    private ProdutoDto produtoDtoResponse;

    private Validator validator;

    @BeforeEach
    void beforeEach(){

        var factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        produtoDtoResponse = ProdutoMock.produtoDtoBuilder()
                .id(1l)
                .nome("Teste")
                .categoria("Vida")
                .precoBase(new BigDecimal(100))
                .precoTarifado(new BigDecimal(106))
                .build();

        when(produtoMapper.toProdutoDto(any()))
                .thenReturn(produtoDtoResponse);

    }

    @Test
    void deveCriarUmProdutoComSucesso(){

        var produtoDtoRequest =
                ProdutoMock.produtoDtoBuilder()
                                .nome("Teste")
                                .categoria("Vida")
                                .precoBase(new BigDecimal(100))
                                .build();

        var response = controller.criar(produtoDtoRequest);

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode().value());
        assertNotNull(response.getBody());

    }

    @Test
    void deveAtualizarUmProdutoComSucesso(){

        var produtoDtoRequest =
                ProdutoMock.produtoDtoBuilder()
                        .id(1l)
                        .nome("Teste")
                        .categoria("Vida")
                        .precoBase(new BigDecimal(100))
                        .precoTarifado(new BigDecimal(106))
                        .build();

        var response = controller.atualizar(produtoDtoRequest.id(), produtoDtoRequest);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertNotNull(response.getBody());

    }

    @Test
    void deveRetornarQueARequisicaoEhValida(){

        var produtoDtoValido = ProdutoMock.produtoDtoBuilder()
                .nome("Teste")
                .categoria("Vida")
                .precoBase(new BigDecimal(100))
                .build();

        var violations = validator.validate(produtoDtoValido);
        assertTrue(violations.isEmpty());

    }

    @ParameterizedTest
    @MethodSource("produtosInvalidos")
    void deveRetornarQueARequisicaoNaoEhValida(ProdutoDto produtoDtoInvalido){

        var violations = validator.validate(produtoDtoInvalido);
        assertFalse(violations.isEmpty());

    }

    protected static Stream<Arguments> produtosInvalidos(){
        return Stream.of(
                Arguments.of(ProdutoMock.produtoDtoBuilder().build()),
                Arguments.of(ProdutoMock.produtoDtoBuilder().nome("Teste").build()),
                Arguments.of(ProdutoMock.produtoDtoBuilder().nome("Teste").categoria("VIDA").build()),
                Arguments.of(ProdutoMock.produtoDtoBuilder().nome("Teste").categoria("VIDA").precoTarifado(BigDecimal.ZERO).build())
        );
    }

}
