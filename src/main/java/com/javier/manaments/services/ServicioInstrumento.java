package com.javier.manaments.services;

import java.util.List;
import java.util.Map;

import com.javier.manaments.model.Instrumento;

public interface ServicioInstrumento {

	void registrarInstrumento(Instrumento i);

	List<Instrumento> obtenerInstrumentos();

	List<Instrumento> obtenerInstrumentos(String nombre);

	List<Instrumento> obtenerInstrumentos(String nombre, int comienzo, int resultadosPorPagina);

	void borrarInstrumento(int id);

	Instrumento obtenerInstrumentoPorId(int id);

	void actualizarInstrumento(Instrumento i);

	Map<String, Object> obtenerInstrumentoVerDetallesPorId(long id);

	List<Map<String, Object>> obtenerInstrumentosParaListado();

	int obtenerTotalInstrumentos();

	int obtenerTotalInstrumentos(String nombre);

}
