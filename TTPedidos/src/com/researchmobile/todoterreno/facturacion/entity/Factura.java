package com.researchmobile.todoterreno.facturacion.entity;

import java.io.Serializable;

public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String movDocumento;
	private String movFecha;
	private String movHora;
	private Integer movTipo;
	private boolean movAnulado;
	private Integer movEstatus;
	private float movTotal;
	private float movEfectivo;
	private float movCheque;
	private float movCredito;
	private String cliCodigo;
	private String factura;
	private boolean movFacturado;
	private String primerDato;
	private String movNotas;
	private Integer movCerrado;
	private Integer cajaCodigo;
	private float movPagado;
	private String pagoDocumento;
	private String movFechaVence;
	private Integer cierreMes;
	private String vendedor;
	private String ruta;
	private String idRepartidor;
	private Integer facturaImpresa;
	private Integer facturaRechazo;
	private long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getMovDocumento() {
		return movDocumento;
	}
	public void setMovDocumento(String movDocumento) {
		this.movDocumento = movDocumento;
	}
	public String getMovFecha() {
		return movFecha;
	}
	public void setMovFecha(String movFecha) {
		this.movFecha = movFecha;
	}
	public String getMovHora() {
		return movHora;
	}
	public void setMovHora(String movHora) {
		this.movHora = movHora;
	}
	public Integer getMovTipo() {
		return movTipo;
	}
	public void setMovTipo(Integer movTipo) {
		this.movTipo = movTipo;
	}
	public boolean isMovAnulado() {
		return movAnulado;
	}
	public void setMovAnulado(boolean movAnulado) {
		this.movAnulado = movAnulado;
	}
	public Integer getMovEstatus() {
		return movEstatus;
	}
	public void setMovEstatus(Integer movEstatus) {
		this.movEstatus = movEstatus;
	}
	public float getMovTotal() {
		return movTotal;
	}
	public void setMovTotal(float movTotal) {
		this.movTotal = movTotal;
	}
	public float getMovEfectivo() {
		return movEfectivo;
	}
	public void setMovEfectivo(float movEfectivo) {
		this.movEfectivo = movEfectivo;
	}
	public float getMovCheque() {
		return movCheque;
	}
	public void setMovCheque(float movCheque) {
		this.movCheque = movCheque;
	}
	public float getMovCredito() {
		return movCredito;
	}
	public void setMovCredito(float movCredito) {
		this.movCredito = movCredito;
	}
	public String getCliCodigo() {
		return cliCodigo;
	}
	public void setCliCodigo(String cliCodigo) {
		this.cliCodigo = cliCodigo;
	}
	public String getFactura() {
		return factura;
	}
	public void setFactura(String factura) {
		this.factura = factura;
	}
	public boolean isMovFacturado() {
		return movFacturado;
	}
	public void setMovFacturado(boolean movFacturado) {
		this.movFacturado = movFacturado;
	}
	public String getPrimerDato() {
		return primerDato;
	}
	public void setPrimerDato(String primerDato) {
		this.primerDato = primerDato;
	}
	public String getMovNotas() {
		return movNotas;
	}
	public void setMovNotas(String movNotas) {
		this.movNotas = movNotas;
	}
	public Integer getMovCerrado() {
		return movCerrado;
	}
	public void setMovCerrado(Integer movCerrado) {
		this.movCerrado = movCerrado;
	}
	public Integer getCajaCodigo() {
		return cajaCodigo;
	}
	public void setCajaCodigo(Integer cajaCodigo) {
		this.cajaCodigo = cajaCodigo;
	}
	public float getMovPagado() {
		return movPagado;
	}
	public void setMovPagado(float movPagado) {
		this.movPagado = movPagado;
	}
	public String getPagoDocumento() {
		return pagoDocumento;
	}
	public void setPagoDocumento(String pagoDocumento) {
		this.pagoDocumento = pagoDocumento;
	}
	public String getMovFechaVence() {
		return movFechaVence;
	}
	public void setMovFechaVence(String movFechaVence) {
		this.movFechaVence = movFechaVence;
	}
	public Integer getCierreMes() {
		return cierreMes;
	}
	public void setCierreMes(Integer cierreMes) {
		this.cierreMes = cierreMes;
	}
	public String getVendedor() {
		return vendedor;
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public String getIdRepartidor() {
		return idRepartidor;
	}
	public void setIdRepartidor(String idRepartidor) {
		this.idRepartidor = idRepartidor;
	}
	public Integer getFacturaImpresa() {
		return facturaImpresa;
	}
	public void setFacturaImpresa(Integer facturaImpresa) {
		this.facturaImpresa = facturaImpresa;
	}
	public Integer getFacturaRechazo() {
		return facturaRechazo;
	}
	public void setFacturaRechazo(Integer facturaRechazo) {
		this.facturaRechazo = facturaRechazo;
	}

}

