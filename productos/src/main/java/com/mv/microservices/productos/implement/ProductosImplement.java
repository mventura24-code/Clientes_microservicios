package com.mv.microservices.productos.implement;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mv.microservices.productos.services.InterfaceProductos;
import com.mv.microservices.productos.entity.ProductosClase;
import com.mv.microservices.productos.repository.ProductosRepository;

@Service
public class ProductosImplement implements InterfaceProductos {
	
	@Autowired
	protected ProductosRepository productosRepository;
	
	@Override
	public List<ProductosClase> findAll() {
		return (List<ProductosClase>) productosRepository.findAll();
	}
	
	   @Override
	    public Optional<ProductosClase> findById(Integer idproducto) {
	        return productosRepository.findById(idproducto);
	    }
	
	
	@Override
	public ProductosClase save(ProductosClase productos) {
		return productosRepository.save(productos);
	}
	
	
	@Override
	public ProductosClase update(Integer idProducto, ProductosClase producto) {
		Optional<ProductosClase> optionalProducto = productosRepository.findById(idProducto);
				if (optionalProducto.isPresent()) {
					ProductosClase existingProducto = optionalProducto.get();
					existingProducto.setProducto(producto.getProducto());
					existingProducto.setDescripcion(producto.getDescripcion());
					existingProducto.setPrecio(producto.getPrecio());
					existingProducto.setStock(producto.getStock());
					return productosRepository.save(existingProducto);
				}
			return null;
	}
	
	@Override
	public Integer deleteById(Integer idProducto) {
	    if (!productosRepository.existsById(idProducto)) {
	        return 404; // Not Found
	    } else {
	        productosRepository.deleteById(idProducto);
	        return 204; // No Content
	    }
	}
	

}
