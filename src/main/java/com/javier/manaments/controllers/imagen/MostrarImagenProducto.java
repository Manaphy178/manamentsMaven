package com.javier.manaments.controllers.imagen;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javier.manaments.services.ServicioInstrumento;

@Controller
public class MostrarImagenProducto {
	@Autowired
	private ServicioInstrumento servicioInstrumento;

	@RequestMapping("mostrar_imagen")
	public void mostrarImagenProducto(String id, HttpServletResponse response) throws IOException {
		System.err.println("controladorMostrarImagenProducto");
		 
		byte[] info = servicioInstrumento.obtenerInstrumentoPorId(Integer.parseInt(id)).getImagenPortada();
		if (info == null) {
			return;
		}
		response.setContentType("image/jpge, image/jpg, image/png, image/gif");
		response.getOutputStream().write(info);
		response.getOutputStream().close();
	}
	
//	@RequestMapping("logo_marca")
//	public void mostrarLogoMarca(String id, HttpServletResponse response)throws IOException {
//		System.err.println("controladorMostrarLogoMarca");
//		byte[] info = servicioMarca.obtenerMarcaPorId(Integer.parseInt(id)).getImagenPortada();
//		if (info == null) {
//			return;
//		}
//		response.setContentType("image/jpge, image/jpg, image/png, image/gif");
//		response.getOutputStream().write(info);
//		response.getOutputStream().close();
//	}
}
