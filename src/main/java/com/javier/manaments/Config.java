package com.javier.manaments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.javier.manaments.interceptores.InterceptorAdmin;

@Component
public class Config implements WebMvcConfigurer {

	@Autowired
	private InterceptorAdmin admin;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(admin);
	}
}
