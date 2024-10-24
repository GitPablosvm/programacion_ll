// Call the dataTables jQuery plugin
$(document).ready(function() {

});

async function registrarUsuario(){
	
	let datos= {};
	datos.nombre=document.getElementById('txtNombre').value;
	datos.apellido=document.getElementById('txtApellido').value;
	datos.email=document.getElementById('txtEmail').value;
	datos.telefono=document.getElementById('txtTelefono').value;
	datos.password=document.getElementById('txtPassword').value;
	
	let repetirpassword=document.getElementById('txtRepeatPassword').value;
	
		if (!datos.nombre || !datos.apellido || !datos.email || !datos.telefono || 
			!datos.password) {
	        
	        alert('Por favor, completa todos los campos');
	        
	        return;
	        
        }
		
		if(repetirpassword!=datos.password){
			
			alert('la contraseña no es igual')
			
			return;
		}
	
	try{
		
	const request = await fetch('api/usuarios', {
		method: 'POST',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
		
		body: JSON.stringify(datos)
		
	});
	
		if(request.ok){
			
			alert('Usuario registrado con exito!');
				window.location.href='login.html';
			
			document.getElementById('txtNombre').value = '';
			document.getElementById('txtApellido').value = '';
			document.getElementById('txtEmail').value = '';
			document.getElementById('txtTelefono').value = '';
			document.getElementById('txtPassword').value = '';
			document.getElementById('txtRepeatPassword').value = '';
			
		}else{
			
			alert('Hubo un problema al registrar el usuario. Por favor, intentelo de nuevo');
		}
	}catch{
		console.error('Error al registrar usuario:', error);
		alert('Error en el servidor. No se pudo registrar el usuario.');
	}
	
	//const usuarios = await request.json(); MANEJA SI HAY UNA SESION DE USUARIO ABIERTA
	//console.log('ingresado');
}