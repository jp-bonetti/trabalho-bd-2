package org.satc.sistemamrp.service;

import org.satc.sistemamrp.model.MovimentacaoEstoque;
import org.satc.sistemamrp.repository.MovimentacaoEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimentacaoEstoqueService {

    @Autowired
    private MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    public List<MovimentacaoEstoque> findAll() {
        return movimentacaoEstoqueRepository.findAll();
    }

    public Optional<MovimentacaoEstoque> findById(Integer id) {
        return movimentacaoEstoqueRepository.findById(id);
    }

    public MovimentacaoEstoque save(MovimentacaoEstoque movimentacaoEstoque) {
        return movimentacaoEstoqueRepository.save(movimentacaoEstoque);
    }

    public void deleteById(Integer id) {
        movimentacaoEstoqueRepository.deleteById(id);
    }
}