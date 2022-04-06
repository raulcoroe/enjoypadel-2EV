package com.svalero.enjoypadel.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.enjoypadel.contract.AddPlayerContract;
import com.svalero.enjoypadel.database.AppDatabase;
import com.svalero.enjoypadel.domain.Player;
import com.svalero.enjoypadel.view.AddPlayerView;

public class AddplayerModel implements AddPlayerContract.Model {

    private AddPlayerView view;
    private AppDatabase db;

    public AddplayerModel(AddPlayerView view) {
        this.view = view;

        db = Room.databaseBuilder(view.getApplicationContext(),
                AppDatabase.class, "tournament").allowMainThreadQueries().build();
    }

    @Override
    public void addPlayer(Player player) {
        db.playerDao().insert(player);
    }

    @Override
    public void modifyPlayer(Player player) {
        db.playerDao().update(player);
    }
}