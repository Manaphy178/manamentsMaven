package com.javier.manaments.servicesSetUp;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		Random random = new Random();
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
			// Vamos a crear unas categorias antes de nada

			/**
			 * SE CREAN LAS CATEGORIAS ANTES PARA LUEGO ASIGNAR A LOS INSTRUMENTOS A QUE
			 * CATEGORIA PERTENECEN
			 */
			Categoria guitarra = new Categoria("Guitarra",
					"Instrumentos de cuerda que se tocan con los dedos o una púa, conocidos por su versatilidad en géneros como rock, jazz y flamenco.");
			Categoria bajo = new Categoria("Bajo",
					"Instrumentos de cuerda que producen tonos bajos y graves, esenciales en la música para proporcionar ritmo y profundidad.");
			Categoria bateria = new Categoria("Batería",
					"Conjunto de instrumentos de percusión, como tambores y platillos, utilizados para crear ritmos y marcar el tiempo en una canción.");
			Categoria cuerda = new Categoria("Cuerda",
					"Instrumentos que producen sonido a través de cuerdas vibrantes");
			Categoria viento = new Categoria("Viento",
					"Instrumentos que producen sonido a través de la vibración de aire");
			Categoria percusion = new Categoria("Percusión",
					"Instrumentos que producen sonido a través de membranas o superficies que se golpean");
			
			Categoria piano = new Categoria("Pianos", "Instrumento clasico de teclas");
			Categoria sintetizadores = new Categoria("Sintetizadores",
					"Instrumento electronico capaz de producir sonidos a cualquier frecuencia");
			entityManager.persist(cuerda);
			entityManager.persist(viento);
			entityManager.persist(percusion);
			
			entityManager.persist(piano);
			entityManager.persist(sintetizadores);
			entityManager.persist(guitarra);
			entityManager.persist(bajo);
			entityManager.persist(bateria);

			/*
			 * la idea es que si no hay ningun registro en la tabla de setup pues preparemos
			 * los registros para todo el sistema
			 */

			/**
			 * MARCAS
			 */
			Marca yamaha = new Marca("Yamaha");
			yamaha.addCategoria(cuerda);
			yamaha.addCategoria(piano);
			yamaha.addCategoria(viento);
			yamaha.addCategoria(percusion);

			yamaha.addCategoria(sintetizadores);
			yamaha.addCategoria(guitarra);
			yamaha.addCategoria(bajo);
			yamaha.addCategoria(bateria);

			Marca roland = new Marca("Roland");
			roland.addCategoria(percusion);
			roland.addCategoria(sintetizadores);
			roland.addCategoria(bateria);

			Marca korg = new Marca("Korg");
			korg.addCategoria(piano);
			korg.addCategoria(sintetizadores);

			Marca casio = new Marca("Casio");
			casio.addCategoria(piano);
			casio.addCategoria(sintetizadores);

			Marca fender = new Marca("Fender");
			fender.addCategoria(guitarra);
			fender.addCategoria(bajo);

			Marca gibson = new Marca("Gibson");
			gibson.addCategoria(guitarra);
			gibson.addCategoria(bajo);

			Marca ibanez = new Marca("Ibanez");
			ibanez.addCategoria(guitarra);
			ibanez.addCategoria(bajo);

			Marca pearl = new Marca("Pearl");
			pearl.addCategoria(percusion);
			pearl.addCategoria(bateria);

			Marca dw = new Marca("Drum Workshop");
			dw.addCategoria(percusion);
			dw.addCategoria(bateria);

			Marca meinl = new Marca("Meinl");
			meinl.addCategoria(percusion);
			meinl.addCategoria(bateria);

			entityManager.persist(yamaha);
			entityManager.persist(roland);
			entityManager.persist(korg);
			entityManager.persist(casio);
			entityManager.persist(fender);
			entityManager.persist(gibson);
			entityManager.persist(ibanez);
			entityManager.persist(pearl);
			entityManager.persist(dw);
			entityManager.persist(meinl);

			/**
			 * END MARCAS
			 */

			/**
			 * Instrumentos Yamaha
			 */
			/**
			 * Ejemplo constructor de Instrumento de lo que tiene que tener
			 * 
			 * Instrumento i1 = new Instrumento("(Nombre_Instrumento)","(Tipo de instrumento
			 * si es acustico o electrico)",(Marca a la que pertenece),(Categoria a la que
			 * pertenece),"(Gamma (alta,media,baja))","(descripcion del
			 * instrumento)","(estado (nuevo, segunda mano,etc))",(precio double),new
			 * Date(System.currentTimeMillis()))
			 */

			// Instrumentos
			Instrumento yamahaI1 = new Instrumento(piano, "Piano SU", yamaha,
					"Piano acústico de la serie SU de Yamaha, conocido por su sonido resonante y su calidad de construcción.",
					5000.0, "Acústico", "Alta", "Nuevo", new Date(System.currentTimeMillis()));

			Instrumento yamahaI2 = new Instrumento(guitarra, "Guitarra Acústica FG", yamaha,
					"Guitarra acústica de la serie FG de Yamaha, famosa por su tono cálido y construcción duradera.",
					350.0, "Acústico", "Media", "Nuevo", new Date(System.currentTimeMillis()));

			Instrumento yamahaI3 = new Instrumento(bajo, "Bajo BB", yamaha,
					"Bajo eléctrico de la serie BB, reconocido por su tono profundo y gran versatilidad.", 700.0,
					"Eléctrico", "Alta", "Nuevo", new Date(System.currentTimeMillis()));

			Instrumento yamahaI4 = new Instrumento(guitarra, "Guitarra Eléctrica Revstar", yamaha,
					"Guitarra eléctrica Revstar de Yamaha, con un diseño moderno y un sonido dinámico ideal para rock y metal.",
					800.0, "Eléctrico", "Alta", "Nuevo", new Date(System.currentTimeMillis()));

			Instrumento yamahaI5 = new Instrumento(bateria, "Batería Live Custom Hybrid Oak", yamaha,
					"Batería acústica Live Custom Hybrid Oak, con un sonido robusto y una excelente respuesta de tono.",
					4000.0, "Acústico", "Alta", "Nuevo", new Date(System.currentTimeMillis()));

			Instrumento yamahaI6 = new Instrumento(bateria, "Batería Electrónica DTX10 Series", yamaha,
					"Batería electrónica DTX10, diseñada para ofrecer una experiencia de percusión más realista y profesional.",
					3000.0, "Electrónico", "Alta", "Nuevo", new Date(System.currentTimeMillis()));

			Instrumento yamahaI7 = new Instrumento(viento, "Trompeta YTR-9335CHS", yamaha,
					"Trompeta de la serie YTR-9335CHS, conocida por su facilidad de ejecución y sonido cálido.", 2500.0,
					"Viento", "Alta", "Nuevo", new Date(System.currentTimeMillis()));

			Instrumento yamahaI8 = new Instrumento(viento, "Saxofón YAS-875EX", yamaha,
					"Saxofón alto YAS-875EX, de alto rendimiento con un sonido brillante y potente.", 4500.0, "Viento",
					"Alta", "Nuevo", new Date(System.currentTimeMillis()));

			Instrumento yamahaI9 = new Instrumento(viento, "Flauta Serie 900", yamaha,
					"Flauta de la serie 900, con una respuesta rápida y un tono cálido ideal para el músico profesional.",
					2000.0, "Viento", "Alta", "Nuevo", new Date(System.currentTimeMillis()));

			Instrumento yamahaI10 = new Instrumento(cuerda, "Violín YVN500S", yamaha,
					"Violín de nivel intermedio YVN500S, conocido por su excelente tono y facilidad de interpretación.",
					800.0, "Cuerda", "Alta", "Nuevo", new Date(System.currentTimeMillis()));

			Instrumento yamahaI11 = new Instrumento(cuerda, "Viola VA7SG", yamaha,
					"Viola VA7SG, instrumento de cuerda con una rica sonoridad, ideal para músicos avanzados.", 1000.0,
					"Cuerda", "Alta", "Nuevo", new Date(System.currentTimeMillis()));

			Instrumento yamahaI12 = new Instrumento(sintetizadores, "Teclado Portátil PSR-EW425", yamaha,
					"Teclado portátil PSR-EW425, con una amplia gama de sonidos y características educativas para principiantes.",
					350.0, "Electrónico", "Media", "Nuevo", new Date(System.currentTimeMillis()));

			Instrumento yamahaI13 = new Instrumento(sintetizadores, "Sintetizador MONTAGE M", yamaha,
					"Sintetizador MONTAGE M, con capacidades avanzadas para la creación de sonidos complejos y manipulaciones en tiempo real.",
					2000.0, "Electrónico", "Alta", "Nuevo", new Date(System.currentTimeMillis()));
			yamahaI1.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/yamaha/instrumentos/yamahaI1.png"));
			yamahaI2.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/yamaha/instrumentos/yamahaI2.png"));
			yamahaI3.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/yamaha/instrumentos/yamahaI3.png"));
			yamahaI4.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/yamaha/instrumentos/yamahaI4.png"));
			yamahaI5.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/yamaha/instrumentos/yamahaI5.png"));
			yamahaI6.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/yamaha/instrumentos/yamahaI6.png"));
			yamahaI7.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/yamaha/instrumentos/yamahaI7.png"));
			yamahaI8.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/yamaha/instrumentos/yamahaI8.png"));
			yamahaI9.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/yamaha/instrumentos/yamahaI9.png"));
			yamahaI10.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/yamaha/instrumentos/yamahaI10.png"));
			yamahaI11.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/yamaha/instrumentos/yamahaI11.png"));
			yamahaI12.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/yamaha/instrumentos/yamahaI12.png"));
			yamahaI13.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/yamaha/instrumentos/yamahaI13.png"));

			yamahaI1.setVentas(random.nextInt(101));
			yamahaI2.setVentas(random.nextInt(101));
			yamahaI3.setVentas(random.nextInt(101));
			yamahaI4.setVentas(random.nextInt(101));
			yamahaI5.setVentas(random.nextInt(101));
			yamahaI6.setVentas(random.nextInt(101));
			yamahaI7.setVentas(random.nextInt(101));
			yamahaI8.setVentas(random.nextInt(101));
			yamahaI9.setVentas(random.nextInt(101));
			yamahaI10.setVentas(random.nextInt(101));
			yamahaI11.setVentas(random.nextInt(101));
			yamahaI12.setVentas(random.nextInt(101));
			yamahaI13.setVentas(random.nextInt(101));

			entityManager.persist(yamahaI1);
			entityManager.persist(yamahaI2);
			entityManager.persist(yamahaI3);
			entityManager.persist(yamahaI4);
			entityManager.persist(yamahaI5);
			entityManager.persist(yamahaI6);
			entityManager.persist(yamahaI7);
			entityManager.persist(yamahaI8);
			entityManager.persist(yamahaI9);
			entityManager.persist(yamahaI10);
			entityManager.persist(yamahaI11);
			entityManager.persist(yamahaI12);
			entityManager.persist(yamahaI13);
			/**
			 * END instrumentos yamaha
			 */
