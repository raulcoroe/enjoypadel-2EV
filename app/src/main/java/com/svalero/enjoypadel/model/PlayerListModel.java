package com.svalero.enjoypadel.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.room.Room;

import com.svalero.enjoypadel.contract.PlayerListContract;
import com.svalero.enjoypadel.database.AppDatabase;
import com.svalero.enjoypadel.domain.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerListModel implements PlayerListContract.Model {

    private List<Player> players;
    private AppDatabase db;
    private Context context;

    public PlayerListModel(Context context) {
        this.context = context;
        players = new ArrayList<>();
        db = Room.databaseBuilder(context,
                AppDatabase.class, "tournament").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
    }

    @Override
    public List<Player> loadAllPlayers() {
        players.clear();

        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(context);
        boolean available = preferencias.getBoolean("available_players", false);
        boolean hideSurname = preferencias.getBoolean("hide_surname", false);
        if (available) {
            players.addAll(db.playerDao().getAvailablePlayers());
        } else {
            players.addAll(db.playerDao().getAll());
        }

        if (hideSurname) {
            for (Player player : players) {
                player.setSurname("");
            }
        }
        return players;
    }

    @Override
    public void deletePlayer(Player player) {
        db.playerDao().delete(player);
    }
}
