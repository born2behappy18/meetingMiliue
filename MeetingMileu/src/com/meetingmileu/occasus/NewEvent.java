package com.meetingmileu.occasus;

import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
 
public class NewEvent extends Activity{
 
    private TextView tvDisplayDate, tvDisplayDate2,tvDisplayTime,tvDisplayTime2,tvDialogBox;
    private Button bSetToggle;
    private int hour;
    private int mins;
    private int year;
    private int month;
    private int day;
    final Context context = this;
    static final int DATE_DIALOG_ID = 1;
    static final int DATE_DIALOG_ID2 = 2;
    static final int TIME_DIALOG_ID = 998;
    static final int TIME_DIALOG_ID2 = 999;
    int cur = 0;
    int tur = 0;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newevent);
        tvDialogBox = (TextView)findViewById(R.id.tvCustomDialog);
        bSetToggle = (Button) findViewById(R.id.bSetToggle);
        setCurrentDateOnView();
        setCurrentTimeOnView();
        addListenerOnButton();
        
        
 
    }
    
    public void setCurrentTimeOnView(){
    	
    	tvDisplayTime = (TextView) findViewById(R.id.tvTm1);
    	tvDisplayTime2 = (TextView) findViewById(R.id.tvTm2);
    	
    	final Calendar c = Calendar.getInstance();
		hour = c.get(Calendar.HOUR_OF_DAY);
		mins = c.get(Calendar.MINUTE);

		// set current time into textView
		tvDisplayTime.setText(
                    new StringBuilder().append(pad(hour))
                                       .append(":").append(pad(mins)));
		tvDisplayTime2.setText(new StringBuilder().append(pad(hour+1))
                .append(":").append(pad(mins)));
    }
    private static String pad(int c) {
		if (c >= 10)
		   return String.valueOf(c);
		else
		   return "0" + String.valueOf(c);
	}
    // display current date
    public void setCurrentDateOnView() {
 
        tvDisplayDate = (TextView) findViewById(R.id.tvDt1);
        tvDisplayDate2 = (TextView) findViewById(R.id.tvDt2);
        
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
 
        // set current date into textView
        tvDisplayDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));
 
        tvDisplayDate2.setText(tvDisplayDate.getText().toString());
    }
 
    public void addListenerOnButton() {
 
    
        tvDisplayDate.setOnClickListener(new OnClickListener() {
 
            @Override
            public void onClick(View v) {
 
                showDialog(DATE_DIALOG_ID);
 
            }
 
        });
    
        tvDisplayDate2.setOnClickListener(new OnClickListener() {
 
            @Override
            public void onClick(View v) {
                 
                showDialog(DATE_DIALOG_ID2);
 
            }
 
        });
 
        tvDisplayTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(TIME_DIALOG_ID);
			}
		});
        tvDisplayTime2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(TIME_DIALOG_ID2);
			}
		});
        tvDialogBox.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog();

			}

		});
        bSetToggle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent k = new Intent(NewEvent.this,Toggle.class);
				startActivityForResult(k,0);
			}
		});
        
    }
 
    private void dialog(){
		// custom dialog
		final Dialog vdialog = new Dialog(context);
	    
					vdialog.setContentView(R.layout.custondialog);
					vdialog.setTitle("Repeat");
					TextView norepeat = (TextView) vdialog.findViewById(R.id.tvdoesnotrepeat);
					TextView everyday = (TextView) vdialog.findViewById(R.id.tveveryday);
					TextView everyweek = (TextView) vdialog.findViewById(R.id.tveveryweek);
					TextView everymonth = (TextView) vdialog.findViewById(R.id.tveverymonth);
					TextView everyyear = (TextView) vdialog.findViewById(R.id.tveveryyear);
					TextView custom = (TextView) vdialog.findViewById(R.id.tvcustom);
					norepeat.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							tvDialogBox.setText("Does Not Repeat");
							vdialog.dismiss();
						}
					});
					everyday.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							tvDialogBox.setText("Every Day");
							vdialog.dismiss();
						}
					});
					everyweek.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							tvDialogBox.setText("Every Week");
							vdialog.dismiss();
						}
					});
					everymonth.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							tvDialogBox.setText("Every Month");
							vdialog.dismiss();
						}
					});
					everyyear.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							tvDialogBox.setText("Every Year");
							vdialog.dismiss();
						}
					});
					custom.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							tvDialogBox.setText("Custom...");
							vdialog.dismiss();
						}
					});
					vdialog.show();
    }

	
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
         
        case DATE_DIALOG_ID:
            System.out.println("onCreateDialog  : " + id);
            cur = DATE_DIALOG_ID;
            // set date picker as current date
            return new DatePickerDialog(this, datePickerListener, year, month,
                    day);
        case DATE_DIALOG_ID2:
            cur = DATE_DIALOG_ID2;
            System.out.println("onCreateDialog2  : " + id);
            // set date picker as current date
            return new DatePickerDialog(this, datePickerListener, year, month,
                    day);
        case TIME_DIALOG_ID:
        	tur=TIME_DIALOG_ID;
			// set time picker as current time
			return new TimePickerDialog(this, 
                                        timePickerListener, hour, mins,false);
        case TIME_DIALOG_ID2:
        	tur=TIME_DIALOG_ID2;
			// set time picker as current time
			return new TimePickerDialog(this, 
                                        timePickerListener, hour, mins,false);

        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
    	 
        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                int selectedMonth, int selectedDay) {
             
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;
 
            if(cur == DATE_DIALOG_ID){
                // set selected date into textView
                tvDisplayDate.setText(new StringBuilder().append(month + 1)
                        .append("-").append(day).append("-").append(year)
                        .append(" "));
            }
            else{
                tvDisplayDate2.setText(new StringBuilder().append(month + 1)
                        .append("-").append(day).append("-").append(year)
                        .append(" "));
            }
 
        }
    };
    private TimePickerDialog.OnTimeSetListener timePickerListener = 
            new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int selectedHour,
				int selectedMinute) {
			hour = selectedHour;
			mins = selectedMinute;
		if(tur==TIME_DIALOG_ID){
			// set current time into textView
			tvDisplayTime.setText(new StringBuilder().append(pad(hour))
					.append(":").append(pad(mins)));
		}
		else{
			tvDisplayTime2.setText(new StringBuilder().append(pad(hour))
					.append(":").append(pad(mins)));
		}
	};
 
    
	
};

}