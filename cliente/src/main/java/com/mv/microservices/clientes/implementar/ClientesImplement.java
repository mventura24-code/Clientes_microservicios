package com.mv.microservices.clientes.implementar;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mv.microservices.clientes.entity.Clientes;
import com.mv.microservices.clientes.repository.ClientesRepository;
import com.mv.microservices.clientes.services.InterfaceClientes;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientesImplement implements InterfaceClientes {
	
	@Autowired
	protected ClientesRepository clientesRepository;
		
	@Override
	public List<Clientes> findAll() {
		return (List<Clientes>) clientesRepository.findAll();
	}
	
    @Override
    public Optional<Clientes> findById(Integer id) {
        return clientesRepository.findById(id); 
    }

    @Override
    public Clientes save(Clientes cliente) {
        return clientesRepository.save(cliente);
    }

    @Override
    public Clientes update(Integer id, Clientes cliente) {
        Optional<Clientes> optionalCliente = clientesRepository.findById(id);
        if (optionalCliente.isPresent()) {
            Clientes existingCliente = optionalCliente.get();
            existingCliente.setNombre(cliente.getNombre());
            existingCliente.setEmail(cliente.getEmail());
            existingCliente.setTelefono(cliente.getTelefono());
            existingCliente.setDireccion(cliente.getDireccion());
            existingCliente.setNit(cliente.getNit());
            return clientesRepository.save(existingCliente);
        }
        throw new EntityNotFoundException("Cliente no encontrado");
    }

    @Override
    public Integer deleteById(Integer id) {
        if (!clientesRepository.existsById(id)) {
            throw new EntityNotFoundException("Cliente no encontrado");
        } else {
            clientesRepository.deleteById(id);
            return 204; 
        }
    }

}
