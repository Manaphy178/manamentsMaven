package com.javier.manaments.servicesJPAImpl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javier.manaments.constantesSQL.ConstantesSQL;
import com.javier.manaments.model.Categoria;
import com.javier.manaments.model.Instrumento;
import com.javier.manaments.services.ServicioInstrumento;

@Service
@Transactional
public class ServiciosInstrumentosJPAImpl implements ServicioInstrumento {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void registrarInstrumento(Instrumento i) {
		/*
		 * Vamos a obtener la categoria dle id de categoria del instrumento para
		 * asociarlo correctamente antes de guardarlo
		 */
		Categoria c = entityManager.find(Categoria.class, i.getIdCategoria());
		i.setCategoria(c);
		entityManager.persist(i);
	}

	@Override
	public List<Instrumento> obtenerInstrumentos() {
		List<Instrumento> i = entityManager.createQuery("SELECT i FROM Instrumento i").getResultList();
		return i;
	}

	@Override
	public List<Instrumento> obtenerInstrumentosMasVendidos() {
		List<Instrumento> i = entityManager.createQuery(
				"select i from Instrumento i where i.ventas > 0 order by i.ventas DESC").setMaxResults(5)
				.getResultList();
		return i;
	}

	@Override
	public void actualizarInstrumento(Instrumento i) {
		entityManager.merge(i);
	}

	@Override
	public void borrarInstrumento(int id) {
		Instrumento i = entityManager.find(Instrumento.class, id);
		entityManager.remove(i);
	}

	@Override
	public Instrumento obtenerInstrumentoPorId(int id) {
		return entityManager.find(Instrumento.class, id);
	}

	@Override
	public Map<String, Object> obtenerInstrumentoVerDetallesPorId(long id) {
		// Una forma de especificar que campos queremos en la consulta es esta:
//		Asi seria usando JPQL:
//		return (Instrumento) entityManager
//				.createQuery("select i.nombre,i.descripcion,i.precio from Instrumento i where i.id = :id")
//				.setParameter("id", id).getSingleResult();
		Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_DETALLES_INSTRUMENTO);
		query.setParameter("id", id);

//		Una vez preparada la query para obtener el resultado como tipo map hay que hacer esto:
		NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		return (Map<String, Object>) nativeQuery.getSingleResult();
	}

	@Override
	public List<Map<String, Object>> obtenerInstrumentosParaListado() {
		Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_LISTADO_INSTRUMENTOS);
		NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		return nativeQuery.getResultList();
	}

	@Override
	public List<Instrumento> obtenerInstrumentos(String nombre) {
		List<Instrumento> i = entityManager.createQuery("SELECT i FROM Instrumento i where i.nombre like :nombre")
				.setParameter("nombre", "%" + nombre + "%").getResultList();
		return i;
	}

	@Override
	public List<Instrumento> obtenerInstrumentos(String nombre, int comienzo, int resultadosPorPagina) {
		List<Instrumento> i = entityManager.createQuery("SELECT i FROM Instrumento i where i.nombre like :nombre")
				.setParameter("nombre", "%" + nombre + "%").setFirstResult(comienzo).setMaxResults(resultadosPorPagina)
				.getResultList();
		return i;
	}

	@Override
	public int obtenerTotalInstrumentos() {
		Query q = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_TOTAL_INSTRUMENTOS);
		int totalInstrumentos = Integer.parseInt(q.getSingleResult().toString());
		return totalInstrumentos;
	}

	@Override
	public int obtenerTotalInstrumentos(String nombre) {
		Query q = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_TOTAL_INSTRUMENTOS_POR_NOMBRE);
		q.setParameter("nombre", "%" + nombre + "%");
		int totalInstrumentos = Integer.parseInt(q.getSingleResult().toString());
		return totalInstrumentos;
	}

}
