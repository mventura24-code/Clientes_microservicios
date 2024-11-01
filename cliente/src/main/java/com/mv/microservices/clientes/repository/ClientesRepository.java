package com.mv.microservices.clientes.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.mv.microservices.clientes.entity.Clientes;

public interface ClientesRepository extends CrudRepository<Clientes, Integer> {
	Optional<Clientes> findByNit(String nit);
   
}
