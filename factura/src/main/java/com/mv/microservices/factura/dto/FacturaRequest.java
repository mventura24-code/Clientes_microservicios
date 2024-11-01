package com.mv.microservices.factura.dto;

import java.util.*;

import lombok.Data;


@Data
public class FacturaRequest {

	private Integer clienteId;
	private String nit;
	private String nombreCliente;
	private String numeroFactura;
    private Double total;
    private String fecha;
	private List<ProductoRequest> productosFactura; // Asegúrate de que este campo no sea null

	// Constructor
	public FacturaRequest() {
		this.productosFactura = new ArrayList<>(); // Inicializa la lista
	}

	// Getters y Setters
	public List<ProductoRequest> getProductosFactura() {
		return productosFactura;
	}

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

    public void setProductosFactura(List<ProductoRequest> productosFactura) {
        this.productosFactura = productosFactura;
    }
    
    
}
