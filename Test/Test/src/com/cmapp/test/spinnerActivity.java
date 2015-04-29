package com.cmapp.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class spinnerActivity extends Activity {
	private String[] select=new String[]{"A","B","C","D"};
	private Spinner spinner,spinnerR;
	private TextView spText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner);
		spinner=(Spinner)findViewById(R.id.btSpinner);
		spinnerR=(Spinner)findViewById(R.id.btSpinnerR);
		spText=(TextView)findViewById(R.id.spinnerText);
		
		ArrayAdapter<String> adapterSelect= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,select);
		ArrayAdapter<CharSequence> adapterSelectR= ArrayAdapter.createFromResource(this,R.array.spinnerSelect,R.layout.spinner_drop);
		
		adapterSelect.setDropDownViewResource(android.R.layout.simple_spinner_item);
		adapterSelectR.setDropDownViewResource(R.layout.spinner_drop);
		spinner.setAdapter(adapterSelect);
		spinnerR.setAdapter(adapterSelectR);
		
		spinner.setOnItemSelectedListener(spListener);
		spinnerR.setOnItemSelectedListener(spListener);
		
	}
	
	private Spinner.OnItemSelectedListener spListener =new Spinner.OnItemSelectedListener(){

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			spText.setText(parent.getSelectedItem().toString());
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
		
	};
}
