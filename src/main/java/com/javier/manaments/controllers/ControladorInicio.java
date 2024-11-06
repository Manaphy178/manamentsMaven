package com.javier.manaments.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javier.manaments.servicesSetUp.SetUp;

@Controller
public class ControladorInicio {
	@Autowired
	private SetUp setUp;

	@Autowired
	private MessageSource messageSource;

//	Este es el metodo que se ejecuta por defecto
//	al entrar en la aplicacion 
	@RequestMapping()
	public String inicio() {
		setUp.setUp();
//		vamos a ver el idioma del usuario para devolverle index.html o index_en.html
		String idiomaActual = messageSource.getMessage("idioma", null, LocaleContextHolder.getLocale());
		if (idiomaActual.equals("en")) {
			return "index_en";
		}
		return "index";
	}
}
