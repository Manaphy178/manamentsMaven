package com.javier.manaments.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControladorAdmin {

	@RequestMapping("admin/")
	public String admin() {
		return "admin/inicio";
	}
}
