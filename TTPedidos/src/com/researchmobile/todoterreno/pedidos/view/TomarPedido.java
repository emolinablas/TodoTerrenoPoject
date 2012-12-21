package com.researchmobile.todoterreno.pedidos.view;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.researchmobile.todoterreno.pedidos.entity.DetallePedido;
import com.researchmobile.todoterreno.pedidos.entity.EncabezadoPedido;
import com.researchmobile.todoterreno.pedidos.entity.Pedido;
import com.researchmobile.todoterreno.pedidos.utility.Fecha;
import com.researchmobile.todoterreno.pedidos.utility.FormatDecimal;
import com.researchmobile.todoterreno.pedidos.utility.Mensaje;
import com.researchmobile.todoterreno.pedidos.ws.Peticion;

public class TomarPedido extends Activity implements TextWatcher, OnItemClickListener, OnClickListener, OnKeyListener {
	
	private ProgressDialog pd = null;
	private TextView totalGeneralTextView;
	private EditText buscarEditText;
	private ImageButton borrarImageButton;
	private ArrayList<HashMap<String, String>> articulosHashMap;
	private SimpleAdapter simpleAdapter;
	private ListView articulosListView;
	private Peticion peticion;
	private Pedido pedido;
	private float total;
	private DetallePedido articuloSeleccionado;
	private EncabezadoPedido encabezado;
	private Fecha fecha;
	private Mensaje mensaje;
	private int numeroPedido;
	private String codigoCliente;
	private String ruta;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tomar_pedido);
		Bundle bundle = (Bundle)getIntent().getExtras();
		setCodigoCliente((String)bundle.getString("codigoCliente"));
		setRuta((String)bundle.getString("idRuta"));
		setMensaje(new Mensaje());
		setTotal(0);
		setFecha(new Fecha());
		setTotalGeneralTextView((TextView)findViewById(R.id.tomar_pedido_total_textview));
		getTotalGeneralTextView().setText(String.valueOf(getTotal()));
		setBuscarEditText((EditText)findViewById(R.id.tomar_pedido_buscar_edittext));
		setBorrarImageButton((ImageButton)findViewById(R.id.tomar_pedido_borrar_imagebutton));
		setArticulosListView((ListView)findViewById(R.id.tomar_pedido_lista_productos_listview));
		setPeticion(new Peticion());
		setPedido(new Pedido());
		
		getBuscarEditText().setOnKeyListener(this);
		getBorrarImageButton().setOnClickListener(this);
		getArticulosListView().setOnItemClickListener(this);
		
		new articulosAsync().execute("");
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		
	}	
	
	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public DetallePedido seleccionaArticulo(String codigoProducto){
		DetallePedido articulo = new DetallePedido();
		articulo = getPeticion().buscaArticulo(TomarPedido.this, codigoProducto);
		return articulo;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		   @SuppressWarnings("unchecked")
           HashMap<String, String> selected = (HashMap<String, String>) getSimpleAdapter().getItem(position);
           String codigoProducto = selected.get("codigoProducto");
           setArticuloSeleccionado(seleccionaArticulo(codigoProducto));
           agregarDialog();
           
    }
	
	/**
	 * MENU
	 */

	// To change item text dynamically
	public boolean onPrepareOptionsMenu(Menu menu) {
		
		return true;
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.tomar_pedido_menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.tomar_pedido_ver_pedido_opcion:
			//setControlMenu(false);
			//VerPedido();
			return true;
		
		case R.id.tomar_pedido_agregar_opcion:
			//setControlMenu(true);
			//AgregarArticulo();
			return true;
			
		case R.id.tomar_pedido_cancelar_opcion:
			cancelarPedido();
			return true;
		case R.id.tomar_pedido_enviar_opcion:
			enviarPedido();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public void enviarPedido(){
		new AlertDialog.Builder(this)
        .setTitle("Enviar Pedido")
        .setMessage("�Esta seguro de enviar el pedido?")
        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                     terminarPedido();
                     Intent intent = new Intent(TomarPedido.this, Rol.class);
                     startActivity(intent);
                }
        })
        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                     
                }
        })
        .show();
	}
	
	public void terminarPedido(){
		llenaEncabezado();
		getPeticion().insertaEncabezado(this, getEncabezado());
		getPeticion().enviarPedido(this, getEncabezado(), getNumeroPedido(), getRuta());
		
	}
	
	public void llenaEncabezado(){
		setEncabezado(new EncabezadoPedido());
		getEncabezado().setCodigoCliente(getCodigoCliente());
		getEncabezado().setCodigoPedidoTemp(String.valueOf(getNumeroPedido()));
		getEncabezado().setCredito("0");
		getEncabezado().setFecha(getFecha().FechaHoy());
		getEncabezado().setHora(getFecha().Hora());
		getEncabezado().setTotal(getTotal());
	}
	
	public void cancelarPedido(){
		new AlertDialog.Builder(this)
        .setTitle("Cancelar Pedido")
        .setMessage("�Esta seguro de cancelar el pedido?")
        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                     finish();
                }
        })
        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                     
                }
        })
        .show();

	}

	private void agregarDialog() {
				
		LayoutInflater factory = LayoutInflater.from(TomarPedido.this);            
     
		final View textEntryView = factory.inflate(R.layout.tomar_pedido_dialog, null);
		
		final EditText cajaEditText = (EditText) textEntryView.findViewById(R.id.tomarpedido_dialog_caja_edittext);
		final EditText unidadEditText = (EditText) textEntryView.findViewById(R.id.tomarpedido_dialog_unidad_edittext);
		final ImageButton eliminarImageButton = (ImageButton) textEntryView.findViewById(R.id.tomar_pedido_dialog_eliminar_imagebutton);
		final TextView precioTextViewDialog = (TextView) textEntryView.findViewById(R.id.precioTextViewDialog);
		
		cajaEditText.setText(String.valueOf(getArticuloSeleccionado().getCaja()));
		unidadEditText.setText(String.valueOf(getArticuloSeleccionado().getUnidad()));
		precioTextViewDialog.setText(String.valueOf(getArticuloSeleccionado().getPrecio()));
			
		FormatDecimal formatDecimal = new FormatDecimal();
		
		final AlertDialog.Builder alert = new AlertDialog.Builder(TomarPedido.this);
		alert.setTitle(getArticuloSeleccionado().getNombre());
		alert.setView(textEntryView);
		alert.setPositiveButton("   OK   ", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int whichButton) {
			insertaDetalle();
			actualizaTotalGeneral();
		}

			private void insertaDetalle() {
				int cajas = Integer.parseInt(cajaEditText.getText().toString());
				int unidades = Integer.parseInt(unidadEditText.getText().toString());
				int totalUnidades = (unidades + (cajas * getArticuloSeleccionado().getUnidadesFardo()));
				float subTotal = totalUnidades * getArticuloSeleccionado().getPrecio();
				
				getArticuloSeleccionado().setCaja(cajas);
				getArticuloSeleccionado().setUnidad(unidades);
				getArticuloSeleccionado().setTotalUnidades(totalUnidades);
				getArticuloSeleccionado().setSubTotal(subTotal);
				
				getPeticion().insertaArticuloTemp(TomarPedido.this, getArticuloSeleccionado(), getNumeroPedido());
			}
		
     });

     alert.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener()
     {
         public void onClick(DialogInterface dialog, int whichButton)
         {
         // Canceled.
         }
     });
     
     eliminarImageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cajaEditText.setText("");
				unidadEditText.setText("");
				
			}
		});
     
     alert.show();
		
	}

	protected void actualizaTotalGeneral() {
		float totalActual = getTotal();
		float subTotal = getArticuloSeleccionado().getSubTotal();
		float nuevoTotal = totalActual + subTotal;
		setTotal(nuevoTotal);
		getTotalGeneralTextView().setText(String.valueOf(getTotal()));
		
	}

	@Override
	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		
	}
	
	public void buscaArticulos(){
		setArticulosHashMap(getPeticion().listaArticulos(TomarPedido.this));
		setNumeroPedido(getPeticion().numeroPedido(this));
		Log.e("TT", "numero pedido = " + getNumeroPedido());
	}
	
	public void llenaListaArticulos(){
		setSimpleAdapter( 
	        	new SimpleAdapter(this, 
	        					  getArticulosHashMap(),	
	        					  R.layout.fila_lista_productos,
	        					  new String[] {"codigoProducto",
	        									"nombreProducto", 
	        									"cajas",
												"unidades",
												"valor",
												"presentacion",
												"existencia",
												"bonificacion",
												"total"},
	        					  new int[] {R.id.fila_lista_producto_codigo_textview, 
	        								 R.id.fila_lista_producto_nombreProducto_textview, 
	        								 R.id.fila_lista_producto_cajas_textview,
	        								 R.id.fila_lista_producto_unidades_textview,
	        								 R.id.fila_lista_producto_precioi_textview,
	        								 R.id.fila_lista_producto_presentacion_textview,
	        								 R.id.fila_lista_producto_existencia_textview,
	        								 R.id.fila_lista_producto_bonificacion_textview,
	        								 R.id.fila_lista_producto_valor_textview}));
		getArticulosListView().setAdapter(getSimpleAdapter());
	}
	
	// Clase para ejecutar en Background
    class articulosAsync extends AsyncTask<String, Integer, Integer> {

          // Metodo que prepara lo que usara en background, Prepara el progress
          @Override
          protected void onPreExecute() {
                pd = ProgressDialog. show(TomarPedido.this, "VERIFICANDO DATOS", "ESPERE UN MOMENTO");
                pd.setCancelable( false);
         }

          // Metodo con las instrucciones que se realizan en background
          @Override
          protected Integer doInBackground(String... urlString) {
                try {
                	buscaArticulos();
                	

               } catch (Exception exception) {

               }
                return null ;
         }

          // Metodo con las instrucciones al finalizar lo ejectuado en background
          protected void onPostExecute(Integer resultado) {
                pd.dismiss();
                llenaListaArticulos();

         }

   }

	public EditText getBuscarEditText() {
		return buscarEditText;
	}

	public void setBuscarEditText(EditText buscarEditText) {
		this.buscarEditText = buscarEditText;
	}

	public ImageButton getBorrarImageButton() {
		return borrarImageButton;
	}

	public void setBorrarImageButton(ImageButton borrarImageButton) {
		this.borrarImageButton = borrarImageButton;
	}

	public ArrayList<HashMap<String, String>> getArticulosHashMap() {
		return articulosHashMap;
	}

	public void setArticulosHashMap(
			ArrayList<HashMap<String, String>> articulosHashMap) {
		this.articulosHashMap = articulosHashMap;
	}

	public SimpleAdapter getSimpleAdapter() {
		return simpleAdapter;
	}

	public void setSimpleAdapter(SimpleAdapter simpleAdapter) {
		this.simpleAdapter = simpleAdapter;
	}

	public ListView getArticulosListView() {
		return articulosListView;
	}

	public void setArticulosListView(ListView articulosListView) {
		this.articulosListView = articulosListView;
	}

	public Peticion getPeticion() {
		return peticion;
	}

	public void setPeticion(Peticion peticion) {
		this.peticion = peticion;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public TextView getTotalGeneralTextView() {
		return totalGeneralTextView;
	}

	public void setTotalGeneralTextView(TextView totalGeneralTextView) {
		this.totalGeneralTextView = totalGeneralTextView;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public DetallePedido getArticuloSeleccionado() {
		return articuloSeleccionado;
	}

	public void setArticuloSeleccionado(DetallePedido articuloSeleccionado) {
		this.articuloSeleccionado = articuloSeleccionado;
	}

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}

	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

	public EncabezadoPedido getEncabezado() {
		return encabezado;
	}

	public void setEncabezado(EncabezadoPedido encabezado) {
		this.encabezado = encabezado;
	}

	public int getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
}
