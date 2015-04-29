package com.cmapp.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class checkBoxActivity extends Activity {
	private CheckBox[] chk=new CheckBox[4];
	private TextView checkText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkbox);
		chk[0]=(CheckBox)findViewById(R.id.chk1);
		chk[1]=(CheckBox)findViewById(R.id.chk2);
		chk[2]=(CheckBox)findViewById(R.id.chk3);
		chk[3]=(CheckBox)findViewById(R.id.chk4);
		checkText=(TextView)findViewById(R.id.chkText);
		for(int i=0;i<4;i++){
			chk[i].setOnCheckedChangeListener(checkListener);
		}
	}
	
	public CheckBox.OnCheckedChangeListener checkListener= new CheckBox.OnCheckedChangeListener(){

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			String select="";
			for(int i=0;i<4;i++){
				if(chk[i].isChecked()){
					select+=chk[i].getText()+" ";
					
				}
			}
			
			checkText.setText("你勾選的是："+select);
		}
		
		
	};
	
}
