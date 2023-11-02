package com.projeto.produtoseguros.exception;

public class RegistroNaoEncontradoException extends RuntimeException {

    public RegistroNaoEncontradoException(){
        super();
    }

    public RegistroNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
