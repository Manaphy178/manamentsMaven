package com.javier.manaments.servicesREST;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javier.manaments.constantesValidaciones.ConstantesValidaciones;
import com.javier.manaments.model.Usuario;
import com.javier.manaments.model.tiposExtra.ResumenPedido;
import com.javier.manaments.services.ServicioPedidos;

@RestController
public class ServicioRESTPedidos {

	@Autowired
	private ServicioPedidos servicioPedidos;

	@RequestMapping("realizar-pedido-paso1")
	public String realizarPedidoPaso1(String nombre, String pais, String telefono, String direccion, String provincia,
			String poblacion, HttpServletRequest request) {
		// Patrones de validación
		Pattern patternNombreCompleto = Pattern.compile(ConstantesValidaciones.regExpNombreCompleto,
				Pattern.CASE_INSENSITIVE);
		Pattern patternPais = Pattern.compile(ConstantesValidaciones.regExpPais, Pattern.CASE_INSENSITIVE);
		Pattern patternTelefono = Pattern.compile(ConstantesValidaciones.regExpTelefono);
		Pattern patternDireccion = Pattern.compile(ConstantesValidaciones.regExpDireccion, Pattern.CASE_INSENSITIVE);
		Pattern patternProvincia = Pattern.compile(ConstantesValidaciones.regExpProvincia, Pattern.CASE_INSENSITIVE);
		Pattern patternPoblacion = Pattern.compile(ConstantesValidaciones.regExpPoblacion, Pattern.CASE_INSENSITIVE);

		// Creación de los Matchers
		Matcher matcherNombreCompleto = patternNombreCompleto.matcher(nombre);
		Matcher matcherPais = patternPais.matcher(pais);
		Matcher matcherTelefono = patternTelefono.matcher(telefono);
		Matcher matcherDireccion = patternDireccion.matcher(direccion);
		Matcher matcherProvincia = patternProvincia.matcher(provincia);
		Matcher matcherPoblacion = patternPoblacion.matcher(poblacion);

		// Verificación de validación
		if (!matcherNombreCompleto.matches()) {
			return "Nombre completo incorrecto desde el lado del servidor";
		}
		if (!matcherPais.matches()) {
			return "País incorrecto desde el lado del servidor";
		}
		if (!matcherTelefono.matches()) {
			return "Número de teléfono incorrecto desde el lado del servidor";
		}
		if (!matcherDireccion.matches()) {
			return "Dirección incorrecta desde el lado del servidor";
		}
		if (!matcherProvincia.matches()) {
			return "Provincia incorrecta desde el lado del servidor";
		}
		if (!matcherPoblacion.matches()) {
			return "Población incorrecta desde el lado del servidor";
		}

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
	public ResumenPedido realizarPerdidoPaso2(String tarjeta, String numero, String titular, long cvv, String caducidad,
			HttpServletRequest request) {
		// Patrones de validación
		Pattern patternTipoTarjeta = Pattern.compile(ConstantesValidaciones.regExpTipoCarta);
		Pattern patternNumeroTarjeta = Pattern.compile(ConstantesValidaciones.regExpNumeroTarjeta);
		Pattern patternTitularTarjeta = Pattern.compile(ConstantesValidaciones.regExpTitularTarjeta,
				Pattern.CASE_INSENSITIVE);
		Pattern patternCodigoSeguridad = Pattern.compile(ConstantesValidaciones.regExpCodigoSeguridad);
		Pattern patternFechaCaducidad = Pattern.compile(ConstantesValidaciones.regExpFechaCaducidad);

		// Creación de los Matchers
		Matcher matcherTipoTarjeta = patternTipoTarjeta.matcher(tarjeta);
		Matcher matcherNumeroTarjeta = patternNumeroTarjeta.matcher(numero);
		Matcher matcherTitularTarjeta = patternTitularTarjeta.matcher(titular);
		Matcher matcherCodigoSeguridad = patternCodigoSeguridad.matcher(String.valueOf(cvv));
		Matcher matcherFechaCaducidad = patternFechaCaducidad.matcher(caducidad);

		// Verificación de validación
		if (!matcherTipoTarjeta.matches()) {
			System.err.println("Tipo de tarjeta incorrecto desde el lado del servidor");
		}
		if (!matcherNumeroTarjeta.matches()) {
			System.err.println("Número de tarjeta incorrecto desde el lado del servidor");
		}
		if (!matcherTitularTarjeta.matches()) {
			System.err.println("Titular de la tarjeta incorrecto desde el lado del servidor");
		}
		if (!matcherCodigoSeguridad.matches()) {
			System.err.println("Código de seguridad (CVV) incorrecto desde el lado del servidor");
		}
		if (!matcherFechaCaducidad.matches()) {
			System.err.println("Fecha de caducidad incorrecta desde el lado del servidor");
		}

		Usuario u = (Usuario) request.getSession().getAttribute("usuario");
		servicioPedidos.procesarPaso2(titular, numero, cvv, tarjeta, caducidad, u.getId());
		ResumenPedido resumen = servicioPedidos.obtenerResumenDelPedido(u.getId());
		return resumen;
	}

	@RequestMapping("resumen-pedido")
	public ResumenPedido resumirPedido(String formaEntrega, String extra, HttpServletRequest request) {
		System.err.println("Entro en resumir pedido");
		Usuario u = (Usuario) request.getSession().getAttribute("usuario");
		servicioPedidos.procesarPaso3(formaEntrega, extra, u.getId());
		ResumenPedido resumen = servicioPedidos.obtenerResumenDelPedido(u.getId());
		return resumen;
	}

	@RequestMapping("confirmar-pedido")
	public String confirmarPedido(HttpServletRequest request) {
		Usuario u = (Usuario) request.getSession().getAttribute("usuario");
		servicioPedidos.confirmarPedido(u.getId());
		return "pedido completado";
	}
}
