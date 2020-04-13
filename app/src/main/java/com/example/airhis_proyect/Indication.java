package com.example.airhis_proyect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Indication extends AppCompatActivity {

    ImageView btnInit;
    int option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indication);
        btnInit = findViewById(R.id.btnInit);

        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), Maps.class);
                option = 1;
                i.putExtra("Option", option);
                startActivity(i);
            }
        });
    }
}
