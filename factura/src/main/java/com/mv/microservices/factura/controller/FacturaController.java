package com.mv.microservices.factura.controller;
/*
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mv.microservices.factura.dto.ClienteDto;
import com.mv.microservices.factura.dto.ProductoDto;
import com.mv.microservices.factura.entity.Factura;
import com.mv.microservices.factura.services.IFactura;

@RestController
@RequestMapping("/api/factura1")
public class FacturaController {
	
	@Autowired
    private IFactura facturaService;

    @Autowired
    private RestTemplate restTemplate;
	
    @GetMapping
    public List<Factura> getAllFacturas() {
        return facturaService.findAll();
    }

    @GetMapping("/clientes/{nit}")
    public ResponseEntity<Factura> getFacturaByNit(@PathVariable String nit) {
        Optional<Factura> factura = facturaService.findByNit(nit);
        return factura.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Factura> crearFactura(@RequestBody Factura factura) {
        Factura nuevaFactura = facturaService.save(factura);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaFactura);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Factura> updateFactura(@PathVariable Integer id, @RequestBody Factura factura) {
        Factura updatedFactura = facturaService.update(id, factura);
        return ResponseEntity.ok(updatedFactura);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFactura(@PathVariable Integer id) {
        Integer deletedId = facturaService.deleteById(id);
        return deletedId != null ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteDto>> getAllClientes() {
        String url = "http://localhost:8081/api/clientes";  
        try {

            ResponseEntity<List<ClienteDto>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<ClienteDto>>() {}
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                List<ClienteDto> clientes = response.getBody();
                return ResponseEntity.ok(clientes);
            } else {
                return ResponseEntity.status(response.getStatusCode()).body(null);
            }
        } catch (Exception e) {
        	return ResponseEntity.status(500).body(null);
        }
    }
    
    
    @GetMapping("/productos")
    public ResponseEntity<List<ProductoDto>> getAllProductos() {
        String url = "http://localhost:8082/api/productos";  // URL del microservicio de productos
        try {
            ResponseEntity<List<ProductoDto>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<ProductoDto>>() {}
            );
            if (response.getStatusCode().is2xxSuccessful()) {
                List<ProductoDto> productos = response.getBody();
                return ResponseEntity.ok(productos);
            } else {
                return ResponseEntity.status(response.getStatusCode()).body(null);
            }
        } catch (Exception e) {
        	return ResponseEntity.status(500).body(null);
        }
    }

}*/
