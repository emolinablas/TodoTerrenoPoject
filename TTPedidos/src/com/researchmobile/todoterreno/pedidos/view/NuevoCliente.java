package com.researchmobile.todoterreno.pedidos.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.Spinner;

public class NuevoCliente extends Activity implements OnClickListener, OnKeyListener{
	//nit, nombreNegocio, nombreContacto, telefono, direccion

	private EditText nombreNegocioEditTExt;
	private EditText nitEditText;
	private EditText contactoEditText;
	private EditText telefonoEditText;
	private EditText direccionEditText;
	
	private Spinner categoriaSpinner;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo);
	
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	 @Override
     public boolean onKey(View v, int keyCode, KeyEvent event)
     {
         if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP)
         {
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
}
