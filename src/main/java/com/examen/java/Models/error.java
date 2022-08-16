package com.examen.java.Models;


import java.sql.Timestamp;

public class error {

	private Timestamp  timestamp;
	

	private int codigo;
	

	private String detail;
	

	
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public int getCodigo() {
		return codigo;
	}
	public String getDetail() {
		return detail;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	

}
