package com.svalero.enjoypadel.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.room.Room;

import com.svalero.enjoypadel.api.EnjoyPadelApi;
import com.svalero.enjoypadel.api.EnjoyPadelApiInterface;
import com.svalero.enjoypadel.contract.PlayerListContract;
import com.svalero.enjoypadel.database.AppDatabase;
import com.svalero.enjoypadel.domain.Player;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public void loadAllPlayers(OnLoadPlayersListener listener) {
        players.clear();

        EnjoyPadelApiInterface api = EnjoyPadelApi.buildInstance();


        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(context);
        boolean available = preferencias.getBoolean("available_players", false);
        boolean hideSurname = preferencias.getBoolean("hide_surname", false);

        Call<List<Player>> callPlayers;
        if (available)
            callPlayers = api.getAvailablePlayers();
        else
            callPlayers = api.getPlayers();

        callPlayers.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                players = response.body();
                if (hideSurname) {
                    for (Player player : players) {
                        player.setSurname("");
                    }
                }
                listener.onLoadPlayersSuccess(players);
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                listener.onLoadPlayersError("Se ha producido un error al conectar con el servidor");
                t.printStackTrace();
            }
        });
    }


    @Override
    public void deletePlayer(Player player) {
        db.playerDao().delete(player);
    }
}
