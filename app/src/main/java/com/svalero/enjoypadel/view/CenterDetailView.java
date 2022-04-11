package com.svalero.enjoypadel.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.maps.model.DirectionsResult;
import com.svalero.enjoypadel.R;
import com.svalero.enjoypadel.contract.CenterDetailContract;
import com.svalero.enjoypadel.domain.Center;
import com.svalero.enjoypadel.presenter.CenterDetailPresenter;
import com.svalero.enjoypadel.task.RouteTask;
import com.svalero.enjoypadel.utils.DirectionUtils;


import java.util.List;
import java.util.concurrent.ExecutionException;

public class CenterDetailView extends AppCompatActivity implements OnMapReadyCallback, CenterDetailContract.View {

    private GoogleMap map;
    private LatLng centerLocation;
    private LatLng ubicacionUsuario;
    private CenterDetailPresenter presenter;
    private String name;
    private FusedLocationProviderClient fusedLocationClient;
    private Location userLocation;
    private double wayLatitude;
    private double wayLongitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_detail);

        presenter = new CenterDetailPresenter(this);
        Intent intent = getIntent();
        int centerId = intent.getIntExtra("centerId", 0);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        presenter.centerDetail(centerId);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        googleMap.setMyLocationEnabled(true);
        map.addMarker(new MarkerOptions()
                .position(centerLocation)
                .snippet(getString(R.string.smipper_text))
                .title(name));

    }

    private void paintRoute(DirectionsResult result, int position) {
        List<com.google.maps.model.LatLng> routePath = result.routes[position].overviewPolyline.decodePath();
        map.addPolyline(new PolylineOptions()
                .add(DirectionUtils.fromMapsToDirections(routePath))
                .color(Color.RED));
        Log.d("sanvalero", routePath.toString());
    }

    @Override
    public void loadCenterDetail(Center center) {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_center_view);
        if (mapFragment != null)
            mapFragment.getMapAsync(this);
        centerLocation = new LatLng(Double.parseDouble(center.getLatitude()), Double.parseDouble(center.getLongitude()));
        name = center.getName();
        TextView nameTv = findViewById(R.id.center_name_view);
        nameTv.setText(name);

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    wayLatitude = location.getLatitude();
                    wayLongitude = location.getLongitude();
                    startTask();
                }
            }
        });
    }

    public void startTask(){
        LatLng ubicacionUsuario = new LatLng(wayLatitude, wayLongitude);
        RouteTask routeTask = new RouteTask(this,ubicacionUsuario, centerLocation);
        routeTask.execute();
        DirectionsResult result;
        try {
            result = routeTask.get();
            if (result.routes.length != 0)
                paintRoute(result, 0);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}