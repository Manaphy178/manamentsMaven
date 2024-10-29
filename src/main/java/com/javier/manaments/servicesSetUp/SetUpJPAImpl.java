package com.javier.manaments.servicesSetUp;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javier.manaments.model.*;
import com.javier.manaments.services.ServicioCarrito;

@Service
@Transactional
public class SetUpJPAImpl implements SetUp {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ServicioCarrito servicioCarrito;

	@Override
	public void setUp() {
		/*
		 * vamos a usar una entidad par ver si ya hemos inicializado la base de datos
		 * dicha entidad la llamamos SetUp
		 */

		com.javier.manaments.model.SetUp registroSetUp = null;
		try {
			// Casi identico a: select s.* from tabla_setup as s
			registroSetUp = (com.javier.manaments.model.SetUp) entityManager.createQuery("select s from SetUp as s")
					.getSingleResult();
		} catch (Exception e) {
			System.err.println("No se encontro ningun registro de setup, comenzamos setup...");
		}
		if (registroSetUp == null || !registroSetUp.isCompleto()) {
//			Vamos a crear unas categorias antes de nada
			Categoria cuerda = new Categoria("Cuerda",
					"Instrumentos que producen sonido a través de cuerdas vibrantes");
			Categoria viento = new Categoria("Viento",
					"Instrumentos que producen sonido a través de la vibración de aire");
			Categoria percusion = new Categoria("Percusión",
					"Instrumentos que producen sonido a través de membranas o superficies que se golpean");
			Categoria electrico = new Categoria("Electrónico", "Instrumentos electrónicos o digitales");
			entityManager.persist(cuerda);
			entityManager.persist(viento);
			entityManager.persist(percusion);
			entityManager.persist(electrico);

			/*
			 * la idea es que si no hay ningun registro en la tabla de setup pues preparemos
			 * los registros para todo el sistema
			 */
			Instrumento i1 = new Instrumento("Guitarra Clásica", "Acústico", "Yamaha", "Alta",
					"Guitarra acústica clásica con cuerdas de nylon", 350, new Date(System.currentTimeMillis()));
			Instrumento i2 = new Instrumento("Guitarra Eléctrica", "Eléctrico", "Fender", "Media",
					"Guitarra eléctrica con pastillas dobles", 1200, new Date(System.currentTimeMillis()));
			Instrumento i3 = new Instrumento("Saxofón", "Acústico", "Selmer", "Alta", "Saxofón alto de latón", 2500,
					new Date(System.currentTimeMillis()));
			Instrumento i4 = new Instrumento("Batería Acústica", "Acústico", "Tama", "Media",
					"Batería acústica de 5 piezas", 800, new Date(System.currentTimeMillis()));
			Instrumento i5 = new Instrumento("Teclado Electrónico", "Eléctrico", "Casio", "Media",
					"Teclado electrónico con 61 teclas y efectos", 300, new Date(System.currentTimeMillis()));
			Instrumento i6 = new Instrumento("Trompeta", "Acústico", "Bach", "Alta", "Trompeta en Sib, cuerpo de latón",
					1800, new Date(System.currentTimeMillis()));
			Instrumento i7 = new Instrumento("Bajo Eléctrico", "Eléctrico", "Ibanez", "Media",
					"Bajo eléctrico de 4 cuerdas", 950, new Date(System.currentTimeMillis()));
			i1.setCategoria(cuerda);
			i2.setCategoria(cuerda);
			i3.setCategoria(viento);
			i4.setCategoria(percusion);
			i5.setCategoria(electrico);
			i6.setCategoria(viento);
			i7.setCategoria(cuerda);
			i1.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/instrumentos/1.jpg"));
			i2.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/instrumentos/2.jpg"));
			i3.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/instrumentos/3.jpg"));
			i4.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/instrumentos/4.jpg"));
			i5.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/instrumentos/5.jpg"));
			i6.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/instrumentos/6.jpg"));
			i7.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/instrumentos/7.jpg"));
			entityManager.persist(i1);
			entityManager.persist(i2);
			entityManager.persist(i3);
			entityManager.persist(i4);
			entityManager.persist(i5);
			entityManager.persist(i6);
			entityManager.persist(i7);
			Usuario u1 = new Usuario("Javier", "Gonzalez", "mana", "jgonzalez@centronelson.org", "123", 28440);
			Usuario u2 = new Usuario("Sergio", "Prados", "prapra", "sprados@centronelson.org", "123", 28440);
			Usuario u3 = new Usuario("Alba", "Rueda", "albitagambita", "alba@gmail.com", "123", 28440);
			entityManager.persist(u1);
			entityManager.persist(u2);
			entityManager.persist(u3);

//			Metemos algunos productos en el carrito del primer y segundo usuario
			servicioCarrito.agregarProducto(i1.getId(), u1.getId(), 5);
			servicioCarrito.agregarProducto(i3.getId(), u1.getId(), 1);
			servicioCarrito.agregarProducto(i5.getId(), u2.getId(), 2);
			servicioCarrito.agregarProducto(i7.getId(), u3.getId(), 10);
			System.out.println("registros iniciales realizados correctamente");

			com.javier.manaments.model.SetUp setUp = new com.javier.manaments.model.SetUp();
			setUp.setCompleto(true);
			entityManager.persist(setUp);
		}
	}// end setup

	private byte[] leerBytesDeRutaOrigen(String rutaOrigen) {
		byte[] info = null;
		try {
			URL url = new URL(rutaOrigen);
			info = IOUtils.toByteArray(url);
		} catch (IOException e) {
			System.err.println("La url esta mal");
			e.printStackTrace();
		}
		return info;

	}
}// end class
