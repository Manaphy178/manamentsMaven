package com.javier.manaments.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javier.manaments.servicesSetUp.SetUp;

@Controller
public class ControladorInicio {
	@Autowired
	private SetUp setUp;

//	Este es el metodo que se ejecuta por defecto
//	al entrar en la aplicacion 
	@RequestMapping()
	public String inicio() {
		setUp.setUp();
		return "index";
	}
}
