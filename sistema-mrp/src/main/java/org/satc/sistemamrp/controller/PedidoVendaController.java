package org.satc.sistemamrp.controller;

import org.satc.sistemamrp.model.PedidoVenda;
import org.satc.sistemamrp.service.PedidoVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos-venda")
public class PedidoVendaController {

    @Autowired
    private PedidoVendaService pedidoVendaService;

    @GetMapping
    public List<PedidoVenda> getAll() {
        return pedidoVendaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoVenda> getById(@PathVariable Integer id) {
        return pedidoVendaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PedidoVenda create(@RequestBody PedidoVenda pedidoVenda) {
        return pedidoVendaService.save(pedidoVenda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoVenda> update(@PathVariable Integer id, @RequestBody PedidoVenda pedidoVenda) {
        return pedidoVendaService.findById(id)
                .map(existing -> {
                    pedidoVenda.setId(id);
                    return ResponseEntity.ok(pedidoVendaService.save(pedidoVenda));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (pedidoVendaService.findById(id).isPresent()) {
            pedidoVendaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}