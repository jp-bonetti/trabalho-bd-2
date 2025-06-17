package org.satc.sistemamrp.repository;

import org.satc.sistemamrp.embeddable.ListaMateriaisId;
import org.satc.sistemamrp.model.ListaMateriais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ListaMateriaisRepository extends JpaRepository<ListaMateriais, ListaMateriaisId> {

    @Query("SELECT lm FROM ListaMateriais lm WHERE lm.listaMateriaisId.produtoId.id = :produtoId")
    List<ListaMateriais> findByProdutoId(@Param("produtoId") Integer produtoId);
}