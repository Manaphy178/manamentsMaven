function checkout_paso_0() {
  // mostrar el formulario donde inserta la informacion de envio
  $("#contenedor").html(html_checkout_1);
  $("#aceptar_paso_1").click(checkout_paso_1_aceptar);
} // end checkout_paso_0

function checkout_paso_1_aceptar() {
  let nombre = $("#campo_nombre").val();
  let direccion = $("#campo_direccion").val();
  let provincia = $("#campo_provincia").val();
  let pais = $("#campo_pais").val();
  let telefono = $("#campo_telefono").val();
  let poblacion = $("#campo_poblacion").val();

  // Ahora lo suyo seria validar los valores recogidos
  // TODO

  $.post("realizar-pedido-paso1", {
    // TODO meter 3 campos mas que puedas meter en campos de envio
    nombre: nombre,
    pais: pais,
    telefono: telefono,
    direccion: direccion,
    provincia: provincia,
    poblacion: poblacion,
  }).done(function (res) {
    if (res == "ok") {
      $("#contenedor").html(html_checkout_2);
      $("#aceptar_paso_2").click(checkout_paso_2_aceptar);
      console.log(res);
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
  let codigo_seguridad = $("#codigo_seguridad").val();
  let fecha_caducidad = $("#fecha_caducidad").val();

  $.post("realizar-pedido-paso2", {
    // TODO meter numero de seguridad y fecha de caducidad
    tarjeta: tipo_tarjeta,
    numero: numero_tarjeta,
    titular: titular_tarjeta,
    cvv: codigo_seguridad,
    caducidad: fecha_caducidad,
  }).done(function (res) {
    /**
     * res tiene el resumen del pedido para mostrarlo
     * en checkout_3.html
     */
    console.log(res);
    let html = Mustache.render(html_checkout_3, res);
    $("#contenedor").html(html);
    $("#aceptar_paso_3").click(resumen_pedido);
  });
}

function mostrarTextarea() {
  const envio = document.querySelector('input[name="entrega"][value="envio"]');
  const text = document.getElementById("especificacion_entrega");
  if (envio.checked) {
    text.style.display = "block";
  } else {
    text.style.display = "none";
  }
}

function resumen_pedido() {
  let envio = $("#entrega").find(":selected").val();
  let extra = $("#extra").val();
  $.post("resumen-pedido", {
    forma_envio: envio,
    extra: extra,
  }).done(function (res) {
    console.log(res);
    let html = Mustache.render(html_resumen_pedido, res);

    $("#contenedor").html(html);
    $("#boton_confirmar_pedido").click(confirmar_pedido);
  });
}

function confirmar_pedido() {
  $.post("confirmar-pedido").done(function (res) {
    console.log("Respuesta del REST de pedidos: " + res);
    if (res == "pedido completado") {
      alert("Gracias por realizar tu pedido con nosotros");
      obtenerProductos();
    }
  });
}

function opcionEntrega() {
  const especificaciones = $("#especificaciones");
  
}
