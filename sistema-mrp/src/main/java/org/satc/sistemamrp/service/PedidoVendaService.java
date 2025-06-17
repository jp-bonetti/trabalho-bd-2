package org.satc.sistemamrp.service;

import org.satc.sistemamrp.model.PedidoVenda;
import org.satc.sistemamrp.repository.PedidoVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoVendaService {

    @Autowired
    private PedidoVendaRepository pedidoVendaRepository;

    public List<PedidoVenda> findAll() {
        return pedidoVendaRepository.findAll();
    }

    public Optional<PedidoVenda> findById(Integer id) {
        return pedidoVendaRepository.findById(id);
    }

    public PedidoVenda save(PedidoVenda pedidoVenda) {
        return pedidoVendaRepository.save(pedidoVenda);
    }

    public void deleteById(Integer id) {
        pedidoVendaRepository.deleteById(id);
    }
}