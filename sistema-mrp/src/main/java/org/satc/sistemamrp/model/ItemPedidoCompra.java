package org.satc.sistemamrp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ItemPedidoCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pedido_compra_id", nullable = false, referencedColumnName = "id")
    private PedidoCompra pedidoCompra;

    @ManyToOne
    @JoinColumn(name = "materia_prima_id", nullable = false, referencedColumnName = "id")
    private MateriaPrima materiaPrima;

    @Column(nullable = false)
    private Double quantidade;

    private Double precoUnitario;

    @Column(length = 10)
    private String unidade;
}