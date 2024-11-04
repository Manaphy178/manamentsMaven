package com.javier.manaments.constantesValidaciones;

public class ConstantesValidaciones {
	public final static String regExpNombreRegistroUsuario = "^[a-z áéíóúñ]{2,10}$";
	public final static String regExpApellidoRegistroUsuario = "^[a-z áéíóúñ]{2,30}$";
	public final static String regExpUsernameRegistroUsuario = "^[a-z0-9áéíóúñ]{3,15}$";
	public final static String regExpEmailRegistroUsuario = "^([a-z0-9_\\.-]+)@([0-9a-z\\.-]+)\\.([a-z\\.]+)$";
	public final static String regExpPassRegistroUsuario = "^[a-z0-9áéíóúñ]{3,10}$";
	public final static String regExpCodPostalRegistroUsuario = "^[0-9]{5}$";
}
