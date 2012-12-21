package com.researchmobile.todoterreno.pedidos.ws;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.researchmobile.todoterreno.pedidos.entity.Articulo;
import com.researchmobile.todoterreno.pedidos.entity.Cliente;
import com.researchmobile.todoterreno.pedidos.entity.ListaArticulos;
import com.researchmobile.todoterreno.pedidos.entity.ListaClientes;
import com.researchmobile.todoterreno.pedidos.entity.LoginEntity;
import com.researchmobile.todoterreno.pedidos.entity.Portafolio;
import com.researchmobile.todoterreno.pedidos.entity.RespuestaWS;
import com.researchmobile.todoterreno.pedidos.entity.Ruta;
import com.researchmobile.todoterreno.pedidos.entity.Usuario;
import com.researchmobile.todoterreno.pedidos.entity.Vendedor;

public class RequestWS {
	private static String WS_LOGIN = "ws_login.php?a=login&";
	private static String WS_CLIENTES = "ws_clientes.php?";

	// Metodo que llena el Entity del login
	@SuppressWarnings("unused")
	public LoginEntity login(String usuario, String password) {
		
		String url = WS_LOGIN + "usuario=" + usuario + "&" + "password=" + password; // string de conexi—n
		JSONObject jsonObject = ConnectWS.obtenerJson(url.replace(" ", "%20"));
		Log.e("TT", "respuesta ws login = " + jsonObject.toString());
		LoginEntity loginEntity = new LoginEntity();
		try {
			if(jsonObject.has("resultado")){ // si se produjo un error al consumir el WS
				// creamos el LoginEntity y le asignamos el resultado recibido
				
				loginEntity.setRespuesta(new RespuestaWS());
				loginEntity.getRespuesta().setResultado(jsonObject.getBoolean("resultado"));
				loginEntity.getRespuesta().setMensaje(jsonObject.getString("mensaje"));
				Log.e("TT", "RequestWS - login. resultado = " + loginEntity.getRespuesta().isResultado());
				if(loginEntity.getRespuesta().isResultado()){ //  si existe resultado relleno los campos del usuario
					loginEntity.setUsuario(new Usuario());
					JSONArray usuarioJsonArray = jsonObject.getJSONArray("usuario");
					JSONObject usuarioJsonObject = usuarioJsonArray.getJSONObject(0);
					loginEntity.getUsuario().setActivo(usuarioJsonObject.getString("activo"));
					loginEntity.getUsuario().setUsuario(usuarioJsonObject.getString("usuario"));
					loginEntity.getUsuario().setPassword(usuarioJsonObject.getString("password"));
					loginEntity.getUsuario().setLastLogin(usuarioJsonObject.getString("lastLogin"));
					loginEntity.getUsuario().setId(usuarioJsonObject.getString("id"));
					Log.e("TT", "RequestWS.login Usuario");
					if(jsonObject.has("vendedor")){ // si viene el Array de vendedor tambien ingreso los resultados al Entity del Login
						loginEntity.setVendedor(new Vendedor());
						JSONArray vendedorJsonArray = jsonObject.getJSONArray("vendedor");
						JSONObject vendedorJsonObject = vendedorJsonArray.getJSONObject(0);
						loginEntity.getVendedor().setVendedor(nullToString(vendedorJsonObject.getString("Vendedor")));
						loginEntity.getVendedor().setNombre(nullToString(vendedorJsonObject.getString("Nombre")));
						loginEntity.getVendedor().setDireccion(nullToString(vendedorJsonObject.getString("Direccion")));
						loginEntity.getVendedor().setTelefono(nullToString(vendedorJsonObject.getString("Telefono")));
						loginEntity.getVendedor().setIdentificacion(nullToString(vendedorJsonObject.getString("Identificacion")));
						loginEntity.getVendedor().setComision(nullToString(vendedorJsonObject.getString("Comision")));
						loginEntity.getVendedor().setRuta(nullToString(vendedorJsonObject.getString("Ruta")));
						loginEntity.getVendedor().setClidesnormal(nullToString(vendedorJsonObject.getString("clidesnormal")));
						loginEntity.getVendedor().setClides1(nullToString(vendedorJsonObject.getString("clides1")));
						loginEntity.getVendedor().setClides2(nullToString(vendedorJsonObject.getString("clides2")));
						loginEntity.getVendedor().setClides3(nullToString(vendedorJsonObject.getString("clides3")));
						loginEntity.getVendedor().setTurno(nullToString(vendedorJsonObject.getString("turno")));
						loginEntity.getVendedor().setOtnumero(nullToString(vendedorJsonObject.getString("otnumero")));
						loginEntity.getVendedor().setIdusuario(nullToString(vendedorJsonObject.getString("idusuario")));
						Log.e("TT", "RequestWS.login Vendedor");
					}else{
						System.out.println("No se obtuvieron datos del vendedor");
					}
					if(jsonObject.has("portafolios")){ // si viene el Array de portafolios asigno los campos al portafolio
						JSONArray portafoliosJsonArray = jsonObject.getJSONArray("portafolios");
						int tamano = portafoliosJsonArray.length();
						Portafolio[] portafolios = new Portafolio[tamano];
						for(int i=0; i < tamano; i++){
						JSONObject portafoliosJsonObject = portafoliosJsonArray.getJSONObject(0);
						Portafolio temp = new Portafolio();
						temp.setIdPortafolio(nullToString(portafoliosJsonObject.getString("IDportafolio")));
						temp.setDescripcion(nullToString(portafoliosJsonObject.getString("descripcion")));
						temp.setFechacreacion(nullToString(portafoliosJsonObject.getString("fechacreacion")));
						temp.setDeshabilitar(nullToString(portafoliosJsonObject.getString("deshabilitar")));
						temp.setAnotaciones(nullToString(portafoliosJsonObject.getString("anotaciones")));
						temp.setUsuario(nullToString(portafoliosJsonObject.getString("usuario")));
						portafolios[i] = temp;
						} loginEntity.setPortafolio(portafolios);
						Log.e("TT", "RequestWS.login Portafolios");
					}else{
						System.out.println("No se obtuvieron datos del portafolios");
					}
					if(jsonObject.has("rutas")){ // si viene el Array de rutas asigno los campos al array de rutas y lo envio al LoginEntity
						JSONArray rutasJsonArray = jsonObject.getJSONArray("rutas");
						int tamano = rutasJsonArray.length();
						Ruta[] rutas = new Ruta[tamano];
						for(int i=0; i < tamano; i++){ // recorro el Array para asignar cada registro a una variable a un objeto temporal y luego agregarlo al Array de listaClientes
						JSONObject rutasJsonObject = rutasJsonArray.getJSONObject(0);
						Ruta temp = new Ruta();
						//temp.setIdPortafolio(nullToString(rutasJsonObject.getString("IDportafolio")));
						temp.setId(nullToString(rutasJsonObject.getString("ID")));
						temp.setDescripcion(nullToString(rutasJsonObject.getString("Descripcion")));
						temp.setTipovehiculo(nullToString(rutasJsonObject.getString("tipovehiculo")));
						temp.setOrigen(nullToString(rutasJsonObject.getString("origen")));
						temp.setDestino(nullToString(rutasJsonObject.getString("destino")));
						temp.setPrecioventa(nullToString(rutasJsonObject.getString("precioventa")));
						temp.setCombustible(nullToString(rutasJsonObject.getString("combustible")));
						temp.setViaticos(nullToString(rutasJsonObject.getString("viaticos")));
						temp.setOtrosgastos(nullToString(rutasJsonObject.getString("otrosgastos")));
						temp.setKilometros(nullToString(rutasJsonObject.getString("kilometros")));
						
						rutas[i] = temp;
						} loginEntity.setRuta(rutas);
						Log.e("TT", "RequestWS.login Rutas");
						return loginEntity;
						
					}else{
						System.out.println("No se obtuvieron datos de las rutas");
					}
					
				}
				return loginEntity;
			}else{
				System.out.println("No se obtuvo resultado del JSON");	
				return null;    
			}
		} catch (Exception e) {
			System.out.println("OCURRIO UN ERROR AL PARSEAR EL JSON Error: " + e );
			return null;
		}

		
	}
	
