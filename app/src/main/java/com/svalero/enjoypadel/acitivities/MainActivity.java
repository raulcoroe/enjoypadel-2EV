package com.svalero.enjoypadel.acitivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.svalero.enjoypadel.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void selectPlayers(View view){
        Intent intent = new Intent(this, PlayerActivity.class);
        startActivity(intent);
    }

    public void selectMatches(View view){
        Intent intent = new Intent(this, MatchActivity.class);
        startActivity(intent);
    }

    public void selectLocations(View view){
        Intent intent = new Intent(this, SportCenterActivity.class);
        startActivity(intent);
    }
}