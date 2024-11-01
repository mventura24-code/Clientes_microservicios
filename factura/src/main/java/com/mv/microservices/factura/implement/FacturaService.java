package com.mv.microservices.factura.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mv.microservices.factura.entity.Factura;
import com.mv.microservices.factura.entity.ProductoFactura;
import com.mv.microservices.factura.dto.ClienteDto;
import com.mv.microservices.factura.dto.FacturaRequest;
import com.mv.microservices.factura.dto.ProductoDto;
import com.mv.microservices.factura.dto.ProductoRequest;
import com.mv.microservices.factura.repository.FacturaRepository;
import com.mv.microservices.factura.repository.ProductoFacturaRepository;
import com.mv.microservices.factura.services.IFactura;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Service
public class FacturaService implements IFactura {
    
    @Autowired
    private FacturaRepository facturaRepository;
    
    @Autowired
    private ProductoFacturaRepository productoFacturaRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String CLIENTES_SERVICE_URL = "http://localhost:8081/api/clientes/";
    private final String PRODUCTOS_SERVICE_URL = "http://localhost:8082/api/productos/";
    
    public ClienteDto getCliente(String nit) {
        try {
            return restTemplate.getForObject(CLIENTES_SERVICE_URL + nit, ClienteDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el cliente: " + e.getMessage(), e);
        }
    }

    public ProductoDto getProducto(Integer id) {
        try {
            return restTemplate.getForObject(PRODUCTOS_SERVICE_URL + id, ProductoDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el producto: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Override
    public Optional<Factura> findByNit(String nit) {
        return facturaRepository.findByNit(nit); 
    }

    @Override
    public Factura save(Factura factura) {
        ClienteDto cliente = getCliente(factura.getNit()); 
        if (cliente == null) {
            throw new RuntimeException("Cliente no encontrado con NIT: " + factura.getNit());
        }
        return facturaRepository.save(factura);
    }

    @Override
    public Factura crearFactura(FacturaRequest facturaRequest) {
        // Lógica para crear y guardar la factura
        Factura factura = new Factura();
        factura.setClienteId(facturaRequest.getClienteId());
        factura.setFecha(LocalDate.now());
        factura.setTotal(0.0); // Inicializa el total

        // Guardar la factura (esto generará el ID necesario para el producto_factura)
        factura = facturaRepository.save(factura);

        // Guardar los productos en producto_factura
        for (ProductoRequest producto : facturaRequest.getProductosFactura()) {
            ProductoFactura productoFactura = new ProductoFactura();
//          productoFactura.setFacturaId(factura.getId());
            productoFactura.setProductoId(producto.getProductoId());
            productoFactura.setNombreProducto(producto.getNombreProducto()); // Recuperar si es necesario
            productoFactura.setPrecio(producto.getPrecio());
            productoFactura.setCantidad(producto.getCantidad());

            productoFacturaRepository.save(productoFactura);
            
            factura.setTotal(factura.getTotal() + (producto.getPrecio() * producto.getCantidad()));
        }

        // Actualiza el total de la factura, agrega todos los productos
        facturaRepository.save(factura);

        return factura; // Retorna la factura creada
 
    }    
    
    @Override
    public Factura update(Integer id, Factura factura) {
        if (facturaRepository.existsById(id)) {
            factura.setId(id);
            return facturaRepository.save(factura);
        }
        throw new RuntimeException("Factura no encontrada");
    }

    @Override
    public Integer deleteById(Integer id) {
        if (facturaRepository.existsById(id)) {
            facturaRepository.deleteById(id);
            return id;
        }
        throw new RuntimeException("Factura no encontrada");
    }       
}

