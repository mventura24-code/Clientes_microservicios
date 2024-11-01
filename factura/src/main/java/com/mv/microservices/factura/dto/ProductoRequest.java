package com.mv.microservices.factura.dto;

import lombok.Data;

@Data
public class ProductoRequest {
		
    private Integer productoId; // ID del producto
    private String nombreProducto; // Nombre del producto
    private Double precio; // Precio del producto
    private Integer cantidad; // Cantidad del producto
    
    
	public Integer getProductoId() {
		return productoId;
	}
	public void setProductoId(Integer productoId) {
		this.productoId = productoId;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	
}
