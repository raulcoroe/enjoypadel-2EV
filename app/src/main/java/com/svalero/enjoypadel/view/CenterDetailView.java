package com.svalero.enjoypadel.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.svalero.enjoypadel.R;
import com.svalero.enjoypadel.contract.CenterDetailContract;
import com.svalero.enjoypadel.domain.Center;
import com.svalero.enjoypadel.presenter.CenterDetailPresenter;

public class CenterDetailView extends AppCompatActivity implements OnMapReadyCallback, CenterDetailContract.View {

    private GoogleMap map;
    private LatLng location;
    private CenterDetailPresenter presenter;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_detail);

        presenter = new CenterDetailPresenter(this);
        Intent intent = getIntent();
        int centerId = intent.getIntExtra("centerId" ,0);
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
                .position(location)
                .snippet(getString(R.string.smipper_text))
                .title(name));

    }

    @Override
    public void loadCenterDetail(Center center) {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_center_view);
        if (mapFragment != null)
            mapFragment.getMapAsync(this);
        location = new LatLng(Double.parseDouble(center.getLatitude()), Double.parseDouble(center.getLongitude()));
        name = center.getName();
        TextView nameTv = findViewById(R.id.center_name_view);
        nameTv.setText(name);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}