package com.javier.manaments.servicesJPAImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.javier.manaments.model.Pedido;
import com.javier.manaments.model.Usuario;
import com.javier.manaments.model.estadosPedido.EstadosPedido;
import com.javier.manaments.model.tiposExtra.ResumenPedido;
import com.javier.manaments.services.ServicioPedidos;

@Service
@Transactional
public class ServicioPedidosJPAimpl implements ServicioPedidos {

	@PersistenceContext
	private EntityManager entityManager;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void confirmarPedido(long idUsuario) {
		// TODO Auto-generated method stub

	}

}
