package org.satc.sistemamrp.repository;

import org.satc.sistemamrp.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}