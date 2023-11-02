package com.projeto.produtoseguros.component.impl;

import com.projeto.produtoseguros.component.CalculadoraDePrecoTarifado;
import com.projeto.produtoseguros.model.Produto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Log4j2
@Component
public class CalculadoraDePrecoTarifadoImpl implements CalculadoraDePrecoTarifado {

    private static final BigDecimal CEM = new BigDecimal(100);

    @Override
    public BigDecimal calcular(Produto produto) {

        if(produto == null){
            throw new IllegalArgumentException("Para calcular o preço tarigado, o produto não pode ser nulo");
        }

        log.info("..: CalculadoraDePrecoTarifadoImpl.calcular");
        var precoBase = produto.getPrecoBase();
        var categoria = produto.getCategoria();

        var percentualIfo = categoria.getIof().divide(CEM);
        var percentualPis = categoria.getPis().divide(CEM);
        var percentualCofins = categoria.getCofins().divide(CEM);

        return precoBase
                .add(precoBase.multiply(percentualIfo))
                .add(precoBase.multiply(percentualPis))
                .add(precoBase.multiply(percentualCofins))
                .setScale(2);
    }
}
