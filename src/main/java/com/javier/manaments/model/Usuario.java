package com.javier.manaments.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Usuario {
	@Id
	@GeneratedValue
	private long id;
	private String nombre;
	private String apellido;
	private String nomUsuario;
	@Column(unique = true)
	private String email;
	private String pass;
	private long codPostal;
	@OneToOne
	private Carrito carrito;
	public Usuario() {

	}

	public Usuario(String nombre, String apellido, String nomUsuario, String email, String pass, long codPostal) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.nomUsuario = nomUsuario;
		this.email = email;
		this.pass = pass;
		this.codPostal = codPostal;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNomUsuario() {
		return nomUsuario;
	}

	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public long getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(long codPostal) {
		this.codPostal = codPostal;
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", nomUsuario=" + nomUsuario
				+ ", email=" + email + ", pass=" + pass + ", codPostal=" + codPostal + "]";
	}

}
