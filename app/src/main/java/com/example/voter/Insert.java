package com.example.voter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog.Builder;

public class Insert extends AppCompatActivity {
    EditText fname,lname,dob,epic,dist,pin,state;
    Button add;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        fname=findViewById(R.id.fname);
        lname=findViewById(R.id.lname);
        dob=findViewById(R.id.dob);
        epic=findViewById(R.id.epic);
        dist=findViewById(R.id.dist);
        pin=findViewById(R.id.pin);
        state=findViewById(R.id.state);

        add=findViewById(R.id.btnAdd);

        fname.requestFocus();

        db = openOrCreateDatabase("Register", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS signup(fname VARCHAR,lname VARCHAR,dob VARCHAR,epic VARCHAR,dist VARCHAR,pin VARCHAR,state VARCHAR);");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (fname.getText().toString().trim().length() == 0 ||
                        lname.getText().toString().trim().length() == 0 ||
                        dob.getText().toString().trim().length() == 0 ||
                        epic.getText().toString().trim().length() == 0 ||
                        dist.getText().toString().trim().length() == 0 ||
                        pin.getText().toString().trim().length() == 0 ||
                        state.getText().toString().trim().length() == 0) {
                    showMessage("Error", "Please enter all values");
                    return;
                }
                db.execSQL("INSERT INTO signup VALUES('" + fname.getText() + "','" + lname.getText() + "','" + dob.getText() + "','" + epic.getText() + "','" + dist.getText() + "','" + pin.getText() + "','" + state.getText() + "');");
                showMessage("Success", "Record added successfully");
                clearText();
            }
        });

    }

    public void showMessage(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        fname.setText("");
        lname.setText("");
        dob.setText("");
        epic.setText("");
        dist.setText("");
        pin.setText("");
        state.setText("");
        fname.requestFocus();
    }
}