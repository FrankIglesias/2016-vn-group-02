function fucionValidarUsuario(){
	if(document.getElementById('nombreDeUsuario').value == 'admin'){
			window.location.href = "administrador.hbs";
	}else {
		window.location.href = "/usuario";
	}
}


	

	