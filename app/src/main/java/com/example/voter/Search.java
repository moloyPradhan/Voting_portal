package com.example.voter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Search extends AppCompatActivity {
    EditText search;
    TextView or;
    SQLiteDatabase db;
    Button display,searchbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search=findViewById(R.id.search);
        or=findViewById(R.id.textView);
        display=findViewById(R.id.allBtn);
        searchbtn=findViewById(R.id.searchBtn);

        db = openOrCreateDatabase("Register", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS signup(fname VARCHAR,lname VARCHAR,dob VARCHAR,epic VARCHAR,dist VARCHAR,pin VARCHAR,state VARCHAR);");


        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = db.rawQuery("SELECT * FROM signup WHERE epic='" + search.getText() + "'", null);
                if (c.getCount() == 0) {
                    showMessage("Error", "No records found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (c.moveToNext()) {
                    buffer.append("First Name: " + c.getString(0) + "\n");
                    buffer.append("Last Name: " + c.getString(1) + "\n");
                    buffer.append("Date of Birth: " + c.getString(2) + "\n");
                    buffer.append("Epic Number: " + c.getString(3) + "\n");
                    buffer.append("District: " + c.getString(4) + "\n");
                    buffer.append("Pin: " + c.getString(5) + "\n");
                    buffer.append("State: " + c.getString(6) + "\n\n");
                }
                showMessage("Voters Details", buffer.toString());
            }
        });

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = db.rawQuery("SELECT * FROM signup", null);
                if (c.getCount() == 0) {
                    showMessage("Error", "No records found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (c.moveToNext()) {
                    buffer.append("First Name: " + c.getString(0) + "\n");
                    buffer.append("Last Name: " + c.getString(1) + "\n");
                    buffer.append("Date of Birth: " + c.getString(2) + "\n");
                    buffer.append("Epic Number: " + c.getString(3) + "\n");
                    buffer.append("District: " + c.getString(4) + "\n");
                    buffer.append("Pin: " + c.getString(5) + "\n");
                    buffer.append("State: " + c.getString(6) + "\n\n");
                }
                showMessage("Voters Details", buffer.toString());
            }
        });

    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}