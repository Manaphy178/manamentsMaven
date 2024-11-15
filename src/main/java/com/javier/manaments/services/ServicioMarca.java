package com.javier.manaments.services;

import java.util.List;
import java.util.Map;

import com.javier.manaments.model.Marca;

public interface ServicioMarca {

	List<Marca> obtenerMarcas();
	
	Marca obtenerMarcaPorId(int id);
	/*
	 * Hacer obtener instrumentos de las marcas y categorias de las marcas
	 */

	List<Map<String,Object>>obtenerMarcaListado();
}
