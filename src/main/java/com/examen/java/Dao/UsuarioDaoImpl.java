package com.examen.java.Dao;


import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.examen.java.Models.Token;
import com.examen.java.Models.User;

@Repository
@Transactional
public class UsuarioDaoImpl implements UsuarioDao {
	
	 @PersistenceContext
	 EntityManager entityManager;

	@Override
	public boolean registro(User usuario) {
		
		Date todayDate = new Date();
		
		usuario.setActivo(true);
		usuario.setFecha_creacion(todayDate);
		usuario.setUltimo_ingreso(todayDate);		
		entityManager.merge(usuario);
		
		return true;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public User listacreate(User us) {
		
     try {
 		String query = "From User where email = ";
		
 		query = query + "'" + us.getEmail() + "'";
 		

		List<User> a  = entityManager.createQuery(query).getResultList();
 		
 		us = a.get(0);
 		
 		 return us;
     }
     catch(Exception e) {
    	 return us;
     }
     
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean login(User usuario) {
		
		String query = "From User where email = :email and password = :password ";
		
		List<User> lista_us = entityManager.createQuery(query)
				.setParameter( "email", usuario.getEmail())
				.setParameter( "password", usuario.getPassword())
				.getResultList();
		
		if(lista_us.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean CreateToken(String token, User us) {
		
		String query = "Update User set token = :token where id = :id";
		
	          int a =  entityManager.createQuery(query)
				.setParameter("token", token)
				.setParameter("id", us.getId())
				.executeUpdate();
	            entityManager.flush();
	            
           if(a > 0) {
        	   return true;
           }
           else {
        	   return false;
           }
	}

	@Override
	public User BuscarUser(Token token) {
	
       String query = "From User where token = :token";
		 		

		@SuppressWarnings("unchecked")
		List<User> a  = entityManager.createQuery(query)
				.setParameter("token", token.getToken())
		        .getResultList();   
        
        if(a.size() > 0) {
        	User us = new User();
        	us = a.get(0);
        	return us;       	
        }
        else {
        	return null;
        }
 		
 		
	}

}
