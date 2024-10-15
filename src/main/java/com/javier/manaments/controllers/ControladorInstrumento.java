package com.javier.manaments.controllers;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javier.manaments.model.*;
import com.javier.manaments.services.*;

@Controller
@RequestMapping("admin/")
public class ControladorInstrumento {
	@Autowired
	private ServicioInstrumento servicioInstrumento;

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
		return "admin/instrumentos-nuevo";

	}

	@RequestMapping("instrumentos-guardar-nuevo")
	public String guardarNuevoInstrumento(Instrumento nuevoInstrumento, Model model, HttpServletRequest request) {
		// lo suyo seria valiar el libro antes de nada
		servicioInstrumento.registrarInstrumento(nuevoInstrumento);

		// nuevoLibro ya tiene el archvo subido, simplemente
		// queremos guardar el archivo en una ruta concreta
		// el proyecto realmente se esta ejecutando en una ruta
		// distinta a la del workspace
		// necesitamos saber esa ruta:
		String rutaRealDelProyecto = request.getServletContext().getRealPath("");
		// vamos a crear una carpeta para las subidas de archivo:
		File rutaSubidas = new File(rutaRealDelProyecto + "/subidas");
		if (!rutaSubidas.exists()) {
			rutaSubidas.mkdirs();
		}
		String nombreArchivo = nuevoInstrumento.getId() + ".jpg";
		// guardar el archivo subido a la ruta indicada
		try {
			nuevoInstrumento.getFoto().transferTo(new File(rutaSubidas, nombreArchivo));
			System.out.println("portada del producto subida en: " + rutaRealDelProyecto + "subidas");
			nuevoInstrumento.setUltimaModificacion(new Date(System.currentTimeMillis()));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obtenerInstrumentos(model);
	}

	@RequestMapping("instrumentos-editar")
	public String editarInstrumento(String id, Model model) {
		Instrumento i = servicioInstrumento.obtenerInstrumentoPorId(Integer.parseInt(id));
		model.addAttribute("instrumentoEditar", i);
		return "admin/instrumentos-editar";
	}

	@RequestMapping("instrumentos-guardar-cambios")
	public String guardarCambioInstrumento(Instrumento instrumentoEditar, Model model, HttpServletRequest request) {
		// Antes de nada lo suyo seria validar los datos introducidos
		servicioInstrumento.actualizarInstrumento(instrumentoEditar);
		String rutaRealDelProyecto = request.getServletContext().getRealPath("");
		if (instrumentoEditar.getFoto().getSize() > 0) {
			// Si se cumple este if es que han subido una nueva imagen
			String rutaSubidas = request.getServletContext().getRealPath("") + "/subidas";
			String rutaImagen = rutaSubidas + "/" + instrumentoEditar.getId() + ".jpg";
			// instrumentoEditar.getFoto().getName();
			try {
				instrumentoEditar.getFoto().transferTo(new File(rutaImagen));
				instrumentoEditar.setUltimaModificacion(new Date(System.currentTimeMillis()));
				System.out.println("imagen actualizada en la ruta:" + rutaImagen);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} // end if archivo subido
		return obtenerInstrumentos(model);
	}
}
