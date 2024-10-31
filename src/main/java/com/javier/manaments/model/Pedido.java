package com.javier.manaments.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pedido {

	@OneToMany(mappedBy = "pedido")
	private List<ProductoPedido> productoPedidos = new ArrayList<ProductoPedido>();

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

//	Se pide paso 3

	private String formaEnvio;
	private String extra;

//	Fin paso 3

	@ManyToOne(targetEntity = Usuario.class, optional = false)
	private Usuario usuario;

	@Id
	@GeneratedValue
	private int id;

	
	

	public List<ProductoPedido> getProductoPedidos() {
		return productoPedidos;
	}

	public void setProductoPedidos(List<ProductoPedido> productoPedidos) {
		this.productoPedidos = productoPedidos;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public String getFormaEnvio() {
		return formaEnvio;
	}

	public void setFormaEnvio(String formaEnvio) {
		this.formaEnvio = formaEnvio;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
