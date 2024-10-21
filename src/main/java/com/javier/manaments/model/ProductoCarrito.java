package com.javier.manaments.model;

import javax.persistence.*;

@Entity
public class ProductoCarrito {

	@OneToOne
	@JoinColumn(referencedColumnName = "id")
	private Instrumento instrumento;

	@ManyToOne
	private Carrito carrito;

	private int cantidad;

	@Id
	@GeneratedValue
	private int id;

	public Instrumento getInstrumento() {
		return instrumento;
	}

	public void setInstrumento(Instrumento instrumento) {
		this.instrumento = instrumento;
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
