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
	private String pais;
	private String telefono;
	private String direccion;
	private String provincia;
	private String poblacion;

//	Fin paso 1

//	Se pide en paso 2

	private String titularTarjeta;
	private String numeroTarjeta;
	private long cvv;
	private String tipoTarjeta;
	private String caducidadTarjeta;

//	Fin paso 2

	@ManyToOne(targetEntity = Usuario.class, optional = false)
	private Usuario usuario;

	@Id
	@GeneratedValue
	private int id;

	public String getEstado() {
		return estado;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public String getPais() {
		return pais;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getProvincia() {
		return provincia;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public String getTitularTarjeta() {
		return titularTarjeta;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public long getCvv() {
		return cvv;
	}

	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	public String getCaducidadTarjeta() {
		return caducidadTarjeta;
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

	public void setPais(String pais) {
		this.pais = pais;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public void setTitularTarjeta(String titularTarjeta) {
		this.titularTarjeta = titularTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public void setCvv(long cvv) {
		this.cvv = cvv;
	}

	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public void setCaducidadTarjeta(String caducidadTarjeta) {
		this.caducidadTarjeta = caducidadTarjeta;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setId(int id) {
		this.id = id;
	}

	

}
