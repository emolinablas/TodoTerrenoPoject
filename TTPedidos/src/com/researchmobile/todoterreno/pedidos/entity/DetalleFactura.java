package com.researchmobile.todoterreno.pedidos.entity;

import java.io.Serializable;

public class DetalleFactura implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer Id;
	private String MovDocumento;
	private String ArtCodigo;
	private Integer ArtUnidadesFardo;
	private Integer MovUnidades;
	private Integer MovUnidadesEntregadas;
	private float MovPrecio;
	private String MovFechaEntregar;
	private Integer CajaCodigo;
	private float Mocostoultimo;
	private Integer MovUnidevueltos;
	private String MovNotas;
	private String Vendedor;
	private String Ruta;
	private String IdRepartidor;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getMovDocumento() {
		return MovDocumento;
	}
	public void setMovDocumento(String movDocumento) {
		MovDocumento = movDocumento;
	}
	public String getArtCodigo() {
		return ArtCodigo;
	}
	public void setArtCodigo(String artCodigo) {
		ArtCodigo = artCodigo;
	}
	public Integer getArtUnidadesFardo() {
		return ArtUnidadesFardo;
	}
	public void setArtUnidadesFardo(Integer artUnidadesFardo) {
		ArtUnidadesFardo = artUnidadesFardo;
	}
	public Integer getMovUnidades() {
		return MovUnidades;
	}
	public void setMovUnidades(Integer movUnidades) {
		MovUnidades = movUnidades;
	}
	public Integer getMovUnidadesEntregadas() {
		return MovUnidadesEntregadas;
	}
	public void setMovUnidadesEntregadas(Integer movUnidadesEntregadas) {
		MovUnidadesEntregadas = movUnidadesEntregadas;
	}
	public float getMovPrecio() {
		return MovPrecio;
	}
	public void setMovPrecio(float movPrecio) {
		MovPrecio = movPrecio;
	}
	public String getMovFechaEntregar() {
		return MovFechaEntregar;
	}
	public void setMovFechaEntregar(String movFechaEntregar) {
		MovFechaEntregar = movFechaEntregar;
	}
	public Integer getCajaCodigo() {
		return CajaCodigo;
	}
	public void setCajaCodigo(Integer cajaCodigo) {
		CajaCodigo = cajaCodigo;
	}
	public float getMocostoultimo() {
		return Mocostoultimo;
	}
	public void setMocostoultimo(float mocostoultimo) {
		Mocostoultimo = mocostoultimo;
	}
	public Integer getMovUnidevueltos() {
		return MovUnidevueltos;
	}
	public void setMovUnidevueltos(Integer movUnidevueltos) {
		MovUnidevueltos = movUnidevueltos;
	}
	public String getMovNotas() {
		return MovNotas;
	}
	public void setMovNotas(String movNotas) {
		MovNotas = movNotas;
	}
	public String getVendedor() {
		return Vendedor;
	}
	public void setVendedor(String vendedor) {
		Vendedor = vendedor;
	}
	public String getRuta() {
		return Ruta;
	}
	public void setRuta(String ruta) {
		Ruta = ruta;
	}
	public String getIdRepartidor() {
		return IdRepartidor;
	}
	public void setIdRepartidor(String idRepartidor) {
		IdRepartidor = idRepartidor;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
