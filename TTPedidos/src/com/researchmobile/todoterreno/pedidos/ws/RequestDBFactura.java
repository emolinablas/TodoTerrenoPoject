package com.researchmobile.todoterreno.pedidos.ws;

import java.util.Iterator;
import java.util.List;

import android.content.Context;

import com.android.dataframework.DataFramework;
import com.android.dataframework.Entity;
import com.researchmobile.todoterreno.pedidos.entity.DetalleFactura;
import com.researchmobile.todoterreno.pedidos.entity.Factura;

public class RequestDBFactura {
	//guarda factura
			public void guardafactura(Context context, Factura[] factura)
			{
				try
				{
					int l= factura.length;
					 for(int a=0; a<l; a++)
					 {
						 DataFramework.getInstance().open(context,"com.researchmobile.todoterreno.facturacion.view");
						 Entity datofactura = new Entity("factura");
						 datofactura.setValue("movdocumento", factura[a].getMovDocumento());
						 datofactura.setValue("movfecha", factura[a].getMovFecha());
						 datofactura.setValue("movhora", factura[a].getMovHora());
						 datofactura.setValue("movtipo", factura[a].getMovTipo());
						 datofactura.setValue("movanulado", factura[a].isMovAnulado());
						 datofactura.setValue("movestatus", factura[a].getMovEstatus());
						 datofactura.setValue("movtotal", factura[a].getMovTotal());
						 datofactura.setValue("movefectivo", factura[a].getMovEfectivo());
						 datofactura.setValue("movcheque", factura[a].getMovCheque());
						 datofactura.setValue("movcredito", factura[a].getMovCredito());
						 datofactura.setValue("clicodigo", factura[a].getCliCodigo());
						 datofactura.setValue("factura", factura[a].getFactura());
						 datofactura.setValue("movfacturado", factura[a].isMovFacturado());
						 datofactura.setValue("primerdato", factura[a].getPrimerDato());
						 datofactura.setValue("movnotas", factura[a].getMovNotas());
						 datofactura.setValue("movcerrado", factura[a].getMovCerrado());
						 datofactura.setValue("cajacodigo", factura[a].getCajaCodigo());
						 datofactura.setValue("movpagado", factura[a].getMovPagado());
						 datofactura.setValue("pagodocumento", factura[a].getPagoDocumento());
						 datofactura.setValue("movfechavence", factura[a].getMovFechaVence());
						 datofactura.setValue("cierremes", factura[a].getCierreMes());
						 datofactura.setValue("vendedor", factura[a].getVendedor());
						 datofactura.setValue("ruta", factura[a].getRuta());
						 datofactura.setValue("idrepartidor", factura[a].getIdRepartidor());
						 datofactura.setValue("facturaimpresa", factura[a].getFacturaImpresa());
						 datofactura.setValue("facturarechazo", factura[a].getFacturaRechazo());
						 datofactura.save();
					 }
				}
				 catch(Exception msj)
				 {
					 msj.printStackTrace();
				 }
			}
			
