package com.projeto.produtoseguros.service.impl;

import com.projeto.produtoseguros.exception.RegistroNaoEncontradoException;
import com.projeto.produtoseguros.model.Categoria;
import com.projeto.produtoseguros.repository.CategoriaRepository;
import com.projeto.produtoseguros.service.CategoriaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class CategoriaServiceImpl implements CategoriaService {

    private CategoriaRepository categoriaRepository;

    @Override
    public Categoria buscarPorDescricao(String descricao) {
        log.info("CategoriaServiceImpl.buscarPorDescricao {} ", descricao);
        return categoriaRepository.findFirstByDescricao(descricao).orElseThrow(() ->
                new RegistroNaoEncontradoException("A categoria não está cadastrada"));
    }
}
