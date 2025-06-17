package org.satc.sistemamrp.controller;

import org.satc.sistemamrp.model.MovimentacaoEstoque;
import org.satc.sistemamrp.service.MovimentacaoEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimentacoes-estoque")
public class MovimentacaoEstoqueController {

    @Autowired
    private MovimentacaoEstoqueService movimentacaoEstoqueService;

    @GetMapping
    public List<MovimentacaoEstoque> getAll() {
        return movimentacaoEstoqueService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentacaoEstoque> getById(@PathVariable Integer id) {
        return movimentacaoEstoqueService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MovimentacaoEstoque create(@RequestBody MovimentacaoEstoque movimentacaoEstoque) {
        return movimentacaoEstoqueService.save(movimentacaoEstoque);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimentacaoEstoque> update(@PathVariable Integer id, @RequestBody MovimentacaoEstoque movimentacaoEstoque) {
        return movimentacaoEstoqueService.findById(id)
                .map(existing -> {
                    movimentacaoEstoque.setId(id);
                    return ResponseEntity.ok(movimentacaoEstoqueService.save(movimentacaoEstoque));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (movimentacaoEstoqueService.findById(id).isPresent()) {
            movimentacaoEstoqueService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}