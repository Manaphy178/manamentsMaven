package com.javier.manaments.servicesJPAImpl;

import java.util.List;

import javax.persistence.*;
import javax.persistence.Query;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javier.manaments.model.Usuario;
import com.javier.manaments.services.ServicioUsuarios;

@Service
@Transactional
public class ServicioUsuariosJPAImpl implements ServicioUsuarios {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void registrarUsuario(Usuario u) {
		entityManager.persist(u);
	}

	@Override
	public List<Usuario> obtenerUsuarios() {
		List<Usuario> us = entityManager.createQuery("SELECT u FROM Usuario u").getResultList();

		return us;
	}

	@Override
	public Usuario obtenerUsuarioPorEmailPass(String email, String pass) {
//		Las consultas con query son con jpql
		Query query = entityManager.createQuery("select u from Usuario u where u.email = :email and u.pass = :pass");
		query.setParameter("email", email);
		query.setParameter("pass", pass);
		List<Usuario> resultado = query.getResultList();
		if (resultado.size() == 0) {
			return null;
		} else {
			return resultado.get(0);
		}
	}

}
