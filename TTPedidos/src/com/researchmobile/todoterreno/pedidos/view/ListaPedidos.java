package com.researchmobile.todoterreno.pedidos.view;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.researchmobile.todoterreno.pedidos.ws.Peticion;

public class ListaPedidos extends Activity implements OnItemClickListener{
	
	private TextView totalGenralTextView;
	private TextView enviadosTextView;
	private TextView pendientesTextView;
	private ListView pedidosListView;
	private ArrayList<HashMap<String, String>> pedidosHashMap;
	private SimpleAdapter simpleAdapter;
	private Peticion peticion;
	private ProgressDialog pd = null;
	
	private float totalGeneral;
	private int enviados;
	private int pendientes;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listapedidos);
        
        setPeticion(new Peticion());
        setTotalGenralTextView((TextView)findViewById(R.id.listapedidos_totalvendido_textview));
        setEnviadosTextView((TextView)findViewById(R.id.listapedidos_enviados_textview));
        setPendientesTextView((TextView)findViewById(R.id.listapedidos_pendientes_textview));
        setPedidosListView((ListView)findViewById(R.id.listapedidos_listView));
        getPedidosListView().setOnItemClickListener(this);
        
        new pedidosAsync().execute("");
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
	
	// Clase para ejecutar en Background
    class pedidosAsync extends AsyncTask<String, Integer, Integer> {

          // Metodo que prepara lo que usara en background, Prepara el progress
          @Override
          protected void onPreExecute() {
                pd = ProgressDialog. show(ListaPedidos.this, "VERIFICANDO DATOS",
                            "ESPERE UN MOMENTO");
                pd.setCancelable( false);
         }

          // Metodo con las instrucciones que se realizan en background
          @Override
          protected Integer doInBackground(String... urlString) {
                try {
                	buscaPedidos();

               } catch (Exception exception) {

               }
                return null ;
         }

          // Metodo con las instrucciones al finalizar lo ejectuado en background
          protected void onPostExecute(Integer resultado) {
                pd.dismiss();
         }
   }
    
    public void buscaPedidos(){
    	setTotalGeneral(getPeticion().totalGeneral(this));
    	setEnviados(getPeticion().totalEnviados(this));
    	setPendientes(getPeticion().totalPendientes(this));
    	
    }


	public TextView getTotalGenralTextView() {
		return totalGenralTextView;
	}

	public void setTotalGenralTextView(TextView totalGenralTextView) {
		this.totalGenralTextView = totalGenralTextView;
	}

	public TextView getEnviadosTextView() {
		return enviadosTextView;
	}

	public void setEnviadosTextView(TextView enviadosTextView) {
		this.enviadosTextView = enviadosTextView;
	}

	public TextView getPendientesTextView() {
		return pendientesTextView;
	}

	public void setPendientesTextView(TextView pendientesTextView) {
		this.pendientesTextView = pendientesTextView;
	}

	public ListView getPedidosListView() {
		return pedidosListView;
	}

	public void setPedidosListView(ListView pedidosListView) {
		this.pedidosListView = pedidosListView;
	}

	public ArrayList<HashMap<String, String>> getPedidosHashMap() {
		return pedidosHashMap;
	}

	public void setPedidosHashMap(ArrayList<HashMap<String, String>> pedidosHashMap) {
		this.pedidosHashMap = pedidosHashMap;
	}

	public SimpleAdapter getSimpleAdapter() {
		return simpleAdapter;
	}

	public void setSimpleAdapter(SimpleAdapter simpleAdapter) {
		this.simpleAdapter = simpleAdapter;
	}

	public Peticion getPeticion() {
		return peticion;
	}

	public void setPeticion(Peticion peticion) {
		this.peticion = peticion;
	}

	public float getTotalGeneral() {
		return totalGeneral;
	}

	public void setTotalGeneral(float totalGeneral) {
		this.totalGeneral = totalGeneral;
	}

	public int getEnviados() {
		return enviados;
	}

	public void setEnviados(int enviados) {
		this.enviados = enviados;
	}

	public int getPendientes() {
		return pendientes;
	}

	public void setPendientes(int pendientes) {
		this.pendientes = pendientes;
	}
	
}
