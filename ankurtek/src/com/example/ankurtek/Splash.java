package com.example.ankurtek;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Splash extends Activity{


MediaPlayer mysong;

	@Override
	protected void onCreate(Bundle ankurlovessandwitch) {
		// TODO Auto-generated method stub
		super.onCreate(ankurlovessandwitch);
		setContentView(R.layout.splash);
		mysong=MediaPlayer.create(Splash.this, R.raw.alco);
		
		SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		boolean music=getPrefs.getBoolean("checkbox", true);
		if(music==true)
		mysong.start();

		Thread timer=new Thread(){
			public void run(){
				try{
					sleep(7000);
				}catch(InterruptedException e ){
					e.printStackTrace();
				}finally{
					Intent openmainact=new Intent("com.example.ankurtek.MENU");
					startActivity(openmainact);
					
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mysong.release();
		finish();
	}
	
	

}