	// Metodo que retorna la lista de clientes obtenidas desde el WS se necesita como parametros el cat‡logo y la ruta del vendedor
	public ListaClientes listaClientes(String catalogo, String ruta){
		String url = WS_CLIENTES + "a=" + catalogo + "&" + "idruta=" + ruta; // string de conexi—n

		JSONObject jsonObject = ConnectWS.obtenerJson(url);
		System.out.println("RESPUESTA WS LOGIN:" + jsonObject.toString());
		ListaClientes listaClientes = new ListaClientes(); // creamos el objeto que se va a retornar, instanciando la clase ListaClientes
		try {
				if(jsonObject.has("resultado")){ // si se produjo un error al consumir el WS
				// creamos el listaClientes y le asignamos el resultado recibido
					listaClientes.setRespuesta(new RespuestaWS());
					listaClientes.getRespuesta().setResultado(jsonObject.getBoolean("resultado"));
					listaClientes.getRespuesta().setMensaje(jsonObject.getString("mensaje"));
					if(jsonObject.has("cliente")){
						JSONArray clientesJsonArray = jsonObject.getJSONArray("cliente"); // obtengo el Array de clientes que viene el el JSON
						Cliente[] clientes = new Cliente[clientesJsonArray.length()];
						for(int i=0;i<clientesJsonArray.length();i++){
							JSONObject clientesJsonObject = clientesJsonArray.getJSONObject(i);
							Cliente temp = new Cliente();
							temp.setCliCodigo(nullToString(clientesJsonObject.getString("clicodigo")));
							temp.setCliCheque(nullToString(clientesJsonObject.getString("clicheque")));
							temp.setCliContacto(nullToString(clientesJsonObject.getString("clicontacto")));
							temp.setCodCatCliete(nullToString(clientesJsonObject.getString("codcatclientes")));
							temp.setCliDes1(nullToString(clientesJsonObject.getString("clides1")));
							temp.setCliDes2(nullToString(clientesJsonObject.getString("clides2")));
							temp.setCliDes3(nullToString(clientesJsonObject.getString("clides3")));
							temp.setCliDesnormal(nullToString(clientesJsonObject.getString("clidesnormal")));
							temp.setCliDireccion(nullToString(clientesJsonObject.getString("clidireccion")));
							temp.setCliDireccionParticular(nullToString(clientesJsonObject.getString("clidireccionparticular")));
							temp.setCliEmail(nullToString(clientesJsonObject.getString("cliemail")));
							temp.setCliEmpresa(nullToString(clientesJsonObject.getString("cliempresa")));
							temp.setCliFax(nullToString(clientesJsonObject.getString("clifax")));
							temp.setCliLimite(nullToString(clientesJsonObject.getString("clilimite")));
							temp.setCliNit(nullToString(clientesJsonObject.getString("clinit")));
							temp.setCliRuta(nullToString(clientesJsonObject.getString("clruta")));
							temp.setCliSaldo(nullToString(clientesJsonObject.getString("clisaldo")));
							temp.setCliTelefono(nullToString(clientesJsonObject.getString("clitelefono")));
							temp.setCliTelefonoCasa(nullToString(clientesJsonObject.getString("clitelecasa")));
							temp.setCliTelefonoMovil(nullToString(clientesJsonObject.getString("clitelecel")));
							temp.setCliWeb(nullToString(clientesJsonObject.getString("cliweb")));
							temp.setFingreso(nullToString(clientesJsonObject.getString("clifingreso")));
							temp.setDiaVisita(nullToString(clientesJsonObject.getString("diavisita")));
							temp.setVisitado(nullToString(clientesJsonObject.getString("visitado")));
							temp.setSemana(nullToString(clientesJsonObject.getString("semana")));
							clientes[i]=temp;
							
						} listaClientes.setCliente(clientes);
						return listaClientes;
						
					}else{
						System.out.println("No se obtuvo el Array de clientes");
					}
					return listaClientes;
				}else{
					System.out.println("No se obtuvo resultado del WS");
					return null;
				}
			
			}catch(Exception e){
				System.out.println("OCURRIO UN ERROR AL PARSEAR EL JSON Error: " + e );
				return null;
				}
		
	}
	
