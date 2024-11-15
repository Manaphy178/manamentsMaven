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
import com.javier.manaments.model.Marca;
import com.javier.manaments.services.ServicioMarca;

@Service
@Transactional
public class ServicioMarcaJPAImpl implements ServicioMarca {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Marca> obtenerMarcas() {
		List<Marca> m = entityManager.createQuery("select m from Marca m").getResultList();
		return m;
	}

	@Override
	public Marca obtenerMarcaPorId(int id) {
		return entityManager.find(Marca.class, id);
	}

	@Override
	public List<Map<String, Object>> obtenerMarcaListado() {
		Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_MARCAS_LISTADO);
		NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		return nativeQuery.getResultList();
	}

}
