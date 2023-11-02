package com.projeto.produtoseguros.component.impl;

import com.projeto.produtoseguros.component.CalculadoraDePrecoTarifado;
import com.projeto.produtoseguros.mock.CategoriaMock;
import com.projeto.produtoseguros.mock.ProdutoMock;
import com.projeto.produtoseguros.model.Produto;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@Tag("unit")
class CalculadoraDePrecoTarifadoImplTest {

    private CalculadoraDePrecoTarifado calculadoraDePrecoTarifado =
            new CalculadoraDePrecoTarifadoImpl();

    @Test
    void deveRetornarOPrecoTarifado(){

        Produto produtoCategoriaSeguroVida =
                ProdutoMock.mock(new BigDecimal("100"), CategoriaMock.CategoriaEnum.VIDA);

        Produto produtoCategoriaSeguroAuto =
                ProdutoMock.mock(new BigDecimal("50"), CategoriaMock.CategoriaEnum.AUTO);

        assertEquals(new BigDecimal("106.00"),
                calculadoraDePrecoTarifado.calcular(produtoCategoriaSeguroVida));
        assertEquals(new BigDecimal("55.25"),
                calculadoraDePrecoTarifado.calcular(produtoCategoriaSeguroAuto));
    }

    @Test
    void deveLancarIllegalArgumentExceptionQuandoOProdutoForNulo(){
        assertThrows(IllegalArgumentException.class, () ->
                calculadoraDePrecoTarifado.calcular(null));
    }

    @Test
    void deveLancarNullPointerExceptionQuandoOProdutoEstiverComAtributosNulos(){
        assertThrows(NullPointerException.class, () ->
                calculadoraDePrecoTarifado.calcular(new Produto()));
    }

}
