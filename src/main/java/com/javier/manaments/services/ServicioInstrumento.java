package com.javier.manaments.services;

import java.util.List;

import com.javier.manaments.model.Instrumento;


public interface ServicioInstrumento {

	void registrarInstrumento(Instrumento i);

	List<Instrumento> obtenerInstrumentos();

	void borrarInstrumento(int id);

	Instrumento obtenerInstrumentoPorId(int id);

	void actualizarInstrumento(Instrumento i);

}
