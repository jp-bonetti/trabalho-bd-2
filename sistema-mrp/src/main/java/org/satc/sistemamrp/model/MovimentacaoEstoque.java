package org.satc.sistemamrp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class MovimentacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipo;

    @ManyToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "materia_prima_id", referencedColumnName = "id")
    private MateriaPrima materiaPrima;

    @Column(nullable = false)
    private Double quantidade;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp data;

    @Column(length = 30)
    @Enumerated(EnumType.STRING)
    private OrigemMovimentacao origem;

    @Column(name = "referencia_id")
    private Integer referenciaId;

    public enum TipoMovimentacao {
        ENTRADA,
        SAIDA
    }

    public enum OrigemMovimentacao {
        PEDIDO_COMPRA,
        PEDIDO_VENDA,
        ORDEM_PRODUCAO,
        AJUSTE
    }
}