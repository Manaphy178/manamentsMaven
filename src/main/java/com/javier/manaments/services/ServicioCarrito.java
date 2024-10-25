package com.javier.manaments.services;

import java.util.List;
import java.util.Map;

public interface ServicioCarrito {
	void agregarProducto(int idProducto, long idUsuario, int cantidad);

	List<Map<String, Object>> obtenerProductosCarritoUsuario(long id);
	
	void restarProductoCarrito(int idProducto, long idUsuario, int cantidad);

	void aumentarProductoCarrito(int idProducto, long idUsuario, int cantidad);
	
	void borrarProductoCarrito(int idProducto, long idUsuario);
}
