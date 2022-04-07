package com.svalero.enjoypadel.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.enjoypadel.contract.CenterDetailContract;
import com.svalero.enjoypadel.contract.MatchDetailContract;
import com.svalero.enjoypadel.database.AppDatabase;
import com.svalero.enjoypadel.domain.Center;
import com.svalero.enjoypadel.domain.Match;
import com.svalero.enjoypadel.view.MatchDetailView;

public class MatchDetailModel implements MatchDetailContract.Model {

    private Context context;
    private AppDatabase db;

    public MatchDetailModel(Context context) {
        this.context = context;

        db = Room.databaseBuilder(context,
                AppDatabase.class, "tournament").allowMainThreadQueries().build();
    }

    @Override
    public Match matchDetail(int matchId) {
        Match match = db.matchDao().findById(matchId);
        return match;
    }
}
