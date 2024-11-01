package com.mv.microservices.productos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import com.mv.microservices.productos.entity.ProductosClase;
import com.mv.microservices.productos.services.InterfaceProductos;

@RestController
@RequestMapping("/api/productos")
@AllArgsConstructor

public class ProductosController {
	
	private final InterfaceProductos productosService;
	
    @Autowired
    public ProductosController(InterfaceProductos productosService) {
        this.productosService = productosService;
    }
		
		@GetMapping
		public ResponseEntity<List<ProductosClase>> getAllProducto(){
			return ResponseEntity.ok(productosService.findAll());
		}
		
		@GetMapping("/{id_Producto}")
	    public ResponseEntity<ProductosClase> getProductoById(@PathVariable Integer id_Producto) {
	        Optional<ProductosClase> producto = productosService.findById(id_Producto);
	        return producto.map(ResponseEntity::ok)
	                       .orElse(ResponseEntity.notFound().build());
	    }
		
		@PostMapping("/save")
		public ResponseEntity<ProductosClase> save(@RequestBody ProductosClase productos) {
		    return ResponseEntity.status(HttpStatus.CREATED).body(productosService.save(productos));
		}
		
		@PutMapping(path = "/update/{id_Producto}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<?> update(@PathVariable("id_Producto") Integer id_Producto, @RequestBody ProductosClase productos) {
		    ProductosClase updateProductos = productosService.update(id_Producto, productos);
		    if (updateProductos != null) {
		        return ResponseEntity.ok(updateProductos);
		    } else {
		        return ResponseEntity.notFound().build();
		    }
		}
		
	    @DeleteMapping("/{id_Producto}")
	    public ResponseEntity<String> delete(@PathVariable Integer id_Producto) {
	        int result = productosService.deleteById(id_Producto);
	        if (result == 404) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                                 .body("Producto con ID " + id_Producto + " no encontrado."); // Mensaje de error
	        } else {
	            return ResponseEntity.ok("Producto con ID " + id_Producto + " ha sido eliminado."); // Mensaje de éxito
	        }
	    }
		
	}


