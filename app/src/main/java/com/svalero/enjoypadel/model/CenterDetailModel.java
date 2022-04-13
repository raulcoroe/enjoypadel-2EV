package com.svalero.enjoypadel.model;

import android.content.Context;

import com.svalero.enjoypadel.api.EnjoyPadelApi;
import com.svalero.enjoypadel.api.EnjoyPadelApiInterface;
import com.svalero.enjoypadel.contract.CenterDetailContract;
import com.svalero.enjoypadel.database.AppDatabase;
import com.svalero.enjoypadel.domain.Center;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CenterDetailModel implements CenterDetailContract.Model {

    private Context context;
    private AppDatabase db;
    private EnjoyPadelApiInterface api;

    public CenterDetailModel(Context context) {
        this.context = context;

        api = EnjoyPadelApi.buildInstance();
    }

    @Override
    public void centerDetail(int centerId, OnShowCenter listener) {
        Call<Center> centerCall = api.findCenterById(centerId);
        centerCall.enqueue(new Callback<Center>() {
            @Override
            public void onResponse(Call<Center> call, Response<Center> response) {
                listener.onShowCenterSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Center> call, Throwable t) {
                listener.onShowCenterError("No se ha podido mostrar el centro deportivo");
            }
        });
    }
}
