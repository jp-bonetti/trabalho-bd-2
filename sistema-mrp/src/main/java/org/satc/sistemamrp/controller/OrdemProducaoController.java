package org.satc.sistemamrp.controller;

import org.satc.sistemamrp.model.OrdemProducao;
import org.satc.sistemamrp.service.OrdemProducaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordens-producao")
public class OrdemProducaoController {

    @Autowired
    private OrdemProducaoService ordemProducaoService;

    @GetMapping
    public List<OrdemProducao> getAll() {
        return ordemProducaoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemProducao> getById(@PathVariable Integer id) {
        return ordemProducaoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public OrdemProducao create(@RequestBody OrdemProducao ordemProducao) {
        return ordemProducaoService.save(ordemProducao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdemProducao> update(@PathVariable Integer id, @RequestBody OrdemProducao ordemProducao) {
        return ordemProducaoService.findById(id)
                .map(existing -> {
                    ordemProducao.setId(id);
                    return ResponseEntity.ok(ordemProducaoService.save(ordemProducao));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (ordemProducaoService.findById(id).isPresent()) {
            ordemProducaoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}