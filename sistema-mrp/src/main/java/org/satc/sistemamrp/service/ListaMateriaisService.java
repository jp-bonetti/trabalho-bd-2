package org.satc.sistemamrp.service;

import org.satc.sistemamrp.embeddable.ListaMateriaisId;
import org.satc.sistemamrp.model.ListaMateriais;
import org.satc.sistemamrp.repository.ListaMateriaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListaMateriaisService {

    @Autowired
    private ListaMateriaisRepository repository;

    public Optional<ListaMateriais> findById(ListaMateriaisId id) {
        return repository.findById(id);
    }

    public ListaMateriais save(ListaMateriais listaMateriais) {
        return repository.save(listaMateriais);
    }

    public void deleteById(ListaMateriaisId id) {
        repository.deleteById(id);
    }

    public List<ListaMateriais> findAll() {
        return repository.findAll();
    }
}