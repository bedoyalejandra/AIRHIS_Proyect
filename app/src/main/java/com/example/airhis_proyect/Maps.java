package com.example.airhis_proyect;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng Medellin = new LatLng(6.2518400, -75.5635900);

        LatLng Estacion44 = new LatLng(6.1825418, -75.55063629999999);

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)).position(latLng));
            }
        });
        /*
        mMap.addMarker(new MarkerOptions().position(Estacion44).title("Estación #44 - Medellín, El Poblado - Tanques La Ye EPM").icon(BitmapDescriptorFactory.fromResource(R.drawable.cdos)));

        LatLng Estacion94 = new LatLng(6.236361, -75.4984741);
        mMap.addMarker(new MarkerOptions().position(Estacion94).title("Estación #94 - Medellín - Santa Elena").icon(BitmapDescriptorFactory.fromResource(R.drawable.cuno)));
        */
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Medellin, 13));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent i= new Intent(getApplicationContext(), Levels.class);
                i.putExtra("Latitud", marker.getPosition().latitude);
                i.putExtra("Longitud", marker.getPosition().longitude);
                startActivity(i);
                return false;
            }
        });

    }
}
