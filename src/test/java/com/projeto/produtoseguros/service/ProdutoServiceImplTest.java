package com.projeto.produtoseguros.service;

import com.projeto.produtoseguros.component.CalculadoraDePrecoTarifado;
import com.projeto.produtoseguros.exception.RegistroNaoEncontradoException;
import com.projeto.produtoseguros.model.Categoria;
import com.projeto.produtoseguros.model.Produto;
import com.projeto.produtoseguros.repository.ProdutoRepository;
import com.projeto.produtoseguros.service.impl.ProdutoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Tag("unit")
class ProdutoServiceImplTest {

    @InjectMocks
    private ProdutoServiceImpl service;

    @Mock
    private ProdutoRepository produtoRepository;
    @Mock
    private CategoriaService categoriaService;
    @Mock
    private CalculadoraDePrecoTarifado calculadoraDePrecoTarifado;

    private Produto produtoMock;

    @BeforeEach
    void beforeEach(){

        produtoMock = mock(Produto.class);
        when(produtoMock.getCategoria()).thenReturn(new Categoria());
        when(produtoMock.getId()).thenReturn(1l);

    }

    @Test
    void deveInserirUmProdutoComSucesso(){

        service.inserir(produtoMock);

        verify(categoriaService, times(1)).buscarPorDescricao(any());
        verify(produtoMock, times(1)).setId(eq(null));
        verify(produtoMock, times(1)).setPrecoTarifado(any());
        verify(produtoMock, times(1)).setCategoria(any());
        verify(produtoRepository, times(1)).save(eq(produtoMock));

    }

    @Test
    void deveLancarRegistroNaoEncontradoExceptionQuandoProdutoNaoExistirDadosQueEhUmUpdate(){

        when(produtoRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(RegistroNaoEncontradoException.class, () ->
                service.atualizar(produtoMock.getId(), produtoMock));

    }

    @Test
    void deveAtualizarRegistroComSucesso(){
        when(produtoRepository.findById(any()))
                .thenReturn(Optional.of(produtoMock));

        service.atualizar(produtoMock.getId(), produtoMock);

        verify(categoriaService, times(1)).buscarPorDescricao(any());
        verify(produtoMock, times(1)).setId(eq(produtoMock.getId()));
        verify(produtoMock, times(1)).setPrecoTarifado(any());
        verify(produtoMock, times(1)).setCategoria(any());
        verify(produtoRepository, times(1)).save(eq(produtoMock));

    }

}
