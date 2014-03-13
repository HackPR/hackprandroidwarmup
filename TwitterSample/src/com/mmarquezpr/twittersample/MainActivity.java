package com.mmarquezpr.twittersample;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.SearchView.OnQueryTextListener;

public class MainActivity extends Activity {

	private SearchView searchView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		searchView = (SearchView) findViewById(R.id.searchView1);
		searchView.setOnQueryTextListener(new OnQueryTextListener(){

			@Override
			public boolean onQueryTextChange(String newText) {
				//NoOp
				return false;
			}

			@Override
			public boolean onQueryTextSubmit(String query) {
				Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
				return true;
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
