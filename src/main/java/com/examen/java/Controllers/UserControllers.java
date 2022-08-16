package com.examen.java.Controllers;

import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.examen.java.Dao.UsuarioDao;
import com.examen.java.Models.Token;
import com.examen.java.Models.User;
import com.examen.java.Models.error;
import com.examen.java.Utils.JWTUtils;
import com.github.cliftonlabs.json_simple.JsonObject;

@RestController
public class UserControllers {
	
	Long datetime = System.currentTimeMillis();
    Timestamp timestamp = new Timestamp(datetime);
    
   public error er = new error();
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private JWTUtils jWTUtils;
	
	
	@RequestMapping(value = "api/login", method = RequestMethod.POST)
	public Object loginUser(@RequestBody Token token){
		
		
		try {
			User us = new User();
			
			
			us = usuarioDao.BuscarUser(token);
			
			if(us != null) {
				String token_nuevo = jWTUtils.create(String.valueOf(us.getId()), us.getEmail());
				
				
				if(usuarioDao.CreateToken(token_nuevo, us)) {
					us.setToken(token_nuevo);
					String pass = DesencriptoPass(us);
					us.setPassword(pass);
					return us;
				}
				else {
					
					er.setTimestamp(timestamp);
		         	er.setCodigo(500);
		         	er.setDetail("No se pudo generar nuevo Token");
		         	
		         	return er;
				}
			}
			else {
				er.setTimestamp(timestamp);
	         	er.setCodigo(500);
	         	er.setDetail("No se Encontro Un usuario con ese token en la DB ");
	         	
	         	return er;
			}
		}
		catch(Exception e) {
			
			er.setTimestamp(timestamp);
         	er.setCodigo(500);
         	er.setDetail("Error interno en el servidor " + e);
         	
         	return er;
		}

	}
	
	

	
	@RequestMapping(value = "api/sign-up", method = RequestMethod.POST)
	public Object registro(@RequestBody User usuario){
		
		try {

			
	        // Patrón para validar el email
	        Pattern pattern = Pattern
	                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	        
	    	

	 
	 
	        Matcher mather = pattern.matcher(usuario.getEmail());
	        
	        //Metodo para Validar contraseña
	        Boolean verificoPass = validoPass(usuario);
	        
	           
	        if(!(usuario.getPassword().isEmpty() || usuario.getPassword().length() < 0) && verificoPass == true) {
	        	 if (mather.find() == true ) {
	        		       		 
	        		usuario = usuarioDao.listacreate(usuario);
	        		 
	         		if(usuario.getId() == 0) {
	         			
	         			if(EncriptoPass(usuario)) {
	         							
		         			if(usuarioDao.registro(usuario)) {
		         				usuario = usuarioDao.listacreate(usuario);	
 			         			
			             		String token = InsertoToken(usuario);   
			             		
			             		
			             		JsonObject json = new JsonObject();
			             		
			             	    json.put("id", usuario.getId());
			             	    json.put("created", usuario.getFecha_creacion());
			             	    json.put("lastLogin", usuario.getUltimo_ingreso());
			             	    json.put("token", token);
			               	    json.put("isActive", usuario.getActivo());
			             		
			             		return json;
		         			}
		         			else {
		         				er.setTimestamp(timestamp);
			                 	er.setCodigo(500);
			                 	er.setDetail("Error al Insertar Usuario");
			                 	
			                 	return er;
		         			}
		             		
	         			}
		         		else {

		                 	
		                 	er.setTimestamp(timestamp);
		                 	er.setCodigo(500);
		                 	er.setDetail("Error al Encriptar pass");
		                 	
		                 	return er;
		         		}
	         			
	         		}
	         		else {

	                 	
	                 	er.setTimestamp(timestamp);
	                 	er.setCodigo(500);
	                 	er.setDetail("El Usuario Ya existe en la DB.");
	                 	
	                 	return er;
	         		}
	         				
	             } else {
             	
	             	er.setTimestamp(timestamp);
	             	er.setCodigo(500);
	             	er.setDetail("El email ingresado es inválido.");
	             	
	             	return er;
	             	
	             }
	        }
	   	 else {
	   		 
	       	er.setTimestamp(timestamp);
	       	er.setCodigo(500);
	       	er.setDetail("Contraseña Invalida");
	       	
	       	return er;
	       	
	       }
		}
		catch(Exception e) {
			      	
	       	er.setTimestamp(timestamp);
	       	er.setCodigo(500);
	       	er.setDetail("Error Interno: " + e);
	       	
	       	return er;
		}


	}
	
	public String InsertoToken(User us) {
				
		String Token = jWTUtils.create(String.valueOf(us.getId()), us.getEmail());
		
		if(usuarioDao.CreateToken(Token , us)){
			return Token;
		}
		else {
			return null;
		}
		
		
	}
	
	
	public Boolean validoPass(User usuario) {
		
		
		 final int MAX=12;
		 final int MIN = 8;
		// Specifying the number of uppercase letters in password
       final int MAX_Mayuscula=1;
       // Specifying the number of digits in a password
       final int NUM=2;
       // Count number of uppercase letters in a password
       int contaNUM=0;
       // Count digits in a password
       int mayuscula=0;
       
       
       String password = usuario.getPassword();
       
       for (int i=0; i < password.length(); i++ ) {
           char c = password.charAt(i);
           if(Character.isUpperCase(c)) 
           	mayuscula++;
           else if(Character.isDigit(c)) 
           	contaNUM++;     

         }
              
       if (password.length() <= MAX && password.length() >= MIN && mayuscula == MAX_Mayuscula  && contaNUM == NUM) { 
      		return true;
       }
       else {
    	   return false;
       }
		
		

	}

	
	public boolean EncriptoPass(User us) {
		
		char array[] = us.getPassword().toCharArray();
		
		for(int a = 0; a < array.length; a++) {
			
			array[a] = (char)(array[a] + (char)5);
			
		}
		
		String pass = String.valueOf(array);
		
		us.setPassword(pass);
		
		return true;	
	}
	
	
	public String DesencriptoPass(User us) {
		
		char array[] = us.getPassword().toCharArray();
		
		for(int a = 0; a < array.length; a++) {
			
			array[a] = (char)(array[a] - (char)5);
			
		}
		
		String pass = String.valueOf(array);
		
		return pass;
		
	}

}