package com.backend.teste.services;

import java.util.Optional;
import java.util.InputMismatchException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.teste.model.Produto;
import com.backend.teste.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Retorna uma lista de todos os produtos.
     * @return lista de produtos.
     */
    public List<Produto> obterTodos() { 
        return produtoRepository.findAll();
    }

    /**
     * Retorna um produto pelo seu Id.
     * @param id do produto desejado.
     * @return produto.
     */
    public Optional<Produto> obterPorId(int id) {
        return produtoRepository.findById(id);
    }

    /**
     * Adiciona um novo produto à lista.
     * @param p produto a ser adicionado.
     * @return novo produto.
     */
    public Produto adicionar(Produto p){
        return produtoRepository.save(p);
    }

    /**
     * Deleta produto da lista pelo id.
     * @param id do produto a ser deletado.
     */
    public void deletar(int id){
        produtoRepository.deleteById(id);
    }

    /**
     * Atualiza os dados de um produto da lista.
     * @param p produto que receberá os novos dados.
     * @param id do produto que será atualizado.
     * @return produto com os dados atualizados.
     */
    public Produto atualizar(int id, Produto p){
        p.setId(id);
        return produtoRepository.save(p);
    }

}
