package com.javier.manaments.servicesREST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.javier.manaments.model.Instrumento;
import com.javier.manaments.services.ServicioInstrumento;

@Controller
public class ServicioRESTProductos {

	@Autowired
	private ServicioInstrumento servicioInstrumento;

	@RequestMapping("obtener-productos-json")
	public ResponseEntity<String> obtenerProductos() {
		List<Instrumento> instrumentos = servicioInstrumento.obtenerInstrumentos();
		String respuesta = new Gson().toJson(instrumentos);
		return new ResponseEntity<String>(respuesta, HttpStatus.OK);
	}
}
