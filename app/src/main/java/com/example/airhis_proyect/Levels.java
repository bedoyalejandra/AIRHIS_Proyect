package com.example.airhis_proyect;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class Levels extends AppCompatActivity {

    ImageView img;
    TextView tv;
    double lat;
    double lon;
    Matrix m;
    double value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        img = findViewById(R.id.img);
        tv = findViewById(R.id.tv);
        m = new Matrix();
        lat = (Double) getIntent().getSerializableExtra("Latitud");
        lon = (Double)getIntent().getSerializableExtra("Longitud");
        value = function(lat, lon);
        level(interval(value));

    }

    private int interval(double f){
        if(f < 12.0){
           return 1;
        }else if(f >= 12.1 && f < 35.4){
            return 2;
        }else if(f >= 35.5 && f < 55.4){
            return 3;
        }else if(f >= 55.5 && f < 150.4){
            return 4;
        }else if(f >= 150.5 && f < 250.4){
            return 5;
        }else{
            return 6;
        }
    }

    private int intervalRsl(double f){
        if(f < 50.0){
            return 1;
        }else if(f >= 50.0 && f < 100.0){
            return 2;
        }else if(f >= 100.0 && f < 150.0){
            return 3;
        }else if(f >= 150.0 && f < 200.0){
            return 4;
        }else if(f >= 200.0 && f < 300.0){
            return 5;
        }else{
            return 6;
        }
    }

    private double function(double la, double lo){
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.parseDouble(df.format(m.function(la,lo)));
    }

    private void level(int n){
        tv.setText(value + "");
        Bitmap imgTem;
        if(n == 1){
            imgTem = BitmapFactory.decodeResource(getResources(), R.drawable.buena);
            img.setImageBitmap(imgTem);
            tv.setTextColor(Color.parseColor("#529934"));

        }else if(n == 2){
            imgTem = BitmapFactory.decodeResource(getResources(), R.drawable.moderada);
            img.setImageBitmap(imgTem);
            tv.setTextColor(Color.parseColor("#d3c302"));

        }else if(n == 3){
            imgTem = BitmapFactory.decodeResource(getResources(), R.drawable.daninagrupos);
            img.setImageBitmap(imgTem);
            tv.setTextColor(Color.parseColor("#F28420"));

        }else if(n == 4){
            imgTem = BitmapFactory.decodeResource(getResources(), R.drawable.danina);
            img.setImageBitmap(imgTem);
            tv.setTextColor(Color.parseColor("#CC3A36"));

        }else if(n == 5){
            imgTem = BitmapFactory.decodeResource(getResources(), R.drawable.muydanina);
            img.setImageBitmap(imgTem);
            tv.setTextColor(Color.parseColor("#5E3AC1"));

        }
    }
}
