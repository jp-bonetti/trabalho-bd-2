package org.satc.sistemamrp.service;

import org.satc.sistemamrp.model.ItemPedidoCompra;
import org.satc.sistemamrp.repository.ItemPedidoCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoCompraService {

    @Autowired
    private ItemPedidoCompraRepository itemPedidoCompraRepository;

    public List<ItemPedidoCompra> findAll() {
        return itemPedidoCompraRepository.findAll();
    }

    public Optional<ItemPedidoCompra> findById(Integer id) {
        return itemPedidoCompraRepository.findById(id);
    }

    public ItemPedidoCompra save(ItemPedidoCompra itemPedidoCompra) {
        return itemPedidoCompraRepository.save(itemPedidoCompra);
    }

    public void deleteById(Integer id) {
        itemPedidoCompraRepository.deleteById(id);
    }
}