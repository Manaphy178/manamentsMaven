package com.javier.manaments.services;

import java.util.List;
import java.util.Map;

import com.javier.manaments.model.Instrumento;

public interface ServicioInstrumento {

	void registrarInstrumento(Instrumento i);

	List<Instrumento> obtenerInstrumentos();

	void borrarInstrumento(int id);

	Instrumento obtenerInstrumentoPorId(int id);

	void actualizarInstrumento(Instrumento i);

	Map<String, Object> obtenerInstrumentoVerDetallesPorId(long id);

	List<Map<String, Object>> obtenerInstrumentosParaListado();

}
