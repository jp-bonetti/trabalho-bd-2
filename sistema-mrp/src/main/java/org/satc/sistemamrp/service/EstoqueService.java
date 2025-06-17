package org.satc.sistemamrp.service;

import org.satc.sistemamrp.model.Estoque;
import org.satc.sistemamrp.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    public List<Estoque> findAll() {
        return estoqueRepository.findAll();
    }

    public Optional<Estoque> findById(Integer id) {
        return estoqueRepository.findById(id);
    }

    public Estoque save(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }

    public void deleteById(Integer id) {
        estoqueRepository.deleteById(id);
    }
}