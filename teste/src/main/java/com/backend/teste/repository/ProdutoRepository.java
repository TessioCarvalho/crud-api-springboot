package com.backend.teste.repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.backend.teste.model.Produto;

@Repository
public class ProdutoRepository {

    private List<Produto> produtos = new ArrayList<Produto>();
    private int ultimoId = 0;


    /**
     * Retorna uma lista de todos os produtos.
     * @return lista de produtos.
     */
    public List<Produto> obterTodos() {
        return produtos;
    }
    
    /**
     * Retorna um produto pelo seu Id.
     * @param id do produto desejado.
     * @return produto.
     */
    public Optional<Produto> obterPorId(int id) {
        return produtos
            .stream()
            .filter(p -> p.getId() == id)
            .findFirst();
    }

    /**
     * Adiciona um novo produto à lista.
     * @param p produto a ser adicionado.
     * @return novo produto.
     */
    public Produto adicionar(Produto p){
        ultimoId++;
        p.setId(ultimoId);
        produtos.add(p);
        return p;
    }

    /**
     * Deleta produto da lista pelo id.
     * @param id do produto a ser deletado.
     */
    public void deletar(int id){
        produtos.removeIf(p -> p.getId() == id);
    }

    /**
     * Atualiza os dados de um produto da lista.
     * @param p produto que receberá os novos dados.
     * @return produto com os dados atualizados.
     */
    public Produto atualizar(Produto p){
        // Busca o produto pelo id.
        Optional<Produto> produtoEncontrado = obterPorId(p.getId());
        if(produtoEncontrado.isEmpty()) {
            throw new InputMismatchException("Produto não encontrado.");
        }

        // Caso encontre o produto, deleta o mesmo.
        deletar(p.getId());

        // Adiciona novamente o produto com os novos dados mantendo o mesmo id.
        produtos.add(p);

        return p;
    }

}
