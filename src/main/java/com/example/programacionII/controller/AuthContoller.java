 package com.example.programacionII.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.programacionII.JWTUtils.JWTUtil;
import com.example.programacionII.dao.UsuarioDao;
import com.example.programacionII.modelo.Usuario;

@RestController
public class AuthContoller {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@RequestMapping(value="api/login", method=RequestMethod.POST)
	public String login(@RequestBody Usuario usuario){

		Usuario usuarioLogueado = usuarioDao.obtenerUsuarioPorCredencial(usuario);
		
		
		if(usuarioLogueado !=null) {
			
			String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
			
			return tokenJwt;
		}	
		return "fail";
	}
}
