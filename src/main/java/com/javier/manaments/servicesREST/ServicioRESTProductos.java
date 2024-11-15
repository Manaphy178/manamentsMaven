package com.javier.manaments.servicesREST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.javier.manaments.model.tiposExtra.InfoInstrumentosListado;
import com.javier.manaments.services.ServicioInstrumento;

@RestController
public class ServicioRESTProductos {

	@Autowired
	private ServicioInstrumento servicioInstrumento;

	@RequestMapping("obtener-productos-json")
	public String obtenerProductos() {
		return new Gson().toJson(servicioInstrumento.obtenerInstrumentosParaListadoPrincipal());
	}

	@RequestMapping("obtener-todos-productos-json")
	public InfoInstrumentosListado obtenerTodosProductos(
			@RequestParam(name = "nombre", defaultValue = "") String nombre,
			@RequestParam(name = "comienzo", defaultValue = "0") Integer comienzo) {
		InfoInstrumentosListado info = new InfoInstrumentosListado();
		info.setInstrumentos(servicioInstrumento.obtenerTodosInstrumentosParaListado(nombre, comienzo));
		info.setTotalInstrumentos(servicioInstrumento.obtenerTotalInstrumentos(nombre));
		return info;
	}

	@RequestMapping("obtener-todos-productos-categoria-json")
	public InfoInstrumentosListado obtenerTodosProductosCategoria(
			@RequestParam(name = "nombre", defaultValue = "") String nombre,
			@RequestParam(name = "comienzo", defaultValue = "0") Integer comienzo,
			@RequestParam(name = "idcat") Integer categoria) {
		InfoInstrumentosListado info = new InfoInstrumentosListado();
		info.setInstrumentos(
				servicioInstrumento.obtenerTodosInstrumentosParaListadoCategoria(nombre, comienzo, categoria));
		info.setTotalInstrumentos(servicioInstrumento.obtenerTotalInstrumentosCategoria(nombre, categoria));
		return info;
	}
	@RequestMapping("obtener-todos-productos-marca-json")
	public InfoInstrumentosListado obtenerTodosProductosMarca(
			@RequestParam(name = "nombre", defaultValue = "") String nombre,
			@RequestParam(name = "comienzo", defaultValue = "0") Integer comienzo,
			@RequestParam(name = "idmar") Integer marca) {
		InfoInstrumentosListado info = new InfoInstrumentosListado();
		info.setInstrumentos(
				servicioInstrumento.obtenerTodosInstrumentosParaListadoMarca(nombre, comienzo, marca));
		info.setTotalInstrumentos(servicioInstrumento.obtenerTotalInstrumentosMarca(nombre, marca));
		return info;
	}

	// @RequestParam("id") Integer id -> es para recibir directamente como entero el
	// id
	@RequestMapping("obtener-detalles-instrumento")
	public String obtenerDetallesInstrumento(@RequestParam("id") Integer id) {
		return new Gson().toJson(servicioInstrumento.obtenerInstrumentoVerDetallesPorId(id));
	}

	@RequestMapping("obtener-mas-vendidos")
	public String obtenerMasVendidos() {
		return new Gson().toJson(servicioInstrumento.obtenerInstrumentosMasVendidos());
	}
}
