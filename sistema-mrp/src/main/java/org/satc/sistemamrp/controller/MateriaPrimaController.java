package org.satc.sistemamrp.controller;

import org.satc.sistemamrp.model.MateriaPrima;
import org.satc.sistemamrp.service.MateriaPrimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materias-primas")
public class MateriaPrimaController {

    @Autowired
    private MateriaPrimaService materiaPrimaService;

    @GetMapping
    public List<MateriaPrima> getAll() {
        return materiaPrimaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaPrima> getById(@PathVariable Integer id) {
        return materiaPrimaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MateriaPrima create(@RequestBody MateriaPrima materiaPrima) {
        return materiaPrimaService.save(materiaPrima);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MateriaPrima> update(@PathVariable Integer id, @RequestBody MateriaPrima materiaPrima) {
        return materiaPrimaService.findById(id)
                .map(existing -> {
                    materiaPrima.setId(id);
                    return ResponseEntity.ok(materiaPrimaService.save(materiaPrima));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (materiaPrimaService.findById(id).isPresent()) {
            materiaPrimaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}