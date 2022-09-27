package com.rj.mercado.gestaoproduto.service;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

import com.rj.mercado.gestaoproduto.model.Produto;
import com.rj.mercado.gestaoproduto.repository.ProdutoRepository;


@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto save(Produto produto) {
        return (Produto)this.produtoRepository.save(produto);
    }

    public List<Produto> findAll() {
        return this.produtoRepository.findAll();
    }

    public Produto findById(UUID id) {
        return (Produto)this.produtoRepository.findById(id).orElse(new Produto());
    }

    public void deleteById(UUID id) {
        this.produtoRepository.deleteById(id);
    }

    public void deleteAll() {
        this.produtoRepository.deleteAll();
    }
}