package com.example.ankurtek;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Camera extends Activity implements View.OnClickListener{
	ImageButton ib;
	ImageView iv;
	Button b;
	Intent i;
	final static int cameradata=0;
	Bitmap bmp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);
		initialise();
		InputStream is=getResources().openRawResource(R.drawable.ic_launcher);
		bmp=BitmapFactory.decodeStream(is);
	}
	
	public void initialise()
	{
		iv=(ImageView)findViewById(R.id.ivreturnpic);
		ib=(ImageButton)findViewById(R.id.ibtakepic);
		b=(Button)findViewById(R.id.setWall);
		ib.setOnClickListener(this);
		b.setOnClickListener(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
		case R.id.ibtakepic:
			i=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(i,cameradata);
			break;
		case R.id.setWall:
			try {
				getApplicationContext().setWallpaper(bmp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==RESULT_OK){
			
		Bundle extras=data.getExtras();
		bmp=(Bitmap)extras.get("data");
		iv.setImageBitmap(bmp);
		}
	}
	
	

}
