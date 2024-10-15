package com.javier.manaments.model;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Carrito {

	@OneToOne
	private Usuario usuario;

	private Date ultimoUso;

	@Id
	@GeneratedValue
	private long id;

	/*
	 * En java si no existe ningun constructor en la clase resulta ser que la clase
	 * se le incorpora uno vacio por defecto
	 */

	/*
	 * la regla anterior no se cumple a la que haya al menos un constructor definido
	 */

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getUltimoUso() {
		return ultimoUso;
	}

	public void setUltimoUso(Date ultimoUso) {
		this.ultimoUso = ultimoUso;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
