package com.mv.microservices.clientes.controller;

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

import com.mv.microservices.clientes.entity.Clientes;
import com.mv.microservices.clientes.services.InterfaceClientes;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/clientes")


public class ClientesController {	
	private final InterfaceClientes clientesService;
	
	@Autowired
    public ClientesController(InterfaceClientes clientesService) {
        this.clientesService = clientesService;
    }
	
	@GetMapping
	public ResponseEntity<List<Clientes>> getAllClientes() {
		List<Clientes> clientes = clientesService.findAll();
	    System.out.println(clientes);
	    return ResponseEntity.ok(clientesService.findAll());
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Clientes> getClienteById(@PathVariable Integer id) {
        Optional<Clientes> cliente = clientesService.findById(id);
        return cliente.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
	}
		
	@PostMapping("/save")
	public ResponseEntity<Clientes> save(@RequestBody Clientes clientes) {
	    return ResponseEntity.status(HttpStatus.CREATED).body(clientesService.save(clientes));
	}
	
	@PutMapping(path = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Clientes clientes) {
	    Clientes updateClientes = clientesService.update(id, clientes);
	    if (updateClientes != null) {
	        return ResponseEntity.ok(updateClientes);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCliente(@PathVariable Integer id) {
	    try {
	        clientesService.deleteById(id);
	        return ResponseEntity.ok("Cliente con ID " + id + " ha sido eliminado."); 
	    } catch (EntityNotFoundException e) {
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente con ID " + id + " no encontrado.");
	    }
	}
}
