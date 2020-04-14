package com.example.airhis_proyect;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Maps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Matrix m;
    ArrayList<Lon> limits = new ArrayList<>();
    int option = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        option = (int) getIntent().getSerializableExtra("Option");
        fillArray();
        m = new Matrix();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng Medellin = new LatLng(6.2518400, -75.5635900);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Medellin, 13));

        // Si se escoge la opción de visualización
        if(option == 0){
            addMarkesHex();
            // Si se escoge la opción 'Escoge un punto'
        }else if (option == 1){
            mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(LatLng latLng) {
                    mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)).position(latLng));
                    System.out.println(latLng.toString());
                }
            });

            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    Intent i = new Intent(getApplicationContext(), Levels.class);
                    i.putExtra("Latitud", marker.getPosition().latitude);
                    i.putExtra("Longitud", marker.getPosition().longitude);
                    startActivity(i);
                    return false;
                }
            });
        }

    }



    private void addMarkesCircles(){
        double lat = 6.132600;
        double lon = 75.534974;
        double distance = 0.001;

        while (lat < 6.35){
            while (lon < 75.66){
                double value = m.function(lat, -1 * lon);
                int[] array = color(value);

                mMap.addCircle(new CircleOptions()
                    .center(new LatLng(lat, -1 * lon))
                    .radius(380)
                    .strokeColor(Color.TRANSPARENT)
                        .fillColor(Color.argb(array[0], array[1], array[2], array[3])));

                lon += distance;
            }
            lon = 75.534974;
            lat += distance;
        }
    }


    private void addMarkesPoligon(){
        double lat = 6.132600;
        double lon = 75.534974;
        double distance = 0.005;

        while (lat < 6.40){
            while (lon < 75.66){

                LatLng v1 = new LatLng(lat, -1 * lon);
                LatLng v2 = new LatLng(lat, (-1 * lon - distance));
                LatLng v3 = new LatLng(lat + distance, (-1 * lon - distance));
                LatLng v4 = new LatLng((lat + distance), -lon);

                double value = m.function(v1.latitude, v1.longitude);

                int[] array = color(value);
                mMap.addPolygon(new PolygonOptions()
                        .add(v1, v2, v3, v4)
                        .strokeColor(Color.TRANSPARENT)
                        .fillColor(Color.argb(array[0], array[1], array[2], array[3])));
                lon += distance;
            }
            lon = 75.534974;
            lat += distance;
        }
    }

    private void addMarkesHex(){
        double lat = 6.132600;
        double lon = 75.483228;
        double distance = 0.005;
        int cont = 1;
        int cont2 = 0;

        while (lat < 6.36){
            while (lon < 75.65){

                if(limits.get(cont2).inside(lon)){
                    LatLng v1 = new LatLng(lat, -1 * lon);
                    LatLng v2 = new LatLng(lat - distance/2, (-1 * lon - distance/2));
                    LatLng v3 = new LatLng(lat, (-1 * lon - distance));
                    LatLng v4 = new LatLng(lat + distance*3/4, (-1 * lon - distance));
                    LatLng v5 = new LatLng(lat + distance*5/4, (-1 * lon - distance/2));
                    LatLng v6 = new LatLng((lat + distance*3/4), -lon);

                    double value = m.function(v1.latitude, v1.longitude);

                    int[] array = color(value);
                    mMap.addPolygon(new PolygonOptions()
                            .add(v1, v2, v3, v4, v5, v6)
                            .strokeColor(Color.TRANSPARENT)
                           // .fillColor(Color.TRANSPARENT));
                    .fillColor(Color.argb(array[0], array[1], array[2], array[3])));
                }

                lon += distance;
            }
            cont2 += 1;

            if(cont == 0){
                lon = 75.483228;
                cont = 1;
            }else {
                lon = 75.483228 + distance/2;
                cont = 0;
            }
            lat += distance*5/4;
        }

    }

    private void fillArray(){
        limits.add(new Lon(75.62151465564966, 75.64041383564472));
        limits.add(new Lon(75.60365952551365, 75.64917594194414));
        limits.add(new Lon(75.58116719126701, 75.64210899174213));
        limits.add(new Lon(75.57065896689892, 75.63906032592058));
        limits.add(new Lon(75.56191094219685, 75.6262581422925));
        limits.add(new Lon(75.55981814861298, 75.61841066926718));
        limits.add(new Lon(75.55680938065052, 75.61991404742004));
        limits.add(new Lon(75.54994460195303, 75.61203371733427));
        limits.add(new Lon(75.54621566087008, 75.61111740767956));
        limits.add(new Lon(75.55030569434166, 75.61203338205814));
        limits.add(new Lon(75.54801676422358, 75.62060102820396));
        limits.add(new Lon(75.53971230983734, 75.632763504982));
        limits.add(new Lon(75.54710078984498, 75.632763504982));
        limits.add(new Lon(75.54016895592214, 75.62543973326683));
        limits.add(new Lon(75.5250134691596, 75.6316715106368));
        limits.add(new Lon(75.52640620619059, 75.62517687678337));
        limits.add(new Lon(75.5250671133399, 75.63673887401818));
        limits.add(new Lon(75.53399082273245, 75.63485629856586));
        limits.add(new Lon(75.53768623620272, 75.63622925430536));
        limits.add(new Lon(75.54533790796995, 75.64419709146023));
        limits.add(new Lon(75.5477250739932, 75.64162284135818));
        limits.add(new Lon(75.5404033139348, 75.64839474856853));
        limits.add(new Lon(75.5431492254138, 75.64107097685337));
        limits.add(new Lon(75.53994432091713, 75.61948388814926));
        limits.add(new Lon(75.53235970437527, 75.60797687619925));
        limits.add(new Lon(75.54014045745134, 75.59045232832432));
        limits.add(new Lon(75.53278416395187, 75.58790255337954));
        limits.add(new Lon(75.54027289152145, 75.5896345898509));
        limits.add(new Lon(75.54282266646624, 75.59216793626547));
        limits.add(new Lon(75.53425636142492, 75.5845833197236));
        limits.add(new Lon(75.52807588130236, 75.5737766996026));
        limits.add(new Lon(75.5130035430193, 75.57560864835978));
        limits.add(new Lon(75.51059626042843, 75.56611362844704));
        limits.add(new Lon(75.50195317715406, 75.56411437690258));
        limits.add(new Lon(75.50137683749199, 75.5575131252408));
        limits.add(new Lon(75.49407955259085, 75.52972041070461));
        limits.add(new Lon(75.49565065652132, 75.51664162427187));

    }


    private int[] color(double f){
        int[] values = new int[4];
        values[0] = 100;
        if(f < 4.0){
            values[1] = 57;
            values[2] = 127;
            values[3] = 19;
        }else if(f >= 4.0 && f < 8.0){
            values[1] = 82;
            values[2] = 153;
            values[3] = 52;
        }else if(f >= 8.0 && f < 12.0){
            values[1] = 134;
            values[2] = 176;
            values[3] = 36;


        }else if(f >= 12.0 && f < 19.6){
            values[1] = 206;
            values[2] = 207;
            values[3] = 14;
        }else if(f >= 19.6 && f < 26.9){
            values[1] = 252;
            values[2] = 227;
            values[3] = 0;
        }else if(f >= 26.9 && f < 35.4){
            values[1] = 251;
            values[2] = 193;
            values[3] = 14;


        }else if(f >= 35.4 && f < 42.0){
            values[1] = 250;
            values[2] = 163;
            values[3] = 26;
        }else if(f >= 42.0 && f < 48.7){
            values[1] = 249;
            values[2] = 133;
            values[3] = 38;
        }else if(f >= 48.7 && f < 55.5){
            values[1] = 255;
            values[2] = 102;
            values[3] = 26;


        }else if(f >= 55.5 && f < 87.0){
            values[1] = 230;
            values[2] = 80;
            values[3] = 45;
        }else if(f >= 87.0 && f < 118.8){
            values[1] = 219;
            values[2] = 49;
            values[3] = 49;
        }else if(f >= 118.8 && f < 150.5){
            values[1] = 173;
            values[2] = 52;
            values[3] = 70;


        }else if(f >= 150.5 && f < 183.3){
            values[1] = 138;
            values[2] = 55;
            values[3] = 142;
        }else if(f >= 183.3 && f < 216.6){
            values[1] = 107;
            values[2] = 57;
            values[3] = 178;
        }else if(f >= 216.6 && f < 250.5){
            values[1] = 88;
            values[2] = 36;
            values[3] = 133;


        }else if(f >= 250.5 && f < 300.0){
            values[1] = 84;
            values[2] = 21;
            values[3] = 90;
        }else if(f >= 250.5 && f < 300.0){
            values[1] = 79;
            values[2] = 2;
            values[3] = 38;
        }else{
            values[1] = 51;
            values[2] = 0;
            values[3] = 26;
        }
        return values;
    }

    private int[] colorRsl(double f){
        int[] values = new int[4];
        //alfa
        values[0] = 100;

        //Verde
        if(f < 16.67){
            values[1] = 57;
            values[2] = 127;
            values[3] = 19;
        }else if(f >= 16.67 && f < 33.33){
            values[1] = 82;
            values[2] = 153;
            values[3] = 52;
        }else if(f >= 33.33 && f < 50.0){
            values[1] = 134;
            values[2] = 176;
            values[3] = 36;

            //Amarillo
        }else if(f >= 50.0 && f < 66.67){
            values[1] = 206;
            values[2] = 207;
            values[3] = 14;
        }else if(f >= 66.67 && f < 83.34){
            values[1] = 252;
            values[2] = 227;
            values[3] = 0;
        }else if(f >= 83.34 && f < 100.0){
            values[1] = 251;
            values[2] = 193;
            values[3] = 14;

            //Naranja
        }else if(f >= 100.0 && f < 116.67){
            values[1] = 250;
            values[2] = 163;
            values[3] = 26;
        }else if(f >= 116.67 && f < 133.34){
            values[1] = 249;
            values[2] = 133;
            values[3] = 38;
        }else if(f >= 133.34 && f < 150.0){
            values[1] = 255;
            values[2] = 102;
            values[3] = 26;

            //Rojo
        }else if(f >= 150.0 && f < 166.67){
            values[1] = 230;
            values[2] = 80;
            values[3] = 45;
        }else if(f >= 166.67 && f < 183.34){
            values[1] = 219;
            values[2] = 49;
            values[3] = 49;
        }else if(f >= 183.34 && f < 200.0){
            values[1] = 173;
            values[2] = 52;
            values[3] = 70;

            //Morado
        }else if(f >= 200.0 && f < 233.33){
            values[1] = 138;
            values[2] = 55;
            values[3] = 142;
        }else if(f >= 233.33 && f < 266.66){
            values[1] = 107;
            values[2] = 57;
            values[3] = 178;
        }else if(f >= 266.66 && f < 300.0){
            values[1] = 88;
            values[2] = 36;
            values[3] = 133;

            //Marrón
        }else if(f >= 300.0 && f < 366.67){
            values[1] = 84;
            values[2] = 21;
            values[3] = 90;
        }else if(f >= 366.67 && f < 433.34){
            values[1] = 79;
            values[2] = 2;
            values[3] = 38;
        }else{
            values[1] = 51;
            values[2] = 0;
            values[3] = 26;
        }
        return values;
    }
    

    private void addHeatMap() {
        ArrayList<LatLng> list = new ArrayList<>();

        TileOverlay mOverlay;
        HeatmapTileProvider mProvider;

        list.add(new LatLng(6.2218938, -75.61060329999999));
        list.add(new LatLng(6.2589092, -75.5482635));
        list.add(new LatLng(6.2778502, -75.6364288));
        list.add(new LatLng(6.2372341, -75.610466));
        list.add(new LatLng(6.1998701, -75.56095120000001));
        list.add(new LatLng(6.2904806, -75.5555191));

        // Create a heat map tile provider, passing it the latlngs of the police stations.
        mProvider = new HeatmapTileProvider.Builder()
                .data(list)
                .build();
        // Add a tile overlay to the map, using the heat map tile provider.
        mOverlay = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));
    }


}
