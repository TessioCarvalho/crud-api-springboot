package com.backend.teste.services;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.teste.model.Produto;
import com.backend.teste.repository.ProdutoRepository;
import com.backend.teste.shared.ProdutoDTO;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Retorna uma lista de todos os produtos.
     * @return lista de produtos.
     */
    public List<ProdutoDTO> obterTodos() { 
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                       .map(p -> new ModelMapper().map(p, ProdutoDTO.class))
                       .collect(Collectors.toList());
    }

    /**
     * Retorna um produto pelo seu Id.
     * @param id do produto desejado.
     * @return produto.
     */
    public Optional<ProdutoDTO> obterPorId(int id) {
        Optional<Produto> p = produtoRepository.findById(id);
        ProdutoDTO dto = new ModelMapper().map(p.get(), ProdutoDTO.class);
        return Optional.of(dto);
    }

    /**
     * Adiciona um novo produto à lista.
     * @param p produto a ser adicionado.
     * @return novo produto.
     */
    public ProdutoDTO adicionar(ProdutoDTO p){
        ModelMapper m = new ModelMapper();
        Produto prod = m.map(p, Produto.class);
        prod = produtoRepository.save(prod);
        p.setId(prod.getId());
        return p;
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
    public ProdutoDTO atualizar(int id, ProdutoDTO pDto){
        pDto.setId(id);
        ModelMapper m = new ModelMapper();
        Produto prod = m.map(pDto, Produto.class);
        produtoRepository.save(prod);
        return pDto;
    }

}
