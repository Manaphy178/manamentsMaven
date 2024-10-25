package com.javier.manaments.servicesREST;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javier.manaments.model.Usuario;
import com.javier.manaments.services.ServicioCarrito;

@RestController
public class ServicioRESTCarrito {
	@Autowired
	private ServicioCarrito servicioCarrito;

	@RequestMapping("agregar-producto-carrito")
	public String agregarProductoAlCarrito(@RequestParam("id") Integer id, @RequestParam("cantidad") Integer cantidad,
			HttpServletRequest request) {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		servicioCarrito.agregarProducto(id, usuario.getId(), cantidad);
		return "ok";
	}

	@RequestMapping("obtener-productos-carrito")
	public List<Map<String, Object>> obtenerProductosCarrito(HttpServletRequest request) {
		return servicioCarrito
				.obtenerProductosCarritoUsuario(((Usuario) request.getSession().getAttribute("usuario")).getId());
	}

	@RequestMapping("restar-producto-carrito")
	public String restarProductoAlCarrito(@RequestParam("id") Integer id, @RequestParam("cantidad") Integer cantidad,
			HttpServletRequest request) {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		servicioCarrito.restarProductoCarrito(id, usuario.getId(), cantidad);
		return "ok";
	}

	@RequestMapping("aumentar-producto-carrito")
	public String aumentarProductoAlCarrito(@RequestParam("id") Integer id, @RequestParam("cantidad") Integer cantidad,
			HttpServletRequest request) {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		servicioCarrito.aumentarProductoCarrito(id, usuario.getId(), cantidad);
		return "ok";
	}

	@RequestMapping("borrar-producto-carrito")
	public String borrarProductoAlCarrito(@RequestParam("id") Integer id, HttpServletRequest request) {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		servicioCarrito.borrarProductoCarrito(id, usuario.getId());
		return "ok";
	}

}
