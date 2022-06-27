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

public class Display extends AppCompatActivity {

    SQLiteDatabase db;
    EditText delete;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        delete=findViewById(R.id.delete);
        btnDelete=findViewById(R.id.btnDelete);

        db = openOrCreateDatabase("Register", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS signup(fname VARCHAR,lname VARCHAR,dob VARCHAR,epic VARCHAR,dist VARCHAR,pin VARCHAR,state VARCHAR);");

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (delete.getText().toString().trim().length() == 0) {
                    showMessage("Error", "Please enter Epic Number");
                    return;
                }
                Cursor c = db.rawQuery("SELECT * FROM signup WHERE epic='" + delete.getText() + "'", null);

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
//                showMessage("Deleted!","Success to Erase data \n\n"+ buffer.toString());

                if (c.moveToFirst()) {
                    db.execSQL("DELETE FROM signup WHERE epic='" + delete.getText() + "'");
                    showMessage("Deleted!","Success to Erase data \n\n"+ buffer.toString());

                } else {
                    showMessage("Error", "Invalid Epic");
                }
                clearText();
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
    public void clearText()
    {
        delete.setText("");
        delete.requestFocus();
    }
}