package com.mv.microservices.factura.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "producto_factura")
@Data
public class ProductoFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 

    @ManyToOne // Relación con la entidad Factura
    @JoinColumn(name = "factura_id") // Nombre de la columna en la base de datos
    private Factura factura; // Asegúrate de que esto esté definido correctamente
    private Integer producto_Id; 
    private String nombreProducto; 
    private Double precio; 
    private Integer cantidad; 
    
   
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
/*	public Integer getFactura_Id() {
		return factura_Id;
	}
	public void setFactura_Id(Integer factura_Id) {
		this.factura_Id = facturaId;
	}*/
	
	public Integer getProducto_Id() {
		return producto_Id;
	}
	public void setProductoId(Integer productoId) {
		this.producto_Id = productoId;
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