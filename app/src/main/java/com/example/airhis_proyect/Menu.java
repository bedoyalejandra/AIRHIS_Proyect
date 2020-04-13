package com.example.airhis_proyect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {
    ImageView btnMaps;
    ImageView btnInt;
    ImageView btnindications;
    int option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnMaps =  findViewById(R.id.btnMaps);
        btnInt =  findViewById(R.id.btnInt);
        btnindications =  findViewById(R.id.btnindications);


        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), Indication.class);
                startActivity(i);
            }
        });



        btnInt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), Maps.class);
                option = 0;
                i.putExtra("Option", option);
                startActivity(i);
            }
        });





        btnindications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), Indications.class);
                startActivity(i);
            }
        });
    }
}
