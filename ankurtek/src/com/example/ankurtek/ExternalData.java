package com.example.ankurtek;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ExternalData extends Activity implements OnItemSelectedListener, OnClickListener{
	
	private TextView canWrite,canRead;
	private String state;
	Boolean canW,canR;
	Spinner spinner;
	String paths[]={ "Music","pictures","download"};
	File path=null;
	File file=null;
	EditText saveFile;
	Button confirm,save;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.externaldata);
		canWrite=(TextView)findViewById(R.id.tvcanWrite);
		canRead=(TextView)findViewById(R.id.tvcanRead);
		confirm=(Button)findViewById(R.id.bConfirmSaveAs);
		save=(Button)findViewById(R.id.bSaveFile);
		confirm.setOnClickListener(this);
		save.setOnClickListener(this);
		checkstate();
			ArrayAdapter<String>adapter = new ArrayAdapter<String>(ExternalData.this, android.R.layout.simple_spinner_item,paths);
		spinner=(Spinner)findViewById(R.id.spinner1);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
	}

	private void checkstate() {
		// TODO Auto-generated method stub
		state = Environment.getExternalStorageState();	//checking if external storage is present
		if(state.equals(Environment.MEDIA_MOUNTED)){
			//read and write
			canWrite.setText("true");
			canRead.setText("true");
			canW=canR=true;
		}
		else if(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
			//read but can't write
			canWrite.setText("false");
			canRead.setText("true");
			canW=false;
			canR=true;
			
			
		}else{
			canWrite.setText("false");
			canRead.setText("false");
			canW=canR=false;
		}
	
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		int pos= spinner.getSelectedItemPosition();
		switch(pos){
		case 0:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			break;
		case 1:

			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			break;
		case 2:

			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			break;
			
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bConfirmSaveAs:
			save.setVisibility(View.VISIBLE);
			break;
		case R.id.bSaveFile:
			String f=saveFile.getText().toString();
			file=new File(path,f);
			checkstate();
			if(canW==canR==true){
				
				try {
					InputStream is= getResources().openRawResource(R.drawable.greenball);
					OutputStream os=new FileOutputStream(file);
					byte[] data= new byte[is.available()];
					is.read(data);
					os.write(data);
					is.close();
					os.close();
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		}
	}
	
	

}
