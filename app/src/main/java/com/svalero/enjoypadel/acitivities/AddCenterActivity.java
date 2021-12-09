package com.svalero.enjoypadel.acitivities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.room.Room;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.svalero.enjoypadel.R;
import com.svalero.enjoypadel.database.AppDatabase;
import com.svalero.enjoypadel.domain.SportCenter;

public class AddCenterActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap map;
    private LatLng location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_center);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_center);
        if (mapFragment != null)
            mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        googleMap.setOnMapClickListener(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        googleMap.setMyLocationEnabled(true);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        map.clear();
        location = latLng;
        TextView nameCenterTv = findViewById(R.id.center_name);
        map.addMarker(new MarkerOptions()
                .position(latLng)
                .snippet("Sport center location")
                .title(nameCenterTv.getText().toString()));
    }

    public void addCenter(View view){
        TextView nameCenterTv = findViewById(R.id.center_name);
        SportCenter center = new SportCenter();

        if (nameCenterTv.getText().toString().equals("")){
            Toast.makeText(this, "You must enter the name of the sport center", Toast.LENGTH_SHORT).show();
        } else {
            AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "tournament").allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build();
            center.setName(nameCenterTv.getText().toString());
            center.setLatitude(String.valueOf(location.latitude));
            center.setLongitude(String.valueOf(location.longitude));
            db.sportCenterDao().insert(center);
            finish();
        }
    }
}