	// Metodo que retorna la lista de clientes obtenidas desde el WS se necesita como parametros el cat‡logo y la ruta del vendedor
		public ListaArticulos listaArticulos(String portafolio, String ruta){
			String url = WS_LOGIN + "a=" + portafolio + "&" + "idportafolio=" + ruta; // string de conexi—n

			JSONObject jsonObject = ConnectWS.obtenerJson(url);
			System.out.println("RESPUESTA WS LOGIN:" + jsonObject.toString());
			ListaArticulos listaArticulos = new ListaArticulos(); // creamos el objeto que se va a retornar, instanciando la clase ListaArticulos
			try {
					if(jsonObject.has("resultado")){ // si se produjo un error al consumir el WS
					// creamos el listaArticulos y le asignamos el resultado recibido
						listaArticulos.getRespuesta().setResultado(jsonObject.getBoolean("resultado"));
						listaArticulos.getRespuesta().setMensaje(jsonObject.getString("mensaje"));
						
						if(jsonObject.has("articulos")){
							JSONArray articulosJsonArray = jsonObject.getJSONArray("articulos"); // obtengo el Array de clientes que viene el el JSON
							Articulo[] articulos = new Articulo[articulosJsonArray.length()];
							for(int i=0;i<articulosJsonArray.length();i++){
								JSONObject articulosJsonObject = articulosJsonArray.getJSONObject(i);
								Articulo temp = new Articulo();
								
								temp.setArtCodigo(nullToString(articulosJsonObject.getString("artcodigo")));
								temp.setArtCodigoAlterno(nullToString(articulosJsonObject.getString("artcodigoalterno")));
								temp.setArtDescripcion(nullToString(nullToString(articulosJsonObject.getString("artdescripcion"))));
								temp.setArtIngrediente(nullToString(articulosJsonObject.getString("artingrediente")));
								temp.setArtOfertaFecha(nullToString(articulosJsonObject.getString("artofertafecha")));
								temp.setCatalogo(nullToString(articulosJsonObject.getString("artcatalogo")));
								temp.setCategoria(nullToString(articulosJsonObject.getString("codcategoria")));
								temp.setDivision(nullToString(articulosJsonObject.getString("coddivision")));
								temp.setFoto(nullToString(articulosJsonObject.getString("artfoto")));
								temp.setLink(nullToString(articulosJsonObject.getString("link")));
								temp.setObservaciones(nullToString(articulosJsonObject.getString("artobservaciones")));
								temp.setOfertado(Boolean.parseBoolean(nullToString(articulosJsonObject.getString("artofertado"))));
								temp.setPrecioDes1(Float.parseFloat(nullToString(articulosJsonObject.getString("artpreciodes1"))));
								temp.setPrecioDes2(Float.parseFloat(nullToString(articulosJsonObject.getString("artpreciodes2"))));
								temp.setPrecioDes3(Float.parseFloat(nullToString(articulosJsonObject.getString("artpreciodes3"))));
								temp.setPrecioOferta(Float.parseFloat(nullToString(articulosJsonObject.getString("artprecioferta"))));
								temp.setPrecioVenta(Float.parseFloat(nullToString(articulosJsonObject.getString("artprecioventa"))));
							    temp.setSector(nullToString(articulosJsonObject.getString("codsector")));
							    temp.setUnidadesFardo(Integer.parseInt(nullToString(articulosJsonObject.getString("artunidadesfardo"))));
								
							    articulos[i]=temp; // asigno el articulo temporal al Array de articulos
								
							} listaArticulos.setArticulo(articulos);
							  
						}else{
							System.out.println("No se obtuvo el Array de articulos");
						}
						return listaArticulos;
					}else{
						System.out.println("No se obtuvo resultado del WS");
						return null;
					}
				
				}catch(Exception e){
					System.out.println("OCURRIO UN ERROR AL PARSEAR EL JSON Error: " + e );
					return null;
					}
			
		}
	
	// Metodo convierte un valor null ingresado a un String y devuelve un espacio en blanco
	public String nullToString(String variable){
		if(variable == null){
			return " ";
		}else{
		return variable;
			}
	}
	
	public int nullToInteger(String variable){
		if (variable == null){
			return 0;
		}else{
			int va = Integer.parseInt(variable);
			return va;
		}
	}

}
