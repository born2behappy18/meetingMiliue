package com.example.ankurtek;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Texting extends Activity implements View.OnClickListener{
	Button checkbut;
	ToggleButton passtog;
	EditText inp;
	TextView disp;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);
		rasogulla();
		
		passtog.setOnClickListener(this);
		checkbut.setOnClickListener(this);
	
}


	private void rasogulla() {
		// TODO Auto-generated method stub
		checkbut=(Button)findViewById(R.id.trybut);
		passtog=(ToggleButton)findViewById(R.id.toggleBut);
		inp=(EditText)findViewById(R.id.edt);
		disp=(TextView)findViewById(R.id.commview);
		
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.trybut:
			String check=inp.getText().toString();
			disp.setText(check);
			if(check.contentEquals("left")){
				disp.setGravity(Gravity.LEFT);
				
			}else if(check.contentEquals("center")){
				disp.setGravity(Gravity.CENTER);
				
			}else if(check.contentEquals("right")){
				disp.setGravity(Gravity.RIGHT);
				
			}else if(check.contentEquals("blue")){
				disp.setTextColor(Color.BLUE);
		    }else if(check.contentEquals("WTF")){
				Random crazy=new Random();
				disp.setText("WTF!!!");
				disp.setTextSize(crazy.nextInt(75));
				disp.setTextColor(Color.rgb(crazy.nextInt(265),crazy.nextInt(265),crazy.nextInt(265)));				
				switch(crazy.nextInt(3)){
				case 0:disp.setGravity(Gravity.LEFT);
					break;
				case 1:disp.setGravity(Gravity.CENTER);
					break;
				case 2:disp.setGravity(Gravity.RIGHT);
					break;
				}
		    }else{
		    	disp.setText("invalid");
		    	disp.setGravity(Gravity.CENTER);
		    	disp.setTextColor(Color.RED);
		    }
			break;
		case R.id.toggleBut:if(passtog.isChecked()){
			inp.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		}else{
			inp.setInputType(InputType.TYPE_CLASS_TEXT);
		}
			
			break;
		}
		
	}}
