package com.example.voter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog.Builder;

public class Modify extends AppCompatActivity {
    EditText epicUpdate,fname,lname,dob,pin,dist,state;
    Button btnUpdate,btnSearch;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        epicUpdate=findViewById(R.id.epicUpdate);
        fname=findViewById(R.id.fnameUp);
        lname=findViewById(R.id.lnameUp);
        dob=findViewById(R.id.dobUp);
        dist=findViewById(R.id.distUp);
        pin=findViewById(R.id.pinUp);
        state=findViewById(R.id.stateUp);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnSearch=findViewById(R.id.btnSearch);

        fname.setEnabled(false);
        lname.setEnabled(false);
        dob.setEnabled(false);
        dist.setEnabled(false);
        pin.setEnabled(false);
        state.setEnabled(false);



        db = openOrCreateDatabase("Register", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS signup(fname VARCHAR,lname VARCHAR,dob VARCHAR,epic VARCHAR,dist VARCHAR,pin VARCHAR,state VARCHAR);");


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (epicUpdate.getText().toString().trim().length() == 0) {
                    showMessage("Error", "Please enter Epic Number");
                    return;
                }
                Cursor c = db.rawQuery("SELECT * FROM signup WHERE epic='" + epicUpdate.getText() + "'", null);
                if (c.moveToFirst()) {
                    db.execSQL("UPDATE signup SET fname='" + fname.getText() + "',lname='" + lname.getText() + "',dob='" + dob.getText() + "',dist='" + dist.getText() + "',pin='" + pin.getText() + "',state='" + state.getText() + "'WHERE epic='" + epicUpdate.getText() + "'");
                    showMessage("Success", "Record Modified");
                }
                else {
                    showMessage("Error", "Invalid Epic Number");
                }
                clearText();
                fname.setEnabled(false);
                lname.setEnabled(false);
                dob.setEnabled(false);
                dist.setEnabled(false);
                pin.setEnabled(false);
                state.setEnabled(false);
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (epicUpdate.getText().toString().trim().length() == 0) {
                    showMessage("Error", "Please enter Epic Number");
                    return;
                }
                Cursor c = db.rawQuery("SELECT * FROM signup WHERE epic='" + epicUpdate.getText() + "'", null);
                if (c.moveToFirst()) {
                    fname.setEnabled(true);
                    lname.setEnabled(true);
                    dob.setEnabled(true);
                    dist.setEnabled(true);
                    pin.setEnabled(true);
                    state.setEnabled(true);

                    fname.setText(c.getString(0));
                    lname.setText(c.getString(1));
                    dob.setText(c.getString(2));
                    epicUpdate.setText(c.getString(3));
                    dist.setText(c.getString(4));
                    pin.setText(c.getString(5));
                    state.setText(c.getString(6));
                } else {
                    showMessage("Error", "Invalid Epic Number");
                    clearText();
                }

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
        epicUpdate.setText("");
        dist.setText("");
        pin.setText("");
        state.setText("");
        fname.requestFocus();
    }
}