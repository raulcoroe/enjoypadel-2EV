package com.svalero.enjoypadel.model;

import androidx.room.Room;

import com.svalero.enjoypadel.contract.AddMatchContract;
import com.svalero.enjoypadel.database.AppDatabase;
import com.svalero.enjoypadel.domain.Match;
import com.svalero.enjoypadel.view.AddMatchView;

public class AddMatchModel implements AddMatchContract.Model {

    private AddMatchView view;
    private AppDatabase db;

    public AddMatchModel(AddMatchView view) {
        this.view = view;

        db = Room.databaseBuilder(view.getApplicationContext(),
                AppDatabase.class, "tournament").allowMainThreadQueries().build();
    }

    @Override
    public void addMatch(Match match) {
        db.matchDao().insert(match);
    }

    @Override
    public void modifyMatch(Match match) {
        db.matchDao().update(match);
    }
}