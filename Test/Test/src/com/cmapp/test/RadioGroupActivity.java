package com.cmapp.test;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.app.Activity;

public class RadioGroupActivity extends Activity {
	private RadioButton[] rb =new RadioButton[3];
	private int[] rbInt=new int[3];
	private RadioGroup rg;
	private TextView rgtext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.radiogroup);
		rb[0]=(RadioButton)findViewById(R.id.rd1);
		rb[1]=(RadioButton)findViewById(R.id.rd2);
		rb[2]=(RadioButton)findViewById(R.id.rd3);
		for(int i=0;i<3;i++)
			rbInt[i]=rb[i].getId();
		rg=(RadioGroup)findViewById(R.id.radiogroup);
		rgtext=(TextView)findViewById(R.id.rdText);
		rg.setOnCheckedChangeListener(checkListener);
	}	
	
	public RadioGroup.OnCheckedChangeListener checkListener=new RadioGroup.OnCheckedChangeListener(){

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			for(int i=0;i<3;i++){
				if(checkedId==rbInt[i]){
					rgtext.setText("你勾選的是："+rb[i].getText().toString());
				}
			}
			
		}
		
		
	};
}
