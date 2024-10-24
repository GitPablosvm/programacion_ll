package com.example.programacionII.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.programacionII.dao.UsuarioDao;
import com.example.programacionII.modelo.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@RestController
public class userController {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	/*metodo para mostrar un usuario*/
	
	@RequestMapping(value="api/usuarios")
	public List<Usuario> getUsuarios(){
			
			List<Usuario> user=usuarioDao.getUsuario();
			
			return user;
		}
	
	/*Metodo para elimienar un usuario*/
	
	@RequestMapping(value="api/usuarios/{id}", method=RequestMethod.DELETE)
	public void eliminarUsuario(@PathVariable Long id){
			
		usuarioDao.eliminarUsuario(id);	  
		}
	
	/* Registra los usuarios en la tabla*/
	
	@RequestMapping(value="api/usuarios", method=RequestMethod.POST)
	public void registrarUsuario(@RequestBody Usuario usuario){
		
		Argon2 argon2;
		argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		String passHasheado = argon2.hash(1, 1024, 10, usuario.getPassword());
		usuario.setPassword(passHasheado);
		
		usuarioDao.registrar(usuario);	  
		}
}	
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	@RequestMapping (value="mensaje")
	public String mensaje() {
		
		return "holiisssssss";
	}
	
	@RequestMapping (value="listar")
	public List<String> listarPersona() {
		
		return List.of("Pablo", "Mary", "Tizi");
	}
	
	@RequestMapping (value="usuarios")
	public Usuario listarUsuarios() {
		
		Usuario u1 = new Usuario();
		
		u1.setNombre("Pablo");
		u1.setApellido("Vicencio");
		u1.setEmail("pablosvm17@hotmail.com");
		u1.setPassword("123123");
				
		Usuario u2 = new Usuario();
		
		u2.setNombre("Tiziano");
		u2.setApellido("Pocho");
		u2.setEmail("elpochdelagente@gmail.com");
		u2.setPassword("2354789");
		
		return u2;
				 
	}
	
	@RequestMapping (value="usuarios/{id}")
	public Usuario getlistarUsuarios(@PathVariable Long id) {
		
		Usuario u1 = new Usuario();
		
		u1.setId(id);
		u1.setNombre("Pablo");
		u1.setApellido("Vicencio");
		u1.setEmail("pablosvm17@hotmail.com");
		u1.setPassword("123123");
	
		return u1;
	}
	
	@RequestMapping (value="lista")
	public List<Usuario> listarVariosUsuarios() {
		
		List<Usuario> lu1 = new ArrayList<>();
		
		Usuario u1 = new Usuario();
		
		u1.setId(1L);
		u1.setNombre("Pablo");
		u1.setApellido("Vicencio");
		u1.setEmail("pablosvm17@hotmail.com");
		u1.setPassword("123123");
		
		
		Usuario u2 = new Usuario();
		
		u2.setId(2L);
		u2.setNombre("Tiziano");
		u2.setApellido("Pocho");
		u2.setEmail("elpochdelagente@gmail.com");
		u2.setPassword("2354789");
		
		Usuario u3 = new Usuario();
		
		u3.setId(3L);
		u3.setNombre("Mili");
		u3.setApellido("lichulita");
		u3.setEmail("lachulita@gmail.com");
		u3.setPassword("9876564");
		
		lu1.add(u1);
		lu1.add(u2);
		lu1.add(u3);
		
		return lu1;
	} 
	*/


	
	
