package com.javier.manaments.servicesJPAImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javier.manaments.model.Categoria;
import com.javier.manaments.services.ServicioCategoria;

@Service
@Transactional
public class ServicioCategoriaJPAImpl implements ServicioCategoria {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Categoria> obtenerCategorias() {
		List<Categoria> c = entityManager.createQuery("SELECT c FROM Categoria c").getResultList();
		return c;
	}

	@Override
	public Categoria obtenerCategoriaPorId(int id) {
		return entityManager.find(Categoria.class, id);
	}

}
