package com.javier.manaments.constantesSQL;

/*
 * Cuando jpa se queda corto y queremos ser mas especificos con las consultas sobre una bd de concreta, podemos usar JPA con SQL
 */
public class ConstantesSQL {
	public final static String SQL_OBTENER_DETALLES_INSTRUMENTO = "select i.nombre_instrumento,i.id,i.descripcion,i.gamma,i.precio,i.tipo,i.categoria_id_categoria,m.nombre_marca,i.estado,i.ventas,c.id_categoria,c.nombre_categoria from tabla_instrumentos as i, marca as m,categoria as c where i.id = :id and i.marca_id = m.id and c.id_categoria =i.categoria_id_categoria";
	public static final String SQL_OBTENER_LISTADO_INSTRUMENTOS = "select i.nombre_instrumento,i.id,i.descripcion,i.gamma,i.precio,i.tipo,i.categoria_id_categoria,m.nombre_marca,i.estado from tabla_instrumentos as i, marca as m where i.marca_id = m.id";
	public static final String SQL_OBTENER_LISTADO_INSTRUMENTOS_INCLUYENDO_NOMBRE_COMIENZO = "select i.nombre_instrumento,i.id,i.descripcion,i.gamma,i.precio,i.tipo,i.categoria_id_categoria,m.nombre_marca,i.estado,c.id_categoria,c.nombre_categoria from tabla_instrumentos as i, marca as m, categoria as c where i.marca_id = m.id and c.id_categoria =i.categoria_id_categoria and i.nombre_instrumento like :nombre order by i.id asc limit :comienzo, 12";
	public static final String SQL_OBTENER_TOP_CINCO_INSTRUMENTOS = "select i.nombre_instrumento,i.id,i.descripcion,i.gamma,i.precio,i.tipo,i.categoria_id_categoria,m.nombre_marca,i.estado,i.ventas FROM tabla_instrumentos AS i, marca as m WHERE i.ventas > 0 and i.marca_id = m.id ORDER BY i.ventas DESC";
	public static final String SQL_OBTENER_PRODUCTOS_CARRITO = "SELECT tl.id,tl.nombre_instrumento,tl.precio,pc.cantidad FROM tabla_instrumentos AS tl, producto_carrito AS pc WHERE pc.instrumento_id = tl.id AND pc.carrito_id = :carrito_id ORDER BY tl.precio DESC;";
	public static final String SQL_BORRAR_PRODUCTOS_CARRITO = "delete from producto_carrito where carrito_id = :carrito_id";
	public static final String SQL_OBTENER_ID_USUARIO_POR_EMAIL = "select id from usuario where email = :email";
	public static final String SQL_OBTENER_TOTAL_INSTRUMENTOS = "SELECT COUNT(id) from tabla_instrumentos";
	public static final String SQL_OBTENER_TOTAL_INSTRUMENTOS_POR_NOMBRE = "SELECT COUNT(id) from tabla_instrumentos where nombre_instrumento like :nombre";
	// Intentar que de el total de la compra
	// public static final String SQL_OBTENER_PRODUCTOS_CARRITO = "SELECT
	// tl.id,tl.nombre_instrumento,tl.precio,pc.cantidad,pc.total"
	// + " FROM tabla_instrumentos AS tl, producto_carrito AS pc"
	// + " WHERE pc.instrumento_id = tl.id AND pc.carrito_id = :carrito_id "
	// + " ORDER BY tl.precio DESC;";

}
