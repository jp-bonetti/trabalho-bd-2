package org.satc.sistemamrp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MateriaPrima {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double quantidade;
    private String unidade;
    @ManyToOne
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private Fornecedor fornecedorId;
}
