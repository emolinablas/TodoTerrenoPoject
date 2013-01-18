package com.researchmobile.todoterreno.pedidos.entity;

import java.io.Serializable;


public class NCliente implements Serializable {
	private static final long serialVersionUID = 1L;
		
	private String idNuevoCliente;
	private String Nit;
	private String NombreNegocio;
	private String CategoriaCliente;
	private String NombreContacto;
	private String Telefono;
	private String Direccion;
	private String Ruta;
	private String DiaVisita;
	
	public String getidNuevoCliente(){
		return idNuevoCliente;
	}
	
	public void setidNuevoCliente(String idNuevoCliente){
		this.idNuevoCliente = idNuevoCliente;
	}
	
	public String getNit(){
		return Nit;
	}
	
	public void setNit(String Nit){
		this.Nit = Nit;
	}
	
	public String getNombreNegocio(){
		return NombreNegocio;
	}
	
	public void setNombreNegocio(String NombreNegocio){
		this.NombreNegocio = NombreNegocio;
	}
	
	public String getCategoriaCliente(){
		return CategoriaCliente;
	}
	
	public void setCategoriaCliente(String CategoriaCliente){
		this.CategoriaCliente = CategoriaCliente;
	}
	
	public String getNombreContacto(){
		return NombreContacto;
	}
	
	public void setNombreContacto(String NombreContacto){
		this.NombreContacto = NombreContacto;
	}
	 
	public String getTelefono(){
		 return Telefono;
	 }
	
	public void setTelefono(String Telefono){
		this.Telefono = Telefono;
	}
	 
	public String getDireccion(){
		return Direccion;
	}
	
	public void setDireccion(String Direccion){
		this.Direccion = Direccion;
	}
	
	public String getRuta(){
		return Ruta;
	}
	
	public void setRuta(String Ruta){
		this.Ruta = Ruta;
	}
	
	public String getDiaVisita(){
		return DiaVisita;
	}
	
	public void setDiaVisita(String DiaVisita){
		this.DiaVisita = DiaVisita;
	}
}
