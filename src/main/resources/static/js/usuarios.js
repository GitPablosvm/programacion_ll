// Call the dataTables jQuery plugin
$(document).ready(function() {
	
	cargarUsuarios()
	
	  $('#usuario').DataTable();
	  actualizaremaildelUsuario();
});

function actualizaremaildelUsuario(){
	
	document.getElementById("txt-email-usuario").outerHTML=localStorage.email;
	
}

async function cargarUsuarios(){
	
	const rawResponse = await fetch('api/usuarios', {
		method: 'GET',
		headers: getHeders()		
	});
	
	const content = await rawResponse.json();
	
	//console.log(content);
			
	let listadoHTML = ' ';
	
	for(let usuario of content){
		
		let botonEliminar = '<a href="#" onclick="eliminarUsuario('+usuario.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i> </a>';
		
		/*
		La linea a continuacion me da error al momento de armar el cuadro por lo que busque y encontre otra forma de realizar el llamado el cual si me fuinciono
		
		let usuarioshtml= '<tr> 
			<td>' + usuario.id + '<td>
			<td>' + usuario.nombre + ' ' + usuario.apelli do + '<td>
			<td>' + usuario.email + '<td>
			<td>' + usuario.telefono + '<td>
			<td>' + botonEliminar + '<td>
		<tr>';
		
		*/
		
		let usuarioshtml = `<tr>
            <td>${usuario.id}</td>
            <td>${usuario.nombre} ${usuario.apellido}</td>
            <td>${usuario.email}</td>
            <td>${usuario.telefono}</td>
            <td>${botonEliminar}</td>
        </tr>`;
		
		listadoHTML += usuarioshtml;
	}
	
	
	document.querySelector('#usuario tbody').innerHTML = listadoHTML;
	//Cambie el outerHTML por innerHTML ya que este ultimo es mejor ya que inner cambia y outher sobrescribe
	
}

function getHeders(){
	
	return 	{
			'Accept': 'application/json',
			'Content-Type': 'application/json',
			'Authorization': localStorage.token
		};
	}

async function eliminarUsuario(id) {
			
		if(!confirm('Desea eliminar usuario')){
			return;
		}
		
		const rawResponse = await fetch('api/usuarios/' + id, {
		method: 'DELETE',
		headers: getHeders()	
			
	});
		location.reload();
}	