package com.javier.manaments.servicesREST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.javier.manaments.model.Instrumento;
import com.javier.manaments.services.ServicioInstrumento;

@RestController
public class ServicioRESTProductos {

	@Autowired
	private ServicioInstrumento servicioInstrumento;

	@RequestMapping("obtener-productos-json")
	public String obtenerProductos() {
		List<Instrumento> instrumentos = servicioInstrumento.obtenerInstrumentos();
		return new Gson().toJson(instrumentos);
	}

//	@RequestParam("id") Integer id -> es para recibir directamente como entero el id
	@RequestMapping("obtener-detalles-instrumento")
	public String obtenerDetallesInstrumento(@RequestParam("id") Integer id) {
		return new Gson().toJson(servicioInstrumento.obtenerInstrumentoPorId(id));
	}
}
