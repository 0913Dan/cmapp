package com.cmapp.test;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class fragmentReplaceActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		FragmentManager fm=getFragmentManager();
		FragmentTransaction ft =fm.beginTransaction();
		nicknameFragment nickf=new nicknameFragment();
		nameFragment nf=new nameFragment();
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragmentreplace);
		
		Button btName=(Button)findViewById(R.id.btFragmentName);
		Button btNickName=(Button)findViewById(R.id.btFragmentNickName);
		btName.setOnClickListener(btListener);
		btNickName.setOnClickListener(btListener);
		
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                0,LayoutParams.MATCH_PARENT, 1.0f);
		
		RelativeLayout thisLayout =(RelativeLayout)findViewById(R.id.fragment_view);
		thisLayout.setLayoutParams(param);
		ft.add(R.id.fragment_view, nf);
		ft.commit();
		
	}

	private Button.OnClickListener btListener =new Button.OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			
			FragmentManager fm=getFragmentManager();
			FragmentTransaction ft =fm.beginTransaction();
			nicknameFragment nickf=new nicknameFragment();
			nameFragment nf=new nameFragment();
			if(v.getId()==R.id.btFragmentName){
				ft.replace(R.id.fragment_view, nf);
			}
			if(v.getId()==R.id.btFragmentNickName){
				
				ft.replace(R.id.fragment_view, nickf);
				
			}
			ft.commit();
		}
		
		
	};
}