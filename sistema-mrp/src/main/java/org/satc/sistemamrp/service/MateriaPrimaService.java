package org.satc.sistemamrp.service;

import org.satc.sistemamrp.model.MateriaPrima;
import org.satc.sistemamrp.repository.MateriaPrimaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaPrimaService {

    @Autowired
    private MateriaPrimaRepository materiaPrimaRepository;

    public List<MateriaPrima> findAll() {
        return materiaPrimaRepository.findAll();
    }

    public Optional<MateriaPrima> findById(Integer id) {
        return materiaPrimaRepository.findById(id);
    }

    public MateriaPrima save(MateriaPrima materiaPrima) {
        return materiaPrimaRepository.save(materiaPrima);
    }

    public void deleteById(Integer id) {
        materiaPrimaRepository.deleteById(id);
    }
}