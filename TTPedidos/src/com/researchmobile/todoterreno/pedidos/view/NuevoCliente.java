package com.researchmobile.todoterreno.pedidos.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.researchmobile.todoterreno.pedidos.entity.Categoria;
import com.researchmobile.todoterreno.pedidos.entity.ClienteNuevo;
import com.researchmobile.todoterreno.pedidos.entity.ListaCategoria;
import com.researchmobile.todoterreno.pedidos.entity.RespuestaWS;
import com.researchmobile.todoterreno.pedidos.entity.Vendedor;
import com.researchmobile.todoterreno.pedidos.utility.Fecha;
import com.researchmobile.todoterreno.pedidos.ws.Peticion;

public class NuevoCliente extends Activity implements OnClickListener, OnKeyListener{
	//nit, nombreNegocio, nombreContacto, telefono, direccion

	private EditText nombreNegocioEditTExt;
	private EditText nitEditText;
	private EditText contactoEditText;
	private EditText telefonoEditText;
	private EditText direccionEditText;
	private TextView codigoTextView;
	private Button crearButton;
	private Button cancelarButton;
	private Spinner categoriaSpinner;
	private CheckBox lunesCheckBox;
	private CheckBox martesCheckBox;
	private CheckBox miercolesCheckBox;
	private CheckBox juevesCheckBox;
	private CheckBox viernesCheckBox;
	private CheckBox sabadoCheckBox;
	
