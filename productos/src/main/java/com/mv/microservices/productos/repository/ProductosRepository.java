package com.mv.microservices.productos.repository;

import org.springframework.data.repository.CrudRepository;

import com.mv.microservices.productos.entity.ProductosClase;

public interface ProductosRepository extends CrudRepository<ProductosClase, Integer>{

}
