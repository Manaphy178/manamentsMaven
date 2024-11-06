package com.javier.manaments.constantesValidaciones;

public class ConstantesValidaciones {

	// Expresiones para registro de usuario
	public final static String regExpNombreRegistroUsuario = "^[a-z áéíóúñ]{2,10}$";
	public final static String regExpApellidoRegistroUsuario = "^[a-z áéíóúñ]{2,30}$";
	public final static String regExpUsernameRegistroUsuario = "^[a-z0-9áéíóúñ]{3,15}$";
	public final static String regExpEmailRegistroUsuario = "^([a-z0-9_\\.-]+)@([0-9a-z\\.-]+)\\.([a-z\\.]+)$";
	public final static String regExpPassRegistroUsuario = "^[a-z0-9áéíóúñ]{3,10}$";
	public final static String regExpCodPostalRegistroUsuario = "^[0-9]{5}$";

	// Expresiones para paso 1 de checkout
	public final static String regExpNombreCompleto = "^[a-zA-Z ñáéíóú]{2,30}$";
	public final static String regExpPais = "^[a-z ñáéíóú]{2,30}$";
	public final static String regExpTelefono = "^\\d{3,15}$";
	public final static String regExpDireccion = "^[a-z0-9 ñáéíóú#.,-]{5,100}$";
	public final static String regExpProvincia = "^[a-z ñáéíóú]{2,30}$";
	public final static String regExpPoblacion = "^[a-z ñáéíóú]{2,30}$";

	// Expresiones para paso 2 de checkout
	public final static String regExpTipoCarta = "^[a-zA-Z_\\s]+$";
	public final static String regExpNumeroTarjeta = "^\\d{10,20}$";
	public final static String regExpTitularTarjeta = "^[a-z  ñáéíóú]{3,50}$";
	public final static String regExpCodigoSeguridad = "^\\d{3,4}$";
	public final static String regExpFechaCaducidad = "^(0[1-9]|1[0-2])\\/([0-9]{2})$";

	// Cantidad de productos en carrito
	public final static String regExpCantidad = "^([1-9]|20)$";
}
