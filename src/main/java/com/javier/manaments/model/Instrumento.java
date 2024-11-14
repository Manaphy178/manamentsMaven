package com.javier.manaments.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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

	@Size(min = 3, max = 80, message = "El nombre del instrumento tiene que tener entre 3 y 80 caracteres")
	@NotEmpty(message = "El nombre no puede estar vacio")
	@Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ-]+$", message = "El nombre solo puede tener números, letras, espacios y guiones")
	@Column(name = "nombre_instrumento", length = 120)
	private String nombre;

	@Transient
	private int idMarca;

	@ManyToOne
	@JoinColumn(name = "marca_id")
	private Marca marca;

	@Column(length = 2000)
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

	public Instrumento(String nombre, String descripcion, Marca marca, double precio) {
		this.nombre = nombre;
		this.marca = marca;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public Instrumento(Categoria categoria,
			@Size(min = 3, max = 80, message = "El nombre del intrumento tiene que tener entre 3 y 80 caracteres") @NotEmpty(message = "El nombre no puede estar vacio") @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ-]{3,80}$", message = "El nombre solo puede tener numeros,letras y espacios en blanco") String nombre,
			Marca marca, String descripcion,
			@NotNull(message = "debes insertar un precio") @Min(value = 1, message = "el precio minimo es 1 euro") @Max(value = 1000000, message = "el precio maximo es 1000000 euros") double precio,
			String tipo, String gamma, String estado, Date fechaCreacion) {
		super();
		this.categoria = categoria;
		this.nombre = nombre;
		this.marca = marca;
		this.descripcion = descripcion;
		this.precio = precio;
		this.tipo = tipo;
		this.gamma = gamma;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
	}

	public Instrumento(String nombre, String tipo, Marca marca, String gamma, String descripcion, double precio) {
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

	public int getIdCategoria() {
		return idCategoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getIdMarca() {
		return idMarca;
	}

	public Marca getMarca() {
		return marca;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public MultipartFile getArchivoSubido() {
		return archivoSubido;
	}

	public String getTipo() {
		return tipo;
	}

	public String getGamma() {
		return gamma;
	}

	public String getEstado() {
		return estado;
	}

	public int getVentas() {
		return ventas;
	}

	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setImagenPortada(byte[] imagenPortada) {
		this.imagenPortada = imagenPortada;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void setArchivoSubido(MultipartFile archivoSubido) {
		this.archivoSubido = archivoSubido;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setGamma(String gamma) {
		this.gamma = gamma;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setVentas(int ventas) {
		this.ventas = ventas;
	}

	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
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
