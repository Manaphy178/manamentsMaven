package com.javier.manaments.servicesSetUp;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javier.manaments.model.*;

@Service
@Transactional
public class SetUpJPAImpl implements SetUp {

	@PersistenceContext
	private EntityManager entityManager;

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
			i1.setCategoria(cuerda);
			Instrumento i2 = new Instrumento("Guitarra Eléctrica", "Eléctrico", "Fender", "Media",
					"Guitarra eléctrica con pastillas dobles", 1200, new Date(System.currentTimeMillis()));
			i2.setCategoria(cuerda);
			Instrumento i3 = new Instrumento("Saxofón", "Acústico", "Selmer", "Alta", "Saxofón alto de latón", 2500,
					new Date(System.currentTimeMillis()));
			i3.setCategoria(viento);
			Instrumento i4 = new Instrumento("Batería Acústica", "Acústico", "Tama", "Media",
					"Batería acústica de 5 piezas", 800, new Date(System.currentTimeMillis()));
			i4.setCategoria(percusion);
			Instrumento i5 = new Instrumento("Teclado Electrónico", "Eléctrico", "Casio", "Media",
					"Teclado electrónico con 61 teclas y efectos", 300, new Date(System.currentTimeMillis()));
			i5.setCategoria(electrico);
			Instrumento i6 = new Instrumento("Trompeta", "Acústico", "Bach", "Alta", "Trompeta en Sib, cuerpo de latón",
					1800, new Date(System.currentTimeMillis()));
			i6.setCategoria(viento);
			Instrumento i7 = new Instrumento("Bajo Eléctrico", "Eléctrico", "Ibanez", "Media",
					"Bajo eléctrico de 4 cuerdas", 950, new Date(System.currentTimeMillis()));
			i7.setCategoria(cuerda);
			entityManager.persist(i1);
			entityManager.persist(i2);
			entityManager.persist(i3);
			entityManager.persist(i4);
			entityManager.persist(i5);
			entityManager.persist(i6);
			entityManager.persist(i7);
			Usuario u1 = new Usuario("Javier", "Gonzalez", "mana", "jgonzalez@centronelson.org", "123", 28440);
			Usuario u2 = new Usuario("Sergio", "Prados", "prapra", "sprados@centronelson.org", "123", 28440);
			entityManager.persist(u1);
			entityManager.persist(u2);

			System.out.println("registros iniciales realizados correctamente");

			com.javier.manaments.model.SetUp setUp = new com.javier.manaments.model.SetUp();
			setUp.setCompleto(true);
			entityManager.persist(setUp);
		}
	}

}
