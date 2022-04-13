package com.svalero.enjoypadel.model;

import android.os.StrictMode;

import androidx.room.Room;

import com.svalero.enjoypadel.api.EnjoyPadelApi;
import com.svalero.enjoypadel.api.EnjoyPadelApiInterface;
import com.svalero.enjoypadel.contract.AddPlayerContract;
import com.svalero.enjoypadel.database.AppDatabase;
import com.svalero.enjoypadel.domain.Player;
import com.svalero.enjoypadel.view.AddPlayerView;

import java.io.IOException;

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

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void addPlayer(Player player, OnAddPlayerListener listener) {

        Call<Player> call = api.addPlayer(player);
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (call.isExecuted()){
            listener.onAddPlayerSuccess("Jugador añadido");
        } else {
            listener.onAddPlayerError("El jugador no se ha podido añadir");
        }

    }

    @Override
    public void modifyPlayer(Player player, OnModifyPlayerListener listener) {

        Call<Player> call = api.modifyPlayer(player.getId(), player);
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (call.isExecuted()){
            listener.onModifyPlayerSuccess("Jugador añadido");
        } else {
            listener.onModifyPlayerError("El jugador no se ha podido añadir");
        }
    }
}