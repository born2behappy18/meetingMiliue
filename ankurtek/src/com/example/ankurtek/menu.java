package com.example.ankurtek;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class menu extends ListActivity {

	String classes[] = { "MainActivity", "Texting", "Email", "Camera", "Data", "GFX", "GFXSurface",
			"SoundStaff","SimpleBrowser","SharedPrefs","InternalData","ExternalData" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(menu.this, android.R.layout.simple_list_item_1, classes));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String pos = classes[position];
		try {
			Class myclass = Class.forName("com.example.ankurtek." + pos);
			Intent myintent = new Intent(menu.this, myclass);
			startActivity(myintent);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater blowup = getMenuInflater();
		blowup.inflate(R.menu.main, menu);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.aboutUs:
			Intent i = new Intent(menu.this, AboutUs.class);
			startActivity(i);
			break;
		case R.id.preferences:
			Intent p = new Intent(menu.this, Prefs.class);
			startActivity(p);
			
			break;
		case R.id.exit:
			finish();

			break;
		}
		return false;
	}

}
