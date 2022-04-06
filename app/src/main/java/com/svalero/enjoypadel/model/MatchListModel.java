package com.svalero.enjoypadel.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.enjoypadel.contract.MatchListContract;
import com.svalero.enjoypadel.database.AppDatabase;
import com.svalero.enjoypadel.domain.Match;
import com.svalero.enjoypadel.view.MatchListView;

import java.util.List;

public class MatchListModel implements MatchListContract.Model {

    private Context context;
    private AppDatabase db;

    public MatchListModel(Context applicationContext) {
        this.context = applicationContext;

        db = Room.databaseBuilder(context,
                AppDatabase.class, "tournament").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
    }


    @Override
    public List<Match> loadAllMatches() {
        List<Match> matches = db.matchDao().getAll();
        return matches;
    }


    @Override
    public void deleteMatch(Match match) {
        db.matchDao().delete(match);
    }
}
