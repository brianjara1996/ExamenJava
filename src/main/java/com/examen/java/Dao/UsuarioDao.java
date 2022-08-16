package com.examen.java.Dao;

import com.examen.java.Models.Token;
import com.examen.java.Models.User;

public interface UsuarioDao {
	
	User listacreate(User us);

	boolean registro(User usuario);

	boolean login(User usuario);

	boolean CreateToken(String token, User us);

	User BuscarUser(Token token);
	
}
