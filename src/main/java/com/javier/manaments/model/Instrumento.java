package com.javier.manaments.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "tabla_instrumentos")
public class Instrumento {

//	Vamos a guardar la portada en base de datos
	@Lob
	@Column(name = "imagen_portada")
	private byte[] imagenPortada;

//	Campo que me ayuda a gestionar la id de categoria del desplegable
	@Transient
	private int idCategoria;

//	asociaciones
	@ManyToOne(optional = true)
	private Categoria categoria;

//	Valores de Instrumento

	@Id
	@GeneratedValue
	private int id;

	@Size(min = 3, max = 40, message = "El nombre del intrumento tiene que tener entre 3 y 40 caracteres")
	@NotEmpty(message = "El nombre no puede estar vacio")
	@Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ]{3,40}$", message = "El nombre solo puede tener numeros,letras y espacios en blanco")
	@Column(name = "nombre_instrumento", length = 120)
	private String nombre;

	@Size(min = 3, max = 40, message = "La marca del intrumento tiene que tener entre 3 y 40 caracteres")
	@NotEmpty(message = "La marca no puede estar vacio")
	@Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ]{3,40}$", message = "La marca solo puede tener numeros,letras y espacios en blanco")
	private String marca;

	@Column(length = 650)
	private String descripcion;

	@NotNull(message = "debes insertar un precio")
	@Min(value = 1, message = "el precio minimo es 1 euro")
	@Max(value = 1000000, message = "el precio maximo es 1000000 euros")
	private double precio;

	@Transient
	private MultipartFile archivoSubido;

	private String tipo;

	private String gamma;
//	Añadir todas las cosas de validacion
	private String estado;

	private int ventas;

	private Date ultimaModificacion;
	private Date fechaCreacion;

	public Instrumento() {
	}

	public Instrumento(String nombre, String descripcion, String marca, double precio) {
		this.nombre = nombre;
		this.marca = marca;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public Instrumento(String nombre, String tipo, String marca, String gamma, String descripcion, double precio,
			Date fechaCreacion) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.marca = marca;
		this.gamma = gamma;
		this.descripcion = descripcion;
		this.precio = precio;
		this.fechaCreacion = fechaCreacion;
	}

	public Instrumento(String nombre, String tipo, String marca, String gamma, String descripcion, double precio) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.marca = marca;
		this.gamma = gamma;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public byte[] getImagenPortada() {
		return imagenPortada;
	}

	public void setImagenPortada(byte[] imagenPortada) {
		this.imagenPortada = imagenPortada;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public MultipartFile getArchivoSubido() {
		return archivoSubido;
	}

	public void setArchivoSubido(MultipartFile archivoSubido) {
		this.archivoSubido = archivoSubido;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getGamma() {
		return gamma;
	}

	public void setGamma(String gamma) {
		this.gamma = gamma;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getVentas() {
		return ventas;
	}

	public void setVentas(int ventas) {
		this.ventas = ventas;
	}

	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}

	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Override
	public String toString() {
		return "Instrumento [categoria=" + categoria + ", id=" + id + ", nombre=" + nombre + ", tipo=" + tipo
				+ ", marca=" + marca + ", gamma=" + gamma + ", descripcion=" + descripcion + ", foto=" + archivoSubido
				+ ", precio=" + precio + ", ultimaModificacion=" + ultimaModificacion + "]";
	}

}
