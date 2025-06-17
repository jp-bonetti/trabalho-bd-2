package org.satc.sistemamrp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class PedidoCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", referencedColumnName = "id")
    private Fornecedor fornecedor;

    @Column(name = "data_pedido", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp dataPedido;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    public enum StatusPedido {
        PENDENTE,
        APROVADO,
        RECEBIDO,
        CANCELADO
    }
}