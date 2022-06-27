package com.example.voter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
ListView lv;
String []fun={"Add Record","Display or Search Record","Delete Record","Update Record"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lv=findViewById(R.id.lv);

        ArrayList<Person> arrayList=new ArrayList<>();

        arrayList.add(new Person(R.drawable.user,"Add Record",""));
        arrayList.add(new Person(R.drawable.display,"Display Record",""));
        arrayList.add(new Person(R.drawable.updated,"Update Record",""));
        arrayList.add(new Person(R.drawable.trash,"Delete Record",""));


        PersonAdapter personAdapter=new PersonAdapter(this,R.layout.list_row,arrayList);
        lv.setAdapter(personAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position)
                {
                    case 0:
                        Intent insert=new Intent(getApplicationContext(),Insert.class);
                        startActivity(insert);
                        break;
                    case 1:
                        Intent display=new Intent(getApplicationContext(),Search.class);
                        startActivity(display);
                        break;
                    case 2:
                        Intent modify=new Intent(getApplicationContext(),Modify.class);
                        startActivity(modify);
                        break;
                    case 3:
                        Intent delete=new Intent(getApplicationContext(),Display.class);
                        startActivity(delete);
                        break;
                }
            }
        });




    }
}