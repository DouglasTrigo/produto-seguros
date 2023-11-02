package com.projeto.produtoseguros.component;

import com.projeto.produtoseguros.model.Produto;

import java.math.BigDecimal;

public interface CalculadoraDePrecoTarifado {

    BigDecimal calcular(Produto produto);

}
