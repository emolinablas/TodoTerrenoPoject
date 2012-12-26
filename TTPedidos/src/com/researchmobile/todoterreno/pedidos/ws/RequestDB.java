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
import com.researchmobile.todoterreno.pedidos.entity.EncabezadoPedido;
import com.researchmobile.todoterreno.pedidos.entity.NoVenta;
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
		
//guarda Motivo no Venta
		public void guardaNoVenta(Context context, NoVenta noVenta)
		{
			try
			{
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				Entity datoUsuario = new Entity("usuario");
				datoUsuario.setValue("id", noVenta.getId());
				datoUsuario.setValue("usuario", noVenta.getDescripcion());
				datoUsuario.save();
			}
			 catch(Exception msj)
			 {
				 Log.e("TT", "RequestDB.guardaNoVenta = " + msj);
				 msj.printStackTrace();
			 }
		}
		
//guarda encabezado de pedido
		public void guardaEncabezadoPedido(Context context, EncabezadoPedido encabezadoPedido) {
			try{
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				Entity encabezado = new Entity("encabezadopedido");
				encabezado.setValue("codigocliente", encabezadoPedido.getCodigoCliente());
				encabezado.setValue("total", encabezadoPedido.getTotal());
				encabezado.setValue("fecha", encabezadoPedido.getFecha());
				encabezado.setValue("hora", encabezadoPedido.getHora());
				encabezado.setValue("credito", "0");
				encabezado.setValue("sinc", 0);
				encabezado.save();
			}catch(Exception exception){
				Log.e("TT", "RequestDB.guardaEncabezadoPedido error = " + exception);
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
		public void guardaDetallePedidoTemp(Context context, DetallePedido articulo, int numeroPedido)
		{
			try
			{
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				Entity datoArticulo = new Entity("detallepedido_temp");
				datoArticulo.setValue("idEncabezado_temp", String.valueOf(numeroPedido));
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
		
//recorre motivos no venta
		public NoVenta[] noVentaDB(Context context)
		{
			try
			{
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				List<Entity> categories = DataFramework.getInstance().getEntityList("noventa");
			 			int tamano = categories.size();
			 			NoVenta[] noVenta = new NoVenta[tamano];
			 			if (tamano > 0){
			 				Iterator iter = categories.iterator();
			 				int i = 0;
				 			while(iter.hasNext())
				 			{
				 			Entity datoNoVenta = (Entity)iter.next();
				 			NoVenta temp = new NoVenta();
				 			temp.setId(datoNoVenta.getString("id"));
				 			temp.setDescripcion(datoNoVenta.getString("descripcion"));
				 			noVenta[i] = temp;
				 			i++;
				 			}
			 			}
			 		return noVenta;	
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
			RespuestaWS respuesta = new RespuestaWS();
			try
			{
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				List<Entity> categories = DataFramework.getInstance().getEntityList("usuario");
					if(categories.size()>0)
					{
						Iterator e = categories.iterator();
						while(e.hasNext())
						{
							Entity ent = (Entity)e.next();
							if (usuario.equalsIgnoreCase(ent.getString("usuario")) && password.equalsIgnoreCase(ent.getString("password"))){
								respuesta.setResultado(true);
							}else{
								respuesta.setResultado(false);
							}
						}
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
		
		public String rutaCliente(Context context, String codigoCliente){
			String ruta = "";
			try{
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				List<Entity> categories = DataFramework.getInstance().getEntityList("cliente");
					Iterator e = categories.iterator();
					while(e.hasNext())
					{
					
					 Entity d = (Entity)e.next();
					 String codigo = d.getString("cliCodigo");
					 if (codigo.equalsIgnoreCase(codigoCliente)){
						 
						 ruta = d.getString("cliRuta");
						 return ruta;
					 }
					}
				return null;
			}catch(Exception exception){
				return null;
			}
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
						 cliente.setCliRuta(d.getString("cliRuta"));
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
		
		public int ultimoEncabezado(Context context){
			int ultimo = 0;
			try{
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				List<Entity> categories = DataFramework.getInstance().getEntityList("encabezadopedido");
				@SuppressWarnings("rawtypes")
				Iterator e = categories.iterator();
				while(e.hasNext())
				{
				
				 Entity d = (Entity)e.next();
				 long codigo = d.getId();
				 ultimo = (int)codigo;
				 
				}
				ultimo++;
				return ultimo;
				
			}catch(Exception e){
				
				return 0;
			}
			
		}
		
		public boolean eliminarArticulos(Context context){ // metodo que vacia la tabla articulos.
			try{
			DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
			DataFramework.getInstance().emptyTable("articulo");
			return true;
			}catch(Exception e){
				return false;
			}
		}
		
		public boolean eliminarClientes(Context context){ // metodo que vacia la tabla cliente.
			try{
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				DataFramework.getInstance().emptyTable("cliente");
				return true;
			}catch(Exception e){
				return false;
			}
			
		}
		public boolean eliminarUsuario(Context context){ // metodo que vacia la tabla usuario
			try{
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				DataFramework.getInstance().emptyTable("usuario");
				return true;
			}catch(Exception e){
				return false;
			}
		}
		public boolean eliminarPortafolio(Context context){ // metodo que vacia la tabla portafolio
			try {
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				DataFramework.getInstance().emptyTable("portafolio");
				return true;
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
		}
		public boolean eliminarRuta(Context context){ // metodo que vacia la tabla usuario
			try {
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				DataFramework.getInstance().emptyTable("ruta");
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		public boolean eliminarVendedor(Context context){
			try {
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				DataFramework.getInstance().emptyTable("vendedor");
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		public boolean eliminarPedido(Context context){
			try {
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				DataFramework.getInstance().emptyTable("encabezadopedido");
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		public boolean eliminarDetallePedido(Context context){
			try {
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				DataFramework.getInstance().emptyTable("detallepedido");
				return true;
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
		}
		public boolean eliminarDetallePedidoTemp(Context context){
			try {
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				DataFramework.getInstance().emptyTable("detallepedido_temp");
				return true;
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
		}
		public boolean eliminarEncabezadoPedidoTemp(Context context){
			try {
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				DataFramework.getInstance().emptyTable("encabezadopedido_temp");
				return true;
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
		}
		
		// Metodo que retorna el detalle de pedido de un encabezado en particular
		public DetallePedido[] buscaDetallePedido (Context context, long numeroPedido){
			try {
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				List<Entity> detalles = DataFramework.getInstance().getEntityList("detallepedido_temp","idEncabezado_temp = " + String.valueOf(numeroPedido));
				int tamano = detalles.size();
				DetallePedido[] totalDetalles = new DetallePedido[tamano];
				Iterator it = detalles.iterator(); 
				int a=0;
				while(it.hasNext())
				{
					Entity datoDetalle = (Entity)it.next();
			 		DetallePedido temp = new DetallePedido();
			 		
			 		temp.setCaja(Integer.parseInt(datoDetalle.getString("caja_temp")));
			 		temp.setCodigo(datoDetalle.getString("codigo_temp"));
			 		temp.setNombre(datoDetalle.getString("nombre_temp"));
			 		temp.setPrecio(Float.parseFloat(datoDetalle.getString("precio_temp")));
			 		temp.setSubTotal(Float.parseFloat(datoDetalle.getString("subtotal_temp")));
			 		temp.setTotalUnidades(Integer.parseInt(datoDetalle.getString("totalunidades_temp")));
			 		temp.setUnidad(Integer.parseInt(datoDetalle.getString("unidad_temp")));
			 		temp.setUnidadesFardo(Integer.parseInt(datoDetalle.getString("unidadesfardo_temp")));
			 		totalDetalles[a]=temp;
			 		a++;
				}
				return totalDetalles;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return null;
			} 
			
			
		}

		// metodo que actualiza el campo visitado y lo cambia a 1 esto significa que el cliente ya fue visitado
		public boolean actualizarCampoVisitado(Context context, String cliCodigo ){ 
			try{
				
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				List<Entity> campo = DataFramework.getInstance().getEntityList("cliente");
				int tamano = campo.size();
				Cliente[] totalEncabezadosSinced = new Cliente[tamano];
				Iterator it = campo.iterator(); 
				int a=0;
				while(it.hasNext())
				{
					Entity datoCliente = (Entity)it.next();
			 		String codigo = datoCliente.getString("cliCodigo");
			 		if (codigo.equalsIgnoreCase(cliCodigo)){
			 			long id = datoCliente.getId();
			 			Entity cliente = new Entity("cliente", id);
			 			cliente.setValue("visitado", 1);
			 			cliente.save();
			 			return true;
			 		}
			 		a++;
				}
				return false;
				}catch(Exception e){
				return false;
			}	
		}
		
		// metodo que actualiza el encabezado pedido para indicar que ya fue sincronizado
		public boolean actualizarSincEncabezadoPedido(Context context, long codigoPedido){
			try{
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				Entity encabezadoPedido = new Entity("encabezadopedido");
				encabezadoPedido = DataFramework.getInstance().getTopEntity("encabezadopedido", "_id = " + String.valueOf(codigoPedido), "_id");
				encabezadoPedido.setValue("sinc", 1);
				encabezadoPedido.save();
				return true;
			}catch(Exception e){
				return false;
			}
			
		}
		
		//metodo que retorna la cantidad de clientes pendientes
		public int clientesPendientes(Context context){
			try{
				DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
				int cantidad=0;
				cantidad = DataFramework.getInstance().getEntityListCount("cliente", "visitado=0");
				return cantidad;
			}catch(Exception e){
				return 0;
			}
		}
		
		//metodo que retorna la cantidad de clientes visitados
				public int clientesVisitados(Context context){
					try{
						DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
						int cantidad=0;
						cantidad = DataFramework.getInstance().getEntityListCount("cliente", "visitado=1");
						return cantidad;
					}catch(Exception e){
						return 0;
					}
				}
				
		//metodo que retorna la cantidad de clientes 
				
				public int totalClientes(Context context){
					try{
						DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
						int cantidad=0;
						cantidad = DataFramework.getInstance().getEntityListCount("cliente","visitado=1 OR visitado=0");
						return cantidad;
					}catch(Exception e){
						return 0;
					}
				}
				
				//metodo que retorna la cantidad pedidos sincronizados
				public int pedidosSincronizados(Context context){
					try{
						DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
						int cantidad=0;
						cantidad = DataFramework.getInstance().getEntityListCount("encabezadopedido", "sinc=1");
						return cantidad;
					}catch(Exception e){
						return 0;
					}
				}
				
				//metodo que retorna la cantidad pedidos no sincronizados
				public int pedidosNoSincronizados(Context context){
					try{
						DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
						int cantidad=0;
						cantidad = DataFramework.getInstance().getEntityListCount("encabezadopedido", "sinc=0");
						return cantidad;
					}catch(Exception e){
						return 0;
					}
				}
				
				// Metodo que retorna el listado de encabezado pedidos sincronizados
				public EncabezadoPedido[] encabezadoPedidoSinc(Context context){
					try {
						DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
						List<Entity> encabezadoPedidoSinced = DataFramework.getInstance().getEntityList("encabezadopedido","sinc = 1");
						int tamano = encabezadoPedidoSinced.size();
						EncabezadoPedido[] totalEncabezadosSinced = new EncabezadoPedido[tamano];
						Iterator it = encabezadoPedidoSinced.iterator(); 
						int a=0;
						while(it.hasNext())
						{
							Entity datoEncabezado = (Entity)it.next();
					 		EncabezadoPedido temp = new EncabezadoPedido();
					 		
					 		temp.setCodigoCliente(datoEncabezado.getString("codigocliente"));
					 		temp.setTotal(Float.parseFloat(datoEncabezado.getString("total")));
					 		temp.setFecha(datoEncabezado.getString("fecha"));
					 		temp.setHora(datoEncabezado.getString("hora"));
					 		temp.setCredito(datoEncabezado.getString("credito"));
					 		temp.setSinc(Integer.parseInt(datoEncabezado.getString("sinc")));
					 		
					 		totalEncabezadosSinced[a]=temp;
					 		a++;
						}
						return totalEncabezadosSinced;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						return null;
					} 
					
					
				}
				
				// Metodo que retorna todos los encabezados pedidos
				public EncabezadoPedido[] encabezadoPedido(Context context){
					try {
						DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
						List<Entity> encabezadoPedido = DataFramework.getInstance().getEntityList("encabezadopedido");
						int tamano = encabezadoPedido.size();
						EncabezadoPedido[] totalEncabezados = new EncabezadoPedido[tamano];
						Iterator it = encabezadoPedido.iterator(); 
						int a=0;
						while(it.hasNext())
						{
							Entity datoEncabezado = (Entity)it.next();
					 		EncabezadoPedido temp = new EncabezadoPedido();
					 		
					 		temp.setCodigoCliente(datoEncabezado.getString("codigocliente"));
					 		temp.setTotal(Float.parseFloat(datoEncabezado.getString("total")));
					 		temp.setFecha(datoEncabezado.getString("fecha"));
					 		temp.setHora(datoEncabezado.getString("hora"));
					 		temp.setCredito(datoEncabezado.getString("credito"));
					 		temp.setSinc(Integer.parseInt(datoEncabezado.getString("sinc")));
					 		
					 		totalEncabezados[a]=temp;
					 		a++;
						}
						return totalEncabezados;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						return null;
					} 
					
					
				}
				public EncabezadoPedido[] encabezadoPedidoNoSinc(Context context){
					try {
						DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
						List<Entity> encabezadoPedidoNoSinced = DataFramework.getInstance().getEntityList("encabezadopedido","sinc = 0");
						int tamano = encabezadoPedidoNoSinced.size();
						EncabezadoPedido[] totalEncabezadosNoSinced = new EncabezadoPedido[tamano];
						Iterator it = encabezadoPedidoNoSinced.iterator(); 
						int a=0;
						while(it.hasNext())
						{
							Entity datoEncabezado = (Entity)it.next();
					 		EncabezadoPedido temp = new EncabezadoPedido();
					 		temp.setId(datoEncabezado.getId());
					 		temp.setCodigoCliente(datoEncabezado.getString("codigocliente"));
					 		temp.setTotal(Float.parseFloat(datoEncabezado.getString("total")));
					 		temp.setFecha(datoEncabezado.getString("fecha"));
					 		temp.setHora(datoEncabezado.getString("hora"));
					 		temp.setCredito(datoEncabezado.getString("credito"));
					 		temp.setSinc(Integer.parseInt(datoEncabezado.getString("sinc")));
					 		
					 		totalEncabezadosNoSinced[a]=temp;
					 		a++;
						}
						return totalEncabezadosNoSinced;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						return null;
					} 
					
					
				}
				
				//Encuentra el total de ventas de todos los pedidos realizados		
				public float totalVentas(Context context)
				{
					try
					{
						DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.pedidos.view");
						List<Entity> encabezadoPedido = DataFramework.getInstance().getEntityList("encabezadopedido");
								Float total = (float) 0;
								Float subTotal = (float) 0;
								Iterator c = encabezadoPedido.iterator();
								while(c.hasNext())
								{
								subTotal = (float) 0;
								Entity datoEncabezadoPedido = (Entity)c.next();
								subTotal = datoEncabezadoPedido.getFloat("total");
								total = total + subTotal;
								}
								return total;
							
					}
					catch(Exception msj)
					{
						msj.printStackTrace();
						return (float) 0;
					}
				}

				

}
