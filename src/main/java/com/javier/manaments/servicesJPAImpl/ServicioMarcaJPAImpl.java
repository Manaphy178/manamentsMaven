package com.javier.manaments.servicesJPAImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javier.manaments.model.Marca;
import com.javier.manaments.services.ServicioMarca;

@Service
@Transactional
public class ServicioMarcaJPAImpl implements ServicioMarca {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Marca> obtenerMarcas() {
		List<Marca> m = entityManager.createQuery("select m from Marca m").getResultList();
		return m;
	}

	@Override
	public Marca obtenerMarcaPorId(int id) {
		return entityManager.find(Marca.class, id);
	}

}
