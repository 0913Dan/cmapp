package com.cmapp.test;


import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class popObject extends Activity {
	private int marginY=30;
	private int layoutCount=0;
	private int[] layoutId= {R.id.txt1,R.id.txt2,R.id.txt3};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popobject);
		
		Button btPop =(Button)findViewById(R.id.btPop);
		btPop.setOnClickListener(btListener);
		
		Button btSlide =(Button)findViewById(R.id.btSlide);
		btSlide.setOnClickListener(btListener);
		
		
		
		
	}
	private Button.OnClickListener btListener = new Button.OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId()==R.id.btPop){
				buildLayout();
			}
			if(v.getId()==R.id.btSlide){
				//for(int i=0;i<layoutCount;i++){
					TextView poptext=(TextView)findViewById(1234);
					poptext.setText("POP");
					RelativeLayout.LayoutParams textLayoutParams = (RelativeLayout.LayoutParams)poptext.getLayoutParams();
					textLayoutParams.setMargins(0,textLayoutParams.topMargin+200, 0, 0);
					//poptext.setLayoutParams(textLayoutParams);
				//}
			}
				
		}
		
		
	};
	
	public void buildLayout(){
		
		/*FrameLayout topView=new FrameLayout(getApplicationContext());
		FrameLayout.LayoutParams topLayoutParams=new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT,300);
		MarginLayoutParams mlp =(MarginLayoutParams)topLayoutParams;
		mlp.setMargins(50, marginY, 0, 0);
		topView.setLayoutParams(mlp);*/
		if(layoutCount<3){
			RelativeLayout thisLayout =(RelativeLayout)findViewById(R.id.popLayout);
			TextView textView=new TextView(getApplicationContext());
			RelativeLayout.LayoutParams textLayoutParams =new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,300);
			textView.setLayoutParams(textLayoutParams);
			textLayoutParams.setMargins(0, marginY, 0, 0);
			textView.setText("POP!!!");
			textView.setTextSize(60);
			textView.setId(1234);
			textView.setTextColor(Color.RED);
			thisLayout.addView(textView);
			marginY+=120;
			
		}
		//topView.addView(textView);
		//addContentView(topView, mlp); 
		
		
	}
}
