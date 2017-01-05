function fucionValidarUsuario() {
	if (document.getElementById('contraUsuario').value == "")
		alert("Ingrese una contrasena");
	else {
		if (document.getElementById('nombreDeUsuario').value == 'admin') {
			window.location.href = "administrador";
		} else {
			if (navigator.geolocation) {
			    navigator.geolocation.getCurrentPosition(function(position) {
			      var pos = {
			        lat: position.coords.latitude,
			        lng: position.coords.longitude
			      };
					window.location.href = "usuario?nombreFiltro="
						+ document.getElementById('nombreDeUsuario').value + "&localizacion="+pos;

			      
			    }, function() {
			      handleLocationError(true, infoWindow, map.getCenter());
			    });
			  } else {
			    // Browser doesn't support Geolocation
			    handleLocationError(false, infoWindow, map.getCenter());
			  }
			}

			function handleLocationError(browserHasGeolocation, infoWindow, pos) {
			}
			
		}
	

};
function initMap() {
	var map = new google.maps.Map(document.getElementById('map'), {
		center : {
			lat : -34.603722,
			lng : -58.381592
		},
		scrollwheel : true,
		zoom : 10
	})
};

function goBack() {
	window.history.back();
};
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}