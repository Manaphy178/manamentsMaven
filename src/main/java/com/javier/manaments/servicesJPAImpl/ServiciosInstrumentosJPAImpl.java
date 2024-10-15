package com.javier.manaments.servicesJPAImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javier.manaments.model.Instrumento;
import com.javier.manaments.services.ServicioInstrumento;

@Service
@Transactional
public class ServiciosInstrumentosJPAImpl implements ServicioInstrumento {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void registrarInstrumento(Instrumento i) {
		entityManager.persist(i);
	}

	@Override
	public List<Instrumento> obtenerInstrumentos() {
		List<Instrumento> i = entityManager.createQuery("SELECT i FROM Instrumento i").getResultList();
		return i;
	}

	@Override
	public void actualizarInstrumento(Instrumento i) {
		entityManager.merge(i);
	}

	@Override
	public void borrarInstrumento(int id) {
		Instrumento i = entityManager.find(Instrumento.class, id);
		entityManager.remove(i);
	}

	@Override
	public Instrumento obtenerInstrumentoPorId(int id) {
		return entityManager.find(Instrumento.class, id);
	}

}
