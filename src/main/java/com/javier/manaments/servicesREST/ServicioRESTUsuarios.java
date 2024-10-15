package com.javier.manaments.servicesREST;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.javier.manaments.model.Usuario;
import com.javier.manaments.services.ServicioUsuarios;

@Controller
public class ServicioRESTUsuarios {

	@Autowired
	private ServicioUsuarios serviciosUsuarios;

	@RequestMapping("registrar-usuario-cliente")
	public ResponseEntity<String> registrarUsuario(String nombre, String apellido, String nomUsuario, String pass,
			String email, long codPostal) {
		Usuario u = new Usuario(nombre, apellido, nomUsuario, email, pass, codPostal);
//		Lo suyo seria validar antes de registrar
		serviciosUsuarios.registrarUsuario(u);
		return new ResponseEntity<String>("usuario registrado correctamente", HttpStatus.OK);

	}

	@RequestMapping("identificar-usuario")
	public ResponseEntity<String> identificarUsuario(String email, String pass, HttpServletRequest request) {
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
		return new ResponseEntity<String>(respuesta, HttpStatus.OK);
	}
}
