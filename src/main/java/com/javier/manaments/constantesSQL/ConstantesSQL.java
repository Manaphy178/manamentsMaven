package com.javier.manaments.constantesSQL;

/*
 * Cuando jpa se queda corto y queremos ser mas especificos con las consultas sobre una bd de concreta, podemos usar JPA con SQL
 */
public class ConstantesSQL {
	public final static String SQL_OBTENER_DETALLES_INSTRUMENTO = "select i.nombre_instrumento,i.precio,i.descripcion,i.id from tabla_instrumentos as i where i.id = :id";
	public static final String SQL_OBTENER_LISTADO_INSTRUMENTOS = "select i.nombre_instrumento,i.precio,i.id from tabla_instrumentos as i";
}
