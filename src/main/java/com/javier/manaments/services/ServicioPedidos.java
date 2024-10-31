package com.javier.manaments.services;

import java.util.List;

import com.javier.manaments.model.Pedido;
import com.javier.manaments.model.tiposExtra.ResumenPedido;

public interface ServicioPedidos {

//	Para administracion
	List<Pedido> obtenerPedido();

	Pedido obtenerPedidoPorId(int idPedido);

//	para la parte cliente
	void procesarPaso1(String nombreCompleto, String pais, String telefono, String direccion, String provincia,
			String poblacion, long idUsuario);

	void procesarPaso2(String titular, String numero, long cvv, String tipoTarjeta, String caducidadTarjeta,
			long idUsuario);

	void procesarPaso3(String formaEntrega, String extra, long idUsuario);

	ResumenPedido obtenerResumenDelPedido(long idUsuario);

	void confirmarPedido(long idUsuario);

	void actualizarEstadoPedido(int id, String estado);

}
