package com.cmapp.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class touchActivity extends Activity {
	TextView view;
	ViewGroup root;
	private int xPadding;
	private int yPadding;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.touch);
		
		 root = (ViewGroup)findViewById(R.id.touchView);

		   // view = new TextView(touchActivity.this);
		    view = (TextView)findViewById(R.id.tvTouch);
		  //  view.setText("TextView!!!!!!!!");

		   /* RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 50);
		    layoutParams.leftMargin = 50;
		    layoutParams.topMargin = 50;
		    layoutParams.bottomMargin = -250;
		    layoutParams.rightMargin = -250;
		    view.setLayoutParams(layoutParams);
		    	*/
		    view.setOnClickListener(btListener);
		   // view.setOnTouchListener(touchListener);
		    //root.addView(view);
	}
	private TextView.OnTouchListener touchListener=new TextView.OnTouchListener(){

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			  final int X = (int) event.getRawX();
			    final int Y = (int) event.getRawY();
			    TextView thisview=(TextView)v;
			    switch (event.getAction() & MotionEvent.ACTION_MASK) {
			        case MotionEvent.ACTION_DOWN:
			            RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) thisview.getLayoutParams();
			            xPadding = X - lParams.leftMargin;
			            yPadding = Y - lParams.topMargin;
			            break;
			        case MotionEvent.ACTION_UP:
			            break;
			        case MotionEvent.ACTION_POINTER_DOWN:
			            break;
			        case MotionEvent.ACTION_POINTER_UP:
			            break;
			        case MotionEvent.ACTION_MOVE:
			            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) thisview.getLayoutParams();
			            layoutParams.leftMargin = X - xPadding;
			            layoutParams.topMargin = Y - yPadding;
			            layoutParams.rightMargin = -250;
			            layoutParams.bottomMargin = -250;
			            thisview.setLayoutParams(layoutParams);
			            break;
			    }
			    root.invalidate();
			    return true;
		}
		
		
	};
	
	private Button.OnClickListener btListener=new Button.OnClickListener(){

		@Override
		public void onClick(View v) {
			RelativeLayout.LayoutParams layoutParams =  new RelativeLayout.LayoutParams( v.getLayoutParams());
		  
			TextView newview = new TextView(touchActivity.this);
			layoutParams.leftMargin = 150;
		    layoutParams.topMargin = 150;
			newview.setLayoutParams(layoutParams);
		    newview.setText("TextView!!!!!!!!");
		    root.addView(newview);
		    newview.setOnTouchListener(touchListener);
			
		}
		
	};
	
}
