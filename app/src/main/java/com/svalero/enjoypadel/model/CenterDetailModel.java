package com.svalero.enjoypadel.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.enjoypadel.contract.CenterDetailContract;
import com.svalero.enjoypadel.database.AppDatabase;
import com.svalero.enjoypadel.domain.Center;

public class CenterDetailModel implements CenterDetailContract.Model {

    private Context context;
    private AppDatabase db;

    public CenterDetailModel(Context context) {
        this.context = context;

        db = Room.databaseBuilder(context,
                AppDatabase.class, "tournament").allowMainThreadQueries().build();
    }

    @Override
    public Center centerDetail(int centerId) {
        Center center = db.centerDao().findById(centerId);
        return center;
    }
}
