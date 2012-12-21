package com.researchmobile.todoterreno.pedidos.ws;

import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.android.dataframework.DataFramework;
import com.android.dataframework.Entity;
import com.researchmobile.todoterreno.pedidos.entity.Articulo;
import com.researchmobile.todoterreno.pedidos.entity.Cliente;
import com.researchmobile.todoterreno.pedidos.entity.DetallePedido;
import com.researchmobile.todoterreno.pedidos.entity.Portafolio;
import com.researchmobile.todoterreno.pedidos.entity.RespuestaWS;
import com.researchmobile.todoterreno.pedidos.entity.Ruta;
import com.researchmobile.todoterreno.pedidos.entity.Usuario;
import com.researchmobile.todoterreno.pedidos.entity.Vendedor;


public class RequestDB {
//guarda usuario
		public void guardaUsuario(Context context, Usuario usuario)
		{
			try
			{
				
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				Entity datoUsuario = new Entity("usuario");
				datoUsuario.setValue("id", usuario.getId());
				datoUsuario.setValue("usuario", usuario.getUsuario());
				datoUsuario.setValue("password", usuario.getPassword());
				datoUsuario.setValue("lastLogin", usuario.getLastLogin());
				datoUsuario.setValue("activo", usuario.getActivo());
				datoUsuario.save();
			}
			 catch(Exception msj)
			 {
				 Log.e("TT", "RequestDB.guardaUsuario = " + msj);
				 msj.printStackTrace();
			 }
		}
		
//guarda vendedor
		public void guardaVendedor(Context context, Vendedor vendedor)
		{
			try
			{
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				Entity datoVendedor = new Entity("vendedor");
				datoVendedor.setValue("vendedor", vendedor.getVendedor());
				datoVendedor.setValue("nombre", vendedor.getNombre());
				datoVendedor.setValue("direccion", vendedor.getDireccion());
				datoVendedor.setValue("telefono",vendedor.getTelefono());
				datoVendedor.setValue("identificacion", vendedor.getIdentificacion());
				datoVendedor.setValue("comision", vendedor.getComision());
				datoVendedor.setValue("ruta", vendedor.getRuta());
				datoVendedor.setValue("clidesnormal", vendedor.getClidesnormal());
				datoVendedor.setValue("clides1", vendedor.getClides1());
				datoVendedor.setValue("clides2", vendedor.getClides2());
				datoVendedor.setValue("clides3", vendedor.getClides3());
				datoVendedor.setValue("turno",vendedor.getTurno());
				datoVendedor.setValue("otnumero", vendedor.getOtnumero());
				datoVendedor.setValue("idusuario", vendedor.getIdusuario());
				datoVendedor.save();
			}
			 catch(Exception msj)
			 {
				 Log.e("TT", "RequestDB.guardaVendedor = " + msj);
				 msj.printStackTrace();
			 }
		}
		
//guarda portafolio
		public void guardaPortafolio(Context context, Portafolio[] portafolio)
		{
			try
			{	
				int tamano = portafolio.length;
				 for(int a = 0; a<tamano; a++)
				 {
					 DataFramework.getInstance().open(context,"com.researchmobile.todoterreno.pedidos.view");		  	 
					 Entity datoPortafolio = new Entity("portafolio");
					 datoPortafolio.setValue("idportafolio", portafolio[a].getIdPortafolio());
					 datoPortafolio.setValue("descripcion", portafolio[a].getDescripcion());
					 datoPortafolio.setValue("fechacreacion", portafolio[a].getFechacreacion());
					 datoPortafolio.setValue("deshabilitar", portafolio[a].getDeshabilitar());
					 datoPortafolio.setValue("anotaciones", portafolio[a].getAnotaciones());
					 datoPortafolio.setValue("usuario", portafolio[a].getUsuario());
					 datoPortafolio.save();
					 
				 }
			}
			catch(Exception msj)
			{
				Log.e("TT", "RequestDB.guardaPortafolios = " + msj);
			 msj.printStackTrace();	
			}
		}
		
//guarda ruta
		public void guardaRuta(Context context, Ruta[] ruta)
		{
			try
			{
				int longitud = ruta.length;
				 for(int b = 0; b<longitud; b++)
				 {
					 DataFramework.getInstance().open(context,"com.researchmobile.todoterreno.pedidos.view");
					 Entity datoruta = new Entity("ruta");
					 datoruta.setValue("id", ruta[b].getId());
					 datoruta.setValue("descripcion", ruta[b].getDescripcion());
					 datoruta.setValue("tipovehiculo", ruta[b].getTipovehiculo());
					 datoruta.setValue("origen", ruta[b].getOrigen());
					 datoruta.setValue("destino", ruta[b].getDestino());
					 datoruta.setValue("precioventa", ruta[b].getPrecioventa());
					 datoruta.setValue("combustible", ruta[b].getCombustible());
					 datoruta.setValue("viaticos", ruta[b].getViaticos());
					 datoruta.setValue("otrosgastos", ruta[b].getOtrosgastos());
					 datoruta.setValue("kilometros", ruta[b].getKilometros());
					 datoruta.save();
				 }
			}	 
				 catch(Exception msj)
				 {
					 Log.e("TT", "RequestDB.guardaRuta = " + msj);
				   msj.printStackTrace();	 
				 }
		}
		
//guarda articulo
		public void guardaArticulo(Context context, Articulo[] articulo)
		{
			try
			{
				int longitud1 = articulo.length;
				for (int c= 0; c<longitud1; c++)
				{
					DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
					Entity datoarticulo = new Entity("articulo");
					datoarticulo.setValue("artCodigo", articulo[c].getArtCodigo());
					datoarticulo.setValue("categoria", articulo[c].getCategoria());
					datoarticulo.setValue("sector", articulo[c].getSector());
					datoarticulo.setValue("division", articulo[c].getDivision());
					datoarticulo.setValue("artDescripcion", articulo[c].getArtDescripcion());
					datoarticulo.setValue("artIngrediente", articulo[c].getArtIngrediente());
					datoarticulo.setValue("precioVenta", articulo[c].getPrecioVenta());
					datoarticulo.setValue("precioDes1", articulo[c].getPrecioDes1());
					datoarticulo.setValue("precioDes2", articulo[c].getPrecioDes2());
					datoarticulo.setValue("precioDes3", articulo[c].getPrecioDes3());
					datoarticulo.setValue("ofertado", articulo[c].isOfertado());//cuando es boolean
					datoarticulo.setValue("precioOferta",articulo[c].getPrecioOferta());
					datoarticulo.setValue("foto",articulo[c].getFoto());
					datoarticulo.setValue("observacion",articulo[c].getObservaciones());
					datoarticulo.setValue("catalogo",articulo[c].getCatalogo());
					datoarticulo.setValue("unidadesFardo",articulo[c].getUnidadesFardo());
					datoarticulo.setValue("link",articulo[c].getLink());
					datoarticulo.setValue("artOfertaFecha",articulo[c].getArtOfertaFecha());
					datoarticulo.save();
				}
			}	
				 catch(Exception msj)
				 {
					 msj.printStackTrace();
				 }
		}
//guarda cliente
		public void guardaCliente (Context context, Cliente[] cliente)
		{
			try
			{			
			 int longitud2 = cliente.length;
			 for(int d=0; d<longitud2; d++)
			 {
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				Entity datocliente = new Entity("cliente");
				datocliente.setValue("cliCodigo", cliente[d].getCliCodigo());
				datocliente.setValue("cliEmpresa", cliente[d].getCliEmpresa());
				datocliente.setValue("cliContacto", cliente[d].getCliContacto());
				datocliente.setValue("codCatCliete", cliente[d].getCodCatCliete());
				datocliente.setValue("cliDireccion", cliente[d].getCliDireccion());
				datocliente.setValue("cliTelefono", cliente[d].getCliTelefono());
				datocliente.setValue("cliFax", cliente[d].getCliFax());
				datocliente.setValue("cliEmail", cliente[d].getCliEmail());
				datocliente.setValue("cliWeb", cliente[d].getCliWeb());
				datocliente.setValue("fingreso", cliente[d].getFingreso());
				datocliente.setValue("cliDesnormal", cliente[d].getCliDesnormal());
				datocliente.setValue("cliDes1", cliente[d].getCliDes1());
				datocliente.setValue("cliDes2", cliente[d].getCliDes2());
				datocliente.setValue("cliDes3", cliente[d].getCliDes3());
				datocliente.setValue("clilimite", cliente[d].getCliLimite());
				datocliente.setValue("cliSaldo", cliente[d].getCliSaldo());
				datocliente.setValue("cliCheque", cliente[d].getCliCheque());
				datocliente.setValue("cliRuta", cliente[d].getCliRuta());
				datocliente.setValue("cliDireccionParticular", cliente[d].getCliDireccionParticular());
				datocliente.setValue("cliTelefonoCasa", cliente[d].getCliTelefonoCasa());
				datocliente.setValue("cliTelefonoMovil", cliente[d].getCliTelefonoMovil());
				datocliente.setValue("cliNit", cliente[d].getCliNit());
				datocliente.setValue("Semana", cliente[d].getSemana());
				datocliente.setValue("diaVisita", cliente[d].getDiaVisita());
				datocliente.setValue("visitado", cliente[d].getVisitado());
				datocliente.save();
			 }
			
			}
			 catch(Exception msj)
			 {
				 msj.printStackTrace();
			 }
		}

//guarda articulo temporal para el pedido
		public void guardaDetallePedidoTemp(Context context, DetallePedido articulo)
		{
			try
			{
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				Entity datoArticulo = new Entity("detallepedido_temp");
				datoArticulo.setValue("idEncabezado_temp", "1");
				datoArticulo.setValue("codigo_temp", articulo.getCodigo());
				datoArticulo.setValue("nombre_temp", articulo.getNombre());
				datoArticulo.setValue("caja_temp", articulo.getCaja());
				datoArticulo.setValue("unidad_temp", articulo.getUnidad());
				datoArticulo.setValue("precio_temp", articulo.getPrecio());
				datoArticulo.setValue("subtotal_temp", articulo.getSubTotal());
				datoArticulo.setValue("totalunidades_temp", articulo.getTotalUnidades());
				datoArticulo.setValue("unidadesfardo_temp", articulo.getUnidadesFardo());
				datoArticulo.save();
			}
			 catch(Exception msj)
			 {
				 Log.e("TT", "RequestDB.guardaDetallePedidoTemp = " + msj);
				 msj.printStackTrace();
			 }
		}
//recorre usuario
		public Usuario usuarioDB(Context context)
		{
			try
			{
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				Usuario usuario = new Usuario();
				List<Entity> categories = DataFramework.getInstance().getEntityList("usuario");
			 		{
			 			Iterator iter = categories.iterator();
			 			while(iter.hasNext())
			 			{
			 			Entity datousuario = (Entity)iter.next();
			 			usuario.setId(datousuario.getString("id"));
			 			usuario.setUsuario(datousuario.getString("usuario"));
			 			usuario.setPassword(datousuario.getString("password"));
			 			usuario.setLastLogin(datousuario.getString("lastLogin"));
			 			usuario.setActivo(datousuario.getString("activo"));	
			 			}
			 		}
			 		return usuario;	
			 }
			  catch(Exception msj)
			  {
				msj.printStackTrace();
				return null;
			  }
			 	
		}
		     	
//recorre vendedor		
		public Vendedor vendedorDB(Context context)
		{
			try
			{
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				Vendedor vendedor = new Vendedor();
			
				List<Entity> categories = DataFramework.getInstance().getEntityList("vendedor");
					{
						Iterator c = categories.iterator();
						while(c.hasNext())
						{
						Entity datovendedor = (Entity)c.next();
						vendedor.setVendedor(datovendedor.getString("vendedor"));
						vendedor.setNombre(datovendedor.getString("nombre"));
						vendedor.setDireccion(datovendedor.getString("direccion"));
						vendedor.setTelefono(datovendedor.getString("telefono"));
						vendedor.setIdentificacion(datovendedor.getString("identificacion"));
						vendedor.setComision(datovendedor.getString("comision"));
						vendedor.setRuta(datovendedor.getString("ruta"));
						vendedor.setClidesnormal(datovendedor.getString("clidesnormal"));
						vendedor.setClides1(datovendedor.getString("clides1"));
						vendedor.setClides2(datovendedor.getString("clides2"));
						vendedor.setClides3(datovendedor.getString("clides3"));
						vendedor.setTurno(datovendedor.getString("turno"));
						vendedor.setOtnumero(datovendedor.getString("otnumero"));
						vendedor.setIdusuario(datovendedor.getString("idusuario"));	
						}
						
						return vendedor;
					}
			}
			catch(Exception msj)
			{
				msj.printStackTrace();
				return null;
			}
		}
			
//recorre articulo
		public Articulo[] articuloDB(Context context)
		{
		 try
		 {
			 DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
			List<Entity> categories = DataFramework.getInstance().getEntityList("articulo");
			{
				int tamano = categories.size();
				Articulo[] articulos = new Articulo[tamano];
				int i = 0;
				Iterator d = categories.iterator(); 
			 		while(d.hasNext())
			 		{
			 			Entity datoArticulo = (Entity)d.next();
			 			Articulo Temp = new Articulo();
			 			Temp.setArtCodigo(datoArticulo.getString("artCodigo"));
			 			Temp.setCategoria(datoArticulo.getString("categoria"));
			 			Temp.setSector(datoArticulo.getString("sector"));
			 			Temp.setDivision(datoArticulo.getString("division"));
			 			Temp.setArtDescripcion(datoArticulo.getString("artDescripcion"));
			 			Temp.setArtIngrediente(datoArticulo.getString("artIngrediente"));
			 			Temp.setPrecioVenta(Float.parseFloat(datoArticulo.getString("precioVenta")));//para convertir la cadena a float
			 			Temp.setPrecioDes1(Float.parseFloat(datoArticulo.getString("precioDes1")));
			 			Temp.setPrecioDes2(Float.parseFloat(datoArticulo.getString("precioDes2")));
			 			Temp.setPrecioDes3(Float.parseFloat(datoArticulo.getString("precioDes3")));
			 			Temp.setOfertado(Boolean.parseBoolean(datoArticulo.getString("ofertado")));
			 			Temp.setPrecioOferta(Float.parseFloat(datoArticulo.getString("precioOferta")));
			 			Temp.setFoto(datoArticulo.getString("foto"));
			 			Temp.setObservaciones(datoArticulo.getString("observacion"));
			 			Temp.setCatalogo(datoArticulo.getString("catalogo"));
			 			Temp.setUnidadesFardo(Integer.parseInt(datoArticulo.getString("unidadesFardo")));
			 			Temp.setLink(datoArticulo.getString("link"));
			 			Temp.setArtOfertaFecha(datoArticulo.getString("artOfertaFecha"));
			 		
			 		articulos[i]=Temp;
			 		i++;
			 	   }
			 	return articulos;		
			}
		 }	
			 catch(Exception msj)
			 {
			   msj.printStackTrace();
			   return null;
			 }
			
			
		}
		
//recorre clientevisitado
		public Cliente[] clienteVisitadoDB(Context context)
		{
		 try
		 {
			 DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
			List<Entity> categories = DataFramework.getInstance().getEntityList("cliente","visitado=1");
			
			int tamano = categories.size();
			Cliente[] clientes = new Cliente[tamano];
			int a=0;
				
				Iterator it = categories.iterator(); 
				while(it.hasNext())
				{
					Entity datoclientevisitado = (Entity)it.next();
			 		Cliente Temp = new Cliente();
			 		Temp.setCliCodigo(datoclientevisitado.getString("cliCodigo"));
			 		Temp.setCliEmpresa(datoclientevisitado.getString("cliEmpresa"));
			 		Temp.setCliContacto(datoclientevisitado.getString("cliContacto"));
			 		Temp.setCodCatCliete(datoclientevisitado.getString("codcatCliete"));
			 		Temp.setCliDireccion(datoclientevisitado.getString("cliDireccion"));
			 		Temp.setCliTelefono(datoclientevisitado.getString("cliTelefono"));
			 		Temp.setCliFax(datoclientevisitado.getString("cliFax"));
			 		Temp.setCliEmail(datoclientevisitado.getString("cliEmail"));
			 		Temp.setCliWeb(datoclientevisitado.getString("cliCWeb"));
			 		Temp.setFingreso(datoclientevisitado.getString("fingreso"));
			 		Temp.setCliDesnormal(datoclientevisitado.getString("cliDesnormal"));
			 		Temp.setCliDes1(datoclientevisitado.getString("cliCDes1"));
			 		Temp.setCliDes2(datoclientevisitado.getString("cliDes2"));
			 		Temp.setCliDes3(datoclientevisitado.getString("cliDes3"));
			 		Temp.setCliLimite(datoclientevisitado.getString("clilimite"));
			 		Temp.setCliSaldo(datoclientevisitado.getString("cliSaldo"));
			 		Temp.setCliCheque(datoclientevisitado.getString("cliCheque"));
			 		Temp.setCliRuta(datoclientevisitado.getString("cliRuta"));
			 		Temp.setCliDireccionParticular(datoclientevisitado.getString("cliDireccionParticular"));
			 		Temp.setCliTelefonoCasa(datoclientevisitado.getString("cliTelefonoCasa"));
			 		Temp.setCliTelefonoMovil(datoclientevisitado.getString("cliTelefonoMovil"));
			 		Temp.setCliNit(datoclientevisitado.getString("cliNit"));
			 		Temp.setSemana(datoclientevisitado.getString("Semana"));
			 		Temp.setDiaVisita(datoclientevisitado.getString("diaVisita"));
			 		Temp.setVisitado(datoclientevisitado.getString("visitado"));
				
			 		clientes[a]=Temp;
			 		a++;
				}
				return clientes;  
				
		 }	
		  catch(Exception msj)
		  {
			  msj.printStackTrace();
			  return null;
		  }
	}
//recorre cliente_pendiente
		public Cliente[] clientePendienteDB(Context context)
		{
		 try
		 {
			DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
			List<Entity> categories = DataFramework.getInstance().getEntityList("cliente");
			int tamano = categories.size();
			Log.e("TT", "RequestDB.clientePendienteDB 4, tamano = " + tamano);
			Cliente[] clientes = new Cliente[tamano];
			int a=0;
				
				Iterator it = categories.iterator(); 
				while(it.hasNext())
				{
					Entity datoclientevisitado = (Entity)it.next();
			 		Cliente Temp = new Cliente();
			 		Temp.setCliCodigo(datoclientevisitado.getString("cliCodigo"));
			 		Temp.setCliEmpresa(datoclientevisitado.getString("cliEmpresa"));
			 		Temp.setCliContacto(datoclientevisitado.getString("cliContacto"));
			 		Temp.setCodCatCliete(datoclientevisitado.getString("codCatCliete"));
			 		Temp.setCliDireccion(datoclientevisitado.getString("cliDireccion"));
			 		Temp.setCliTelefono(datoclientevisitado.getString("cliTelefono"));
			 		Temp.setCliFax(datoclientevisitado.getString("cliFax"));
			 		Temp.setCliEmail(datoclientevisitado.getString("cliEmail"));
			 		Temp.setCliWeb(datoclientevisitado.getString("cliWeb"));
			 		Temp.setFingreso(datoclientevisitado.getString("fingreso"));
			 		Temp.setCliDesnormal(datoclientevisitado.getString("cliDesnormal"));
			 		Temp.setCliDes1(datoclientevisitado.getString("cliDes1"));
			 		Temp.setCliDes2(datoclientevisitado.getString("cliDes2"));
			 		Temp.setCliDes3(datoclientevisitado.getString("cliDes3"));
			 		Temp.setCliLimite(datoclientevisitado.getString("clilimite"));
			 		Temp.setCliSaldo(datoclientevisitado.getString("cliSaldo"));
			 		Temp.setCliCheque(datoclientevisitado.getString("cliCheque"));
			 		Temp.setCliRuta(datoclientevisitado.getString("cliRuta"));
			 		Temp.setCliDireccionParticular(datoclientevisitado.getString("cliDireccionParticular"));
			 		Temp.setCliTelefonoCasa(datoclientevisitado.getString("cliTelefonoCasa"));
			 		Temp.setCliTelefonoMovil(datoclientevisitado.getString("cliTelefonoMovil"));
			 		Temp.setCliNit(datoclientevisitado.getString("cliNit"));
			 		Temp.setSemana(datoclientevisitado.getString("Semana"));
			 		Temp.setDiaVisita(datoclientevisitado.getString("diaVisita"));
			 		Temp.setVisitado(datoclientevisitado.getString("visitado"));
				
			 		clientes[a]=Temp;
			 		a++;
				}
				return clientes;
		 }	
			catch(Exception msj)
			{
				Log.e("TT", "RequestDB.clientePendienteDB error = " + msj);
				msj.printStackTrace();
				return null;
			}
		}
//recibe user y pass
		public RespuestaWS  verificaLoginDB(Context context, String usuario, String password)
		{
			Log.e("TT", "RequestDB.verificaLoginDB 1");
			RespuestaWS respuesta = new RespuestaWS();
			try
			{
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				Log.e("TT", "RequestDB.verificaLoginDB 2");
				List<Entity> categories = DataFramework.getInstance().getEntityList("usuario");
				Log.e("TT", "RequestDB.verificaLoginDB 3");
					if(categories.size()>0)
					{
						
						respuesta.setResultado(true);
					}
					 else {
						respuesta.setResultado(false);
					 }
				
			}
			catch(Exception msj)
			{
				Log.e("TT", "RequestDB.verificaLoginDB error = " + msj);
				respuesta.setResultado(false);
			 msj.printStackTrace();
			}
			return respuesta;
		}
		
//buscacliente
		public Cliente buscaCliente(Context context, String codigocliente)
		{
			Log.e("TT", "RequestDB.buscaCliente - codigoCliente = " + codigocliente);
			Cliente cliente = new Cliente();
			try
			{
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				List<Entity> categories = DataFramework.getInstance().getEntityList("cliente");
					Iterator e = categories.iterator();
					while(e.hasNext())
					{
					
					 Entity d = (Entity)e.next();
					 String codigo = d.getString("cliCodigo");
					 if (codigo.equalsIgnoreCase(codigocliente)){
						 Log.e("TT", "RequestDB.buscaCliente " + codigo + " " + codigocliente);
						 cliente.setCliCodigo(d.getString("cliCodigo"));
						 cliente.setCliEmpresa(d.getString("cliEmpresa"));
						 cliente.setCliContacto(d.getString("cliContacto"));
						 cliente.setCodCatCliete(d.getString("codCatCliete"));
						 cliente.setCliDireccion(d.getString("cliDireccion"));
						 cliente.setCliTelefono(d.getString("cliTelefono"));
						 cliente.setCliFax(d.getString("cliFax"));
						 cliente.setCliContacto(d.getString("cliContacto"));
						 return cliente;
					 }
					 
					}
				return cliente;
			}catch(Exception exception){
				return null;
			}
		}
		
//buscaArticulo
		public DetallePedido buscaArticulo(Context context, String codigoArticulo)
		{
			Log.e("TT", "RequestDB.buscaCliente - codigoCliente = " + codigoArticulo);
			DetallePedido articulo = new DetallePedido();
			try
			{
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				List<Entity> categories = DataFramework.getInstance().getEntityList("articulo");
					Iterator e = categories.iterator();
					while(e.hasNext())
					{
					
					 Entity d = (Entity)e.next();
					 String codigo = d.getString("artCodigo");
					 if (codigo.equalsIgnoreCase(codigoArticulo)){
						 Log.e("TT", "RequestDB.buscaCliente " + codigo + " " + codigoArticulo);
						 articulo.setCodigo(d.getString("artCodigo"));
						 articulo.setCaja(0);
						 articulo.setUnidad(0);
						 articulo.setNombre(d.getString("artDescripcion"));
						 articulo.setPrecio(d.getFloat("precioVenta"));
						 articulo.setSubTotal(0);
						 articulo.setTotalUnidades(0);
						 articulo.setUnidadesFardo(d.getInt("unidadesFardo"));
						 return articulo;
					 }
					 
					}
				return articulo;
			}catch(Exception exception){
				return null;
			}
		}
//ultima llave
		
		
//Borrar registros
		
		
}
