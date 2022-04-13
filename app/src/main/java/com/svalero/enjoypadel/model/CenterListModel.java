package com.svalero.enjoypadel.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.enjoypadel.api.EnjoyPadelApi;
import com.svalero.enjoypadel.api.EnjoyPadelApiInterface;
import com.svalero.enjoypadel.contract.CenterListContract;
import com.svalero.enjoypadel.database.AppDatabase;
import com.svalero.enjoypadel.domain.Center;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CenterListModel implements CenterListContract.Model {

    private List<Center> centers;
    private AppDatabase db;
    private Context context;
    private EnjoyPadelApiInterface api;

    public CenterListModel(Context context) {
        this.context = context;
        centers = new ArrayList<>();
        db = Room.databaseBuilder(context,
                AppDatabase.class, "tournament").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
        api = EnjoyPadelApi.buildInstance();
    }


    @Override
    public void loadAllCenters(OnLoadCentersListener listener) {
        Call<List<Center>> callCenters = api.getCenters();
        callCenters.enqueue(new Callback<List<Center>>() {
            @Override
            public void onResponse(Call<List<Center>> call, Response<List<Center>> response) {
                List<Center> centers = response.body();
                listener.onLoadCentersSuccess(centers);
            }

            @Override
            public void onFailure(Call<List<Center>> call, Throwable t) {
                listener.onLoadCentersError("No se han podido visualizar los centros deportivos");
                t.printStackTrace();
            }
        });
    }

    @Override
    public void deleteCenter(Center center, OnDeleteCenterListener listener) {
        api.deleteCenter(center.getId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                listener.onDeleteCenterSuccess("Centro deportivo eliminado");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onDeleteCenterError("No se ha podido eliminar el centro deportivo");
            }
        });
    }
}
