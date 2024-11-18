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
			Categoria bateria = new Categoria("Bateria",
					"Conjunto de instrumentos de percusión, como tambores y platillos, utilizados para crear ritmos y marcar el tiempo en una canción.");
			Categoria cuerda = new Categoria("Cuerda",
					"Instrumentos que producen sonido a través de cuerdas vibrantes");
			Categoria viento = new Categoria("Viento",
					"Instrumentos que producen sonido a través de la vibración de aire");
			Categoria percusion = new Categoria("Percusion",
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
			roland.addCategoria(viento);
			roland.addCategoria(piano);
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

			Marca dw = new Marca("DrumWorkshop");
			dw.addCategoria(percusion);
			dw.addCategoria(bateria);

			Marca meinl = new Marca("Meinl");
			meinl.addCategoria(percusion);

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
			Date fechaCreacion = new Date(System.currentTimeMillis());
			// Instrumentos
			Instrumento yamahaI1 = new Instrumento(piano, "Piano SU", yamaha,
					"Piano acústico de la serie SU de Yamaha, fabricado a mano con materiales de alta calidad, ofreciendo un sonido resonante, claridad de tonos graves y agudos, y una acción de teclas excepcional que proporciona una experiencia interpretativa de primer nivel. Ideal para estudios profesionales y salas de conciertos.",
					5000.0, "Acústico", "Alta", "Nuevo", fechaCreacion);

			Instrumento yamahaI2 = new Instrumento(guitarra, "Guitarra Acústica FG", yamaha,
					"Guitarra acústica de la serie FG de Yamaha, famosa por su tono cálido, proyección equilibrada y una construcción duradera con tapa de abeto macizo, ofreciendo un sonido lleno y resonante ideal tanto para principiantes como para guitarristas avanzados.",
					350.0, "Acústico", "Media", "Nuevo", fechaCreacion);

			Instrumento yamahaI3 = new Instrumento(bajo, "Bajo BB", yamaha,
					"Bajo eléctrico de la serie BB, reconocido por su tono profundo, sustain prolongado, y una gran versatilidad sonora que lo hace adecuado para diversos estilos musicales, desde rock hasta jazz, con una construcción robusta y cómoda para tocar en escenarios o en el estudio.",
					700.0, "Eléctrico", "Alta", "Nuevo", fechaCreacion);

			Instrumento yamahaI4 = new Instrumento(guitarra, "Guitarra Eléctrica Revstar", yamaha,
					"Guitarra eléctrica Revstar de Yamaha, caracterizada por su diseño moderno inspirado en motocicletas clásicas, un sonido dinámico y potente ideal para géneros como rock y metal, con pastillas humbucker de alta salida y un cuerpo ergonómico para mayor confort durante largas sesiones de interpretación.",
					800.0, "Eléctrico", "Alta", "Nuevo", fechaCreacion);

			Instrumento yamahaI5 = new Instrumento(bateria, "Batería Live Custom Hybrid Oak", yamaha,
					"Batería acústica Live Custom Hybrid Oak de Yamaha, construida con capas de roble y un núcleo de phenol para un sonido más robusto y con mayor proyección. Ideal para actuaciones en vivo, esta batería destaca por su ataque potente y una excelente respuesta de tono que se adapta a múltiples géneros musicales.",
					4000.0, "Acústico", "Alta", "Nuevo", fechaCreacion);

			Instrumento yamahaI6 = new Instrumento(bateria, "Batería Electrónica DTX10 Series", yamaha,
					"Batería electrónica DTX10 Series de Yamaha, diseñada para ofrecer una experiencia de percusión extremadamente realista gracias a sus pads de silicona y su módulo de sonido avanzado, permitiendo una amplia gama de sonidos y funcionalidades, ideal para bateristas profesionales y estudios de grabación.",
					3000.0, "Electrónico", "Alta", "Nuevo", fechaCreacion);

			Instrumento yamahaI7 = new Instrumento(viento, "Trompeta YTR-9335CHS", yamaha,
					"Trompeta de la serie YTR-9335CHS, conocida por su facilidad de ejecución, proyección impresionante, y un sonido cálido con una excelente afinación en todos los registros. Construida con precisión para músicos de orquestas y solistas exigentes.",
					2500.0, "Viento", "Alta", "Nuevo", fechaCreacion);

			Instrumento yamahaI8 = new Instrumento(viento, "Saxofón YAS-875EX", yamaha,
					"Saxofón alto de la serie YAS-875EX de Yamaha, diseñado para ofrecer un rendimiento superior con un sonido brillante y potente, respuesta rápida y una excelente proyección. Ideal para intérpretes profesionales que buscan versatilidad en jazz, clásico y otros estilos.",
					4500.0, "Viento", "Alta", "Nuevo", fechaCreacion);

			Instrumento yamahaI9 = new Instrumento(viento, "Flauta Serie 900", yamaha,
					"Flauta de la serie 900 de Yamaha, con una respuesta rápida, construcción en plata maciza para un tono cálido y proyectado, diseñada para músicos profesionales que requieren una precisión excepcional y expresividad en sus interpretaciones.",
					2000.0, "Viento", "Alta", "Nuevo", fechaCreacion);

			Instrumento yamahaI10 = new Instrumento(cuerda, "Violín YVN500S", yamaha,
					"Violín de nivel intermedio YVN500S de Yamaha, reconocido por su tono claro y resonante, con maderas seleccionadas que brindan una proyección óptima. Ideal para estudiantes avanzados y músicos que buscan un sonido refinado y una ejecución cómoda.",
					800.0, "Cuerda", "Alta", "Nuevo", fechaCreacion);

			Instrumento yamahaI11 = new Instrumento(cuerda, "Viola VA7SG", yamaha,
					"Viola VA7SG de Yamaha, un instrumento de cuerda de calidad superior con un tono rico y cálido, ideal para músicos avanzados y profesionales que requieren una respuesta tonal precisa y una construcción duradera.",
					1000.0, "Cuerda", "Alta", "Nuevo", fechaCreacion);

			Instrumento yamahaI12 = new Instrumento(sintetizadores, "Teclado Portátil PSR-EW425", yamaha,
					"Teclado portátil PSR-EW425 de Yamaha, equipado con 76 teclas sensibles al tacto, más de 800 sonidos, y características educativas avanzadas como lecciones integradas y conectividad USB, ideal para principiantes y músicos en desarrollo.",
					350.0, "Electrónico", "Media", "Nuevo", fechaCreacion);

			Instrumento yamahaI13 = new Instrumento(sintetizadores, "Sintetizador MONTAGE M", yamaha,
					"Sintetizador MONTAGE M de Yamaha, con un motor de síntesis híbrido que combina AWM2 y FM-X, permitiendo la creación de sonidos complejos y manipulaciones en tiempo real gracias a su Super Knob y funciones avanzadas de control en vivo. Ideal para músicos y productores que buscan un sintetizador de alto rendimiento.",
					2000.0, "Electrónico", "Alta", "Nuevo", fechaCreacion);

			Instrumento yamahaI14 = new Instrumento(percusion, "Timbales Serie TP-8300R", yamaha,
					"Timbales de la serie TP-8300R de Yamaha, con una construcción de cobre martillado a mano para un tono rico y resonante, diseñado para músicos de orquestas sinfónicas y bandas que requieren una resonancia excepcional y durabilidad en cada presentación.",
					1500.0, "Acústico", "Alta", "Nuevo", fechaCreacion);

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
			yamahaI14.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/yamaha/instrumentos/yamahaI14.png"));

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
			yamahaI14.setVentas(random.nextInt(101));

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
			entityManager.persist(yamahaI14);

			/**
			 * END instrumentos yamaha
			 */

			/**
			 * Instrumentos Roland
			 */

			Instrumento rolandI1 = new Instrumento(piano, "Kiyola KF-10", roland,
					"Piano digital Kiyola (Made in Japan series KF-10) de Roland, combina un diseño elegante de inspiración escandinava con tecnología de sonido de última generación. Este piano digital cuenta con un mueble artesanal de madera maciza, fabricado por artesanos de la renombrada empresa Karimoku, y un motor de sonido SuperNATURAL que reproduce con gran fidelidad la resonancia y el tono de un piano acústico. Con teclas de madera PHA-50 y un sistema de altavoces integrados, ofrece una experiencia musical envolvente y auténtica, ideal tanto para decoración de interiores modernos como para músicos exigentes.",
					4500.0, "Electrónico", "Alta", "Nuevo", fechaCreacion);

			Instrumento rolandI2 = new Instrumento(sintetizadores, "GAIA 2", roland,
					"Sintetizador GAIA 2 de Roland, una potente herramienta para el diseño de sonido y la experimentación musical. Equipado con un motor de síntesis avanzado, este sintetizador es capaz de crear una amplia gama de sonidos, desde texturas ambientales hasta leads agresivos. Cuenta con un panel de control intuitivo con faders, perillas y botones iluminados para un flujo de trabajo rápido y creativo. Además, incluye polifonía mejorada, efectos integrados, y un teclado de 49 teclas sensibles al tacto, lo que lo convierte en una excelente opción tanto para estudios como para presentaciones en vivo.",
					800.0, "Electrónico", "Media", "Nuevo", fechaCreacion);

			Instrumento rolandI3 = new Instrumento(sintetizadores, "GR-55", roland,
					"Sintetizador de guitarra GR-55 de Roland, un revolucionario procesador de guitarra que combina sintetización avanzada y modelado de amplificadores. Equipado con la tecnología COSM, este dispositivo permite transformar el sonido de tu guitarra en una amplia gama de timbres, desde emulaciones de instrumentos acústicos hasta complejos pads de sintetizador. Con 910 tonos diferentes, MIDI integrado, y capacidad para conectar una pastilla GK, es ideal tanto para guitarristas que desean expandir su paleta sonora como para músicos de estudio en busca de versatilidad.",
					1200.0, "Electrónico", "Alta", "Nuevo", fechaCreacion);

			Instrumento rolandI4 = new Instrumento(sintetizadores, "Katana Artist Gen 3", roland,
					"Sintetizador de bajo Katana Artist Gen 3 de Roland, diseñado específicamente para bajistas que buscan un sonido potente y personalizado. Este sintetizador combina modelado de amplificadores con efectos premium, y ofrece un motor de sonido completamente ajustable con control de tono preciso. Incluye 5 tipos de amplificadores, efectos integrados, y un altavoz especial Waza de 12 pulgadas que proporciona un rendimiento inigualable tanto en el escenario como en el estudio.",
					1100.0, "Electrónico", "Alta", "Nuevo", fechaCreacion);

			Instrumento rolandI5 = new Instrumento(bateria, "VAD507 V-Drums Acoustic Design", roland,
					"Batería electrónica VAD507 de Roland, perteneciente a la serie V-Drums Acoustic Design, combina la estética clásica de un set acústico con la tecnología digital avanzada de Roland. Ofrece pads con parches de malla de doble capa para una sensación realista y un sistema de sonido dinámico que incluye el módulo TD-27, proporcionando una extensa biblioteca de kits personalizables. Con un chasis de madera real y herrajes de alta calidad, esta batería está diseñada para brindar una experiencia de interpretación auténtica, ideal para bateristas profesionales que buscan versatilidad tanto en el estudio como en el escenario.",
					3500.0, "Electrónico", "Alta", "Nuevo", fechaCreacion);

			Instrumento rolandI6 = new Instrumento(percusion, "SPD-SX PRO Sampling Pad", roland,
					"Pad de percusión SPD-SX PRO de Roland, el estándar para músicos que necesitan llevar su creatividad a nuevos niveles en el escenario y en el estudio. Este pad de muestras avanzado permite cargar y manipular hasta 4 GB de muestras, ofreciendo 9 pads sensibles al tacto con retroalimentación LED para una mejor visualización en escenarios oscuros. Con funciones como Multi-Pad Sampling, efectos integrados, y conectividad avanzada, es una herramienta esencial para bateristas, DJs y productores que desean integrar elementos electrónicos en sus actuaciones en vivo.",
					1000.0, "Electrónico", "Alta", "Nuevo", fechaCreacion);

			Instrumento rolandI7 = new Instrumento(viento, "Aerophone AE-20", roland,
					"Instrumento de viento digital Aerophone AE-20 de Roland, diseñado para músicos versátiles que buscan la expresividad de un instrumento de viento tradicional combinado con la flexibilidad digital. Equipado con un sensor de respiración y teclas sensibles, el AE-20 permite interpretar sonidos de saxofón, flauta, clarinete y otros instrumentos de viento con gran realismo. Además, incluye una extensa librería de sonidos electrónicos, conectividad Bluetooth para aplicaciones de música móvil, y un diseño ergonómico que lo hace perfecto tanto para el estudio como para presentaciones en vivo.",
					900.0, "Electrónico", "Alta", "Nuevo", fechaCreacion);

			rolandI1.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/roland/instrumentos/rolandI1.png"));
			rolandI2.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/roland/instrumentos/rolandI2.png"));
			rolandI3.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/roland/instrumentos/rolandI3.png"));
			rolandI4.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/roland/instrumentos/rolandI4.png"));
			rolandI5.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/roland/instrumentos/rolandI5.png"));
			rolandI6.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/roland/instrumentos/rolandI6.png"));
			rolandI7.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/roland/instrumentos/rolandI7.png"));
			rolandI1.setVentas(random.nextInt(101));
			rolandI2.setVentas(random.nextInt(101));
			rolandI3.setVentas(random.nextInt(101));
			rolandI4.setVentas(random.nextInt(101));
			rolandI5.setVentas(random.nextInt(101));
			rolandI6.setVentas(random.nextInt(101));
			rolandI7.setVentas(random.nextInt(101));
			entityManager.persist(rolandI1);
			entityManager.persist(rolandI2);
			entityManager.persist(rolandI3);
			entityManager.persist(rolandI4);
			entityManager.persist(rolandI5);
			entityManager.persist(rolandI6);
			entityManager.persist(rolandI7);

			/**
			 * Instrumentos korg
			 */
			Instrumento korgI1 = new Instrumento(piano, "EK-50 Limitless ENTERTAINER KEYBOARD", korg,
					"Teclado profesional con acompañamiento automático, ideal para entretenimiento y actuaciones en vivo.",
					450.00, "Teclado", "Alta", "Nuevo", fechaCreacion);

			// 2. Piano - XE20 DIGITAL ENSEMBLE PIANO
			Instrumento korgI2 = new Instrumento(piano, "XE20 DIGITAL ENSEMBLE PIANO", korg,
					"Piano digital con acompañamientos, adecuado tanto para principiantes como para músicos avanzados.",
					750.00, "Piano digital", "Media", "Nuevo", fechaCreacion);

			// 3. Piano - Pa600 PROFESSIONAL ARRANGER
			Instrumento korgI3 = new Instrumento(piano, "Pa600 PROFESSIONAL ARRANGER", korg,
					"Teclado arranger profesional con sonidos realistas y funciones avanzadas de edición.", 1200.00,
					"Arranger", "Alta", "Nuevo", fechaCreacion);

			// 4. Sintetizador - modwave module WAVETABLE SYNTHESIZER
			Instrumento korgI4 = new Instrumento(sintetizadores, "modwave module WAVETABLE SYNTHESIZER", korg,
					"Sintetizador de tablas de ondas con capacidades de modulación avanzadas, ideal para diseñadores de sonido.",
					900.00, "Sintetizador", "Alta", "Nuevo", fechaCreacion);

			// 5. Sintetizador - opsix module ALTERED FM SYNTHESIZER
			Instrumento korgI5 = new Instrumento(sintetizadores, "opsix module ALTERED FM SYNTHESIZER", korg,
					"Sintetizador de síntesis FM alterada, perfecto para exploración sonora y diseño de tonos únicos.",
					950.00, "Sintetizador FM", "Alta", "Nuevo", fechaCreacion);

			// 6. Sintetizador - wavestate module WAVE SEQUENCING SYNTHESIZER
			Instrumento korgI6 = new Instrumento(sintetizadores, "wavestate module WAVE SEQUENCING SYNTHESIZER", korg,
					"Sintetizador de secuenciación de ondas con funciones avanzadas de manipulación de sonido.",
					1100.00, "Sintetizador", "Alta", "Nuevo", fechaCreacion);
			korgI1.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/korg/instrumentos/korgI1.png"));
			korgI2.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/korg/instrumentos/korgI2.png"));
			korgI3.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/korg/instrumentos/korgI3.png"));
			korgI4.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/korg/instrumentos/korgI4.png"));
			korgI5.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/korg/instrumentos/korgI5.png"));
			korgI6.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/korg/instrumentos/korgI6.png"));

			korgI1.setVentas(random.nextInt(101));
			korgI2.setVentas(random.nextInt(101));
			korgI3.setVentas(random.nextInt(101));
			korgI4.setVentas(random.nextInt(101));
			korgI5.setVentas(random.nextInt(101));
			korgI6.setVentas(random.nextInt(101));

			entityManager.persist(korgI1);
			entityManager.persist(korgI2);
			entityManager.persist(korgI3);
			entityManager.persist(korgI4);
			entityManager.persist(korgI5);
			entityManager.persist(korgI6);

			// Objetos Instrumento de la marca Casio

			// 1. Piano - CELVIANO AP-550BN
			Instrumento casioI1 = new Instrumento(piano, "CELVIANO AP-550BN", casio,
					"Piano digital de alta gama con teclado de acción de martillo graduado, ideal para interpretaciones clásicas.",
					1300.00, "Piano digital", "Alta", "Nuevo", fechaCreacion);

			// 2. Piano - CELVIANO AP-550WE
			Instrumento casioI2 = new Instrumento(piano, "CELVIANO AP-550WE", casio,
					"Piano digital con un elegante acabado en blanco, adecuado para entornos domésticos y profesionales.",
					1350.00, "Piano digital", "Alta", "Nuevo", fechaCreacion);

			// 3. Piano - CELVIANO AP-S450BK
			Instrumento casioI3 = new Instrumento(piano, "CELVIANO AP-S450BK", casio,
					"Piano digital con sonidos de alta calidad y funciones avanzadas para estudiantes y profesionales.",
					1200.00, "Piano digital", "Media", "Nuevo", fechaCreacion);

			// 4. Piano - CELVIANO AP-S450BN
			Instrumento casioI4 = new Instrumento(piano, "CELVIANO AP-S450BN", casio,
					"Piano digital con un diseño en color marrón, ideal para una experiencia de interpretación auténtica.",
					1250.00, "Piano digital", "Media", "Nuevo", fechaCreacion);

			// 5. Sintetizador - CT-S200WE Casiotone
			Instrumento casioI5 = new Instrumento(sintetizadores, "CT-S200WE Casiotone", casio,
					"Sintetizador compacto y portátil, perfecto para principiantes y músicos en movimiento.", 150.00,
					"Sintetizador portátil", "Baja", "Nuevo", fechaCreacion);

			// 6. Sintetizador - CT-S100 Casiotone
			Instrumento casioI6 = new Instrumento(sintetizadores, "CT-S100 Casiotone", casio,
					"Teclado compacto diseñado para principiantes, con funciones simples y facilidad de uso.", 100.00,
					"Sintetizador portátil", "Baja", "Nuevo", fechaCreacion);

			// 7. Sintetizador - CT-S1-76BK Casiotone
			Instrumento casioI7 = new Instrumento(sintetizadores, "CT-S1-76BK Casiotone", casio,
					"Sintetizador portátil con 76 teclas, ideal para músicos que buscan portabilidad sin sacrificar funciones.",
					200.00, "Sintetizador portátil", "Media", "Nuevo", fechaCreacion);
			casioI1.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/casio/instrumentos/casioI1.png"));
			casioI2.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/casio/instrumentos/casioI2.png"));
			casioI3.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/casio/instrumentos/casioI3.png"));
			casioI4.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/casio/instrumentos/casioI4.png"));
			casioI5.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/casio/instrumentos/casioI5.png"));
			casioI6.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/casio/instrumentos/casioI6.png"));
			casioI7.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/casio/instrumentos/casioI7.png"));
			casioI1.setVentas(random.nextInt(101));
			casioI2.setVentas(random.nextInt(101));
			casioI3.setVentas(random.nextInt(101));
			casioI4.setVentas(random.nextInt(101));
			casioI5.setVentas(random.nextInt(101));
			casioI6.setVentas(random.nextInt(101));
			casioI7.setVentas(random.nextInt(101));
			entityManager.persist(casioI1);
			entityManager.persist(casioI2);
			entityManager.persist(casioI3);
			entityManager.persist(casioI4);
			entityManager.persist(casioI5);
			entityManager.persist(casioI6);
			entityManager.persist(casioI7);

			/**
			 * Objetos Instrumento de la marca Fender
			 */

			// Guitarras
			// 1. Guitarra - Stratocaster Player Plus de Edición Limitada
			Instrumento fenderI1 = new Instrumento(guitarra, "Stratocaster Player Plus de Edición Limitada", fender,
					"Guitarra eléctrica con diseño icónico y mejoras modernas, ideal para guitarristas que buscan versatilidad.",
					1200.00, "Guitarra eléctrica", "Alta", "Nuevo", fechaCreacion);

			// 2. Guitarra - Stratocaster HSS HT Player Plus de Edición Limitada
			Instrumento fenderI2 = new Instrumento(guitarra, "Stratocaster HSS HT Player Plus de Edición Limitada",
					fender,
					"Guitarra eléctrica con configuración HSS y puente fijo, perfecta para tonos pesados y solos.",
					1300.00, "Guitarra eléctrica", "Alta", "Nuevo", fechaCreacion);

			// 3. Guitarra - Fender x Hello Kitty Stratocaster blanca
			Instrumento fenderI3 = new Instrumento(guitarra, "Fender x Hello Kitty Stratocaster blanca", fender,
					"Edición especial con diseño temático de Hello Kitty, ideal para coleccionistas y fans.", 900.00,
					"Guitarra eléctrica", "Media", "Nuevo", fechaCreacion);

			// 4. Guitarra - Stratocaster Player II British Racing Green de Edición Limitada
			Instrumento fenderI4 = new Instrumento(guitarra,
					"Stratocaster Player II British Racing Green de Edición Limitada", fender,
					"Guitarra de edición limitada con acabado en verde, destacada por su tono y estilo clásico.",
					1400.00, "Guitarra eléctrica", "Alta", "Nuevo", fechaCreacion);

			// Bajos
			// 5. Bajo - Jazz Bass V American Ultra II
			Instrumento fenderI5 = new Instrumento(bajo, "Jazz Bass V American Ultra II", fender,
					"Bajo de 5 cuerdas con un diseño moderno y electrónica avanzada, ideal para músicos profesionales.",
					1600.00, "Bajo eléctrico", "Alta", "Nuevo", fechaCreacion);

			// 6. Bajo - Jazz Bass American Ultra II
			Instrumento fenderI6 = new Instrumento(bajo, "Jazz Bass American Ultra II", fender,
					"Bajo eléctrico con un perfil delgado y comodidad mejorada para largas sesiones de interpretación.",
					1500.00, "Bajo eléctrico", "Alta", "Nuevo", fechaCreacion);

			// 7. Bajo - Jaguar Bass Mark Hoppus de Edición Limitada
			Instrumento fenderI7 = new Instrumento(bajo, "Jaguar Bass Mark Hoppus de Edición Limitada", fender,
					"Bajo de edición especial diseñado en colaboración con Mark Hoppus, ideal para fans del punk rock.",
					1400.00, "Bajo eléctrico", "Alta", "Nuevo", fechaCreacion);

			// 8. Bajo - Jazz Bass Player Plus x Blu DeTiger de Edición Limitada
			Instrumento fenderI8 = new Instrumento(bajo, "Jazz Bass Player Plus x Blu DeTiger de Edición Limitada",
					fender, "Edición especial en colaboración con Blu DeTiger, perfecto para bajos funky y grooves.",
					1300.00, "Bajo eléctrico", "Alta", "Nuevo", fechaCreacion);

			// 9. Bajo - American Professional II Precision Bass
			Instrumento fenderI9 = new Instrumento(bajo, "American Professional II Precision Bass", fender,
					"Bajo de alto rendimiento con diseño Precision clásico y mejoras modernas.", 1700.00,
					"Bajo eléctrico", "Alta", "Nuevo", fechaCreacion);

			// 10. Bajo - Player Plus Active Precision Bass
			Instrumento fenderI10 = new Instrumento(bajo, "Player Plus Active Precision Bass", fender,
					"Bajo eléctrico activo con gran versatilidad de tonos y comodidad para tocar.", 1250.00,
					"Bajo eléctrico", "Media", "Nuevo", fechaCreacion);

			fenderI1.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/fender/instrumentos/fenderI1.png"));
			fenderI2.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/fender/instrumentos/fenderI2.png"));
			fenderI3.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/fender/instrumentos/fenderI3.png"));
			fenderI4.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/fender/instrumentos/fenderI4.png"));
			fenderI5.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/fender/instrumentos/fenderI5.png"));
			fenderI6.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/fender/instrumentos/fenderI6.png"));
			fenderI7.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/fender/instrumentos/fenderI7.png"));
			fenderI8.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/fender/instrumentos/fenderI8.png"));
			fenderI9.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/fender/instrumentos/fenderI9.png"));
			fenderI10.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/fender/instrumentos/fenderI10.png"));

			fenderI1.setVentas(random.nextInt(101));
			fenderI2.setVentas(random.nextInt(101));
			fenderI3.setVentas(random.nextInt(101));
			fenderI4.setVentas(random.nextInt(101));
			fenderI5.setVentas(random.nextInt(101));
			fenderI6.setVentas(random.nextInt(101));
			fenderI7.setVentas(random.nextInt(101));
			fenderI8.setVentas(random.nextInt(101));
			fenderI9.setVentas(random.nextInt(101));
			fenderI10.setVentas(random.nextInt(101));

			entityManager.persist(fenderI1);
			entityManager.persist(fenderI2);
			entityManager.persist(fenderI3);
			entityManager.persist(fenderI4);
			entityManager.persist(fenderI5);
			entityManager.persist(fenderI6);
			entityManager.persist(fenderI7);
			entityManager.persist(fenderI8);
			entityManager.persist(fenderI9);
			entityManager.persist(fenderI10);

			/**
			 * Objetos Instrumento de la marca Gibson
			 * 
			 */
			// Guitarras
			// 1. Guitarra - Les Paul Standard 60s - Bourbon Burst
			Instrumento gibsonI1 = new Instrumento(guitarra, "Les Paul Standard 60s - Bourbon Burst", gibson,
					"Guitarra eléctrica icónica con tono clásico y acabado en Bourbon Burst, perfecta para rock y blues.",
					2500.00, "Guitarra eléctrica", "Alta", "Nuevo", fechaCreacion);

			// 2. Guitarra - Les Paul Supreme - Ebony, 3-Pickup
			Instrumento gibsonI2 = new Instrumento(guitarra, "Les Paul Supreme - Ebony, 3-Pickup", gibson,
					"Edición de lujo con 3 pastillas y un acabado elegante en Ebony, ideal para tonos versátiles.",
					3500.00, "Guitarra eléctrica", "Premium", "Nuevo", fechaCreacion);

			// 3. Guitarra - Les Paul Standard 60s Figured Top - Dark Purple Burst
			Instrumento gibsonI3 = new Instrumento(guitarra, "Les Paul Standard 60s Figured Top - Dark Purple Burst",
					gibson, "Versión moderna de la Les Paul con tapa figurada y acabado en Dark Purple Burst.", 2700.00,
					"Guitarra eléctrica", "Alta", "Nuevo", fechaCreacion);

			// 4. Guitarra - Les Paul Modern Figured - Cobalt Burst
			Instrumento gibsonI4 = new Instrumento(guitarra, "Les Paul Modern Figured - Cobalt Burst", gibson,
					"Diseño moderno con mejoras ergonómicas y acabado en Cobalt Burst.", 2800.00, "Guitarra eléctrica",
					"Alta", "Nuevo", fechaCreacion);

			// 5. Guitarra - Adam Jones Les Paul Standard - Antique Silverburst
			Instrumento gibsonI5 = new Instrumento(guitarra, "Adam Jones Les Paul Standard - Antique Silverburst",
					gibson,
					"Edición especial inspirada en el guitarrista de Tool, Adam Jones, con un acabado distintivo.",
					4000.00, "Guitarra eléctrica", "Edición Limitada", "Nuevo", fechaCreacion);

			// 6. Guitarra - ES-335 - Dark Purple Burst
			Instrumento gibsonI6 = new Instrumento(guitarra, "ES-335 - Dark Purple Burst", gibson,
					"Clásica guitarra semi-hueca, famosa por su versatilidad en jazz y blues.", 3200.00,
					"Guitarra semi-hueca", "Alta", "Nuevo", fechaCreacion);

			// 7. Guitarra - ES-335 Figured - Sixties Cherry
			Instrumento gibsonI7 = new Instrumento(guitarra, "ES-335 Figured - Sixties Cherry", gibson,
					"Versión con tapa figurada y acabado vintage en Sixties Cherry.", 3300.00, "Guitarra semi-hueca",
					"Alta", "Nuevo", fechaCreacion);

			// 8. Guitarra - SG Standard '61 - Vintage Cherry
			Instrumento gibsonI8 = new Instrumento(guitarra, "SG Standard '61 - Vintage Cherry", gibson,
					"Guitarra eléctrica con diseño SG clásico y tono icónico en acabado Vintage Cherry.", 2200.00,
					"Guitarra eléctrica", "Alta", "Nuevo", fechaCreacion);

			// Bajos
			// 9. Bajo - Thunderbird Bass - Tobacco Burst
			Instrumento gibsonI9 = new Instrumento(bajo, "Thunderbird Bass - Tobacco Burst", gibson,
					"Bajo eléctrico con diseño icónico y tonos profundos, ideal para rock y metal.", 1800.00,
					"Bajo eléctrico", "Alta", "Nuevo", fechaCreacion);

			// 10. Bajo - Non-Reverse Thunderbird - Ebony
			Instrumento gibsonI10 = new Instrumento(bajo, "Non-Reverse Thunderbird - Ebony", gibson,
					"Versión con diseño no reversible, acabado en Ebony y tonos versátiles.", 1700.00, "Bajo eléctrico",
					"Alta", "Nuevo", fechaCreacion);

			// 11. Bajo - Gene Simmons G2 Thunderbird - Ébano
			Instrumento gibsonI11 = new Instrumento(bajo, "Gene Simmons G2 Thunderbird - Ébano", gibson,
					"Edición especial diseñada en colaboración con Gene Simmons, perfecta para el escenario.", 2100.00,
					"Bajo eléctrico", "Edición Limitada", "Nuevo", fechaCreacion);

			// 12. Bajo - Rex Brown Signature Thunderbird - Ébano
			Instrumento gibsonI12 = new Instrumento(bajo, "Rex Brown Signature Thunderbird - Ébano", gibson,
					"Bajo de firma diseñado para el bajista de Pantera, Rex Brown.", 2300.00, "Bajo eléctrico",
					"Edición Limitada", "Nuevo", fechaCreacion);

			// 13. Bajo - Les Paul Junior Tribute DC Bass - Worn Ebony
			Instrumento gibsonI13 = new Instrumento(bajo, "Les Paul Junior Tribute DC Bass - Worn Ebony", gibson,
					"Bajo eléctrico compacto con un diseño clásico y tono contundente.", 1500.00, "Bajo eléctrico",
					"Media", "Nuevo", fechaCreacion);

			gibsonI1.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/gibson/instrumentos/gibsonI1.png"));
			gibsonI2.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/gibson/instrumentos/gibsonI2.png"));
			gibsonI3.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/gibson/instrumentos/gibsonI3.png"));
			gibsonI4.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/gibson/instrumentos/gibsonI4.png"));
			gibsonI5.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/gibson/instrumentos/gibsonI5.png"));
			gibsonI6.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/gibson/instrumentos/gibsonI6.png"));
			gibsonI7.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/gibson/instrumentos/gibsonI7.png"));
			gibsonI8.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/gibson/instrumentos/gibsonI8.png"));
			gibsonI9.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/gibson/instrumentos/gibsonI9.png"));
			gibsonI10.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/gibson/instrumentos/gibsonI10.png"));
			gibsonI11.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/gibson/instrumentos/gibsonI11.png"));
			gibsonI12.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/gibson/instrumentos/gibsonI12.png"));
			gibsonI13.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/gibson/instrumentos/gibsonI13.png"));

			gibsonI1.setVentas(random.nextInt(101));
			gibsonI2.setVentas(random.nextInt(101));
			gibsonI3.setVentas(random.nextInt(101));
			gibsonI4.setVentas(random.nextInt(101));
			gibsonI5.setVentas(random.nextInt(101));
			gibsonI6.setVentas(random.nextInt(101));
			gibsonI7.setVentas(random.nextInt(101));
			gibsonI8.setVentas(random.nextInt(101));
			gibsonI9.setVentas(random.nextInt(101));
			gibsonI10.setVentas(random.nextInt(101));
			gibsonI11.setVentas(random.nextInt(101));
			gibsonI12.setVentas(random.nextInt(101));
			gibsonI13.setVentas(random.nextInt(101));

			entityManager.persist(gibsonI1);
			entityManager.persist(gibsonI2);
			entityManager.persist(gibsonI3);
			entityManager.persist(gibsonI4);
			entityManager.persist(gibsonI5);
			entityManager.persist(gibsonI6);
			entityManager.persist(gibsonI7);
			entityManager.persist(gibsonI8);
			entityManager.persist(gibsonI9);
			entityManager.persist(gibsonI10);
			entityManager.persist(gibsonI11);
			entityManager.persist(gibsonI12);
			entityManager.persist(gibsonI13);

			/**
			 * Objetos Instrumento de la marca Ibanez
			 */

			// Guitarras
			// 1. Guitarra - AZES31
			Instrumento ibanezI1 = new Instrumento(guitarra, "AZES31", ibanez,
					"Guitarra eléctrica versátil y moderna, ideal para principiantes y profesionales.", 450.00,
					"Guitarra eléctrica", "Media", "Nuevo", fechaCreacion);

			// 2. Guitarra - AZES40
			Instrumento ibanezI2 = new Instrumento(guitarra, "AZES40", ibanez,
					"Modelo avanzado con características mejoradas para un tono excepcional.", 550.00,
					"Guitarra eléctrica", "Media", "Nuevo", fechaCreacion);

			// 3. Guitarra - AZES40L
			Instrumento ibanezI3 = new Instrumento(guitarra, "AZES40L", ibanez,
					"Versión para zurdos de la AZES40, con el mismo diseño y calidad.", 550.00, "Guitarra eléctrica",
					"Media", "Nuevo", fechaCreacion);

			// 4. Guitarra - AZ2407F Prestige
			Instrumento ibanezI4 = new Instrumento(guitarra, "AZ2407F Prestige", ibanez,
					"Guitarra de alta gama de la serie Prestige, diseñada para la máxima precisión y confort.", 2200.00,
					"Guitarra eléctrica", "Alta", "Nuevo", fechaCreacion);

			// 5. Guitarra - AZ2402 Prestige
			Instrumento ibanezI5 = new Instrumento(guitarra, "AZ2402 Prestige", ibanez,
					"Modelo de gama alta con construcción de precisión y componentes premium.", 2100.00,
					"Guitarra eléctrica", "Alta", "Nuevo", fechaCreacion);

			// 6. Guitarra - AZ2402L Prestige
			Instrumento ibanezI6 = new Instrumento(guitarra, "AZ2402L Prestige", ibanez,
					"Guitarra Prestige para zurdos, diseñada para músicos exigentes.", 2100.00, "Guitarra eléctrica",
					"Alta", "Nuevo", fechaCreacion);

			// 7. Guitarra - AZ24027 Prestige
			Instrumento ibanezI7 = new Instrumento(guitarra, "AZ24027 Prestige", ibanez,
					"Guitarra de 7 cuerdas de la serie Prestige, perfecta para géneros pesados y técnicos.", 2300.00,
					"Guitarra eléctrica", "Alta", "Nuevo", fechaCreacion);

			// Bajos
			// 8. Bajo - EHB1500
			Instrumento ibanezI8 = new Instrumento(bajo, "EHB1500", ibanez,
					"Bajo eléctrico de cuerpo ergonómico, ideal para tocar durante largas sesiones.", 1600.00,
					"Bajo eléctrico", "Alta", "Nuevo", fechaCreacion);

			// 9. Bajo - EHB1505
			Instrumento ibanezI9 = new Instrumento(bajo, "EHB1505", ibanez,
					"Bajo de 5 cuerdas con diseño moderno y electrónica avanzada.", 1800.00, "Bajo eléctrico", "Alta",
					"Nuevo", fechaCreacion);

			// 10. Bajo - EHB1505MS
			Instrumento ibanezI10 = new Instrumento(bajo, "EHB1505MS", ibanez,
					"Versión multiescala del EHB1505, optimizada para mayor rango tonal.", 1900.00,
					"Bajo eléctrico multiescala", "Alta", "Nuevo", fechaCreacion);

			// 11. Bajo - EHB1506MS
			Instrumento ibanezI11 = new Instrumento(bajo, "EHB1506MS", ibanez,
					"Bajo de 6 cuerdas multiescala, perfecto para bajistas que buscan versatilidad.", 2000.00,
					"Bajo eléctrico multiescala", "Alta", "Nuevo", fechaCreacion);

			// 12. Bajo - EHB1265MS
			Instrumento ibanezI12 = new Instrumento(bajo, "EHB1265MS", ibanez,
					"Bajo multiescala con un diseño ergonómico y gran rango de tonos.", 1700.00,
					"Bajo eléctrico multiescala", "Alta", "Nuevo", fechaCreacion);

			// 13. Bajo - EHB1000
			Instrumento ibanezI13 = new Instrumento(bajo, "EHB1000", ibanez,
					"Bajo eléctrico de la serie EHB con diseño sin cabezal, ideal para portabilidad.", 1400.00,
					"Bajo eléctrico", "Media", "Nuevo", fechaCreacion);

			ibanezI1.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/ibanez/instrumentos/ibanezI1.png"));
			ibanezI2.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/ibanez/instrumentos/ibanezI2.png"));
			ibanezI3.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/ibanez/instrumentos/ibanezI3.png"));
			ibanezI4.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/ibanez/instrumentos/ibanezI4.png"));
			ibanezI5.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/ibanez/instrumentos/ibanezI5.png"));
			ibanezI6.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/ibanez/instrumentos/ibanezI6.png"));
			ibanezI7.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/ibanez/instrumentos/ibanezI7.png"));
			ibanezI8.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/ibanez/instrumentos/ibanezI8.png"));
			ibanezI9.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/ibanez/instrumentos/ibanezI9.png"));
			ibanezI10.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/ibanez/instrumentos/ibanezI10.png"));
			ibanezI11.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/ibanez/instrumentos/ibanezI11.png"));
			ibanezI12.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/ibanez/instrumentos/ibanezI12.png"));
			ibanezI13.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/ibanez/instrumentos/ibanezI13.png"));

			ibanezI1.setVentas(random.nextInt(101));
			ibanezI2.setVentas(random.nextInt(101));
			ibanezI3.setVentas(random.nextInt(101));
			ibanezI4.setVentas(random.nextInt(101));
			ibanezI5.setVentas(random.nextInt(101));
			ibanezI6.setVentas(random.nextInt(101));
			ibanezI7.setVentas(random.nextInt(101));
			ibanezI8.setVentas(random.nextInt(101));
			ibanezI9.setVentas(random.nextInt(101));
			ibanezI10.setVentas(random.nextInt(101));
			ibanezI11.setVentas(random.nextInt(101));
			ibanezI12.setVentas(random.nextInt(101));
			ibanezI13.setVentas(random.nextInt(101));

			entityManager.persist(ibanezI1);
			entityManager.persist(ibanezI2);
			entityManager.persist(ibanezI3);
			entityManager.persist(ibanezI4);
			entityManager.persist(ibanezI5);
			entityManager.persist(ibanezI6);
			entityManager.persist(ibanezI7);
			entityManager.persist(ibanezI8);
			entityManager.persist(ibanezI9);
			entityManager.persist(ibanezI10);
			entityManager.persist(ibanezI11);
			entityManager.persist(ibanezI12);
			entityManager.persist(ibanezI13);

			/**
			 * Objetos Instrumento de la marca Drum Workshop (DW)
			 * 
			 */
			// Baterías
			// 1. Batería - 6-Piece Performance Series Kit
			Instrumento dwI1 = new Instrumento(bateria, "6-Piece Performance Series Kit", dw,
					"Set de batería de 6 piezas, parte de la serie Performance, conocida por su calidad y resonancia.",
					3200.00, "Batería acústica", "Alta", "Nuevo", fechaCreacion);

			// 2. Batería - 4-Piece Performance Series Kit
			Instrumento dwI2 = new Instrumento(bateria, "4-Piece Performance Series Kit", dw,
					"Set de batería de 4 piezas, ideal para estudios y presentaciones en vivo.", 2500.00,
					"Batería acústica", "Alta", "Nuevo", fechaCreacion);

			// 3. Batería - DWe Drum Kit Bundle, 4-Piece
			Instrumento dwI3 = new Instrumento(bateria, "DWe Drum Kit Bundle, 4-Piece", dw,
					"Batería electrónica de 4 piezas, incluye todos los elementos esenciales para empezar.", 1800.00,
					"Batería electrónica", "Media", "Nuevo", fechaCreacion);

			// 4. Batería - DWe Drum Kit Bundle, 5-Piece
			Instrumento dwI4 = new Instrumento(bateria, "DWe Drum Kit Bundle, 5-Piece", dw,
					"Batería electrónica de 5 piezas, perfecta para estudios y práctica en casa.", 2000.00,
					"Batería electrónica", "Media", "Nuevo", fechaCreacion);

			// 5. Batería - Design 4-Piece Kit, Limited
			Instrumento dwI5 = new Instrumento(bateria, "Design 4-Piece Kit, Limited", dw,
					"Edición limitada de la serie Design, con un diseño exclusivo y componentes de alta calidad.",
					2700.00, "Batería acústica", "Edición Limitada", "Nuevo", fechaCreacion);

			// Percusión
			// 6. Percusión - DWe Hi-Hat Cymbal, 14"
			Instrumento dwI6 = new Instrumento(percusion, "DWe Hi-Hat Cymbal, 14 inch", dw,
					"Platillo de Hi-Hat de 14 pulgadas, diseñado para un sonido nítido y definido.", 400.00, "Platillo",
					"Alta", "Nuevo", fechaCreacion);

			// 7. Percusión - DWe Electronic Crash/Ride Cymbal 18 inch
			Instrumento dwI7 = new Instrumento(percusion, "DWe Electronic Crash/Ride Cymbal 18 inch", dw,
					"Platillo electrónico de 18 pulgadas, perfecto para setups híbridos.", 350.00,
					"Platillo Electrónico", "Media", "Nuevo", fechaCreacion);

			// 8. Percusión - DW DWe Electronic Cymbal Pack, 3-Piece
			Instrumento dwI8 = new Instrumento(percusion, "DW DWe Electronic Cymbal Pack, 3-Piece", dw,
					"Set de 3 platillos electrónicos, ideal para expandir kits electrónicos.", 800.00,
					"Platillo Electrónico", "Media", "Nuevo", fechaCreacion);

			// 9. Percusión - DW DWe Electronic Cymbal Pack, 4-Piece
			Instrumento dwI9 = new Instrumento(percusion, "DW DWe Electronic Cymbal Pack, 4-Piece", dw,
					"Set de 4 platillos electrónicos, diseñado para mayor versatilidad en kits de batería.", 1000.00,
					"Platillo Electrónico", "Alta", "Nuevo", fechaCreacion);

			// 10. Percusión - Design Maple Snare 6x14, Limited
			Instrumento dwI10 = new Instrumento(percusion, "Design Maple Snare 6x14, Limited", dw,
					"Caja de madera de arce de 6x14 pulgadas, edición limitada con un tono profundo.", 500.00, "Caja",
					"Edición Limitada", "Nuevo", fechaCreacion);

			// 11. Percusión - Design Series Maple Snare, 6x14
			Instrumento dwI11 = new Instrumento(percusion, "Design Series Maple Snare, 6x14", dw,
					"Caja de la serie Design con un tono brillante y versátil.", 450.00, "Caja", "Alta", "Nuevo",
					fechaCreacion);

			dwI1.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/dw/instrumentos/dwI1.png"));
			dwI2.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/dw/instrumentos/dwI2.png"));
			dwI3.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/dw/instrumentos/dwI3.png"));
			dwI4.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/dw/instrumentos/dwI4.png"));
			dwI5.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/dw/instrumentos/dwI5.png"));
			dwI6.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/dw/instrumentos/dwI6.png"));
			dwI7.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/dw/instrumentos/dwI7.png"));
			dwI8.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/dw/instrumentos/dwI8.png"));
			dwI9.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/dw/instrumentos/dwI9.png"));
			dwI10.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/dw/instrumentos/dwI10.png"));
			dwI11.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/dw/instrumentos/dwI11.png"));

			dwI1.setVentas(random.nextInt(101));
			dwI2.setVentas(random.nextInt(101));
			dwI3.setVentas(random.nextInt(101));
			dwI4.setVentas(random.nextInt(101));
			dwI5.setVentas(random.nextInt(101));
			dwI6.setVentas(random.nextInt(101));
			dwI7.setVentas(random.nextInt(101));
			dwI8.setVentas(random.nextInt(101));
			dwI9.setVentas(random.nextInt(101));
			dwI10.setVentas(random.nextInt(101));
			dwI11.setVentas(random.nextInt(101));

			entityManager.persist(dwI1);
			entityManager.persist(dwI2);
			entityManager.persist(dwI3);
			entityManager.persist(dwI4);
			entityManager.persist(dwI5);
			entityManager.persist(dwI6);
			entityManager.persist(dwI7);
			entityManager.persist(dwI8);
			entityManager.persist(dwI9);
			entityManager.persist(dwI10);
			entityManager.persist(dwI11);

			// Objetos Instrumento de la marca Pearl

			// Baterías
			// 1. Batería - Masterworks
			Instrumento pearlI1 = new Instrumento(bateria, "Masterworks", pearl,
					"Batería de alta gama completamente personalizable, diseñada para ofrecer la máxima calidad sonora.",
					7000.00, "Batería acústica", "Premium", "Nuevo", fechaCreacion);

			// 2. Batería - Masters Maple Gum
			Instrumento pearlI2 = new Instrumento(bateria, "Masters Maple Gum", pearl,
					"Set de batería de arce y goma, perfecto para un tono cálido y resonante.", 4000.00,
					"Batería acústica", "Alta", "Nuevo", fechaCreacion);

			// 3. Batería - Crystal Beat
			Instrumento pearlI3 = new Instrumento(bateria, "Crystal Beat", pearl,
					"Batería transparente de acrílico, diseñada para destacar visual y sonoramente en el escenario.",
					3000.00, "Batería acústica", "Media", "Nuevo", fechaCreacion);

			// 4. Batería - Decade Maple Artisan
			Instrumento pearlI4 = new Instrumento(bateria, "Decade Maple Artisan", pearl,
					"Set de batería artesanal de arce, perfecto para músicos que buscan un tono clásico.", 2000.00,
					"Batería acústica", "Media", "Nuevo", fechaCreacion);

			// 5. Batería - Decade Maple
			Instrumento pearlI5 = new Instrumento(bateria, "Decade Maple", pearl,
					"Batería versátil de arce, ideal para estudios y presentaciones en vivo.", 1500.00,
					"Batería acústica", "Media", "Nuevo", fechaCreacion);

			// 6. Batería - Roadshow
			Instrumento pearlI6 = new Instrumento(bateria, "Roadshow", pearl,
					"Set de batería para principiantes, incluye todo lo necesario para empezar a tocar.", 800.00,
					"Batería acústica", "Baja", "Nuevo", fechaCreacion);

			// Percusión
			// 7. Percusión - Stave Craft
			Instrumento pearlI7 = new Instrumento(percusion, "Stave Craft", pearl,
					"Caja artesanal de alta gama, diseñada para ofrecer un tono profundo y resonante.", 900.00, "Caja",
					"Alta", "Nuevo", fechaCreacion);

			// 8. Percusión - Sensitone
			Instrumento pearlI8 = new Instrumento(percusion, "Sensitone", pearl,
					"Caja versátil con un tono brillante, ideal para todo tipo de géneros musicales.", 600.00, "Caja",
					"Media", "Nuevo", fechaCreacion);

			// 9. Percusión - Hybrid Exotic
			Instrumento pearlI9 = new Instrumento(percusion, "Hybrid Exotic", pearl,
					"Caja de diseño híbrido con materiales exóticos, perfecta para músicos que buscan un sonido único.",
					750.00, "Caja", "Alta", "Nuevo", fechaCreacion);

			// 10. Percusión - Effects Snares
			Instrumento pearlI10 = new Instrumento(percusion, "Effects Snares", pearl,
					"Caja diseñada para efectos especiales y sonidos únicos en el estudio o en vivo.", 500.00, "Caja",
					"Media", "Nuevo", fechaCreacion);

			// 11. Percusión - UltraCast
			Instrumento pearlI11 = new Instrumento(percusion, "UltraCast", pearl,
					"Caja de aluminio fundido, conocida por su proyección y potencia sonora.", 650.00, "Caja", "Media",
					"Nuevo", fechaCreacion);

			pearlI1.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/pearl/instrumentos/pearlI1.png"));
			pearlI2.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/pearl/instrumentos/pearlI2.png"));
			pearlI3.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/pearl/instrumentos/pearlI3.png"));
			pearlI4.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/pearl/instrumentos/pearlI4.png"));
			pearlI5.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/pearl/instrumentos/pearlI5.png"));
			pearlI6.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/pearl/instrumentos/pearlI6.png"));
			pearlI7.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/pearl/instrumentos/pearlI7.png"));
			pearlI8.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/pearl/instrumentos/pearlI8.png"));
			pearlI9.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/pearl/instrumentos/pearlI9.png"));
			pearlI10.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/pearl/instrumentos/pearlI10.png"));
			pearlI11.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/pearl/instrumentos/pearlI11.png"));

			pearlI1.setVentas(random.nextInt(101));
			pearlI2.setVentas(random.nextInt(101));
			pearlI3.setVentas(random.nextInt(101));
			pearlI4.setVentas(random.nextInt(101));
			pearlI5.setVentas(random.nextInt(101));
			pearlI6.setVentas(random.nextInt(101));
			pearlI7.setVentas(random.nextInt(101));
			pearlI8.setVentas(random.nextInt(101));
			pearlI9.setVentas(random.nextInt(101));
			pearlI10.setVentas(random.nextInt(101));
			pearlI11.setVentas(random.nextInt(101));

			entityManager.persist(pearlI1);
			entityManager.persist(pearlI2);
			entityManager.persist(pearlI3);
			entityManager.persist(pearlI4);
			entityManager.persist(pearlI5);
			entityManager.persist(pearlI6);
			entityManager.persist(pearlI7);
			entityManager.persist(pearlI8);
			entityManager.persist(pearlI9);
			entityManager.persist(pearlI10);
			entityManager.persist(pearlI11);

			// Objetos Instrumento de la marca Meinl (Percusión)

			// 1. Percusión - String Cajon Seguiriya Line, Indian Heartwood
			Instrumento meinlI1 = new Instrumento(percusion, "String Cajon Seguiriya Line, Indian Heartwood", meinl,
					"Cajón de la línea Seguiriya, hecho de Indian Heartwood, con cuerdas internas ajustables para un sonido flamenco auténtico.",
					350.00, "Cajón", "Alta", "Nuevo", fechaCreacion);

			// 2. Percusión - String Cajon Fandago Line, Indian Heartwood, Tulip Poplar
			Instrumento meinlI2 = new Instrumento(percusion,
					"String Cajon Fandago Line, Indian Heartwood, Tulip Poplar", meinl,
					"Cajón de la línea Fandago, construido con Indian Heartwood y Tulip Poplar para un tono cálido y profundo.",
					320.00, "Cajón", "Alta", "Nuevo", fechaCreacion);

			// 3. Percusión - String Cajon Martinete Line, Brazilian Ironwood
			Instrumento meinlI3 = new Instrumento(percusion, "String Cajon Martinete Line, Brazilian Ironwood", meinl,
					"Cajón de la línea Martinete, fabricado con Brazilian Ironwood para una proyección potente y clara.",
					400.00, "Cajón", "Alta", "Nuevo", fechaCreacion);

			// 4. Percusión - String Cajon Cantina Line Limba
			Instrumento meinlI4 = new Instrumento(percusion, "String Cajon Cantina Line Limba", meinl,
					"Cajón de la línea Cantina, hecho de Limba, ideal para tonos equilibrados y versátiles.", 300.00,
					"Cajón", "Media", "Nuevo", fechaCreacion);

			// 5. Percusión - String Cajon Buleria Line, Mongoy
			Instrumento meinlI5 = new Instrumento(percusion, "String Cajon Buleria Line, Mongoy", meinl,
					"Cajón de la línea Buleria, con acabado en Mongoy, proporciona un sonido profundo y resonante perfecto para flamenco.",
					370.00, "Cajón", "Alta", "Nuevo", fechaCreacion);

			// 6. Percusión - String Cajon Cantina Line Brown Eucalyptus
			Instrumento meinlI6 = new Instrumento(percusion, "String Cajon Cantina Line Brown Eucalyptus", meinl,
					"Cajón de la línea Cantina con acabado en Brown Eucalyptus, ofreciendo un sonido cálido y redondo.",
					330.00, "Cajón", "Media", "Nuevo", fechaCreacion);

			meinlI1.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/meinl/instrumentos/meinlI1.png"));
			meinlI2.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/meinl/instrumentos/meinlI2.png"));
			meinlI3.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/meinl/instrumentos/meinlI3.png"));
			meinlI4.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/meinl/instrumentos/meinlI4.png"));
			meinlI5.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/meinl/instrumentos/meinlI5.png"));
			meinlI6.setImagenPortada(
					leerBytesDeRutaOrigen("http://localhost:8080/img/marcas/meinl/instrumentos/meinlI6.png"));

			meinlI1.setVentas(random.nextInt(101));
			meinlI2.setVentas(random.nextInt(101));
			meinlI3.setVentas(random.nextInt(101));
			meinlI4.setVentas(random.nextInt(101));
			meinlI5.setVentas(random.nextInt(101));
			meinlI6.setVentas(random.nextInt(101));

			entityManager.persist(meinlI1);
			entityManager.persist(meinlI2);
			entityManager.persist(meinlI3);
			entityManager.persist(meinlI4);
			entityManager.persist(meinlI5);
			entityManager.persist(meinlI6);

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
			servicioCarrito.agregarProducto(rolandI5.getId(), u1.getId(), 1);
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
