package com.javier.manaments.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javier.manaments.model.Pedido;
import com.javier.manaments.model.estadosPedido.EstadosPedido;
import com.javier.manaments.services.ServicioPedidos;

@Controller
@RequestMapping("admin/")

public class ControladorPedidos {

	@Autowired
	private ServicioPedidos servicioPedidos;

	@RequestMapping("pedidos")
	public String obtenerPedidos(Model model) {
		model.addAttribute("pedidos", servicioPedidos.obtenerPedido());
		return "admin/pedidos";
	}

	@RequestMapping("ver-detalles-pedido")
	public String verDetallesPedido(String id, Model model) {
		Pedido p = servicioPedidos.obtenerPedidoPorId(Integer.parseInt(id));
		model.addAttribute("pedido", p);
//		vamos a darle a la vista tambien, los valores del desplegable
//		de estados del pedido
		Map<String, String> estados = new HashMap<String, String>();
		estados.put(EstadosPedido.INCOMPLETO.name(), "iniciado por el usuario");
		estados.put(EstadosPedido.COMPLETO.name(), "completado por el usuario");
		estados.put(EstadosPedido.FINALIZADO.name(), "pedido ya enviado");
		model.addAttribute("estados", estados);

		return "admin/pedido-detalles";

	}
}
