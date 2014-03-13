package com.mmarquez.desingdemo.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mmarquez.designdemo.fragments.ColorFragment;
import com.mmarquez.designdemo.fragments.HelloWorldFragment;
import com.mmarquez.designdemo.fragments.NumberFragment;


public class MainActivity extends FragmentActivity {

	
	  private DrawerLayout mDrawerLayout;
      private ListView mDrawerList;
      private ActionBarDrawerToggle mDrawerToggle;

      private String[] drawerTitles = new String[]{"Hello World", "Color Fragment", "Number Fragment", "Slide Activity", "Back Activity"};
	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Action Bar Work
		final ActionBar ab = this.getActionBar();
		ab.setTitle("Design Demo");
		

       

        
        
        //==Set Up the List==
        
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, drawerTitles));
      
        //Set Drawer List to React to Click
        mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long id) {
				selectDrawerItem(pos);
			}
		});

        //==Init Drawer Layout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        
        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
        

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) { //Callback for toggle
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectDrawerItem(0);
        }
		
		
	}

	
	//In Charge of changing between fragments
	private void selectDrawerItem(int position) {
		//Explicar V4 vs Regular
		FragmentManager fragmentManager = this.getSupportFragmentManager();
		switch (position) {
		case 0:
			fragmentManager.beginTransaction().replace(R.id.content_frame, new HelloWorldFragment()).commit();
			break;
		case 1:
			fragmentManager.beginTransaction().replace(R.id.content_frame, new ColorFragment()).commit();
			break;
		case 2:
			fragmentManager.beginTransaction().replace(R.id.content_frame, new NumberFragment()).commit();
			break;
		case 3:
			Intent tabIntent = new Intent(this, TabsActivity.class);
			this.startActivity(tabIntent);
			break; //Glitch to Show;
		case 4:
			Intent intent = new Intent(this, ReturnActivity.class);
			this.startActivity(intent);
			break;

		}
		
		
        
		// update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerList);
		
	}



	//Need to invalidate states
	 /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

	
	
	
	//Action Bar Menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		 // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
       if (mDrawerToggle.onOptionsItemSelected(item)) {
           return true;
       }
       
       //Regular Swtich for Action Bar
		switch (item.getItemId()) {
		case R.id.ab_btn_camera:
			openCamaraActivity();
			break;
		case R.id.ab_btn_clock:
			Toast.makeText(this, "Not Yet Implemented :D", Toast.LENGTH_SHORT).show();
			break;
		}
		
		return true;
	}


	private static final int CAMERA_PIC_REQUEST = 1337;

	private void openCamaraActivity() {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	     startActivityForResult(takePictureIntent, CAMERA_PIC_REQUEST);	
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
	    Toast.makeText(this, "Volvimos de la Camara", Toast.LENGTH_SHORT).show();;
	} 
	
	
	
	

}
