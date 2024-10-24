package com.example.programacionII.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.programacionII.modelo.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

	 
@Repository 
@Transactional
public class UsuarioDaoImp implements UsuarioDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")	
	@Override
	public List<Usuario> getUsuario() {
		
		String query="from Usuario";
				
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public void eliminarUsuario(Long id) {
		
		Usuario usuario = entityManager.find(Usuario.class, id);
		 
		entityManager.remove(usuario);
	}

	@Override
	public void registrar(Usuario usuario) {
		
		entityManager.merge(usuario);
		
	}

	@Override
	public Usuario obtenerUsuarioPorCredencial(Usuario usuario) {
		
		String query="FROM Usuario WHERE email= :email";
		
		List<Usuario> lista=entityManager.
				createQuery(query).
				setParameter("email", usuario.getEmail()).
				getResultList();
		
		if(lista.isEmpty()) {	
			return null;
		}	
		
		String passHash = lista.get(0).getPassword();
		
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		
			if (argon2.verify(passHash,usuario.getPassword())) {
				
				return lista.get(0);
			}
							
			return null;
	}
}
