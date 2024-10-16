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
			Categoria cuerda = new Categoria("Cuerda", "Categoria de instrumentos de cuerda");
			Categoria viento = new Categoria("Viento", "Categoria de instrumentos de viento");
			Categoria percusion = new Categoria("Percusion", "Categoria de instrumetos de percusion");
			entityManager.persist(cuerda);
			entityManager.persist(viento);
			entityManager.persist(percusion);

			/*
			 * la idea es que si no hay ningun registro en la tabla de setup pues preparemos
			 * los registros para todo el sistema
			 */
			Instrumento i1 = new Instrumento("Bajo", "bajo", "marca", "gamma", "descripcion", 1,
					new Date(System.currentTimeMillis()));
			i1.setCategoria(cuerda);
			Instrumento i2 = new Instrumento("guitarra", "guitarra", "marca", "gamma", "descripcion", 2,
					new Date(System.currentTimeMillis()));
			i2.setCategoria(cuerda);
			Instrumento i3 = new Instrumento("bateria", "bateria", "marca", "gamma", "descripcion", 3,
					new Date(System.currentTimeMillis()));
			i3.setCategoria(percusion);
			entityManager.persist(i1);
			entityManager.persist(i2);
			entityManager.persist(i3);
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
