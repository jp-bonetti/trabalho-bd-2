package org.satc.sistemamrp.repository;

import org.satc.sistemamrp.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {
}