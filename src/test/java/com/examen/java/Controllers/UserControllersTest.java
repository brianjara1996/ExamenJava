package com.examen.java.Controllers;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.examen.java.Dao.UsuarioDaoImpl;
import com.examen.java.Models.Phone;
import com.examen.java.Models.Token;
import com.examen.java.Models.User;


public class UserControllersTest {
	
	Long datetime = System.currentTimeMillis();
    Timestamp timestamp = new Timestamp(datetime);
    
	
	UsuarioDaoImpl usuarioDaoImpl = Mockito.mock(UsuarioDaoImpl.class);
	
	
	@Autowired
	UserControllers usercontrollers = new UserControllers();

	
	@BeforeEach
	void setUp() {
		
		System.out.println("antes de la prueba");
		
		User us = new User();
		
		us.setId(1);
		us.setActivo(true);
		us.setEmail("bb@kaka.com");
		us.setName("brian");
		us.setPassword("sadsadsa4");
		us.setFecha_creacion(timestamp);
		us.setUltimo_ingreso(timestamp);
		us.setToken("token1");
		us.setPhone(new Phone());
		
		Token tk = new Token();
		
		tk.setToken("token1");
		
		Mockito.when(usuarioDaoImpl.BuscarUser(tk)).thenReturn(us);
		Mockito.when(usuarioDaoImpl.CreateToken("token2", us)).thenReturn(true);
		Mockito.when(usuarioDaoImpl.listacreate(us)).thenReturn(us);
		Mockito.when(usuarioDaoImpl.registro(us)).thenReturn(true);
		
	}
	
	
	@Test
	void BuscarToken() {
		
		Object aa = new Object();
		
		Token tkk = new Token();
		
		tkk.setToken("");
		
        aa = usercontrollers.loginUser(tkk);

		System.out.println(aa);
		
		
		
		User uss = new User();
		
		uss.setId(1);
		uss.setActivo(true);
		uss.setEmail("bb@kaka.com");
		uss.setName("brian");
		uss.setPassword("Chupala12");
		uss.setFecha_creacion(timestamp);
		uss.setUltimo_ingreso(timestamp);
		uss.setToken("token1");
		uss.setPhone(new Phone());
		
		aa =  usercontrollers.registro(uss);
		
		System.out.println(aa);
		
	}

}