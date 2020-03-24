package com.example.airhis_proyect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Sigin extends AppCompatActivity {

    ImageView btnInit;
    ImageView btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin);
        btnInit = findViewById(R.id.btnInit);
        btnCreate = findViewById(R.id.btnCreate);

        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), Autentication.class);
                startActivity(i);
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), CreateCount.class);
                startActivity(i);
            }
        });
    }
}
