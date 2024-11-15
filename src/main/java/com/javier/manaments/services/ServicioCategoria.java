package com.javier.manaments.services;

import java.util.List;
import java.util.Map;

import com.javier.manaments.model.Categoria;


public interface ServicioCategoria {

	List<Categoria> obtenerCategorias();
	
	Categoria obtenerCategoriaPorId(int id);
	List<Map<String,Object>> obtenerCategoriaListado();
}
