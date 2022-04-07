package com.svalero.enjoypadel.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.enjoypadel.contract.AddCenterContract;
import com.svalero.enjoypadel.contract.AddPlayerContract;
import com.svalero.enjoypadel.database.AppDatabase;
import com.svalero.enjoypadel.domain.Center;
import com.svalero.enjoypadel.domain.Player;
import com.svalero.enjoypadel.view.AddPlayerView;

public class AddCenterModel implements AddCenterContract.Model {

    private Context context;
    private AppDatabase db;

    public AddCenterModel(Context context) {
        this.context = context;

        db = Room.databaseBuilder(context,
                AppDatabase.class, "tournament").allowMainThreadQueries().build();
    }

    @Override
    public void addCenter(Center center) {
        db.centerDao().insert(center);
    }
}