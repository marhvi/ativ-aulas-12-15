package com.rj.mercado.gestaoproduto.controller;

import com.rj.mercado.gestaoproduto.model.Produto;
import com.rj.mercado.gestaoproduto.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(path = "stock")
@Tag(name = "Produto Controller", description = "Control that manages the registration of a product")

public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping(value = "/mercado", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Registra um Produto", responses = {@ApiResponse(description = "Registrado com sucesso", responseCode = "201", content = {@Content})})
    ResponseEntity<Produto> save(@RequestBody Produto produto) {
        return new ResponseEntity<>(this.produtoService.save(produto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/mercado", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update um Produto", responses = {@ApiResponse(description = "Update com sucesso", responseCode = "204", content = {@Content})})
    ResponseEntity<?> update(@RequestBody Produto produto) {
        this.produtoService.save(produto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/mercado/{id}/{provider}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update o fornecimento do produto", responses = {@ApiResponse(description = "Update do produto com sucesso", responseCode = "204", content = {@Content})})
    ResponseEntity<Produto> updateProvider(@PathVariable UUID id, @PathVariable String provider) {
        try {
            Produto produto = this.produtoService.findById(id);
            produto.setProvider(provider);
            return new ResponseEntity<Produto>(this.produtoService.save(produto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/mercado", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Lista os Produtos", responses = {@ApiResponse(description = "Sucesso em listar os Produtos", responseCode = "200", content = {@Content})})
    ResponseEntity<List<Produto>> findAll() {
        return new ResponseEntity<>(this.produtoService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/mercado/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Encontrado um Produto por id", responses = {@ApiResponse(description = "Sucesso em procurar Produto por id", responseCode = "200", content = {@Content})})
    ResponseEntity<Produto> findById(@PathVariable UUID id) {
        return new ResponseEntity<>(this.produtoService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/mercado/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Deleta um Produto por id", responses = {@ApiResponse(description = "Sucesso em deletar o Produto", responseCode = "204", content = {@Content})})
    ResponseEntity<?> deleteByID(@PathVariable UUID id) {
        this.produtoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/mercado/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Deleta todos os Produtos", responses = {@ApiResponse(description = "Sucesso em deletar todos os Produtos", responseCode = "204", content = {@Content})})
    ResponseEntity<?> deleteAll() {
        this.produtoService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
