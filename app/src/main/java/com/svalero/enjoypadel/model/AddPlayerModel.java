package com.svalero.enjoypadel.model;

import androidx.room.Room;

import com.svalero.enjoypadel.api.EnjoyPadelApi;
import com.svalero.enjoypadel.api.EnjoyPadelApiInterface;
import com.svalero.enjoypadel.contract.AddPlayerContract;
import com.svalero.enjoypadel.database.AppDatabase;
import com.svalero.enjoypadel.domain.Player;
import com.svalero.enjoypadel.view.AddPlayerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPlayerModel implements AddPlayerContract.Model {

    private AddPlayerView view;
    private AppDatabase db;
    private EnjoyPadelApiInterface api;

    public AddPlayerModel(AddPlayerView view) {
        this.view = view;

        db = Room.databaseBuilder(view.getApplicationContext(),
                AppDatabase.class, "tournament").allowMainThreadQueries().build();
        api = EnjoyPadelApi.buildInstance();
    }

    @Override
    public void addPlayer(Player player, OnAddPlayerListener listener) {
        api.addPlayer(player).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                listener.onAddPlayerSuccess("Jugador añadido");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onAddPlayerError("El jugador no ha sido añadido");
            }
        });
    }

    @Override
    public void modifyPlayer(Player player, OnModifyPlayerListener listener) {
        api.modifyPlayer(player.getId(), player).enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                listener.onModifyPlayerSuccess("Jugador modificado");
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                listener.onModifyPlayerError("El jugador no ha sido modificado");
            }
        });
    }
}