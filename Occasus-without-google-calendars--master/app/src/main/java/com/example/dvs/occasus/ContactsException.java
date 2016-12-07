package com.example.dvs.occasus;



import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;


import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import android.app.Activity;
import android.net.Uri;
import android.provider.ContactsContract;
import android.database.Cursor;

import android.graphics.Color;


public class ContactsException extends ActionBarActivity {

    private static final int PICK_CONTACT = 3;

    private static int i;

    String id_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_exception);


        //to add logo to action bar
        ActionBar ac=getSupportActionBar();
        ac.setDisplayShowHomeEnabled(true);
        ac.setLogo(R.drawable.occasus1);
        ac.setDisplayUseLogoEnabled(true);




        getSupportActionBar().setDisplayHomeAsUpEnabled(false);



        Intent intent = getIntent();

        Button add_contact = (Button)findViewById(R.id.add_contact);

        Button delete_contact = (Button)findViewById(R.id.delete_contact);

        final ListView toggle_list = (ListView)findViewById(R.id.contacts);

        add_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });


        delete_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBAdapterException db = new DBAdapterException(ContactsException.this);

                db.deleteContact(id_1);
                Intent intent = new Intent(ContactsException.this,ContactsException.class);
                startActivity(intent);
            }
        });

        i = 0;

        final String[] toggle= new String[100];
        String[] toggle1 = new String[100];
        toggle[0]="Hello";

        DBAdapterException db = new DBAdapterException(this);

        db.open();
        Cursor c = db.getAllContacts();
        if (c.moveToFirst())
        {
            do {
                toggle[i] = c.getString(c.getColumnIndex("id"));
                toggle1[i] = c.getString(c.getColumnIndex("Name")) + "  " + c.getString(c.getColumnIndex("PhoneNo"));
                i++;
                //DisplayContact(c);
            } while (c.moveToNext());
        }

        String[] toggle2 = new String[i];
        int j;

        for(j = 0; j < i; j++) toggle2[j] = toggle1[j];


        if(i!=0){
            ListAdapter toggle_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, toggle2);
            toggle_list.setAdapter(toggle_adapter);
            setListViewHeightBasedOnChildren(toggle_list);
        }


        toggle_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int color = Color.TRANSPARENT;
                Drawable background = parent.getChildAt(position).getBackground();
                if (background instanceof ColorDrawable)
                    color = ((ColorDrawable) background).getColor();

                if(color == Color.rgb(22,17,81))
                    parent.getChildAt(position).setBackgroundColor(Color.TRANSPARENT);
                else
                    parent.getChildAt(position).setBackgroundColor(Color.rgb(22,17,81));

                id_1 = toggle[position];
            }
        });

        db.close();
    }




    //back button override......the app closes after pressing back button on mainactivity screen
    @Override
    public void onBackPressed() {
        Intent intent= new Intent(ContactsException.this,MainActivity.class);
        startActivity(intent);

    }


    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, AbsListView.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }







    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent
            data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case (PICK_CONTACT):
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c = getContentResolver().query(contactData, null, null, null, null);
                    if (c.moveToFirst()) {

                        String name = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));

                        String id = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

                        String hasPhone = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                        if (hasPhone.equalsIgnoreCase("1")) {
                            Cursor phones = getContentResolver().query(
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                                    null, null);
                            phones.moveToFirst();
                            String cNumber = phones.getString(phones.getColumnIndex("data1"));
                            String Number = cNumber.replaceAll(" ", "");

                            DBAdapterException db = new DBAdapterException(this);

                            db.open();
                            db.insertContact(name, id, Number);
                            db.close();

                            Intent intent = new Intent(ContactsException.this,ContactsException.class);
                            startActivity(intent);
                        }
                    }
                }
        }

    }


}
