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

    ImageView bar;
    TextView tv;
    TextView text;

    ImageView s1;
    ImageView s2;
    ImageView s3;
    ImageView s4;
    ImageView s5;
    ImageView s6;

    double lat;
    double lon;
    Matrix m;
    double value;

    ICA ica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        initComponents();
        ica = new ICA();
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
        DecimalFormat df = new DecimalFormat("#.00");
        double ic = ica.calculateIca(value);
        double number = Double.parseDouble(df.format(ic));
        tv.setText(number + "");

        if(n == 1){
            images(R.drawable.bbuena, 0, 0, 0, 0, 0, 0);
            tv.setTextColor(Color.parseColor("#529934"));
            text.setText("La calidad del aire es buena, no existe ningún riesgo para la salud.");

        }else if(n == 2){
            images(R.drawable.bmoderada, 1, 1, 0, 0, 0, 0);
            tv.setTextColor(Color.parseColor("#d3c302"));
            text.setText("La calidad del aire es moderada y existe poco o ningún riesgo para la salud.");


        }else if(n == 3){
            images(R.drawable.bdaninagrupos, 1, 1, 1, 1, 1, 0);
            tv.setTextColor(Color.parseColor("#F28420"));
            text.setText("La calidad del aire es dañina y exite un alto riesgo para la salud de grupos sensibles. \n Se recomienda el uso de tapabocas y no realizar actividades al aire libre.");


        }else if(n == 4){
            images(R.drawable.bdanina, 1, 1, 1, 1, 1, 1);
            tv.setTextColor(Color.parseColor("#CC3A36"));
            text.setText("La calidad del aire es dañina y exite un alto riesgo para la salud. \n Se recomienda el uso de tapabocas y no realizar actividades al aire libre.");

        }else if(n == 5){
            images(R.drawable.bmuydanina, 1, 1, 1, 1, 1, 1);
            tv.setTextColor(Color.parseColor("#5E3AC1"));
            text.setText("La calidad del aire es muy dañina y exite un alto riesgo para la salud. \n Se recomienda el uso de tapabocas y no realizar ningún tipo de actividad al aire libre.");

        }
    }

    private void images(int a1, int a2, int a3, int a4, int a5, int a6, int a7){

        if(a2 == 0){
            a2 = R.drawable.respiratoria;
        }else {
            a2 = R.drawable.prespiratoria;
        }

        if(a3 == 0){
            a3 = R.drawable.cardiaca;
        }else {
            a3 = R.drawable.pcardiaca;
        }

        if(a4 == 0){
            a4 = R.drawable.ancianos;
        }else {
            a4 = R.drawable.pancianos;
        }

        if(a5 == 0){
            a5 = R.drawable.actividad;
        }else {
            a5 = R.drawable.pactividad;
        }

        if(a6 == 0){
            a6 = R.drawable.ninos;
        }else {
            a6 = R.drawable.pninos;
        }

        if(a7 == 0){
            a7 = R.drawable.adulto;
        }else {
            a7 = R.drawable.padultos;
        }

        Bitmap img1 = BitmapFactory.decodeResource(getResources(), a1);
        Bitmap img2 = BitmapFactory.decodeResource(getResources(), a2);
        Bitmap img3 = BitmapFactory.decodeResource(getResources(), a3);
        Bitmap img4 = BitmapFactory.decodeResource(getResources(), a4);
        Bitmap img5 = BitmapFactory.decodeResource(getResources(), a5);
        Bitmap img6 = BitmapFactory.decodeResource(getResources(), a6);
        Bitmap img7 = BitmapFactory.decodeResource(getResources(), a7);

        bar.setImageBitmap(img1);

        s1.setImageBitmap(img2);
        s2.setImageBitmap(img3);
        s3.setImageBitmap(img4);
        s4.setImageBitmap(img5);
        s5.setImageBitmap(img6);
        s6.setImageBitmap(img7);

    }

    private void initComponents(){
        tv = findViewById(R.id.tv);
        bar = findViewById(R.id.bar);
        text = findViewById(R.id.text);
        s1 = findViewById(R.id.s1);
        s2 = findViewById(R.id.s2);
        s3 = findViewById(R.id.s3);
        s4 = findViewById(R.id.s4);
        s5 = findViewById(R.id.s5);
        s6 = findViewById(R.id.s6);
    }
}
