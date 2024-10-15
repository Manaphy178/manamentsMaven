package com.javier.manaments.servicesJPAImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

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
		try {
			Usuario usuario = (Usuario) entityManager
					.createQuery("SELECT u FROM Usuario u WHERE u.email = :email AND u.pass = :pass", Usuario.class)
					.setParameter("email", email).setParameter("pass", pass).getSingleResult();
			return usuario;
		} catch (NoResultException e) {
			// Si no se encuentra ningún usuario con ese email y pass, manejamos la
			// excepción
			return null;
		}

	}

}
