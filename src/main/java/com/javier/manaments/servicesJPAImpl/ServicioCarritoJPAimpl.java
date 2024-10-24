package com.javier.manaments.servicesJPAImpl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.javier.manaments.constantesSQL.ConstantesSQL;
import com.javier.manaments.model.Carrito;
import com.javier.manaments.model.Instrumento;
import com.javier.manaments.model.ProductoCarrito;
import com.javier.manaments.model.Usuario;
import com.javier.manaments.services.ServicioCarrito;
import com.javier.manaments.utilidades.Utilidades;

@Service
@Transactional
public class ServicioCarritoJPAimpl implements ServicioCarrito {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void agregarProducto(int idProducto, long idUsuario, int cantidad) {
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

	@Override
	public List<Map<String, Object>> obtenerProductosCarritoUsuario(long idUsuario) {
		Usuario usuario = entityManager.find(Usuario.class, idUsuario);
		Carrito carrito = usuario.getCarrito();
		List<Map<String, Object>> res = null;
		if (carrito != null) {
//			createNativeQuery es para poder usar SQL
			Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_PRODUCTOS_CARRITO);
			query.setParameter("carrito_id", carrito.getId());
			res = Utilidades.procesaNativeQuery(query);
		}
		
		return res;
	}

}
