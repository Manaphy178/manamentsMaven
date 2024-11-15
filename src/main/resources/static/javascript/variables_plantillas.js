let html_formulario_registro_usuario = "";
let html_listado_productos = "";
let html_listado_todos_productos = "";
let html_formulario_identificacion_usuario = "";
let html_detalles_instrumento = "";
let html_carrito = "";
let html_carrito_vacio = "";
let html_checkout_1 = "";
let html_checkout_2 = "";
let html_checkout_3 = "";
let html_resumen_pedido = "";
let html_mejores_cinco = "";
let html_listado_marca = "";
let html_instrumentos_marca = "";
let html_listado_categoria = "";
let html_instrumentos_categoria = "";

// Vamos a descargar todas las plantillas
$.get("plantillas" + sufijo_idioma + "/formulario-registro-usuario.html").done(
  function (res) {
    html_formulario_registro_usuario = res;
  }
);
$.get("plantillas" + sufijo_idioma + "/listado_marcas.html").done(function (
  res
) {
  html_listado_marca = res;
});
$.get(
  "plantillas" + sufijo_idioma + "/listado_instrumentos_marcas.html"
).done(function (res) {
  html_instrumentos_marca = res;
});
$.get("plantillas" + sufijo_idioma + "/listado_categoria.html").done(function (
  res
) {
  html_listado_categoria = res;
});
$.get(
  "plantillas" + sufijo_idioma + "/listado_instrumentos_categoria.html"
).done(function (res) {
  html_instrumentos_categoria = res;
});
$.get("plantillas" + sufijo_idioma + "/listado-productos.html").done(function (
  res
) {
  html_listado_productos = res;
});
$.get("plantillas" + sufijo_idioma + "/listado_todos_instrumentos.html").done(
  function (res) {
    html_listado_todos_productos = res;
  }
);
$.get(
  "plantillas" + sufijo_idioma + "/formulario-identificacion-usuario.html"
).done(function (res) {
  html_formulario_identificacion_usuario = res;
});
$.get("plantillas" + sufijo_idioma + "/detalles-instrumento.html").done(
  function (res) {
    html_detalles_instrumento = res;
  }
);
$.get("plantillas" + sufijo_idioma + "/carrito.html").done(function (res) {
  html_carrito = res;
});
$.get("plantillas" + sufijo_idioma + "/carrito_vacio.html").done(function (
  res
) {
  html_carrito_vacio = res;
});
$.get("plantillas" + sufijo_idioma + "/checkout_1.html").done(function (res) {
  html_checkout_1 = res;
});
$.get("plantillas" + sufijo_idioma + "/checkout_2.html").done(function (res) {
  html_checkout_2 = res;
});
$.get("plantillas" + sufijo_idioma + "/checkout_3.html").done(function (res) {
  html_checkout_3 = res;
});
$.get("plantillas" + sufijo_idioma + "/resumen_pedido.html").done(function (
  res
) {
  html_resumen_pedido = res;
});
$.get("plantillas" + sufijo_idioma + "/listado-cinco-mejores.html").done(
  function (res) {
    html_mejores_cinco = res;
  }
);
