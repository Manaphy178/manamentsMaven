package com.javier.manaments.model.tiposExtra;

import java.util.List;
import java.util.Map;

public class InfoInstrumentosListado {

	private List<Map<String, Object>> instrumentos;
	private int totalInstrumentos;

	public List<Map<String, Object>> getInstrumentos() {
		return instrumentos;
	}

	public void setInstrumentos(List<Map<String, Object>> instrumentos) {
		this.instrumentos = instrumentos;
	}

	public int getTotalInstrumentos() {
		return totalInstrumentos;
	}

	public void setTotalInstrumentos(int totalInstrumentos) {
		this.totalInstrumentos = totalInstrumentos;
	}

}
