package com.javier.manaments.interceptores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.javier.manaments.constantesGenerales.ConstantesGenerales;

@Component
public class InterceptorAdmin implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		/*
		 * la idea es que este codigo se va a ejecutar siempre antes de cualquier acceso
		 * a /admin
		 */
		/*
		 * si el codigo recibe un parametro ocn la contraseña de admin pues guardo un
		 * token en sesion, para identificar al usuario actual como admin
		 */

		if (request.getParameter("pass-login-admin") != null) {
			if (request.getParameter("pass-login-admin").equals(ConstantesGenerales.PASS_ADMIN)) {
				request.getSession().setAttribute("token-admin", "ok");
			}
		}
		/*
		 * a parte de lo anterior, compruebo el token para permitir acceder hacia /admin
		 * o redirigir a un formulario de identificacion de admin
		 */
		if (request.getRequestURI().contains("/admin/")) {
			if (!(request.getSession().getAttribute("token-admin") != null
					&& request.getSession().getAttribute("token-admin").equals("ok"))) {
				/*
				 * si no se cumple esto, es que el usuario esta accediendo a admin sin estar
				 * correctamente identificado como administrador
				 */
				response.sendRedirect("../loginAdmin");
				return false;
			}
		}
		return true;
	}

}
