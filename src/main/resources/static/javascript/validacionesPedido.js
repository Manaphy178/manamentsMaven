// Paso 1
let regExpNombreCompleto = /^[a-z áéíóúñ\s]{2,50}$/i;
let regExpDireccion = /^[a-z0-9 áéíóúñ\s,.-]{5,100}$/i;
let regExpProvincia = /^[a-z áéíóúñ\s]{2,50}$/i;
let regExpPais = /^[a-z áéíóúñ\s]{2,50}$/i;
let regExpTelefono = /^\d{7,15}$/i;
let regExpPoblacion = /^[a-z áéíóúñ\s]{2,50}$/i;

function validarNombreCompleto(nombre) {
  if (regExpNombreCompleto.test(nombre)) {
    return true;
  } else {
    alert(
      "El nombre introducido es incorrecto. Solo letras de 2 a 50 caracteres."
    );
  }
}

function validarDireccion(direccion) {
  if (regExpDireccion.test(direccion)) {
    return true;
  } else {
    alert(
      "La dirección introducida es incorrecta. Solo letras, números, espacios, comas y puntos, de 5 a 100 caracteres."
    );
  }
}

function validarProvincia(provincia) {
  if (regExpProvincia.test(provincia)) {
    return true;
  } else {
    alert(
      "La provincia introducida es incorrecta. Solo letras de 2 a 50 caracteres."
    );
  }
}

function validarPais(pais) {
  if (regExpPais.test(pais)) {
    return true;
  } else {
    alert(
      "El país introducido es incorrecto. Solo letras de 2 a 50 caracteres."
    );
  }
}

function validarTelefono(telefono) {
  if (regExpTelefono.test(telefono)) {
    return true;
  } else {
    alert(
      "El teléfono introducido es incorrecto. Solo números de 7 a 15 dígitos."
    );
  }
}

function validarPoblacion(poblacion) {
  if (regExpPoblacion.test(poblacion)) {
    return true;
  } else {
    alert(
      "La población introducida es incorrecta. Solo letras de 2 a 50 caracteres."
    );
  }
}
// Fin paso 1

// Paso 2

let regExpNumeroTarjeta = /^[0-9]{13,19}$/;
let regExpTitularTarjeta = /^[a-z áéíóúñ\s]{2,50}$/i;
let regExpCodigoSeguridad = /^[0-9]{3}$/;
let regExpFechaCaducidad = /^(0[1-9]|1[0-2])\/\d{2}$/;

function validarNumeroTarjeta(numero_tarjeta) {
  if (regExpNumeroTarjeta.test(numero_tarjeta)) {
    return true;
  } else {
    alert(
      "El número de tarjeta es incorrecto. Debe contener entre 13 y 19 dígitos."
    );
  }
}

function validarTitularTarjeta(titular_tarjeta) {
  if (regExpTitularTarjeta.test(titular_tarjeta)) {
    return true;
  } else {
    alert(
      "El titular de la tarjeta es incorrecto. Solo letras y espacios, de 2 a 50 caracteres."
    );
  }
}

function validarCodigoSeguridad(codigo_seguridad) {
  if (regExpCodigoSeguridad.test(codigo_seguridad)) {
    return true;
  } else {
    alert(
      "El código de seguridad es incorrecto. Debe contener solo 3 dígitos."
    );
  }
}

function validarFechaCaducidad(fecha_caducidad) {
  if (regExpFechaCaducidad.test(fecha_caducidad)) {
    return true;
  } else {
    alert(
      "La fecha de caducidad es incorrecta. Debe estar en formato MM/AA y ser válida."
    );
  }
}
