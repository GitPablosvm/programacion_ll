// Call the dataTables jQuery plugin
$(document).ready(function() {
});

async function iniciarSesion(){
	
	let datos= {};
	datos.email=document.getElementById('txtEmail').value;
	datos.password=document.getElementById('txtPassword').value;
	
		if (!datos.email || !datos.password) {
	        alert('Por favor, completa todos los campos');
	        return;
        }
		
	const request = await fetch('/api/login', {
		method: 'POST',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(datos)
	});

	const respuesta = await request.text();
	
		if(respuesta !='fail'){
			localStorage.token=respuesta;
			localStorage.email=datos.email;
			/*const nombreUsuario = respuesta.nombre + ' ' + respuesta.apellido;
			localStorage.setItem('nombreUsuario', respuesta.nombre + ' ' + respuesta.apellido);
			console.log("Usuario guardado en localStorage: ", nombreCompleto)*/
			window.location.href='Usuario.html';
		}else
			alert('El usuario o la contraseña no son correcto');
		}
		
		
		
/*		document.addEventListener('DOMContentLoaded', function () {
    const nombreUsuario = localStorage.getItem('nombreUsuario');
    if (nombreUsuario) {
        document.getElementById('nombreUsuario').textContent = nombreUsuario;
    }
});*/

	//const respuesta = await request.json(); //MANEJA SI HAY UNA SESION DE USUARIO ABIERTA
	
	//console.log('ingresado');