	private ListaCategoria listaCategoria;
	private ProgressDialog pd = null;
	private ArrayAdapter<Categoria> categoriaAdapter;
	private Peticion peticion = new Peticion();
	private RespuestaWS respuestaWS = new RespuestaWS();
	private String codigoNuevoCliente;
	private String ruta;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo);
        
        setNombreNegocioEditTExt((EditText)findViewById(R.id.nuevo_nombre_edittext));
        setNitEditText((EditText)findViewById(R.id.nuevo_nit_edittext));
        setContactoEditText((EditText)findViewById(R.id.nuevo_contacto_edittext));
        setTelefonoEditText((EditText)findViewById(R.id.nuevo_telefono_edittext));
        setDireccionEditText((EditText)findViewById(R.id.nuevo_direccion_edittext));
        setCodigoTextView((TextView)findViewById(R.id.nuevo_codigo_textview));
        
        getNombreNegocioEditTExt().setOnKeyListener(this);
        getNitEditText().setOnKeyListener(this);
        getContactoEditText().setOnKeyListener(this);
        getTelefonoEditText().setOnKeyListener(this);
        getDireccionEditText().setOnKeyListener(this);
        
        setCrearButton((Button)findViewById(R.id.nuevo_crear_button));
        setCancelarButton((Button)findViewById(R.id.nuevo_cancelar_button));
        getCrearButton().setOnClickListener(this);
        getCancelarButton().setOnClickListener(this);
        
        setLunesCheckBox((CheckBox)findViewById(R.id.nuevo_lunes_checkBox));
        setMartesCheckBox((CheckBox)findViewById(R.id.nuevo_martes_checkBox));
        setMiercolesCheckBox((CheckBox)findViewById(R.id.nuevo_miercoles_checkBox));
        setJuevesCheckBox((CheckBox)findViewById(R.id.nuevo_jueves_checkBox));
        setViernesCheckBox((CheckBox)findViewById(R.id.nuevo_viernes_checkBox));
        setSabadoCheckBox((CheckBox)findViewById(R.id.nuevo_sabado_checkBox));
        
        setCategoriaSpinner((Spinner)findViewById(R.id.nuevo_categoria_spinner));
        
        Vendedor vendedor = new Vendedor();
		vendedor = peticion.vendedor(NuevoCliente.this);
        setRuta(vendedor.getRuta());
        
        Fecha fecha = new Fecha();
        setCodigoNuevoCliente(""+fecha.diaAnio()+getRuta()+"-"+fecha.fechaUnida());
        getCodigoTextView().setText(getCodigoNuevoCliente());
        new categoriasAsync().execute("");
        
        
    }

	@Override
	public void onClick(View view) {
		if (view == getCrearButton()){
			new crearAsync().execute("");
		}else if (view == getCancelarButton()){
			
		}
	}

	 @Override
     public boolean onKey(View view, int keyCode, KeyEvent event)
     {
         if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP)
         {
        	 if (view == getNombreNegocioEditTExt()){
        		 getNitEditText().requestFocus();
        	 }else if (view == getNitEditText()){
        		 getContactoEditText().requestFocus();
        	 }else if (view == getContactoEditText()){
        		 getTelefonoEditText().requestFocus();
        	 }else if (view == getTelefonoEditText()){
        		 getDireccionEditText().requestFocus();
        	 }else if (view == getDireccionEditText()){
        		 InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                 imm.hideSoftInputFromWindow(getDireccionEditText().getWindowToken(), 0);
               return true;

        	 }
             //editText2.requestFocus();//TODO: When the enter key is released
             return true;
         }
         if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)
         {
             //TODO: When the enter key is pressed
             return true;
         }
         return false;
     }
	 
	 @SuppressWarnings("null")
	public RespuestaWS enviarDatos(){
		 
		 RespuestaWS res = new RespuestaWS();
		 
		 ClienteNuevo cliente = new ClienteNuevo();
		 
		 String codigo = getCodigoTextView().getText().toString();
		 String nombre = getNombreNegocioEditTExt().getText().toString();
		 String nit = getNitEditText().getText().toString();
		 String contacto = getNombreNegocioEditTExt().getText().toString();
		 String telefono = getTelefonoEditText().getText().toString();
		 String direccion = getDireccionEditText().getText().toString();
		 Categoria categoria = (Categoria)getCategoriaSpinner().getSelectedItem();
		 
		 
		 boolean lunes = getLunesCheckBox().isChecked();
		 boolean martes = getMartesCheckBox().isChecked();
		 boolean miercoles = getMiercolesCheckBox().isChecked();
		 boolean jueves = getJuevesCheckBox().isChecked();
		 boolean viernes = getViernesCheckBox().isChecked();
		 boolean sabado = getSabadoCheckBox().isChecked();
		 
		 String dia = new String();
		 
		 if (lunes) {dia = dia + "L";};
		 if (martes) {dia = dia + "M";};
		 if (miercoles) {dia = dia + "K";};
		 if (jueves) {dia = dia + "J";};
		 if (viernes) {dia = dia + "V";};
		 if (sabado) {dia = dia + "S";};
		 
		 if (codigo.equalsIgnoreCase("") || nombre.equalsIgnoreCase("") || nit.equalsIgnoreCase("") || contacto.equalsIgnoreCase("") || telefono.equalsIgnoreCase("") || direccion.equalsIgnoreCase("")){
			 res.setMensaje("Debe llenar todos los campos");
			 res.setResultado(false);
			 return res;
		 }
		 
		 if (dia.equalsIgnoreCase("")){
			 res.setMensaje("Debe marcar al menos un día");
			 res.setResultado(false);
			 return res;
		 }else{
			 
			 cliente.setIdNuevoCliente(codigo);
			 cliente.setNombreNegocio(nombre);
			 cliente.setNit(nit);
			 cliente.setNombreContacto(contacto);
			 cliente.setTelefono(telefono);
			 cliente.setDireccion(direccion);
			 
			 //cliente.setCategoriaCliente(categoria.getIdCategoria());
			 cliente.setRuta(getRuta());
			 cliente.setDiaVisita(dia);
			 res = peticion.enviarNuevoCliente(NuevoCliente.this, cliente);
		 }
		 return res;
	 }
	 
	 
	// Clase para ejecutar en Background
	class crearAsync extends AsyncTask<String, Integer, Integer> {

		// Metodo que prepara lo que usara en background, Prepara el progress
		@Override
		protected void onPreExecute() {
			pd = ProgressDialog.show(NuevoCliente.this, "GUARDANDO DATOS", "ESPERE UN MOMENTO");
			pd.setCancelable(false);
		}

		// Metodo con las instrucciones que se realizan en background
		@Override
		protected Integer doInBackground(String... urlString) {
			try {
				respuestaWS = enviarDatos();
			} catch (Exception exception) {

			}
			return null;
		}

		// Metodo con las instrucciones al finalizar lo ejectuado en background
		protected void onPostExecute(Integer resultado) {
			pd.dismiss();
			//if (respuestaWS.isResultado()){
				//finish();
			//}else{
				//Toast.makeText(NuevoCliente.this, respuestaWS.getMensaje(), Toast.LENGTH_SHORT).show();
			//}
		}
		
	}
	
	// Clase para ejecutar en Background
    class categoriasAsync extends AsyncTask<String, Integer, Integer> {

          // Metodo que prepara lo que usara en background, Prepara el progress
          @Override
          protected void onPreExecute() {
                pd = ProgressDialog. show(NuevoCliente.this, "CARGANDO CATEGORIAS", "ESPERE UN MOMENTO");
                pd.setCancelable( false);
         }

          // Metodo con las instrucciones que se realizan en background
          @Override
          protected Integer doInBackground(String... urlString) {
                try {
                	buscarCategorias();
               } catch (Exception exception) {

               }
                return null ;
         }

          // Metodo con las instrucciones al finalizar lo ejectuado en background
          protected void onPostExecute(Integer resultado) {
                pd.dismiss();
                try{
                	if (getListaCategoria().getCategoria().length > 0 || getListaCategoria().getCategoria() != null){
        				fillDataSpinner();
        			}
                }catch(Exception exception){
                	Log.e("TT", "error al cargar las categorias");
                }
    			
         }

   }

	
	private void fillDataSpinner(){
		Categoria[] cat = getListaCategoria().getCategoria();
		if (cat != null){
			setCategoriaAdapter(new ArrayAdapter<Categoria>(NuevoCliente.this, R.layout.item_spinner, R.id.item_spinner_textview, cat));
			getCategoriaSpinner().setAdapter(getCategoriaAdapter());
		}
	}
	
	public void buscarCategorias(){
		
		setListaCategoria(peticion.buscaCategoria(NuevoCliente.this));
	}

	public EditText getNombreNegocioEditTExt() {
		return nombreNegocioEditTExt;
	}

	public void setNombreNegocioEditTExt(EditText nombreNegocioEditTExt) {
		this.nombreNegocioEditTExt = nombreNegocioEditTExt;
	}

	public EditText getNitEditText() {
		return nitEditText;
	}

	public void setNitEditText(EditText nitEditText) {
		this.nitEditText = nitEditText;
	}

	public EditText getContactoEditText() {
		return contactoEditText;
	}

	public void setContactoEditText(EditText contactoEditText) {
		this.contactoEditText = contactoEditText;
	}

	public EditText getTelefonoEditText() {
		return telefonoEditText;
	}

	public void setTelefonoEditText(EditText telefonoEditText) {
		this.telefonoEditText = telefonoEditText;
	}

	public EditText getDireccionEditText() {
		return direccionEditText;
	}

	public void setDireccionEditText(EditText direccionEditText) {
		this.direccionEditText = direccionEditText;
	}

	public TextView getCodigoTextView() {
		return codigoTextView;
	}

	public void setCodigoTextView(TextView codigoTextView) {
		this.codigoTextView = codigoTextView;
	}

	public Button getCrearButton() {
		return crearButton;
	}

	public void setCrearButton(Button crearButton) {
		this.crearButton = crearButton;
	}

	public Button getCancelarButton() {
		return cancelarButton;
	}

	public void setCancelarButton(Button cancelarButton) {
		this.cancelarButton = cancelarButton;
	}

	public ListaCategoria getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(ListaCategoria listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public Spinner getCategoriaSpinner() {
		return categoriaSpinner;
	}

	public void setCategoriaSpinner(Spinner categoriaSpinner) {
		this.categoriaSpinner = categoriaSpinner;
	}

	public CheckBox getLunesCheckBox() {
		return lunesCheckBox;
	}

	public void setLunesCheckBox(CheckBox lunesCheckBox) {
		this.lunesCheckBox = lunesCheckBox;
	}

	public CheckBox getMartesCheckBox() {
		return martesCheckBox;
	}

	public void setMartesCheckBox(CheckBox martesCheckBox) {
		this.martesCheckBox = martesCheckBox;
	}

	public CheckBox getMiercolesCheckBox() {
		return miercolesCheckBox;
	}

	public void setMiercolesCheckBox(CheckBox miercolesCheckBox) {
		this.miercolesCheckBox = miercolesCheckBox;
	}

	public CheckBox getJuevesCheckBox() {
		return juevesCheckBox;
	}

	public void setJuevesCheckBox(CheckBox juevesCheckBox) {
		this.juevesCheckBox = juevesCheckBox;
	}

	public CheckBox getViernesCheckBox() {
		return viernesCheckBox;
	}

	public void setViernesCheckBox(CheckBox viernesCheckBox) {
		this.viernesCheckBox = viernesCheckBox;
	}

	public CheckBox getSabadoCheckBox() {
		return sabadoCheckBox;
	}

	public void setSabadoCheckBox(CheckBox sabadoCheckBox) {
		this.sabadoCheckBox = sabadoCheckBox;
	}

	public ArrayAdapter<Categoria> getCategoriaAdapter() {
		return categoriaAdapter;
	}

	public void setCategoriaAdapter(ArrayAdapter<Categoria> categoriaAdapter) {
		this.categoriaAdapter = categoriaAdapter;
	}

	public String getCodigoNuevoCliente() {
		return codigoNuevoCliente;
	}

	public void setCodigoNuevoCliente(String codigoNuevoCliente) {
		this.codigoNuevoCliente = codigoNuevoCliente;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
}
