// validaciones
/**
 * LA "i" al final indica que sea case I-nsensitive, ademas el espacio
 * entre "z"" y "á"" integra el espacio como caracter valido
 */
let regexp_nombre = /^[a-z áéíóúñ]{2,10}$/i;
let regexp_apellido = /^[a-z áéíóúñ]{2,30}$/i;
let regexp_username = /^[a-z0-9áéíóúñ]{3,15}$/i;
let regexp_email = /^([a-z0-9_\.-]+)@([0-9a-z\.-]+)\.([a-z\.]+)$/i;
let regexp_pass = /^[a-z0-9áéíóúñ]{3,10}$/i;
let regexp_codPostal = /^[0-9]{5}$/;

function validarNombre(nombre) {
	if (regexp_nombre.test(nombre)) {
		return true;
	} else {
		alert(
			"El nombre introducido es incorrecto \n Solo letras de 2 a 10 caracteres"
		);
	}
}

function validarApellidos(apellidos) {
	if (regexp_apellido.test(apellidos)) {
		return true;
	} else {
		alert(
			"Los apellidos introducido es incorrecto \n Solo letras de 2 a 30 caracteres"
		);
	}
}

function validarUsuario(nomUsu) {
	if (regexp_username.test(nomUsu)) {
		return true;
	} else {
		alert(
			"El nombre de usuario introducido es incorrecto \n Una longitud de 3 a 15 caracteres"
		);
	}
}

function validarEmail(email) {
	if (regexp_email.test(email)) {
		return true;
	} else {
		alert("El email introducido es incorrecto");
	}
}

function validarPass(pass) {
	if (regexp_pass.test(pass)) {
		return true;
	} else {
		alert(
			"La contraseña introducida es incorrecta \n Una contraseña de 3 a 10 caracteres"
		);
	}
}

function validarCodigoPostal(codPostal) {
	if (regexp_codPostal.test(codPostal)) {
		return true;
	} else {
		alert(
			"El codigo postal es inscorrecto\nUn codigo postal solo tiene 5 numeros"
		);
	}
}
