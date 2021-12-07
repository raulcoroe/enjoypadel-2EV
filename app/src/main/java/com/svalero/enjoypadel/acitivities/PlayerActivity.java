package com.svalero.enjoypadel.acitivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.widget.AdapterView.AdapterContextMenuInfo;

import com.svalero.enjoypadel.R;
import com.svalero.enjoypadel.adapter.PlayerAdapter;
import com.svalero.enjoypadel.database.AppDatabase;
import com.svalero.enjoypadel.domain.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerActivity extends AppCompatActivity {

    private List<Player> players;
    private PlayerAdapter playerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        players = new ArrayList<>();
        ListView lvPlayers = findViewById(R.id.player_list);
        playerAdapter = new PlayerAdapter(this, players);
        lvPlayers.setAdapter(playerAdapter);
        registerForContextMenu(lvPlayers);
    }

    @Override
    protected void onResume() {
        super.onResume();

        players.clear();
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "tournament").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();

        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        boolean available = preferencias.getBoolean("available_players", false);
        boolean hideSurname = preferencias.getBoolean("hide_surname", false);
        players.addAll(db.playerDao().getAll());

        if (available) {
            players.clear();
            players.addAll(db.playerDao().getAvailablePlayers());
        }

        if (hideSurname) {
            for (Player player : players) {
                player.setSurname("");
            }
        }
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

        if (item.getItemId() == R.id.preferences) {
            Intent intent = new Intent(this, Preferences.class);
            startActivity(intent);
            return true;
        }

        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        //Preferencia sobre restringir modificar y eliminar
        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        boolean removeDelete = preferencias.getBoolean("restrict", false);

        if (!removeDelete)
        getMenuInflater().inflate(R.menu.contextual_menu, menu);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        Player player = players.get(info.position);

        switch (item.getItemId()) {
            case R.id.action_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure?")
                        .setPositiveButton("Si",
                                (dialog, which) -> {
                                    AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                                            AppDatabase.class, "tournament").allowMainThreadQueries()
                                            .fallbackToDestructiveMigration().build();
                                    db.playerDao().delete(player);
                                    players.remove(player);
                                    playerAdapter.notifyDataSetChanged();
                                }
                        ).setNegativeButton("No",
                        (dialog, which) -> dialog.dismiss());
                builder.create().show();
                return true;


            case R.id.action_edit:
                Intent intent = new Intent(this, AddPlayerActivity.class);
                intent.putExtra("name", player.getName());
                intent.putExtra("surname", player.getSurname());
                intent.putExtra("level", player.getLevel());
                intent.putExtra("availability", player.isAvailability());
                intent.putExtra("image", player.getImage());
                intent.putExtra("modify", 1);
                intent.putExtra("playerId", player.getId());
                startActivity(intent);
                return true;
        }
        return super.onContextItemSelected(item);
    }
}