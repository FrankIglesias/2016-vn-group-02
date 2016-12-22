function fucionValidarUsuario() {
	if (document.getElementById('contraUsuario').value == "")
		alert("Ingrese una contrasena");
	else {
		if (document.getElementById('nombreDeUsuario').value == 'admin') {
			window.location.href = "administrador.hbs";
		} else {
			window.location.href = "usuario?nombreFiltro="
					+ document.getElementById('nombreDeUsuario').value;
		}
	}

}

function goBack() {
	window.history.back();
}
