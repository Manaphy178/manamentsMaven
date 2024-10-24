package com.javier.manaments.servicesJPAImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.javier.manaments.model.Carrito;
import com.javier.manaments.model.Instrumento;
import com.javier.manaments.model.ProductoCarrito;
import com.javier.manaments.model.Usuario;
import com.javier.manaments.services.ServicioCarrito;

@Service
@Transactional
public class ServicioCarritoJPAimpl implements ServicioCarrito {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void agregarProducto(long idProducto, long idUsuario, int cantidad) {
		Usuario usuario = entityManager.find(Usuario.class, idUsuario);
		Carrito carrito = usuario.getCarrito();
		// si el usuario no tiene asociado un carrito, pues creamos un carrito
		// para el usuario
		if (carrito == null) {
			carrito = new Carrito();
			carrito.setUsuario(usuario);
			usuario.setCarrito(carrito);
			entityManager.persist(carrito);
		}
		// si el carrito no tiene un productoCarrito del idProducto que se quiere
		// agregar
		// pues lo creamos
		boolean productoEnCarrito = false;
		for (ProductoCarrito pc : carrito.getProductoCarritos()) {
			if (pc.getInstrumento().getId() == idProducto) {
				productoEnCarrito = true;
				// aprovechamos e incrementamos su cantidad
				pc.setCantidad(pc.getCantidad() + cantidad);
				entityManager.merge(pc);
			}
		} // end for
		if (!productoEnCarrito) {
			ProductoCarrito pc = new ProductoCarrito();
			pc.setCarrito(carrito);
			pc.setInstrumento(entityManager.find(Instrumento.class, idProducto));
			pc.setCantidad(cantidad);
			entityManager.persist(pc);
		}
	}

}
