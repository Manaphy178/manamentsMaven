function checkout_paso_0() {
  // mostrar el formulario donde inserta la informacion de envio
  $("#contenedor").html(html_checkout_1);
  $("#aceptar_paso_1").click(checkout_paso_1_aceptar);
} // end checkout_paso_0

function checkout_paso_1_aceptar() {
  let nombre = $("#campo_nombre").val();
  let direccion = $("#campo_direccion").val();
  let provincia = $("#campo_provincia").val();

  // Ahora lo suyo seria validar los valores recogidos
  // TODO

  $.post("realizar-pedido-paso1", {
    // TODO meter 3 campos mas que puedas meter en campos de envio
    nombre: nombre,
    direccion: direccion,
    provincia: provincia,
  }).done(function (res) {
    if (res == "ok") {
      $("#contenedor").html(html_checkout_2);
      $("#aceptar_paso_2").click(checkout_paso_2_aceptar);
    } else {
      alert("fallo en realizar-pedido-paso1");
      console.log(res);
    }
  });
} // end checkout_paso_1_aceptar

function checkout_paso_2_aceptar() {
  let tipo_tarjeta = $("#tipo_tarjeta").find(":selected").val();
  let numero_tarjeta = $("#numero_tarjeta").val();
  let titular_tarjeta = $("#titular_tarjeta").val();

  $.post("realizar-pedido-paso2", {
    // TODO meter numero de seguridad y fecha de caducidad
    tarjeta: tipo_tarjeta,
    numero: numero_tarjeta,
    titular: titular_tarjeta,
  }).done(function (res) {
    if ((res = "ok")) {
      $("#contenedor").html(html_checkout_3);
      $("#aceptar_paso_3").click(checkout_paso_3_aceptar);
    } else {
      alert("fallo en realizar-pedido-paso2");
      console.log(res);
    }
  });
}
function checkout_paso_3_aceptar() {}
