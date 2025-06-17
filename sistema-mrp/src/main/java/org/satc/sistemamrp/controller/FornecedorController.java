package org.satc.sistemamrp.controller;

import org.satc.sistemamrp.model.Fornecedor;
import org.satc.sistemamrp.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping
    public List<Fornecedor> getAll() {
        return fornecedorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> getById(@PathVariable Integer id) {
        return fornecedorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Fornecedor create(@RequestBody Fornecedor fornecedor) {
        return fornecedorService.save(fornecedor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> update(@PathVariable Integer id, @RequestBody Fornecedor fornecedor) {
        return fornecedorService.findById(id)
                .map(existing -> {
                    fornecedor.setId(id);
                    return ResponseEntity.ok(fornecedorService.save(fornecedor));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (fornecedorService.findById(id).isPresent()) {
            fornecedorService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}