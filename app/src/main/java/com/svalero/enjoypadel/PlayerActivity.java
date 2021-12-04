package com.svalero.enjoypadel;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.svalero.enjoypadel.database.AppDatabase;
import com.svalero.enjoypadel.domain.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerActivity extends AppCompatActivity {

    private List<Player> players;
    private ArrayAdapter<Player> playerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        players = new ArrayList<>();
        ListView lvPlayers = findViewById(R.id.player_list);
        playerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, players);
        lvPlayers.setAdapter(playerAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        players.clear();
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "players").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
        players.addAll(db.playerDao().getAll());

        playerAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_player_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_player) {
            Intent intent = new Intent(this, AddPlayerActivity.class);
            startActivity(intent);
            return true;
        }

        return false;
    }
}