//			Instrumento i2 = new Instrumento("Guitarra Eléctrica", "Eléctrico", "Fender", "Media",
//					"Guitarra eléctrica con pastillas dobles", 1200, new Date(System.currentTimeMillis()));
//			Instrumento i3 = new Instrumento("Saxofón", "Acústico", "Selmer", "Alta", "Saxofón alto de latón", 2500,
//					new Date(System.currentTimeMillis()));
//			Instrumento i4 = new Instrumento("Batería Acústica", "Acústico", "Tama", "Media",
//					"Batería acústica de 5 piezas", 800, new Date(System.currentTimeMillis()));
//			Instrumento i5 = new Instrumento("Teclado Electrónico", "Eléctrico", "Casio", "Media",
//					"Teclado electrónico con 61 teclas y efectos", 300, new Date(System.currentTimeMillis()));
//			Instrumento i6 = new Instrumento("Trompeta", "Acústico", "Bach", "Alta", "Trompeta en Sib, cuerpo de latón",
//					1800, new Date(System.currentTimeMillis()));
//			Instrumento i7 = new Instrumento("Bajo Eléctrico", "Eléctrico", "Ibanez", "Media",
//					"Bajo eléctrico de 4 cuerdas", 950, new Date(System.currentTimeMillis()));
//			i2.setCategoria(cuerda);
//			i3.setCategoria(viento);
//			i4.setCategoria(percusion);
//			i5.setCategoria(electrico);
//			i6.setCategoria(viento);
//			i7.setCategoria(cuerda);
//
//			i2.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/instrumentos/2.jpg"));
//			i3.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/instrumentos/3.jpg"));
//			i4.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/instrumentos/4.jpg"));
//			i5.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/instrumentos/5.jpg"));
//			i6.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/instrumentos/6.jpg"));
//			i7.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/instrumentos/7.jpg"));
//
//			i2.setVentas(15);
//			i3.setVentas(10);
//			i4.setVentas(5);
//			i5.setVentas(30);
//			i6.setVentas(55);
//			i7.setVentas(20);
//
//			i2.setEstado("nuevo");
//			i3.setEstado("nuevo");
//			i4.setEstado("nuevo");
//			i5.setEstado("nuevo");
//			i6.setEstado("nuevo");
//			i7.setEstado("nuevo");
//
//			entityManager.persist(i2);
//			entityManager.persist(i3);
//			entityManager.persist(i4);
//			entityManager.persist(i5);
//			entityManager.persist(i6);
//			entityManager.persist(i7);
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

			// Metemos algunos productos en el carrito del primer y segundo usuario
			servicioCarrito.agregarProducto(yamahaI1.getId(), u1.getId(), 5);
			servicioCarrito.agregarProducto(yamahaI5.getId(), u1.getId(), 1);
			servicioCarrito.agregarProducto(yamahaI3.getId(), u2.getId(), 2);
			servicioCarrito.agregarProducto(yamahaI11.getId(), u3.getId(), 10);
			System.err.println("Registros iniciales realizados correctamente");

			// meter unos pedidos:
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
			pp1.setInstrumento(yamahaI1);
			pp1.setCantidad(2);
			ProductoPedido pp2 = new ProductoPedido();
			pp2.setPedido(p2);
			pp2.setInstrumento(yamahaI4);
			pp2.setCantidad(5);
			ProductoPedido pp3 = new ProductoPedido();
			pp3.setPedido(p3);
			pp3.setInstrumento(yamahaI2);
			pp3.setCantidad(1);

			ProductoPedido pp4 = new ProductoPedido();
			pp4.setPedido(p4);
			pp4.setInstrumento(yamahaI3);
			pp4.setCantidad(3);

			ProductoPedido pp5 = new ProductoPedido();
			pp5.setPedido(p5);
			pp5.setInstrumento(yamahaI5);
			pp5.setCantidad(4);

			ProductoPedido pp6 = new ProductoPedido();
			pp6.setPedido(p6);
			pp6.setInstrumento(yamahaI6);
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
			List<Marca> marcas = new ArrayList<Marca>();
			marcas.add(yamaha);
			marcas.add(roland);
			marcas.add(pearl);
			marcas.add(dw);
			marcas.add(meinl);

			for (int i = 0; i < 100; i++) {
				// Selecciona una marca aleatoriamente del ArrayList
				Marca marcaAleatoria = marcas.get(random.nextInt(marcas.size()));

				// Crea un nuevo instrumento con la marca aleatoria
				Instrumento nuevo = new Instrumento(nombre + i, // Nombre del instrumento
						descripcion + i, // Descripción del instrumento
						marcaAleatoria, // Marca aleatoria seleccionada
						precio + i // Precio (puedes ajustar la lógica si lo prefieres)
				);

				// Asigna la categoría al instrumento
				nuevo.setCategoria(percusion);

				// Persistir el objeto en la base de datos
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