			//consulta factura
			public Factura facturaconsulta()
			{
				try
				{
					Factura factura = new Factura();
					List<Entity> categories = DataFramework.getInstance().getEntityList("factura");
					{
						int tamano = categories.size();
						Factura[] factura1 = new Factura[tamano];
						int a=0;
						Iterator it = categories.iterator();
						 while(it.hasNext())
						 {
							 Entity datoFactura = (Entity)it.next();
							 Factura Temp = new Factura();
							 Temp.setMovDocumento(datoFactura.getString("movdocumento"));
							 Temp.setMovFecha(datoFactura.getString("movfecha"));
							 Temp.setMovHora(datoFactura.getString("movhora"));
							 Temp.setMovTipo(Integer.parseInt(datoFactura.getString("movtipo")));
							 Temp.setMovAnulado(Boolean.parseBoolean(datoFactura.getString("movanulado")));
							 Temp.setMovEstatus(Integer.parseInt(datoFactura.getString("movestatus")));
							 Temp.setMovTotal(Float.parseFloat(datoFactura.getString("movtotal")));
							 Temp.setMovEfectivo(Float.parseFloat(datoFactura.getString("movefectivo")));
							 Temp.setMovCheque(Float.parseFloat(datoFactura.getString("movcheque")));
							 Temp.setMovCredito(Float.parseFloat(datoFactura.getString("movcredito")));
							 Temp.setCliCodigo(datoFactura.getString("clicodigo"));
							 Temp.setFactura(datoFactura.getString("factura"));
							 Temp.setMovFacturado(Boolean.parseBoolean(datoFactura.getString("movfacturado")));
							 Temp.setPrimerDato(datoFactura.getString("primerdato"));
							 Temp.setMovNotas(datoFactura.getString("movnotas"));
							 Temp.setMovCerrado(Integer.parseInt(datoFactura.getString("movcerrado")));
							 Temp.setCajaCodigo(Integer.parseInt(datoFactura.getString("cajacodigo")));
							 Temp.setMovPagado(Float.parseFloat(datoFactura.getString("movPagado")));
							 Temp.setPagoDocumento(datoFactura.getString("pagodocumento"));
							 Temp.setMovFechaVence(datoFactura.getString("movfechavence"));
							 Temp.setCierreMes(Integer.parseInt(datoFactura.getString("cierremes")));
							 Temp.setVendedor(datoFactura.getString("vendedor"));
							 Temp.setRuta(datoFactura.getString("ruta"));
							 Temp.setIdRepartidor(datoFactura.getString("idrepartidor"));
							 Temp.setFacturaImpresa(datoFactura.getString("facturaimpresa"));
							 Temp.setFacturaRechazo(datoFactura.getString("facturarechazo"));
							 
							 factura1[a]=Temp;
							 a++;
							 						 
						 }
						 return factura;
					}
					
				}
				catch(Exception msj){
					msj.printStackTrace();
					return null;
				}
			}
			
			//guarda detallefactura
			public void guardadetallefactura(Context context, DetalleFactura[] detallefactura)
			{
				try
				{
					int a = detallefactura.length;
					 for(int b=0; b<a; b++)
					 {
						 DataFramework.getInstance().open(context, "com.researchmobile.todoterreno.facturacion.view");
						 Entity datodetallefactura = new Entity("detallefactura");
						 datodetallefactura.setValue("id", detallefactura[b].getId());
						 datodetallefactura.setValue("movdocumento", detallefactura[b].getMovDocumento());
						 datodetallefactura.setValue("artcodigo", detallefactura[b].getArtCodigo());
						 datodetallefactura.setValue("artunidadesfardo",detallefactura[b].getArtUnidadesFardo());
						 datodetallefactura.setValue("movunidades", detallefactura[b].getMovUnidades());
						 datodetallefactura.setValue("movunidadesentregadas", detallefactura[b].getMovUnidadesEntregadas());
						 datodetallefactura.setValue("movprecio", detallefactura[b].getMovPrecio());
						 datodetallefactura.setValue("movfechaentregar",detallefactura[b].getMovFechaEntregar());
						 datodetallefactura.setValue("cajacodigo", detallefactura[b].getCajaCodigo());
						 datodetallefactura.setValue("mocostoultimo",detallefactura[b].getMocostoultimo());
						 datodetallefactura.setValue("movunidevueltos", detallefactura[b].getMovUnidevueltos());
						 datodetallefactura.setValue("movnotas", detallefactura[b].getMovNotas());
						 datodetallefactura.setValue("vendedor",detallefactura[b].getVendedor());
						 datodetallefactura.setValue("ruta",detallefactura[b].getRuta());
						 datodetallefactura.setValue("idrepartidor",detallefactura[b].getIdRepartidor());
						 datodetallefactura.save();
					 }	 
						 
					 }
						catch(Exception msj)
						{
							msj.printStackTrace();
				}
			}
	}

