package com.researchmobile.todoterreno.pedidos.utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.researchmobile.todoterreno.pedidos.entity.Pedido;

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

	public String jsonPedido(Pedido pedido, String ruta, String vendedor) {
		String jsonPedido = "";
		
		JSONObject allDataJson = new JSONObject();
		JSONArray encabezadoJsonArray = new JSONArray();
		JSONArray detalleJsonArray = new JSONArray();
		
		try {
			JSONObject encabezadoJson = new JSONObject();
			encabezadoJson.put("clicodigo", pedido.getEncabezadoPedido().getCodigoCliente());
			encabezadoJson.put("id", pedido.getEncabezadoPedido().getCodigoPedidoTemp());
			encabezadoJson.put("movfecha", pedido.getEncabezadoPedido().getFecha());
			encabezadoJson.put("movhora", pedido.getEncabezadoPedido().getHora());
			encabezadoJson.put("movcredito", pedido.getEncabezadoPedido().getCredito());
			encabezadoJson.put("movtotal", pedido.getEncabezadoPedido().getTotal());
			encabezadoJson.put("movefectivo", "0");
			encabezadoJson.put("movcheque", "0");
			encabezadoJsonArray.put(encabezadoJson);
			int tamano = pedido.getDetallePedido().length;
			for (int i = 0; i < tamano; i++){
				JSONObject temp = new JSONObject();
				//temp.put("idencabezado", pedido.getEncabezadoPedido().getCodigoPedidoTemp());
				temp.put("movprecio", pedido.getDetallePedido()[i].getPrecio());
				temp.put("movunidades", pedido.getDetallePedido()[i].getTotalUnidades());
				temp.put("artcodigo", pedido.getDetallePedido()[i].getCodigo());
				//temp.put("unidadesxfardo", pedido.getDetallePedido()[i].getUnidadesFardo());
				//temp.put("movfechaentregar", pedido.getEncabezadoPedido().getFecha());
				detalleJsonArray.put(temp);
			}
			allDataJson.put("encabezado", encabezadoJsonArray);
			allDataJson.put("detalle", detalleJsonArray);
			jsonPedido = allDataJson.toString();
			return jsonPedido;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
