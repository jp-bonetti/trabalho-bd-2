package org.satc.sistemamrp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class PedidoVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "data_pedido", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp dataPedido;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private StatusPedidoVenda status;

    public enum StatusPedidoVenda {
        PENDENTE,
        EM_SEPARACAO,
        ENVIADO,
        ENTREGUE,
        CANCELADO
    }
}