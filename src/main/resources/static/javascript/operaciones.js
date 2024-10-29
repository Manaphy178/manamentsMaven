/**
 * PRODUCTOS
 */

function obtenerProductos() {
  $.ajax("obtener-productos-json").done(function (respuesta) {
    let instrumento = JSON.parse(respuesta);
    let texto_html = "";
    texto_html = Mustache.render(html_listado_productos, instrumento);
    $("#contenedor").html(texto_html);
    // una vez volcado el listado, decimos que tiene que hacer
    // el enlace ver detalles
    $(".enlace_ver_detalles_instrumento").click(mostrarDetallesProducto);
  }); //end ajax
}
function mostrarDetallesProducto() {
  // this en js para eventos, en este caso un click de raton
  // indica el elemento sobre el que se ha hecho click
  let idProducto = $(this).attr("id-producto");
  $.getJSON("obtener-detalles-instrumento", {
    id: idProducto,
  }).done(function (res) {
    let html = Mustache.render(html_detalles_instrumento, res);
    $("#contenedor").html(html);
    $("#enlace_agregar_al_carrito").click(agregarProductoAlCarrito);
  });
}

/**
 * FIN PRODUCTOS
 */

/**
 *    USUARIOS
 */
function enviarInfoUsuarioAlServidor() {
  let nombre = $("#nombre").val();
  let apellido = $("#apellido").val();
  let nomUsuario = $("#nomUsuario").val();
  let email = $("#email").val();
  let pass = $("#pass").val();
  let codPostal = $("#codPostal").val();
  //falta validar los datos antes de mandarlos a ServicioUsuarios
  $.post("registrar-usuario-cliente", {
    nombre: nombre,
    apellido: apellido,
    nomUsuario: nomUsuario,
    email: email,
    pass: pass,
    codPostal: codPostal,
  }).done(function (res) {
    alert(res);
  });
} //end enviarInfoUsuarioAlServidor

function mostrarFormularioLogin() {
  $("#contenedor").html(html_formulario_identificacion_usuario);

  if (typeof Cookies.get("email") != "undefined") {
    $("#email").val(Cookies.get("email"));
  }
  if (typeof Cookies.get("pass") != "undefined") {
    $("#pass").val(Cookies.get("pass"));
  }

  /**
   * Para evitar el comportamiento por defecto de un form
   * al hacer click en el boton de submit:
   */
  $("#form_login").submit(
    function (e) {
      /**
       * La siguiente linea es necesaria para que la pagina no se
       * refresque con el envio de formulario
       */
      e.preventDefault();
      /**
       * vamos a mandar por post el email y pass introducido a un servicioWEB
       * de indentificacion de usuario
       */
      $.post("identificar-usuario", {
        email: $("#email").val(),
        pass: $("#pass").val(),
      }).done(function (res) {
        if (res.operacion == "ok") {
          /**
           * Si el login fue correcto y se activo el checkbox
           * guardo el email y pass del usuario en una cookie
           */
          if ($("#recordar_datos").prop("checked")) {
            Cookies.set("email", $("#email").val(), { expires: 100 });
            Cookies.set("pass", $("#pass").val(), { expires: 100 });
          } else {
            /**
             * La idea basica es que si el usuario se identifica
             * sin activar el recordar datos, que se borren
             * las coockies previas con su email y pass
             */
            if (
              typeof Cookies.get("email") != "undefined" ||
              typeof Cookies.get("pass") != "undefined"
            ) {
              H;
              Cookies.remove("email");
              Cookies.remove("pass");
            }
          }
          nombre_login = res.usuario;
          alert(
            "bienvenido " + nombre_login + " ya puedes comprar instrumentos"
          );
          obtenerProductos();
          $("#menu-cerrar-sesion").css("visibility", "visible");
          $("#menu-identificarme").hide();
          $("#menu-registrarme").hide();
        } else {
          alert("email o pass incorrecto");
        }
      });
    } //end funcion mostrarFormularioLogin
  ); //end submit mostrarFormularioLogin
} //end mostrarFormularioLogin

function mostrarFormularioRegistroUsuario() {
  $("#contenedor").html(html_formulario_registro_usuario);
  $("#boton_registro_usuario").click(enviarInfoUsuarioAlServidor);
} //end mostrarFormularioRegistroUsuario

function cerrarSesionUsuario() {
  $.get("cerrar-sesion-usuario").done(function (res) {
    if (res == "ok") {
      alert("hasta pronto " + nombre_login);
      nombre_login = "";
      $("#menu-cerrar-sesion").css("visibility", "hidden");
      $("#menu-identificarme").show();
      $("#menu-registrarme").show();
      obtenerProductos();
    }
  });
} //end cerrarSesionUsuario

/**
 *    FIN USUARIOS
 */

/**
 *    CARRITO
 */
function agregarProductoAlCarrito() {
  if (nombre_login == "") {
    alert("tienes que identificarte para poder comprar productos");
    return;
  }
  let idProducto = $(this).attr("id-producto");
  $.post("agregar-producto-carrito", {
    id: idProducto,
    cantidad: 1,
  }).done(function (res) {
    if (res == "ok") {
      alert("producto agregado al carrito correctamente");
    }
  });
}
function obtenerProductosCarrito() {
  if (nombre_login == "") {
    alert("Tienes que identificarte para acceder a tu carrito");
    mostrarFormularioLogin();
    return;
  }
  $.getJSON("obtener-productos-carrito").done(function (res) {
    console.log("producto del carrito:");
    console.log(res);
    if (res.length < 1) {
      let res_html = Mustache.render(html_carrito_vacio, res);
      $("#contenedor").html(res_html);
    } else {
      // alert("respuesta recibida: " + JSON.stringify(res));
      let res_html = Mustache.render(html_carrito, res);
      $("#contenedor").html(res_html);
      $(".enlace_ver_detalles_instrumento").click(mostrarDetallesProducto);
      $(".restar_cantidad_producto").click(restarCantidadProducto);
      $(".aumentar_cantidad_producto").click(aumentarCantidadProducto);
      $(".button_delete").click(borrarProductoCarrito);
      let total_productos = 0;
      let precio_total = 0;
      for (i in res) {
        total_productos += res[i].CANTIDAD;
        precio_total += res[i].PRECIO * res[i].CANTIDAD;
      }
      $("#total_productos").html(total_productos);
      $("#total_precio").html(precio_total);
      $("#realizar-pedido").click(checkout_paso_0);
    }
  });
} //End obtenerProductosCarrito

function restarCantidadProducto() {
  let idProducto = $(this).attr("id-producto");
  $.post("restar-producto-carrito", {
    id: idProducto,
    cantidad: 1,
  }).done(function (res) {
    if (res == "ok") {
      obtenerProductosCarrito();
    }
  });
} //End restarCantidadProducto

function aumentarCantidadProducto() {
  let idProducto = $(this).attr("id-producto");
  $.post("aumentar-producto-carrito", {
    id: idProducto,
    cantidad: 1,
  }).done(function (res) {
    if (res == "ok") {
      obtenerProductosCarrito();
    }
  });
} //End restarCantidadProducto

function borrarProductoCarrito() {
  let idProducto = $(this).attr("id-producto");
  $.post("borrar-producto-carrito", {
    id: idProducto,
  }).done(function (res) {
    if (res == "ok") {
      obtenerProductosCarrito();
    }
  });
}
/**
 *    FIN CARRITO
 */
