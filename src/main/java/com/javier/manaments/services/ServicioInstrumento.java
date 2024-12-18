package com.javier.manaments.services;

import java.util.List;
import java.util.Map;

import com.google.gson.JsonElement;
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

	List<Map<String, Object>> obtenerInstrumentosParaListadoPrincipal();

	List<Map<String, Object>> obtenerTodosInstrumentosParaListado();

	List<Map<String, Object>> obtenerTodosInstrumentosParaListado(String nombre, int comienzo);

	int obtenerTotalInstrumentos();

	int obtenerTotalInstrumentos(String nombre);

	List<Map<String, Object>> obtenerInstrumentosMasVendidos();

	List<Map<String, Object>> obtenerTodosInstrumentosParaListadoCategoria(String nombre, int comienzo, int categoria);

	int obtenerTotalInstrumentosCategoria(int categoria);

	int obtenerTotalInstrumentosCategoria(String nombre, int categoria);

	int obtenerTotalInstrumentosMarca(int marca);

	int obtenerTotalInstrumentosMarca(String nombre, int marca);

	List<Map<String, Object>> obtenerTodosInstrumentosParaListadoMarca(String nombre, int comienzo, int marca);

}
