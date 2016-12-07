package com.meetingmileu.occasus;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Toggle extends Activity implements OnClickListener{

	private ToggleButton tgbBluetooth,tgbWifi,tgbData;
	final Context context = this;
	private Button bProfile,bSave;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.toggle);
		declare();
		
		tgbBluetooth.setOnClickListener(this);
		tgbWifi.setOnClickListener(this);
		tgbData.setOnClickListener(this);
		bProfile.setOnClickListener(this);
		bSave.setOnClickListener(this);
		
	}
	
	private void declare() {
		// TODO Auto-generated method stub
		tgbBluetooth = (ToggleButton)findViewById(R.id.tgbBluetooth);
		tgbWifi = (ToggleButton) findViewById(R.id.tgbWifi);
		tgbData = (ToggleButton) findViewById(R.id.tgbdata);
		bProfile = (Button) findViewById(R.id.bProfile);
		bSave = (Button) findViewById(R.id.bSave);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId()){
		
		case R.id.tgbBluetooth:
			
			break;
		case R.id.tgbWifi:
			
			break;
		case R.id.tgbdata:
			
			break;
		case R.id.bProfile:{
			final Dialog dialog = new Dialog(context);
			dialog.setContentView(R.layout.profile);
			dialog.setTitle("Profile");

			// set the custom dialog components - text, image and button
			TextView Silent = (TextView) dialog.findViewById(R.id.tvSilent);
			TextView Ring = (TextView) dialog.findViewById(R.id.tvRing);
			TextView Vibrate = (TextView) dialog.findViewById(R.id.tvVibrate);
			
			Silent.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					bProfile.setText("Silent");
					dialog.dismiss();
				}
			});
			Ring.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					bProfile.setText("Ring");
					dialog.dismiss();
				}
			});
			Vibrate.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					bProfile.setText("Vibrate");
					dialog.dismiss();
				}
			});

			dialog.show();
		}
			
			break;
		case R.id.bSave:
			Intent p = new Intent(Toggle.this,NewEvent.class);
			setResult(RESULT_OK, p);
			finish();
			break;
		}
	}

	
}
