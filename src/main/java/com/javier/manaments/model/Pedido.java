package com.javier.manaments.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Pedido {

	private String estado;

//	Se pide en paso 1

	private String nombreCompleto;
	private String direccion;
	private String provincia;

//	Fin paso 1

//	Se pide en paso 2

	private String titularTarjeta;
	private String numeroTarjeta;
	private String tipoTarjeta;

//	Fin paso 2

	@ManyToOne(targetEntity = Usuario.class, optional = false)
	private Usuario usuario;

	@Id
	@GeneratedValue
	private int id;

//	Se pide en paso 3

//	Fin paso 3

	public String getEstado() {
		return estado;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getProvincia() {
		return provincia;
	}

	public String getTitularTarjeta() {
		return titularTarjeta;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public int getId() {
		return id;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public void setTitularTarjeta(String titularTarjeta) {
		this.titularTarjeta = titularTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setId(int id) {
		this.id = id;
	}
}
