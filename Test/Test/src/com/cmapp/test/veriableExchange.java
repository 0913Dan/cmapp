package com.cmapp.test;

import java.io.Console;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class veriableExchange extends Activity {
	EditText edString,edinteger;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.veriableexchange);
		Log.d("myLog", "start");
		edString=(EditText)findViewById(R.id.edVeriableString);
		edinteger=(EditText)findViewById(R.id.edVeriableInteger);
		Button btOK=(Button)findViewById(R.id.btveriable);
		
		btOK.setOnClickListener(btListener);
		
	}
	
	private Button.OnClickListener btListener =new Button.OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			Bundle bundle =new Bundle();
			intent.setClass(veriableExchange.this, veriableExchangeToActivity.class);
			bundle.putString("Name", edString.getText().toString());
			bundle.putInt("Number", Integer.parseInt(edinteger.getText().toString()));
			intent.putExtras(bundle);
			startActivity(intent);
		}
		
	};
}
