package com.javier.manaments.services;

import com.javier.manaments.model.tiposExtra.ResumenPedido;

public interface ServicioPedidos {

	void procesarPaso1(String nombreCompleto, String direccion, String provincia, long idUsuario);

	void procesarPaso2(String titular, String numero, String tipoTarjeta, long idUsuario);

	ResumenPedido obtenerResumenDelPedido(long idUsuario);

	void confirmarPedido(long idUsuario);
}
