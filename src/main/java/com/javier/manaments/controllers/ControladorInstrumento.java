package com.javier.manaments.controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javier.manaments.model.Categoria;
import com.javier.manaments.model.Instrumento;
import com.javier.manaments.services.ServicioCategoria;
import com.javier.manaments.services.ServicioInstrumento;

@Controller
@RequestMapping("admin/")
public class ControladorInstrumento {

	@Autowired
	private ServicioInstrumento servicioInstrumento;

	@Autowired
	private ServicioCategoria servicioCategoria;

	@RequestMapping("instrumentos")
	public String obtenerInstrumentos(Model model) {
		List<Instrumento> instrumentos = servicioInstrumento.obtenerInstrumentos();
		model.addAttribute("instrumentos", instrumentos);
		return "admin/instrumentos";
	}

	@RequestMapping("instrumentos-borrar")
	public String borrarInstrumento(String id, Model model) {
		servicioInstrumento.borrarInstrumento(Integer.parseInt(id));
		return obtenerInstrumentos(model);
	}

	@RequestMapping("instrumentos-nuevo")
	public String nuevoInstrumento(Model model) {
		Instrumento i = new Instrumento();
		i.setPrecio(1);
		model.addAttribute("nuevoInstrumento", i);
		model.addAttribute("categorias", servicioCategoria.obtenerCategorias());
		return "admin/instrumentos-nuevo";

	}

	@RequestMapping("instrumentos-guardar-nuevo")
	public String guardarNuevoInstrumento(@ModelAttribute("nuevoInstrumento") @Valid Instrumento nuevoInstrumento,
			BindingResult br, Model model, HttpServletRequest request) {
		if (br.hasErrors()) {
			model.addAttribute("categorias", servicioCategoria.obtenerCategorias());
			return "admin/instrumentos-nuevo";
		}
		// vamos a asignarle el archivo subido
		System.err.println("instrumento guardar nuevo");
		try {
			nuevoInstrumento.setImagenPortada(nuevoInstrumento.getArchivoSubido().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		nuevoInstrumento.setFechaCreacion(new Date(System.currentTimeMillis()));
		servicioInstrumento.registrarInstrumento(nuevoInstrumento);

		return obtenerInstrumentos(model);
	}

	@RequestMapping("instrumentos-editar")
	public String editarInstrumento(String id, Model model) {
		Instrumento i = servicioInstrumento.obtenerInstrumentoPorId(Integer.parseInt(id));
		model.addAttribute("instrumentoEditar", i);
		model.addAttribute("categorias", servicioCategoria.obtenerCategorias());
		return "admin/instrumentos-editar";
	}

	@RequestMapping("instrumentos-guardar-cambios")
	public String guardarCambioInstrumento(@ModelAttribute("instrumentoEditar") @Valid Instrumento instrumentoEditar,
			BindingResult br, Model model, HttpServletRequest request) {
		if (br.hasErrors()) {
			return "admin/instrumentos-editar";
		}
		if (instrumentoEditar.getArchivoSubido() != null && !instrumentoEditar.getArchivoSubido().isEmpty()) {
			try {
				instrumentoEditar.setImagenPortada(instrumentoEditar.getArchivoSubido().getBytes());
			} catch (IOException e) {
				e.printStackTrace();
				model.addAttribute("imageError", "Error al procesar la imagen");
				return "admin/instrumentos-editar";
			}
		}
		Categoria categoria = servicioCategoria.obtenerCategoriaPorId(instrumentoEditar.getIdCategoria());
		instrumentoEditar.setCategoria(categoria);
		instrumentoEditar.setUltimaModificacion(new Date(System.currentTimeMillis()));
		servicioInstrumento.actualizarInstrumento(instrumentoEditar);
		return obtenerInstrumentos(model);
	}
}
