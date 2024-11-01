package com.mv.microservices.factura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mv.microservices.factura.entity.ProductoFactura;

@Repository
	public interface ProductoFacturaRepository extends JpaRepository<ProductoFactura, Integer> {
	    
	}


