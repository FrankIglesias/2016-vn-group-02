function fucionValidarUsuario(){
	if(document.getElementById('nombreDeUsuario').value == 'admin'){
			window.location.href = "administrador.html";
	}else {
		window.location.href = "usuario.html";
	}
}


	

	