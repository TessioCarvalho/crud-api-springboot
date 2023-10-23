package com.backend.teste.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.teste.model.Produto;
import com.backend.teste.services.ProdutoService;

@RestController
@RequestMapping("api/produtos")
public class ProdutoController {

    @Autowired    
    private ProdutoService produtoService;

    /**
     * Retorna uma lista de todos os produtos.
     * @return lista de produtos.
     */
    @GetMapping
    public List<Produto> obterTodos() {
        return produtoService.obterTodos();
    }
    
     /**
     * Retorna um produto pelo seu Id.
     * @param id do produto desejado.
     * @return produto.
     */
    @GetMapping("/{id}")
    public Optional<Produto> obterPorId(@PathVariable int id) {
        return produtoService.obterPorId(id);
    }

     /**
     * Adiciona um novo produto à lista.
     * @param p produto a ser adicionado.
     * @return novo produto.
     */
    @PostMapping
    public Produto adicionar(@RequestBody Produto p){
        return produtoService.adicionar(p);
    }
    
    /**
     * Deleta produto da lista pelo id.
     * @param id do produto a ser deletado.
     */
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id){
        produtoService.deletar(id);
    }

    /**
     * Atualiza os dados de um produto da lista.
     * @param p produto que receberá os novos dados.
     * @param id do produto que será atualizado.
     * @return produto com os dados atualizados.
     */
    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable int id, @RequestBody Produto p){
        return produtoService.atualizar(id, p);
    }

}
