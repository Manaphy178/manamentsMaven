package com.javier.manaments.model.tiposExtra;

import java.util.*;

public class ResumenPedido {

//	Productos que hay en el carrito
	private List<Map<String, Object>> instrumentos;

//	Datos del paso 1
	private String nombreCompleto;
	private String pais;
	private String telefono;
	private String direccion;
	private String provincia;
	private String poblacion;

//	Datos paso 2
	private String titularTarjeta;
	private String numeroTarjeta;
	private long cvv;
	private String tipoTarjeta;
	private String caducidadTarjeta;

//	Datos paso 3
	private String formaEntrega;
	private String extra;

	/**
	 * Si no hay ningun constructor, java incorpora uno vacio, que no pide nada, por
	 * defecto
	 */
	public List<Map<String, Object>> getInstrumentos() {
		return instrumentos;
	}

	public void setInstrumentos(List<Map<String, Object>> instrumentos) {
		this.instrumentos = instrumentos;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getTitularTarjeta() {
		return titularTarjeta;
	}

	public void setTitularTarjeta(String titularTarjeta) {
		this.titularTarjeta = titularTarjeta;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public long getCvv() {
		return cvv;
	}

	public void setCvv(long cvv) {
		this.cvv = cvv;
	}

	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public String getCaducidadTarjeta() {
		return caducidadTarjeta;
	}

	public void setCaducidadTarjeta(String caducidadTarjeta) {
		this.caducidadTarjeta = caducidadTarjeta;
	}

	public String getFormaEntrega() {
		return formaEntrega;
	}

	public void setFormaEntrega(String formaEntrega) {
		this.formaEntrega = formaEntrega;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

}
