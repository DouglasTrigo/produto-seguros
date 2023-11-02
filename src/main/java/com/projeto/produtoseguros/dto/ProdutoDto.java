package com.projeto.produtoseguros.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProdutoDto(

        Long id,

        @NotBlank(message = "'nome' é obrigatório")
        @Size(min=3, max = 50, message = "O 'nome' deve ter entre 3 e 50 caracteres")
        String nome,

        @NotBlank(message = "'categoria' é obrigatório")
        String categoria,

        @NotNull(message = "'preco_base' é obrigatório")
        @JsonProperty("preco_base")
        BigDecimal precoBase,

        @JsonProperty("preco_tarifado")
        BigDecimal precoTarifado

) {}
