package com.javier.manaments.services;

import java.util.List;

import com.javier.manaments.model.Usuario;



public interface ServicioUsuarios {

	void registrarUsuario(Usuario u);

	List<Usuario> obtenerUsuarios();

	Usuario obtenerUsuarioPorEmailPass(String email, String pass);
}
