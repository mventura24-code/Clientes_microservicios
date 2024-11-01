package com.mv.microservices.factura.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mv.microservices.factura.entity.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Integer> {
	 Optional<Factura> findByNit(String nit);

}
