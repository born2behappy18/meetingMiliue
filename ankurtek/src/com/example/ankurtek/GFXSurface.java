package com.example.ankurtek;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener{

	ankurtekSurface ourSurfaceView;
	float x,y;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ourSurfaceView = new ankurtekSurface(this);
		ourSurfaceView.setOnTouchListener(this);
		x=0;
		y=0;
		setContentView(ourSurfaceView);
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ourSurfaceView.resume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSurfaceView.pause();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		x=event.getX();
		y=event.getY();
		
		return true; 		//when false type there will be no continous motion
	}
	
	public class ankurtekSurface extends SurfaceView implements Runnable{

		SurfaceHolder ourHolder;
		Thread ourThread=null;
		Boolean isRunning=false;
		
		public ankurtekSurface(Context context) {
			// TODO Auto-generated constructor stub
			super(context);
			ourHolder= getHolder(); 		//holders tells if surface is valid or available
			
		}
		public void resume(){
			isRunning=true;
			ourThread=new Thread(this);
			ourThread.start();
		}
		public void pause(){
			isRunning=false;
			while(true){
				try {
					ourThread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			ourThread=null;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(isRunning){
				if(!ourHolder.getSurface().isValid())
					continue;
				Canvas canvas = ourHolder.lockCanvas();   //no other threads or classes can access it now
				canvas.drawRGB(02, 02, 150);
				if(x!=0 && y!=0){
					Bitmap test= BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
					canvas.drawBitmap(test, x-(test.getWidth()/2), y-(test.getHeight()/2), null);
				}
				ourHolder.unlockCanvasAndPost(canvas);
				
			}
			
		}	

	}

	
}
