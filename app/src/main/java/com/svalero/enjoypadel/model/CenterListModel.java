package com.svalero.enjoypadel.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.enjoypadel.contract.CenterListContract;
import com.svalero.enjoypadel.database.AppDatabase;
import com.svalero.enjoypadel.domain.Center;

import java.util.ArrayList;
import java.util.List;

public class CenterListModel implements CenterListContract.Model {

    private List<Center> centers;
    private AppDatabase db;
    private Context context;

    public CenterListModel(Context context) {
        this.context = context;
        centers = new ArrayList<>();
        db = Room.databaseBuilder(context,
                AppDatabase.class, "tournament").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
    }

    @Override
    public List<Center> loadAllCenters() {
        centers.clear();
        centers = db.centerDao().getAll();
        return centers;
    }

    @Override
    public void deleteCenter(Center center) {
        db.centerDao().delete(center);
    }
}
