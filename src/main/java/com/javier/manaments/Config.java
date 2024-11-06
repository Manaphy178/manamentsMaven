package com.javier.manaments;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.javier.manaments.interceptores.InterceptorAdmin;

@Component
public class Config implements WebMvcConfigurer {

	@Autowired
	private InterceptorAdmin admin;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(admin);
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Bean
	public SessionLocaleResolver localeResolver() {
		/*
		 * En spring cuando ponemos @Bean tanto sobre una clase como sobre un metodo,
		 * estamos diciendo:
		 * 
		 * si es una clase: que un objeto de la misma pase a formar parte del contenedor
		 * de spring, y podemos pedirlo por @Autowired
		 * 
		 * si es en un metodo, el objeto que de en el return el metodo, es el que pasa a
		 * formar parte del contenedor de spring, y tambien se puede pedir
		 * por @Autowired
		 */
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.getDefault());
		return localeResolver;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		/*
		 * la anterior bean es solo para indicar que el idioma del usuario se mantenga
		 * en sesion, y por defecto sea el que tiene el navegador
		 * 
		 * esta bean es para indicar como realizar un cambio de idioma
		 */
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setIgnoreInvalidLocale(true);
		localeChangeInterceptor.setParamName("idioma");
		return localeChangeInterceptor;
	}

}
