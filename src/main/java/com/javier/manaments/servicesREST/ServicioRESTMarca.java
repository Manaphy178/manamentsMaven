package com.javier.manaments.servicesREST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.javier.manaments.services.ServicioMarca;

@RestController
public class ServicioRESTMarca {
	@Autowired
	private ServicioMarca servicioMarca;

	@RequestMapping("obtener-marcas-json")
	public String obtenerMarcas() {
		return new Gson().toJson(servicioMarca.obtenerMarcaListado());
	}
}
