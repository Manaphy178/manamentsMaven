package com.javier.manaments.servicesREST;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javier.manaments.model.Usuario;
import com.javier.manaments.services.ServicioUsuarios;
import com.javier.manaments.servicesREST.respuestas.RespuestaLogin;

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
	public RespuestaLogin identificarUsuario(String email, String pass, HttpServletRequest request) {
		Usuario u = serviciosUsuarios.obtenerUsuarioPorEmailPass(email, pass);
		RespuestaLogin rl = null;
		if( u != null) {
			rl = new RespuestaLogin("ok", u.getNombre());
			//vamos a meter en sesion, la informacion del usuario que se ha identificado
			request.getSession().setAttribute("usuario", u);
		}else {
			rl = new RespuestaLogin("no-ok", "");
		}
		return rl;
	}
	
	@RequestMapping("cerrar-sesion-usuario")
	public String cerrarSesionUsuario(HttpServletRequest request) {
		request.getSession().invalidate();//esto elimina la sesion
		return "ok";
	}
}
