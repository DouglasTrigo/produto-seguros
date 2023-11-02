package com.projeto.produtoseguros.repository;

import com.projeto.produtoseguros.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findFirstByDescricao(String descricao);

}
