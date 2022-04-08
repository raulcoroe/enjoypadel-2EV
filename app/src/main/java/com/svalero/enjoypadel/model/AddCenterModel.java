package com.svalero.enjoypadel.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.enjoypadel.api.EnjoyPadelApi;
import com.svalero.enjoypadel.api.EnjoyPadelApiInterface;
import com.svalero.enjoypadel.contract.AddCenterContract;
import com.svalero.enjoypadel.contract.AddPlayerContract;
import com.svalero.enjoypadel.database.AppDatabase;
import com.svalero.enjoypadel.domain.Center;
import com.svalero.enjoypadel.domain.Player;
import com.svalero.enjoypadel.view.AddPlayerView;

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
    }

    @Override
    public void addCenter(Center center, OnAddCenterListener listener) {
        api.addCenter(center).enqueue(new Callback<Center>() {
            @Override
            public void onResponse(Call<Center> call, Response<Center> response) {
                listener.onAddCenterSuccess("Centro deportivo añadido");
            }

            @Override
            public void onFailure(Call<Center> call, Throwable t) {
                listener.onAddCenterError("No se ha podido añadir un centro");
            }
        });
    }
}