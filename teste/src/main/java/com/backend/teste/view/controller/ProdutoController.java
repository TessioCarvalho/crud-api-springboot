package com.backend.teste.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.teste.services.ProdutoService;
import com.backend.teste.shared.ProdutoDTO;
import com.backend.teste.view.model.ProdutoRequest;
import com.backend.teste.view.model.ProdutoResponse;

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
    public ResponseEntity<List<ProdutoResponse>> obterTodos() {
         try {
            List<ProdutoDTO> produtos = produtoService.obterTodos();
            ModelMapper m = new ModelMapper();
            List<ProdutoResponse> resposta = produtos.stream()
                       .map(p -> m.map(p, ProdutoResponse.class))
                       .collect(Collectors.toList());
         return new ResponseEntity<>(resposta, HttpStatus.OK);
         } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }
    }
    
     /**
     * Retorna um produto pelo seu Id.
     * @param id do produto desejado.
     * @return produto.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProdutoResponse>> obterPorId(@PathVariable int id) {
        try {
            Optional<ProdutoDTO> dto = produtoService.obterPorId(id);
            ProdutoResponse p = new ModelMapper().map(dto.get(), ProdutoResponse.class);
            return new ResponseEntity<>(Optional.of(p), HttpStatus.OK);   
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }     
    }

     /**
     * Adiciona um novo produto à lista.
     * @param p produto a ser adicionado.
     * @return novo produto.
     */
    @PostMapping
    public ResponseEntity<ProdutoResponse> adicionar(@RequestBody ProdutoRequest produtoReq){
        ModelMapper m = new ModelMapper();
        ProdutoDTO p = m.map(produtoReq, ProdutoDTO.class);
        p = produtoService.adicionar(p);
        return new ResponseEntity<>(m.map(p, ProdutoResponse.class), HttpStatus.CREATED);
    }
    
    /**
     * Deleta produto da lista pelo id.
     * @param id do produto a ser deletado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable int id){
        produtoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Atualiza os dados de um produto da lista.
     * @param p produto que receberá os novos dados.
     * @param id do produto que será atualizado.
     * @return produto com os dados atualizados.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(@PathVariable int id, @RequestBody ProdutoRequest produtoReq){
        ModelMapper m = new ModelMapper();
        ProdutoDTO pDto = m.map(produtoReq, ProdutoDTO.class);
        pDto = produtoService.atualizar(id, pDto);
        return new ResponseEntity<>(
            m.map(pDto, ProdutoResponse.class), HttpStatus.OK
        );
    }

}
