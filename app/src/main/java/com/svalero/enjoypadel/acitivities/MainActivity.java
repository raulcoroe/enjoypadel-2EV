package com.svalero.enjoypadel.acitivities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.svalero.enjoypadel.R;
import com.svalero.enjoypadel.view.MatchListView;
import com.svalero.enjoypadel.view.PlayerListView;
import com.svalero.enjoypadel.view.CenterListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void selectPlayers(View view){
        Intent intent = new Intent(this, PlayerListView.class);
        startActivity(intent);
    }

    public void selectMatches(View view){
        Intent intent = new Intent(this, MatchListView.class);
        startActivity(intent);
    }

    public void selectLocations(View view){
        Intent intent = new Intent(this, CenterListView.class);
        startActivity(intent);
    }
}