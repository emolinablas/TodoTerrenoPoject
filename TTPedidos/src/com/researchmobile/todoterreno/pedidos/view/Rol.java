package com.researchmobile.todoterreno.pedidos.view;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
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

import com.researchmobile.todoterreno.pedidos.entity.ListaClientes;
import com.researchmobile.todoterreno.pedidos.utility.Fecha;
import com.researchmobile.todoterreno.pedidos.ws.Peticion;

public class Rol extends Activity implements OnClickListener, OnKeyListener, TextWatcher, OnItemClickListener{
	
private ProgressDialog pd = null;
	
	private ImageButton borrarImageButton;
	private EditText buscarEditText;
	private TextView semanaTextView;
	private TextView diaTextView;
	private SimpleAdapter simpleAdapter;
	private ListView clientesListView;
	private ListaClientes listaClientes;
	private Peticion peticion;
	private boolean visitados;
	private Fecha fecha;
	private ArrayList<HashMap<String, String>> clientesPendientesHashMap;
	private ArrayList<HashMap<String, String>> clientesVisitadosHashMap;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rol);
        setPeticion(new Peticion());
        setFecha(new Fecha());
        setBuscarEditText((EditText)findViewById(R.id.rol_buscar_editText));
        getBuscarEditText().addTextChangedListener(this);
        
        setBorrarImageButton((ImageButton)findViewById(R.id.rol_borrar_imagebutton));
        getBorrarImageButton().setOnClickListener(this);

        setSemanaTextView((TextView)findViewById(R.id.rol_semana_textview));
        setDiaTextView((TextView)findViewById(R.id.rol_dia_textview));
        getSemanaTextView().setText("Semana " + String.valueOf(getFecha().semanaAnio()));
        getDiaTextView().setText(getFecha().Dia());
        
        setClientesListView((ListView)findViewById(R.id.rol_lista_clientes_listView));
        getClientesListView().setOnItemClickListener(this);
        setVisitados(false);
        
        new ClientesPendientesAsync().execute("");
        
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		   @SuppressWarnings("unchecked")
           HashMap<String, String> selected = (HashMap<String, String>) getSimpleAdapter().getItem(position);
           String idCliente = selected.get("codigoCliente");
           detalleActivity(idCliente);
    }
	
	public void detalleActivity(String idCliente){
		Intent intent = new Intent(Rol.this, DetalleCliente.class);
		intent.putExtra("codigoCliente", idCliente);
		startActivity(intent);
	}
	
	@Override
	public boolean onKey(View view, int keyCode, KeyEvent event) {
		if (view == getBorrarImageButton()){
			getBuscarEditText().setText("");
		}
		return false;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void afterTextChanged(Editable s) {
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
	
	// Buscar Clientes Pendientes
	class ClientesPendientesAsync extends AsyncTask<String, Integer, Integer> {

		// Metodo que prepara lo que usara en background, Prepara el progress
		@Override
		protected void onPreExecute() {
			
			pd = ProgressDialog.show(Rol.this, "VERIFICANDO DATOS", "ESPERE UN MOMENTO");
			pd.setCancelable(false);
		}

		// Metodo con las instrucciones que se realizan en background
		@Override
		protected Integer doInBackground(String... urlString) {
			try {
				cargarClientesPendientes();
			
			} catch (Exception exception) {

			}
			return null;
		}

		private void llenaListaPendientes() {
			
			setSimpleAdapter(new SimpleAdapter(Rol.this, 
					getClientesPendientesHashMap(), 
					R.layout.fila_lista_clientes,
					new String[] {"codigoCliente",
						"empresa",
						"contacto",
						"direccion",
						"telefono",
						"nit"},
					new int[] {R.id.fila_lista_clientes_codigo_cliente_textview,
					 R.id.fila_lista_clientes_empresa_textview, 
						 R.id.fila_lista_clientes_contacto_textview,
						 R.id.fila_lista_clientes_codigo_direccion_textview,
						 R.id.fila_lista_clientes_telefono_textview,
						 R.id.fila_lista_clientes_nit_textview}));
			getClientesListView().setAdapter(getSimpleAdapter());
			Log.e("TT", "async");
		}

		// Metodo con las instrucciones al finalizar lo ejectuado en background
		protected void onPostExecute(Integer resultado) {
			pd.dismiss();
			llenaListaPendientes();
		}
	}
	
	// Buscar Clientes Visitados
	class ClientesVisitadosAsync extends AsyncTask<String, Integer, Integer> {

		// Metodo que prepara lo que usara en background, Prepara el progress
		@Override
		protected void onPreExecute() {
			pd = ProgressDialog.show(Rol.this, "VERIFICANDO DATOS", "ESPERE UN MOMENTO");
			pd.setCancelable(false);
		}

		// Metodo con las instrucciones que se realizan en background
		@Override
		protected Integer doInBackground(String... urlString) {
			try {
				cargarClientesVisitados();
			} catch (Exception exception) {
				

			}
			return null;
		}

		// Metodo con las instrucciones al finalizar lo ejectuado en background
		protected void onPostExecute(Integer resultado) {
			pd.dismiss();
			llenaListaVisitados();
			
		}
		
		
	}
	
	private void llenaListaVisitados() {
		setSimpleAdapter(new SimpleAdapter(Rol.this, 
				getClientesVisitadosHashMap(), 
				R.layout.fila_lista_clientes,
				new String[] {"codigoCliente",
					"empresa",
					"contacto",
					"direccion",
					"telefono",
					"nit"},
				new int[] {R.id.fila_lista_clientes_codigo_cliente_textview,
				 R.id.fila_lista_clientes_empresa_textview, 
					 R.id.fila_lista_clientes_contacto_textview,
					 R.id.fila_lista_clientes_codigo_direccion_textview,
					 R.id.fila_lista_clientes_telefono_textview,
					 R.id.fila_lista_clientes_nit_textview}));
		getClientesListView().setAdapter(getSimpleAdapter());
		Log.e("TT", "async");
		
	}
	
	private void cargarClientesPendientes() {
		setClientesPendientesHashMap(getPeticion().ListaClientesPendientes(Rol.this));
		
	}
	
	private void cargarClientesVisitados() {
		setClientesVisitadosHashMap(getPeticion().ListaClientesVisitados(Rol.this));
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
		inflater.inflate(R.menu.pedido_menu, menu);

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.pedido_menu_clientesvisitados_opcion:
			RellenarLista();

			return true;
		case R.id.pedido_menu_listapedidos_opcion:
			listaPedidosActivity();
			return true;
			
		case R.id.pedido_menu_salir_opcion:
			//Salir();
			return true;
			
		case R.id.pedido_menu_sincronizar_opcion:
			//Sincronizar();
			//Productos();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public void listaPedidosActivity(){
		Intent intent = new Intent(Rol.this, ListaPedidos.class);
		startActivity(intent);
	}

	private void RellenarLista() {
		if (isVisitados()){
			new ClientesPendientesAsync().execute("");
			setVisitados(false);
		}else{
			new ClientesVisitadosAsync().execute("");
			setVisitados(true);
		}
		
	}
	public ImageButton getBorrarImageButton() {
		return borrarImageButton;
	}
	public void setBorrarImageButton(ImageButton borrarImageButton) {
		this.borrarImageButton = borrarImageButton;
	}
	public EditText getBuscarEditText() {
		return buscarEditText;
	}
	public void setBuscarEditText(EditText buscarEditText) {
		this.buscarEditText = buscarEditText;
	}
	
	public TextView getSemanaTextView() {
		return semanaTextView;
	}
	public void setSemanaTextView(TextView semanaTextView) {
		this.semanaTextView = semanaTextView;
	}
	public TextView getDiaTextView() {
		return diaTextView;
	}
	public void setDiaTextView(TextView diaTextView) {
		this.diaTextView = diaTextView;
	}
	
	public SimpleAdapter getSimpleAdapter() {
		return simpleAdapter;
	}
	public void setSimpleAdapter(SimpleAdapter simpleAdapter) {
		this.simpleAdapter = simpleAdapter;
	}
	public ListView getClientesListView() {
		return clientesListView;
	}
	public void setClientesListView(ListView clientesListView) {
		this.clientesListView = clientesListView;
	}
	public ListaClientes getListaClientes() {
		return listaClientes;
	}
	public void setListaClientes(ListaClientes listaClientes) {
		this.listaClientes = listaClientes;
	}
	public Peticion getPeticion() {
		return peticion;
	}
	public void setPeticion(Peticion peticion) {
		this.peticion = peticion;
	}
	public boolean isVisitados() {
		return visitados;
	}
	public void setVisitados(boolean visitados) {
		this.visitados = visitados;
	}

	public ArrayList<HashMap<String, String>> getClientesPendientesHashMap() {
		return clientesPendientesHashMap;
	}

	public void setClientesPendientesHashMap(
			ArrayList<HashMap<String, String>> clientesPendientesHashMap) {
		this.clientesPendientesHashMap = clientesPendientesHashMap;
	}

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}

	public ArrayList<HashMap<String, String>> getClientesVisitadosHashMap() {
		return clientesVisitadosHashMap;
	}

	public void setClientesVisitadosHashMap(
			ArrayList<HashMap<String, String>> clientesVisitadosHashMap) {
		this.clientesVisitadosHashMap = clientesVisitadosHashMap;
	}

	
}
