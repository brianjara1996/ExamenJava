package com.examen.java.Models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "phone")
public class Phone {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
	private long id;
	private long number;
	private int citycode;
	private String contrycode;
	
	@OneToOne(mappedBy="phone") //hace referencia a la relacion 1 a 1 y al campo de la clase usuario 
	private User user;
	
	
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	public int getCitycode() {
		return citycode;
	}
	public void setCitycode(int citycode) {
		this.citycode = citycode;
	}
	public String getContrycode() {
		return contrycode;
	}
	public void setContrycode(String contrycode) {
		this.contrycode = contrycode;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	

}
