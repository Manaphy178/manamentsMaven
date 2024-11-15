package com.javier.manaments.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Marca {

	@Id
	@GeneratedValue
	@Column(name="id_marca")
	private int id;

	/**
	 * CHATGPT ESTA PARTE
	 */
	@OneToMany(mappedBy = "marca", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Instrumento> instrumentos;

	@ManyToMany
	@JoinTable(name = "marca_categoria", joinColumns = @JoinColumn(name = "marca_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private List<Categoria> categorias = new ArrayList<Categoria>();

	@Size(min = 3, max = 40, message = "El nombre de la marca")
	@NotEmpty(message = "El nombre no puede estar vacio")
	@Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ]{3,40}$", message = "El nombre solo puede tener numeros,letras y espacios en blanco")
	@Column(name = "nombre_marca", length = 120)
	private String nombre;

	public Marca() {
	}

	public Marca(
			@Size(min = 3, max = 40, message = "El nombre de la marca") @NotEmpty(message = "El nombre no puede estar vacio") @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ]{3,40}$", message = "El nombre solo puede tener numeros,letras y espacios en blanco") String nombre) {
		super();
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public List<Instrumento> getInstrumentos() {
		return instrumentos;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void addCategoria(Categoria categoria) {
		categorias.add(categoria);
	}

	public String getNombre() {
		return nombre;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setInstrumentos(List<Instrumento> instrumentos) {
		this.instrumentos = instrumentos;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
