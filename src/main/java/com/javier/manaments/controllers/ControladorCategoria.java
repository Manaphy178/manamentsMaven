package com.javier.manaments.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javier.manaments.model.Categoria;
import com.javier.manaments.services.ServicioCategoria;


@Controller
@RequestMapping("admin/")
public class ControladorCategoria {
	@Autowired
	private ServicioCategoria servicioCategoria;

	@RequestMapping("categorias")
	public String obtenerCategorias(Model model) {
		System.err.println("Controlador categoria");
		List<Categoria> categorias = servicioCategoria.obtenerCategorias();
		model.addAttribute("categorias", categorias);
		return "admin/categorias";
	}
}
