package com.fatec.projeto1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.projeto1.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
