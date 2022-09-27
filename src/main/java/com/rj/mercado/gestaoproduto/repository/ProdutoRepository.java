package com.rj.mercado.gestaoproduto.repository;

import com.rj.mercado.gestaoproduto.model.Produto;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("produtoRepository")
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
}