package com.javier.manaments.servicesREST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.javier.manaments.services.ServicioCategoria;

@RestController
public class ServicioRESTCategoria {

	@Autowired
	private ServicioCategoria servicioCategoria;

	@RequestMapping("obtener-categorias-json")
	public String obtenerCategorias() {
		return new Gson().toJson(servicioCategoria.obtenerCategoriaListado());
	}

	@RequestMapping("obtener-instrumentos-categoria")
	public 
}
