package com.javier.manaments.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "tabla_instrumentos")
public class Instrumento {

//	asociaciones
	@ManyToOne()
	private Categoria categoria;

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "nombre_instrumento", length = 120)
	private String nombre;
	private String tipo;
	private String marca;
	private String gamma;
	@Column(length = 650)
	private String description;
	@Transient
	private MultipartFile foto;
	private double precio;
	private Date ultimaModificacion;

	public Instrumento() {
	}

	public Instrumento(String nombre, String tipo, String marca, String gamma, String description, double precio,
			Date ultimaModificacion) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.marca = marca;
		this.gamma = gamma;
		this.description = description;
		this.precio = precio;
		this.ultimaModificacion = ultimaModificacion;
	}

	public Instrumento(String nombre, String tipo, String marca, String gamma, String description, double precio) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.marca = marca;
		this.gamma = gamma;
		this.description = description;
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getGamma() {
		return gamma;
	}

	public void setGamma(String gamma) {
		this.gamma = gamma;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MultipartFile getFoto() {
		return foto;
	}

	public void setFoto(MultipartFile foto) {
		this.foto = foto;
	}

	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}

	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Instrumento [categoria=" + categoria + ", id=" + id + ", nombre=" + nombre + ", tipo=" + tipo
				+ ", marca=" + marca + ", gamma=" + gamma + ", description=" + description + ", foto=" + foto
				+ ", precio=" + precio + ", ultimaModificacion=" + ultimaModificacion + "]";
	}

}
