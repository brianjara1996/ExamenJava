package com.examen.java.Models;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "usuarios")
public class User {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
	private long id;
	private String name;
	
	private String email;
	
	private String password;
	private Date fecha_creacion;
	private Date ultimo_ingreso;
	private String token;
	private Boolean activo;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_phone", referencedColumnName = "id")
	private Phone phone;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Phone getPhone() {
		return phone;
	}
	public void setPhone(Phone phone) {
		this.phone = phone;
	}
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public Date getUltimo_ingreso() {
		return ultimo_ingreso;
	}
	public void setUltimo_ingreso(Date ultimo_ingreso) {
		this.ultimo_ingreso = ultimo_ingreso;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	

}
