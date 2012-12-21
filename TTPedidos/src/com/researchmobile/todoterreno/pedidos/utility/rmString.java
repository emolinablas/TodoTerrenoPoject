package com.researchmobile.todoterreno.pedidos.utility;

public class rmString {
	private Fecha fecha = new Fecha();
	
	public boolean diaVisitaHoy(String diaVisita){
		
		boolean resultado = diaVisita.startsWith(fecha.diaLetra());
		return resultado;
	}
	
	public boolean semanaVisitaHoy(String semana){
		boolean resultado = semana.startsWith(String.valueOf(fecha.semanaMes()));
		return resultado;
	}

}
