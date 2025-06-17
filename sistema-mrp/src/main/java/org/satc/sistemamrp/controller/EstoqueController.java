package org.satc.sistemamrp.controller;

import org.satc.sistemamrp.model.Estoque;
import org.satc.sistemamrp.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estoques")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @GetMapping
    public List<Estoque> getAll() {
        return estoqueService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estoque> getById(@PathVariable Integer id) {
        return estoqueService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Estoque create(@RequestBody Estoque estoque) {
        return estoqueService.save(estoque);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estoque> update(@PathVariable Integer id, @RequestBody Estoque estoque) {
        return estoqueService.findById(id)
                .map(existing -> {
                    estoque.setId(id);
                    return ResponseEntity.ok(estoqueService.save(estoque));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (estoqueService.findById(id).isPresent()) {
            estoqueService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}