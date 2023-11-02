package com.projeto.produtoseguros.exception;

import java.util.List;

public class ApiErrorMessage {

    private List<String> errors;

    public ApiErrorMessage(List<String> errors) {
        this.errors = errors;
    }
}