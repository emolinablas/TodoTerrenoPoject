package com.researchmobile.todoterreno.pedidos.ws;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.util.Log;

import com.researchmobile.todoterreno.pedidos.entity.Cliente;
import com.researchmobile.todoterreno.pedidos.entity.ListaArticulos;
import com.researchmobile.todoterreno.pedidos.entity.ListaClientes;
import com.researchmobile.todoterreno.pedidos.entity.LoginEntity;
import com.researchmobile.todoterreno.pedidos.entity.RespuestaWS;
import com.researchmobile.todoterreno.pedidos.entity.User;

public class Peticion {
	//Temp
	//private RequestWSTemp requestWS = new RequestWSTemp();
	//private RequestDBTemp requestDB = new RequestDBTemp();
	
	private RequestWS requestWS = new RequestWS();
	private RequestDB requestDB = new RequestDB();
	private RespuestaWS respuesta = new RespuestaWS();

	public RespuestaWS login(Context context) {
		try{
			respuesta = requestDB.verificaLoginDB(context, User.getUsername(), User.getClave());
			if(respuesta.isResultado()){
				Log.e("TT", "Peticion.login resultadoDB = true");
				return respuesta;
			}else{
				Log.e("TT", "Peticion.login resultadoDB = false");
				LoginEntity loginEntity = new LoginEntity();
				loginEntity = requestWS.login(User.getUsername(), User.getClave());
				respuesta = loginEntity.getRespuesta();
				if (respuesta.isResultado()){
					Log.e("TT", "Peticion.login resultadoWS = true");
					guardarDatos(context, loginEntity);
					cargarClientes(context, loginEntity);
					//cargarArticulos(context, loginEntity);
					return respuesta;
				}else{
					Log.e("TT", "Peticion.login resultadoWS = false");
					return respuesta;
				}
			}
		}catch(Exception exception){
			Log.e("TT", "Peticion.login - " + exception);
		}
		// TODO Auto-generated method stub
		return null;
	}

	private void cargarArticulos(Context context, LoginEntity loginEntity) {
		/*ListaArticulos listaArticulos = new ListaArticulos();
		int tamanoPortafolio = loginEntity.getPortafolio().length;
		for (int i = 0; i < tamanoPortafolio; i++){
			listaArticulos = requestWS.listaArticulos(loginEntity.getPortafolio()[i].getIdPortafolio());
			if (listaArticulos.getArticulo().length > 0){
				guardarArticulos(context, listaArticulos);
			}
		}*/
		
	}

	private void guardarArticulos(Context context, ListaArticulos listaArticulos) {
		requestDB.guardaArticulo(context, listaArticulos.getArticulo());
		
	}

	private void cargarClientes(Context context, LoginEntity loginEntity) {
		Log.e("TT", "Peticion.cargarClientes 1");
		ListaClientes listaClientes = new ListaClientes();
		Log.e("TT", "Peticion.cargarClientes 2");
		int tamanoRuta = loginEntity.getRuta().length;
		Log.e("TT", "Peticion.cargarClientes 3 = " + tamanoRuta);
		for (int i = 0; i < tamanoRuta; i++){
			listaClientes = requestWS.listaClientes("catalogo", loginEntity.getRuta()[i].getId());
			if (listaClientes.getCliente().length > 0){
				guardarClientes(context, listaClientes);
			}
		}
		
	}

	private void guardarClientes(Context context, ListaClientes listaClientes) {
		Log.e("TT", "Peticion.guardarClientes 1");
		requestDB.guardaCliente(context, listaClientes.getCliente());
	}

	private void guardarDatos(Context context, LoginEntity loginEntity) {
		requestDB.guardaUsuario(context, loginEntity.getUsuario());
		requestDB.guardaVendedor(context, loginEntity.getVendedor());
		requestDB.guardaPortafolio(context, loginEntity.getPortafolio());
		requestDB.guardaRuta(context, loginEntity.getRuta());
	}

	private void cargarDatosWS(LoginEntity loginEntity) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<HashMap<String, String>> ListaClientesPendientes (Context context){
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
		Cliente[] cliente = requestDB.clientePendienteDB(context);
		//int tamano = cliente.length;
		for (int i = 0; i < cliente.length; i++){
			String visitado = cliente[i].getVisitado();
			if (visitado.equalsIgnoreCase("null") || visitado.equalsIgnoreCase("0")){
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("codigoCliente", cliente[i].getCliCodigo());
		        map.put("empresa", cliente[i].getCliEmpresa());
		        map.put("contacto", cliente[i].getCliContacto());
		        map.put("direccion", cliente[i].getCliDireccion());
		        map.put("telefono", cliente[i].getCliTelefono());
		        map.put("nit", cliente[i].getCliNit());
		        mylist.add(map);
			}
			
			/*map.put("codigoCliente", "001");
	        map.put("empresa", "Mi Empresa");
	        map.put("contacto", "Mi Contacto");
	        map.put("direccion", "Mi Direccion");
	        map.put("telefono", "Mi Telefono");
	        map.put("nit", "Mi Nit");*/
	        
		}
		return mylist;
		
	}

	public ArrayList<HashMap<String, String>> ListaClientesVisitados (Context context){
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
		Cliente[] cliente = requestDB.clienteVisitadoDB(context);
		int tamano = cliente.length;
		for (int i = 0; i < tamano; i++){
			String visitado = cliente[i].getVisitado();
			if (visitado.equalsIgnoreCase("1")){
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("codigoCliente", cliente[i].getCliCodigo());
				map.put("empresa", cliente[i].getCliEmpresa());
				map.put("contacto", cliente[i].getCliContacto());
				map.put("direccion", cliente[i].getCliDireccion());
				map.put("telefono", cliente[i].getCliTelefono());
				map.put("nit", cliente[i].getCliNit());
				mylist.add(map);
			}
		}
		return mylist;
		
	}

	public Cliente DetalleCliente(Context context, String codigoCliente) {
		Cliente cliente = new Cliente();
		cliente = requestDB.buscaCliente(context, codigoCliente);
		Log.e("TT", "Peticion.detalleCliente = " + cliente.getCliCodigo());
		return cliente;
	}

}
