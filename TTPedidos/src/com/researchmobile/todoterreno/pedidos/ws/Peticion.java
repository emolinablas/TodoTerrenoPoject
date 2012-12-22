package com.researchmobile.todoterreno.pedidos.ws;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.util.Log;

import com.researchmobile.todoterreno.pedidos.entity.Articulo;
import com.researchmobile.todoterreno.pedidos.entity.Cliente;
import com.researchmobile.todoterreno.pedidos.entity.DetallePedido;
import com.researchmobile.todoterreno.pedidos.entity.EncabezadoPedido;
import com.researchmobile.todoterreno.pedidos.entity.ListaArticulos;
import com.researchmobile.todoterreno.pedidos.entity.ListaClientes;
import com.researchmobile.todoterreno.pedidos.entity.LoginEntity;
import com.researchmobile.todoterreno.pedidos.entity.Pedido;
import com.researchmobile.todoterreno.pedidos.entity.RespuestaWS;
import com.researchmobile.todoterreno.pedidos.entity.User;
import com.researchmobile.todoterreno.pedidos.entity.Vendedor;
import com.researchmobile.todoterreno.pedidos.utility.ConnectState;

public class Peticion {
	//Temp
	//private RequestWSTemp requestWS = new RequestWSTemp();
	//private RequestDBTemp requestDB = new RequestDBTemp();
	
	private RequestWS requestWS = new RequestWS();
	private RequestDB requestDB = new RequestDB();
	private RespuestaWS respuesta = new RespuestaWS();
	private ConnectState connectState = new ConnectState();

	public RespuestaWS login(Context context) {
		try{
			respuesta = requestDB.verificaLoginDB(context, User.getUsername(), User.getClave());
			if(respuesta.isResultado()){
				return respuesta;
			}else{
				if (connectState.isConnectedToInternet(context)){
					LoginEntity loginEntity = new LoginEntity();
					loginEntity = requestWS.login(User.getUsername(), User.getClave());
					respuesta = loginEntity.getRespuesta();
					if (respuesta.isResultado()){
						reiniciaDB(context);
						guardarDatos(context, loginEntity);
						cargarClientes(context, loginEntity);
						cargarArticulos(context, loginEntity);
						return respuesta;
					}else{
						return respuesta;
					}
				}else{
					respuesta.setResultado(false);
					respuesta.setMensaje("No cuenta con conexion a internet");
					return respuesta;
				}
			}
		}catch(Exception exception){
			Log.e("TT", "Peticion.login - " + exception);
			respuesta.setResultado(false);
			respuesta.setMensaje("Verifique sus datos, Intente nuevamente");
			return respuesta;
			
		}
	}
	
	private void reiniciaDB(Context context){
		requestDB.eliminarUsuario(context);
		requestDB.eliminarVendedor(context);
		requestDB.eliminarPortafolio(context);
		requestDB.eliminarRuta(context);
		requestDB.eliminarArticulos(context);
		requestDB.eliminarClientes(context);
		requestDB.eliminarDetallePedido(context);
		requestDB.eliminarDetallePedidoTemp(context);
		requestDB.eliminarEncabezadoPedidoTemp(context);
		requestDB.eliminarPedido(context);
	}

	private void cargarArticulos(Context context, LoginEntity loginEntity) {
		ListaArticulos listaArticulos = new ListaArticulos();
		int tamanoPortafolio = loginEntity.getPortafolio().length;
		for (int i = 0; i < tamanoPortafolio; i++){
			listaArticulos = requestWS.listaArticulos(loginEntity.getPortafolio()[i].getIdPortafolio());
			if (listaArticulos.getArticulo().length > 0){
				guardarArticulos(context, listaArticulos);
			}
		}
		
	}
	
	public void insertaEncabezado(Context context, EncabezadoPedido encabezadoPedido) {
		requestDB.guardaEncabezadoPedido(context, encabezadoPedido);
		
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
	
	public void insertaArticuloTemp(Context context, DetallePedido articulo, int numeroPedido) {
		requestDB.guardaDetallePedidoTemp(context, articulo, numeroPedido);
		
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
			
		}
		return mylist;
		
	}
	
	public ArrayList<HashMap<String, String>> listaArticulos(Context context) {
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
		Articulo[] articulo = requestDB.articuloDB(context);
		int tamano = articulo.length;
		for (int i = 0; i < tamano; i++){
			HashMap<String, String> map = new HashMap<String, String>();
        	map.put("codigoProducto", articulo[i].getArtCodigo());
        	map.put("nombreProducto", articulo[i].getArtDescripcion());
        	map.put("cajas", "0");
        	map.put("unidades", "0");
        	map.put("valor", String.valueOf(articulo[i].getPrecioVenta()));
        	map.put("presentacion", String.valueOf(articulo[i].getUnidadesFardo()));
        	map.put("existencia", "0");
        	map.put("bonificacion", "0");
        	map.put("total", "0");
        	mylist.add(map);
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
	
	public ArrayList<HashMap<String, String>> listaPedidos(Context context) {
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
		EncabezadoPedido[] encabezados = requestDB.encabezadoPedido(context);
		int tamano = encabezados.length;
		Log.e("Peticion", "encabezados = " + tamano);
		for (int i = 0; i < tamano; i++){
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("codigoCliente", encabezados[i].getCodigoCliente());
				map.put("total", String.valueOf(encabezados[i].getTotal()));
				map.put("fecha", encabezados[i].getFecha());
				map.put("hora", encabezados[i].getHora());
				mylist.add(map);
		}
		return mylist;
	}

	public Cliente DetalleCliente(Context context, String codigoCliente) {
		Cliente cliente = new Cliente();
		cliente = requestDB.buscaCliente(context, codigoCliente);
		Log.e("TT", "Peticion.detalleCliente = " + cliente.getCliCodigo());
		return cliente;
	}

	public DetallePedido buscaArticulo(Context context, String codigoProducto) {
		DetallePedido articulo = new DetallePedido();
		articulo = requestDB.buscaArticulo(context, codigoProducto);
		
		// TODO Auto-generated method stub
		return articulo;
	}

	public int numeroPedido(Context context) {
		int numero = 0;
		numero = requestDB.ultimoEncabezado(context);
		return numero;
	}

	public void enviarPedido(Context context, EncabezadoPedido encabezado, int numeroPedido, String ruta) {
		if (connectState.isConnectedToInternet(context)){
			Pedido pedido = new Pedido();
			pedido.setEncabezadoPedido(encabezado);
			pedido.setDetallePedido(requestDB.buscaDetallePedido(context, numeroPedido));
			Vendedor vendedor = new Vendedor();
			vendedor = requestDB.vendedorDB(context);
			RespuestaWS respuesta = new RespuestaWS();
			respuesta = requestWS.enviaPedido(pedido, ruta, vendedor.getIdusuario());
			if (!respuesta.isResultado()){
				Log.e("TT", "resultado del envio = " + respuesta.getMensaje());
				requestDB.actualizarCampoVisitado(context, encabezado.getCodigoCliente());
				requestDB.actualizarSincEncabezadoPedido(context, numeroPedido);
				
			}
		}
		
	}

	public float totalGeneral(Context context) {
		float total = requestDB.totalVentas(context);
		return total;
	}
	
	public int totalEnviados(Context context) {
		int total = requestDB.pedidosSincronizados(context);
		return total;
	}
	
	public int totalPendientes(Context context) {
		int total = requestDB.pedidosNoSincronizados(context);
		return total;
	}

	
}
