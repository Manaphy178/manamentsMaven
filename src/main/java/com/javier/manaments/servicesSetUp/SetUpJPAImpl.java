package com.javier.manaments.servicesSetUp;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javier.manaments.model.*;
import com.javier.manaments.model.estadosPedido.EstadosPedido;
import com.javier.manaments.services.ServicioCarrito;

@Service
@Transactional
public class SetUpJPAImpl implements SetUp {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ServicioCarrito servicioCarrito;

	@Override
	public void setUp() {
		/*
		 * vamos a usar una entidad par ver si ya hemos inicializado la base de datos
		 * dicha entidad la llamamos SetUp
		 */

		com.javier.manaments.model.SetUp registroSetUp = null;
		try {
			// Casi identico a: select s.* from tabla_setup as s
			registroSetUp = (com.javier.manaments.model.SetUp) entityManager.createQuery("select s from SetUp as s")
					.getSingleResult();
		} catch (Exception e) {
			System.err.println("No se encontro ningun registro de setup, comenzamos setup...");
		}
		if (registroSetUp == null || !registroSetUp.isCompleto()) {
//			Vamos a crear unas categorias antes de nada
			Categoria cuerda = new Categoria("Cuerda",
					"Instrumentos que producen sonido a través de cuerdas vibrantes");
			Categoria viento = new Categoria("Viento",
					"Instrumentos que producen sonido a través de la vibración de aire");
			Categoria percusion = new Categoria("Percusión",
					"Instrumentos que producen sonido a través de membranas o superficies que se golpean");
			Categoria electrico = new Categoria("Electrónico", "Instrumentos electrónicos o digitales");
			entityManager.persist(cuerda);
			entityManager.persist(viento);
			entityManager.persist(percusion);
			entityManager.persist(electrico);

			/*
			 * la idea es que si no hay ningun registro en la tabla de setup pues preparemos
			 * los registros para todo el sistema
			 */
			Instrumento i1 = new Instrumento("Guitarra Clásica", "Acústico", "Yamaha", "Alta",
					"Guitarra acústica clásica con cuerdas de nylon", 350, new Date(System.currentTimeMillis()));
			Instrumento i2 = new Instrumento("Guitarra Eléctrica", "Eléctrico", "Fender", "Media",
					"Guitarra eléctrica con pastillas dobles", 1200, new Date(System.currentTimeMillis()));
			Instrumento i3 = new Instrumento("Saxofón", "Acústico", "Selmer", "Alta", "Saxofón alto de latón", 2500,
					new Date(System.currentTimeMillis()));
			Instrumento i4 = new Instrumento("Batería Acústica", "Acústico", "Tama", "Media",
					"Batería acústica de 5 piezas", 800, new Date(System.currentTimeMillis()));
			Instrumento i5 = new Instrumento("Teclado Electrónico", "Eléctrico", "Casio", "Media",
					"Teclado electrónico con 61 teclas y efectos", 300, new Date(System.currentTimeMillis()));
			Instrumento i6 = new Instrumento("Trompeta", "Acústico", "Bach", "Alta", "Trompeta en Sib, cuerpo de latón",
					1800, new Date(System.currentTimeMillis()));
			Instrumento i7 = new Instrumento("Bajo Eléctrico", "Eléctrico", "Ibanez", "Media",
					"Bajo eléctrico de 4 cuerdas", 950, new Date(System.currentTimeMillis()));
			i1.setCategoria(cuerda);
			i2.setCategoria(cuerda);
			i3.setCategoria(viento);
			i4.setCategoria(percusion);
			i5.setCategoria(electrico);
			i6.setCategoria(viento);
			i7.setCategoria(cuerda);
			i1.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/instrumentos/1.jpg"));
			i2.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/instrumentos/2.jpg"));
			i3.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/instrumentos/3.jpg"));
			i4.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/instrumentos/4.jpg"));
			i5.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/instrumentos/5.jpg"));
			i6.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/instrumentos/6.jpg"));
			i7.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/instrumentos/7.jpg"));
			entityManager.persist(i1);
			entityManager.persist(i2);
			entityManager.persist(i3);
			entityManager.persist(i4);
			entityManager.persist(i5);
			entityManager.persist(i6);
			entityManager.persist(i7);
			Usuario u1 = new Usuario("Javier", "Gonzalez", "mana", "jgonzalez@centronelson.org", "123", 28440);
			Usuario u2 = new Usuario("Sergio", "Prados", "prapra", "sprados@centronelson.org", "123", 28440);
			Usuario u3 = new Usuario("Alba", "Rueda", "albitagambita", "alba@gmail.com", "123", 28440);
			Usuario u4 = new Usuario("Carla", "Ramirez", "carram", "cramirez@centronelson.org", "123", 28440);
			Usuario u5 = new Usuario("David", "Mendez", "davmen", "dmendez@centronelson.org", "123", 28440);
			Usuario u6 = new Usuario("Lucia", "Vega", "luvga", "lvega@centronelson.org", "123", 28440);

			entityManager.persist(u1);
			entityManager.persist(u2);
			entityManager.persist(u3);
			entityManager.persist(u4);
			entityManager.persist(u5);
			entityManager.persist(u6);

//			Metemos algunos productos en el carrito del primer y segundo usuario
			servicioCarrito.agregarProducto(i1.getId(), u1.getId(), 5);
			servicioCarrito.agregarProducto(i3.getId(), u1.getId(), 1);
			servicioCarrito.agregarProducto(i5.getId(), u2.getId(), 2);
			servicioCarrito.agregarProducto(i7.getId(), u3.getId(), 10);
			System.err.println("Registros iniciales realizados correctamente");

//			meter unos pedidos:
			Pedido p1 = new Pedido();
			p1.setNombreCompleto("Javier Gonzalez");
			p1.setDireccion("C. del Olmo 3");
			p1.setProvincia("Madrid");
			p1.setTipoTarjeta("VISA");
			p1.setTitularTarjeta("Javier Gonzalez");
			p1.setNumeroTarjeta("1234 5678 1234 5678");
			p1.setCvv(150);
			p1.setPoblacion("Guadarrama");
			p1.setCaducidadTarjeta("09/25");
			p1.setPais("España");
			p1.setEstado(EstadosPedido.COMPLETO.name());
			p1.setFormaEnvio("Recogida");
			p1.setUsuario(u1);

			Pedido p2 = new Pedido();
			p2.setNombreCompleto("Sergio Prados");
			p2.setDireccion("Calle Treviño");
			p2.setProvincia("Madrid");
			p2.setTipoTarjeta("VISA");
			p2.setTitularTarjeta("Sergio Prados");
			p2.setNumeroTarjeta("1234 5678 1234 5678");
			p2.setCvv(540);
			p2.setPoblacion("Madrid");
			p2.setCaducidadTarjeta("01/26");
			p2.setPais("España");
			p2.setEstado(EstadosPedido.COMPLETO.name());
			p2.setFormaEnvio("Recogida");
			p2.setUsuario(u2);

			Pedido p3 = new Pedido();
			p3.setNombreCompleto("Alba Rueda");
			p3.setDireccion("Paseo de la Castellana 22");
			p3.setProvincia("Madrid");
			p3.setTipoTarjeta("VISA");
			p3.setTitularTarjeta("Alba Rueda");
			p3.setNumeroTarjeta("1111 2222 3333 4444");
			p3.setCvv(300);
			p3.setPoblacion("Madrid");
			p3.setCaducidadTarjeta("05/24");
			p3.setPais("España");
			p3.setEstado(EstadosPedido.COMPLETO.name());
			p3.setFormaEnvio("Recogida");
			p3.setUsuario(u3);

			Pedido p4 = new Pedido();
			p4.setNombreCompleto("Carla Ramirez");
			p4.setDireccion("C. del Carmen 45");
			p4.setProvincia("Madrid");
			p4.setTipoTarjeta("American Express");
			p4.setTitularTarjeta("Carla Ramirez");
			p4.setNumeroTarjeta("9999 8888 7777 6666");
			p4.setCvv(150);
			p4.setPoblacion("Alcorcón");
			p4.setCaducidadTarjeta("02/27");
			p4.setPais("España");
			p4.setEstado(EstadosPedido.COMPLETO.name());
			p4.setFormaEnvio("Envío a domicilio");
			p4.setUsuario(u4);
			Pedido p5 = new Pedido();
			p5.setNombreCompleto("David Mendez");
			p5.setDireccion("Av. de América 56");
			p5.setProvincia("Madrid");
			p5.setTipoTarjeta("VISA");
			p5.setTitularTarjeta("David Mendez");
			p5.setNumeroTarjeta("5555 6666 7777 8888");
			p5.setCvv(250);
			p5.setPoblacion("Las Rozas");
			p5.setCaducidadTarjeta("07/25");
			p5.setPais("España");
			p5.setEstado(EstadosPedido.COMPLETO.name());
			p5.setFormaEnvio("Recogida");
			p5.setUsuario(u5);
			Pedido p6 = new Pedido();
			p6.setNombreCompleto("Lucia Vega");
			p6.setDireccion("C. de Alcalá 100");
			p6.setProvincia("Madrid");
			p6.setTipoTarjeta("VISA");
			p6.setTitularTarjeta("Lucia Vega");
			p6.setNumeroTarjeta("4444 3333 2222 1111");
			p6.setCvv(350);
			p6.setPoblacion("Pozuelo de Alarcón");
			p6.setCaducidadTarjeta("08/26");
			p6.setPais("España");
			p6.setEstado(EstadosPedido.COMPLETO.name());
			p6.setFormaEnvio("Envío a domicilio");
			p6.setUsuario(u6);
			Pedido p7 = new Pedido();
			p7.setNombreCompleto("Sergio Prados");
			p7.setDireccion("Calle Treviño");
			p7.setProvincia("Madrid");
			p7.setTipoTarjeta("VISA");
			p7.setTitularTarjeta("Sergio Prados");
			p7.setNumeroTarjeta("1234 5678 1234 5678");
			p7.setCvv(540);
			p7.setPoblacion("Madrid");
			p7.setCaducidadTarjeta("01/26");
			p7.setPais("España");
			p7.setEstado(EstadosPedido.COMPLETO.name());
			p7.setFormaEnvio("Envío a domicilio");
			p7.setUsuario(u2);
			entityManager.persist(p1);
			entityManager.persist(p2);
			entityManager.persist(p3);
			entityManager.persist(p4);
			entityManager.persist(p5);
			entityManager.persist(p6);
			entityManager.persist(p7);

			ProductoPedido pp1 = new ProductoPedido();
			pp1.setPedido(p1);
			pp1.setInstrumento(i1);
			pp1.setCantidad(2);
			ProductoPedido pp2 = new ProductoPedido();
			pp2.setPedido(p2);
			pp2.setInstrumento(i4);
			pp2.setCantidad(5);
			ProductoPedido pp3 = new ProductoPedido();
			pp3.setPedido(p3);
			pp3.setInstrumento(i2);
			pp3.setCantidad(1);

			ProductoPedido pp4 = new ProductoPedido();
			pp4.setPedido(p4);
			pp4.setInstrumento(i3);
			pp4.setCantidad(3);

			ProductoPedido pp5 = new ProductoPedido();
			pp5.setPedido(p5);
			pp5.setInstrumento(i5);
			pp5.setCantidad(4);

			ProductoPedido pp6 = new ProductoPedido();
			pp6.setPedido(p6);
			pp6.setInstrumento(i6);
			pp6.setCantidad(2);

			entityManager.persist(pp1);
			entityManager.persist(pp2);
			entityManager.persist(pp3);
			entityManager.persist(pp4);
			entityManager.persist(pp5);
			entityManager.persist(pp6);

			/**
			 * Antes de finalizar el setup
			 * 
			 * vamos a registrar 100 instrumentos para poder hacer unas pruebas con la
			 * paginacion
			 */
			String nombre = "Bateria";
			double precio = 1;
			String descripcion = "Instrumento que le pegas y hace ruido";
			String marca = "Lenovo";
			for (int i = 0; i < 100; i++) {
				Instrumento nuevo = new Instrumento(nombre + i, descripcion + i,marca+i, precio + i);
				nuevo.setCategoria(percusion);
				entityManager.persist(nuevo);

			}

			com.javier.manaments.model.SetUp setUp = new com.javier.manaments.model.SetUp();
			setUp.setCompleto(true);
			entityManager.persist(setUp);
		}
	}// end setup

	private byte[] leerBytesDeRutaOrigen(String rutaOrigen) {
		byte[] info = null;
		try {
			URL url = new URL(rutaOrigen);
			info = IOUtils.toByteArray(url);
		} catch (IOException e) {
			System.err.println("La url esta mal");
			e.printStackTrace();
		}
		return info;

	}
}// end class
