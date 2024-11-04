package com.javier.manaments.servicesREST;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javier.manaments.constantesValidaciones.ConstantesValidaciones;
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
		if (serviciosUsuarios.comprobarEmailExiste(email)) {
			return "email ya en uso";
		}
//		Validar datos:
		Pattern patternNombre = Pattern.compile(ConstantesValidaciones.regExpNombreRegistroUsuario);
		Pattern patternApellido = Pattern.compile(ConstantesValidaciones.regExpApellidoRegistroUsuario);
		Pattern patternUsername = Pattern.compile(ConstantesValidaciones.regExpUsernameRegistroUsuario);
		Pattern patternEmail = Pattern.compile(ConstantesValidaciones.regExpEmailRegistroUsuario);
		Pattern patternPassword = Pattern.compile(ConstantesValidaciones.regExpPassRegistroUsuario);
		Pattern patternCodPostal = Pattern.compile(ConstantesValidaciones.regExpCodPostalRegistroUsuario);

		Matcher matcherNombre = patternNombre.matcher(nombre);
		Matcher matcherApellido = patternApellido.matcher(apellido);
		Matcher matcherUsername = patternUsername.matcher(nomUsuario);
		Matcher matcherEmail = patternEmail.matcher(email);
		Matcher matcherPassword = patternPassword.matcher(pass);
		Matcher matcherCodPostal = patternCodPostal.matcher(String.valueOf(codPostal));

		if (!matcherNombre.matches()) {
			return "nombre incorrecto desde el lado del servidor";
		}
		if (!matcherApellido.matches()) {
			return "apellido incorrecto desde el lado del servidor";
		}
		if (!matcherUsername.matches()) {
			return "nombre de usuario incorrecto desde el lado del servidor";
		}
		if (!matcherEmail.matches()) {
			return "email incorrecto desde el lado del servidor";
		}
		if (!matcherPassword.matches()) {
			return "contraseña incorrecta desde el lado del servidor";
		}
		if (!matcherCodPostal.matches()) {
			return "código postal incorrecto desde el lado del servidor";
		}

		Usuario u = new Usuario(nombre, apellido, nomUsuario, email, pass, codPostal);
		serviciosUsuarios.registrarUsuario(u);
		return "usuario registrado correctamente";

	}

	@RequestMapping("identificar-usuario")
	public RespuestaLogin identificarUsuario(String email, String pass, HttpServletRequest request) {
		Usuario u = serviciosUsuarios.obtenerUsuarioPorEmailPass(email, pass);
		RespuestaLogin rl = null;
		if (u != null) {
			rl = new RespuestaLogin("ok", u.getNombre());
			// vamos a meter en sesion, la informacion del usuario que se ha identificado
			request.getSession().setAttribute("usuario", u);
		} else {
			rl = new RespuestaLogin("no-ok", "");
		}
		return rl;
	}

	@RequestMapping("cerrar-sesion-usuario")
	public String cerrarSesionUsuario(HttpServletRequest request) {
		request.getSession().invalidate();// esto elimina la sesion
		return "ok";
	}
}
