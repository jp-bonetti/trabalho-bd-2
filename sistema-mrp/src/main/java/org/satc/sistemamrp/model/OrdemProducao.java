package org.satc.sistemamrp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class OrdemProducao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false, referencedColumnName = "id")
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "data_inicio")
    private Timestamp dataInicio;

    @Column(name = "data_fim")
    private Timestamp dataFim;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private StatusOrdemProducao status;

    @Column(name = "data_criacao", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp dataCriacao;

    public enum StatusOrdemProducao {
        PENDENTE,
        EM_ANDAMENTO,
        FINALIZADA,
        CANCELADA
    }
}