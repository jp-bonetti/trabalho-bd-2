package org.satc.sistemamrp.service;

import org.satc.sistemamrp.model.OrdemProducao;
import org.satc.sistemamrp.repository.OrdemProducaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdemProducaoService {

    @Autowired
    private OrdemProducaoRepository ordemProducaoRepository;

    public List<OrdemProducao> findAll() {
        return ordemProducaoRepository.findAll();
    }

    public Optional<OrdemProducao> findById(Integer id) {
        return ordemProducaoRepository.findById(id);
    }

    public OrdemProducao save(OrdemProducao ordemProducao) {
        return ordemProducaoRepository.save(ordemProducao);
    }

    public void deleteById(Integer id) {
        ordemProducaoRepository.deleteById(id);
    }
}