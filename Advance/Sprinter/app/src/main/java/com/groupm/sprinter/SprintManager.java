package com.groupm.sprinter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by Jerick.Duguran on 5/2/2016.
 */
public class SprintManager extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap sprinter_map;
    private ArrayList<Circle> sprinter_map_circles;
    private Circle current_location = null;
    private Circle current_location_radius = null;

    public SprintManager(){

        LocationManager mLocationmanager = null;

        String gosProvider = LocationManager.GPS_PROVIDER;
        String networkProvider = LocationManager.NETWORK_PROVIDER;
        mLocationmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mLocationmanager.requestLocationUpdates(gosProvider, 1000,20, this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        sprinter_map = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        sprinter_map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        sprinter_map.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        sprinter_map_circles = new ArrayList<>();
    }

    @Override
    public void onLocationChanged(Location location) {
        sprinter_map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 17));

        if(null != current_location){
            current_location.remove();
        }

        if(null != current_location_radius){
            current_location_radius.remove();
        }

        current_location_radius = sprinter_map.addCircle(new CircleOptions()
                .center(new LatLng(location.getLatitude(), location.getLongitude()))
                .radius(50)
                .strokeColor(Color.RED)
                .strokeWidth(3)
                .fillColor(Color.TRANSPARENT));

        current_location = sprinter_map.addCircle(new CircleOptions()
                .center(new LatLng(location.getLatitude(), location.getLongitude()))
                .radius(5)
                .strokeColor(Color.BLUE)
                .fillColor(Color.BLUE));

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


}
