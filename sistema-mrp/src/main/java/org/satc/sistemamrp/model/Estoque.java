package org.satc.sistemamrp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private TipoEstoque tipo;

    @Column(name = "referencia_id", nullable = false)
    private Integer referenciaId;

    @Column(nullable = false)
    private Double quantidade;

    @Column(length = 100)
    private String localizacao;

    public enum TipoEstoque {
        PRODUTO,
        MATERIA_PRIMA
    }
}