package com.javier.manaments.servicesREST;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javier.manaments.model.Usuario;
import com.javier.manaments.services.ServicioUsuarios;

@RestController
public class ServicioRESTUsuarios {

	@Autowired
	private ServicioUsuarios serviciosUsuarios;

	@RequestMapping("registrar-usuario-cliente")
	public String registrarUsuario(String nombre, String apellido, String nomUsuario, String pass, String email,
			long codPostal) {
		Usuario u = new Usuario(nombre, apellido, nomUsuario, email, pass, codPostal);
//		Lo suyo seria validar antes de registrar
		serviciosUsuarios.registrarUsuario(u);
		return "usuario registrado correctamente";

	}

	@RequestMapping("identificar-usuario")
	public String identificarUsuario(String email, String pass, HttpServletRequest request) {
		String respuesta = "";
		Usuario u = serviciosUsuarios.obtenerUsuarioPorEmailPass(email, pass);
		if (u != null) {
			respuesta = "Bienvenido " + u.getNombre() + " ya puedes comprar en la tienda";
			/*
			 * vamos a meter en sesion, la informacion del usuario que se ha identificado
			 */
			request.getSession().setAttribute("usuario", u);
		} else {
			respuesta = "email o pass incorrectos";
		}
		return respuesta;
	}
	@RequestMapping("cerrar-sesion-usuario")
	public String cerrarSesionUsuario(HttpServletRequest request) {
		request.getSession().invalidate();//esto elimina la sesion
		return "ok";
	}
}
