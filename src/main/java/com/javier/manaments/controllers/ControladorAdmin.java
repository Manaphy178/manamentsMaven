package com.javier.manaments.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControladorAdmin {

	@Autowired
	private ControladorInicio inicio;

	@RequestMapping("admin/")
	public String admin() {
		return "admin/inicio";
	}

	@RequestMapping("loginAdmin")
	public String login() {
		return "admin/login-admin";
	}

	@RequestMapping("logout-admin")
	public String logoutAdmin(HttpServletRequest request) {
		request.getSession().invalidate();
		return inicio.inicio();
	}
}
