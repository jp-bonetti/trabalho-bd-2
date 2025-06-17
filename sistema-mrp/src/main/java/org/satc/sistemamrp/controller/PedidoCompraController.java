package org.satc.sistemamrp.controller;

import org.satc.sistemamrp.model.PedidoCompra;
import org.satc.sistemamrp.service.PedidoCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos-compra")
public class PedidoCompraController {

    @Autowired
    private PedidoCompraService pedidoCompraService;

    @GetMapping
    public List<PedidoCompra> getAll() {
        return pedidoCompraService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoCompra> getById(@PathVariable Integer id) {
        return pedidoCompraService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PedidoCompra create(@RequestBody PedidoCompra pedidoCompra) {
        return pedidoCompraService.save(pedidoCompra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoCompra> update(@PathVariable Integer id, @RequestBody PedidoCompra pedidoCompra) {
        return pedidoCompraService.findById(id)
                .map(existing -> {
                    pedidoCompra.setId(id);
                    return ResponseEntity.ok(pedidoCompraService.save(pedidoCompra));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (pedidoCompraService.findById(id).isPresent()) {
            pedidoCompraService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}