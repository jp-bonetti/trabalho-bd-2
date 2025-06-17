package org.satc.sistemamrp.controller;

import org.satc.sistemamrp.model.ItemPedidoCompra;
import org.satc.sistemamrp.service.ItemPedidoCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens-pedido-compra")
public class ItemPedidoCompraController {

    @Autowired
    private ItemPedidoCompraService itemPedidoCompraService;

    @GetMapping
    public List<ItemPedidoCompra> getAll() {
        return itemPedidoCompraService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoCompra> getById(@PathVariable Integer id) {
        return itemPedidoCompraService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ItemPedidoCompra create(@RequestBody ItemPedidoCompra itemPedidoCompra) {
        return itemPedidoCompraService.save(itemPedidoCompra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedidoCompra> update(@PathVariable Integer id, @RequestBody ItemPedidoCompra itemPedidoCompra) {
        return itemPedidoCompraService.findById(id)
                .map(existing -> {
                    itemPedidoCompra.setId(id);
                    return ResponseEntity.ok(itemPedidoCompraService.save(itemPedidoCompra));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (itemPedidoCompraService.findById(id).isPresent()) {
            itemPedidoCompraService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}