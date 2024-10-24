package com.javier.manaments.servicesREST.respuestas;

public class RespuestaLogin {
	private String operacion;
	
	private String usuario;

	public RespuestaLogin(String operacion, String usuario) {
		super();
		this.operacion = operacion;
		this.usuario = usuario;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
}
