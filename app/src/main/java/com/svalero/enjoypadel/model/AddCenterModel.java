package com.svalero.enjoypadel.model;

import android.content.Context;
import android.os.StrictMode;

import androidx.room.Room;

import com.svalero.enjoypadel.api.EnjoyPadelApi;
import com.svalero.enjoypadel.api.EnjoyPadelApiInterface;
import com.svalero.enjoypadel.contract.AddCenterContract;
import com.svalero.enjoypadel.contract.AddPlayerContract;
import com.svalero.enjoypadel.database.AppDatabase;
import com.svalero.enjoypadel.domain.Center;
import com.svalero.enjoypadel.domain.Player;
import com.svalero.enjoypadel.view.AddPlayerView;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCenterModel implements AddCenterContract.Model {

    private Context context;
    private AppDatabase db;
    private EnjoyPadelApiInterface api;

    public AddCenterModel(Context context) {
        this.context = context;

        db = Room.databaseBuilder(context,
                AppDatabase.class, "tournament").allowMainThreadQueries().build();
        api = EnjoyPadelApi.buildInstance();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void addCenter(Center center, OnAddCenterListener listener) {

        Call<Center> call = api.addCenter(center);
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (call.isExecuted()){
            listener.onAddCenterSuccess("Centro deportivo añadido");
        } else {
            listener.onAddCenterError("El centro deportivo no se ha podido añadir");
        }
    }
}