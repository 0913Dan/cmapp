package com.cmapp.test;






import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SliderDrawerActivity extends Activity {
	 private DrawerLayout mDrawerLayout;
	    private LinearLayout mDrawerList;
	    private ActionBarDrawerToggle mDrawerToggle;

	    private CharSequence mDrawerTitle;
	private String[] select=new String[]{"A","B","C","D"};
	private ListView listView;
	private TextView lvText;
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sliderdrawer);
		
		listView=(ListView)findViewById(R.id.sliderContent);
		lvText=(TextView)findViewById(R.id.sliderDetail);
		
		ArrayAdapter<String> adapterSelect= new ArrayAdapter<String>(this,R.layout.spinner_drop,select);
		
		listView.setAdapter(adapterSelect);
		listView.setOnItemClickListener(lvListener);
		
		
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (LinearLayout) findViewById(R.id.left_drawer);
		getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
            }

            public void onDrawerOpened(View drawerView) {
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        
	}
	
	
	private ListView.OnItemClickListener lvListener =new ListView.OnItemClickListener(){

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			lvText.setText(parent.getItemAtPosition(position).toString());
			//lvText.setText(((TextView)view).getText().toString());
			mDrawerLayout.closeDrawer(mDrawerList);
		}

		
	};
	
	  public boolean onOptionsItemSelected(MenuItem item) {
	         // The action bar home/up action should open or close the drawer.
	         // ActionBarDrawerToggle will take care of this.
	        if (mDrawerToggle.onOptionsItemSelected(item)) {
	            return true;
	        }
	        // Handle action buttons
	     return false;
	    }
}
