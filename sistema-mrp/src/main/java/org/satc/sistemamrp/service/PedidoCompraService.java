package org.satc.sistemamrp.service;

import org.satc.sistemamrp.model.PedidoCompra;
import org.satc.sistemamrp.repository.PedidoCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoCompraService {

    @Autowired
    private PedidoCompraRepository pedidoCompraRepository;

    public List<PedidoCompra> findAll() {
        return pedidoCompraRepository.findAll();
    }

    public Optional<PedidoCompra> findById(Integer id) {
        return pedidoCompraRepository.findById(id);
    }

    public PedidoCompra save(PedidoCompra pedidoCompra) {
        return pedidoCompraRepository.save(pedidoCompra);
    }

    public void deleteById(Integer id) {
        pedidoCompraRepository.deleteById(id);
    }
}