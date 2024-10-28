package com.javier.manaments.servicesREST;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javier.manaments.model.Usuario;
import com.javier.manaments.services.ServicioPedidos;

@RestController
public class ServicioRESTPedidos {

	@Autowired
	private ServicioPedidos servicioPedidos;

	@RequestMapping("realizar-pedido-paso1")
	public String realizarPedidoPaso1(String nombre, String pais, String telefono, String direccion, String provincia,
			String poblacion, HttpServletRequest request) {
		/**
		 * al completar el paso 1 vamos a generar una instancia/registro de la entidad
		 * Pedido con el campo estado a INCOMPLETO. Cuando el usuario complete todos los
		 * pasos, marcaremos el estado del pedido a COMPLETO
		 */
		Usuario u = (Usuario) request.getSession().getAttribute("usuario");
		servicioPedidos.procesarPaso1(nombre, pais, telefono, direccion, provincia, poblacion, u.getId());
		return "ok";
	}

	@RequestMapping("realizar-pedido-paso2")
	public String realizarPerdidoPaso2(String tipoTarjeta, String numeroTarjeta, String titularTarjeta, long cvv,
			String caducidadTarjeta, HttpServletRequest request) {
		Usuario u = (Usuario) request.getSession().getAttribute("usuario");
		servicioPedidos.procesarPaso2(titularTarjeta, numeroTarjeta, cvv, tipoTarjeta, caducidadTarjeta, u.getId());
		return "ok";
	}
}
