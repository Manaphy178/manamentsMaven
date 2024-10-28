package com.javier.manaments.services;

import com.javier.manaments.model.tiposExtra.ResumenPedido;

public interface ServicioPedidos {

	void procesarPaso1(String nombreCompleto, String pais, String telefono, String direccion, String provincia,
			String poblacion, long idUsuario);

	void procesarPaso2(String titular, String numero, long cvv, String tipoTarjeta, String caducidadTarjeta,
			long idUsuario);

	ResumenPedido obtenerResumenDelPedido(long idUsuario);

	void confirmarPedido(long idUsuario);
}
