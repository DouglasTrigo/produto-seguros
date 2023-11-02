package com.projeto.produtoseguros.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        var errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        var apiErrorMessage = new ApiErrorMessage(errors);

        log.warn("..: ExceptionHandlerControllerAdvice.handleMethodArgumentNotValid -  {} ", ex);

        return new ResponseEntity<>(apiErrorMessage, status);
    }

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ResponseEntity<Object> handleUserNotFoundException(
            RegistroNaoEncontradoException exception,
            WebRequest request) {

        log.warn("..: ExceptionHandlerControllerAdvice.handleUserNotFoundException - registro não encontrado {} ",
                exception.getMessage());

        return new ResponseEntity<>(
                new ApiErrorMessage(Arrays.asList(exception.getMessage())),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }
}
