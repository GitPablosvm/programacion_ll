package com.example.programacionII.dao;

import java.util.List;

import com.example.programacionII.modelo.Usuario;

import jakarta.transaction.Transactional;


@Transactional
public interface UsuarioDao {
	
	List<Usuario> getUsuario();
	
	void eliminarUsuario(Long id);
	
	void registrar(Usuario usuario);

	Usuario obtenerUsuarioPorCredencial(Usuario usuario);

}
