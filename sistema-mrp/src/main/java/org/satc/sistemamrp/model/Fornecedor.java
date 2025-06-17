package org.satc.sistemamrp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 100)
    private String contato;

    @Column(columnDefinition = "TEXT")
    private String endereco;
}