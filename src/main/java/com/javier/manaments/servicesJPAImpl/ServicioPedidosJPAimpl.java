package com.javier.manaments.servicesJPAImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javier.manaments.constantesSQL.ConstantesSQL;
import com.javier.manaments.model.Carrito;
import com.javier.manaments.model.Pedido;
import com.javier.manaments.model.ProductoCarrito;
import com.javier.manaments.model.ProductoPedido;
import com.javier.manaments.model.Usuario;
import com.javier.manaments.model.estadosPedido.EstadosPedido;
import com.javier.manaments.model.tiposExtra.ResumenPedido;
import com.javier.manaments.services.ServicioCarrito;
import com.javier.manaments.services.ServicioPedidos;

@Service
@Transactional
public class ServicioPedidosJPAimpl implements ServicioPedidos {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ServicioCarrito servicioCarrito;

	/**
	 * en cuanto el usuario completa el paso 1 generamos, si no existe ya, un unico
	 * pedido incompleto para el usuario sobre el que vamos a preparar los datos de
	 * la compra
	 * 
	 * @throws Exception
	 */
	private Pedido obtenerPedidoActual(long idUsuario) throws Exception {
		Usuario usuario = entityManager.find(Usuario.class, idUsuario);
		Object pedidoEnProceso = null;
		List<Pedido> resultadoConsulta = entityManager
				.createQuery("select p from Pedido p where p.estado = :estado and p.usuario.id = :usuario_id")
				.setParameter("estado", EstadosPedido.INCOMPLETO.name()).setParameter("usuario_id", idUsuario)
				.getResultList();
		if (resultadoConsulta.size() == 1) {
			pedidoEnProceso = resultadoConsulta.get(0);
		} else if (resultadoConsulta.size() > 1) {
			throw new Exception("se ha colado mas de un pedido incompleto para el mismo usuario");
		}
		Pedido pedido = null;
		if (pedidoEnProceso != null) {
			pedido = (Pedido) pedidoEnProceso;
		} else {
			pedido = new Pedido();
			pedido.setEstado(EstadosPedido.INCOMPLETO.name());
			pedido.setUsuario(usuario);
		}

		return pedido;
	}

	@Override
	public void procesarPaso1(String nombreCompleto, String pais, String telefono, String direccion, String provincia,
			String poblacion, long idUsuario) {
		try {
			Pedido p = obtenerPedidoActual(idUsuario);
			p.setNombreCompleto(nombreCompleto);
			p.setDireccion(direccion);
			p.setProvincia(provincia);
			p.setTelefono(telefono);
			p.setPais(pais);
			p.setPoblacion(poblacion);
			entityManager.merge(p);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Hubo un problema obteniendo el pedido actual");
		}

	}

	@Override
	public void procesarPaso2(String titular, String numero, long cvv, String tipoTarjeta, String caducidadTarjeta,
			long idUsuario) {
		try {
			Pedido p = obtenerPedidoActual(idUsuario);
			p.setTitularTarjeta(titular);
			p.setNumeroTarjeta(numero);
			p.setCvv(cvv);
			p.setTipoTarjeta(tipoTarjeta);
			p.setCaducidadTarjeta(caducidadTarjeta);
			entityManager.merge(p);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Hubo un problema obteniendo el pedido actual");
		}
	}

	@Override
	public ResumenPedido obtenerResumenDelPedido(long idUsuario) {
		ResumenPedido resumen = new ResumenPedido();
		try {
			Pedido p = obtenerPedidoActual(idUsuario);
//			Paso 1
			resumen.setNombreCompleto(p.getNombreCompleto());
			resumen.setPais(p.getPais());
			resumen.setTelefono(p.getTelefono());
			resumen.setDireccion(p.getDireccion());
			resumen.setProvincia(p.getProvincia());
			resumen.setPoblacion(p.getPoblacion());
//			paso 2
			resumen.setTitularTarjeta(p.getTitularTarjeta());
			resumen.setNumeroTarjeta(p.getNumeroTarjeta());
			resumen.setCvv(p.getCvv());
			resumen.setTipoTarjeta(p.getTipoTarjeta());
			resumen.setCaducidadTarjeta(p.getCaducidadTarjeta());

			resumen.setInstrumentos(servicioCarrito.obtenerProductosCarritoUsuario(idUsuario));
		} catch (Exception e) {
			System.err.println("error en obtener pedido en obtenerResumenDelPedido");
			e.printStackTrace();
		}
		return resumen;
	}

	@Override
	public void confirmarPedido(long idUsuario) {
		try {
			Pedido p = obtenerPedidoActual(idUsuario);
			Usuario usuario = entityManager.find(Usuario.class, idUsuario);
			Carrito carrito = usuario.getCarrito();
//			pasar todos los productos del carrito al pedido
			if (carrito != null && carrito.getProductoCarritos().size() > 0) {
				for (ProductoCarrito pc : carrito.getProductoCarritos()) {
					ProductoPedido pp = new ProductoPedido();
					pp.setCantidad(pc.getCantidad());
					pp.setInstrumento(pc.getInstrumento());
					p.getProductoPedidos().add(pp);
					pp.setPedido(p);
					entityManager.persist(pp);
				}
			}
//			Borrar los productos del carrito del usuario
			Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_BORRAR_PRODUCTOS_CARRITO);
			query.setParameter("carrito_id", carrito.getId());
			query.executeUpdate();
			p.setEstado(EstadosPedido.COMPLETO.name());
			entityManager.merge(p);
//			Finalizar pedido
		} catch (Exception e) {
			System.err.println("error al obtener pedido en confirmarPedido");
			e.printStackTrace();
		}
	}// end confirmarPedido

}
