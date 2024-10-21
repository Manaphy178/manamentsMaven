let html_formulario_registro_usuario = "";
let html_listado_productos = "";
let html_formulario_identificacion_usuario = "";

// Vamos a descargar todas las plantillas
$.get("plantillas/formulario-registro-usuario.html").done(function (res) {
  html_formulario_registro_usuario = res;
});
$.get("plantillas/listado-productos.html").done(function (res) {
  html_listado_productos = res;
});
$.get("plantillas/formulario-identificacion-usuario.html").done(function (res) {
  html_formulario_identificacion_usuario = res;
});
