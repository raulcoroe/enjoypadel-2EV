package com.svalero.enjoypadel.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.enjoypadel.api.EnjoyPadelApi;
import com.svalero.enjoypadel.api.EnjoyPadelApiInterface;
import com.svalero.enjoypadel.contract.CenterDetailContract;
import com.svalero.enjoypadel.contract.MatchDetailContract;
import com.svalero.enjoypadel.database.AppDatabase;
import com.svalero.enjoypadel.domain.Center;
import com.svalero.enjoypadel.domain.Match;
import com.svalero.enjoypadel.view.MatchDetailView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchDetailModel implements MatchDetailContract.Model {

    private Context context;
    private AppDatabase db;
    private EnjoyPadelApiInterface api;

    public MatchDetailModel(Context context) {
        this.context = context;

        api = EnjoyPadelApi.buildInstance();
    }

    @Override
    public void matchDetail(int matchId, OnShowMatch listener) {
        Call<Match> callMatch = api.findMatchById(matchId);
        callMatch.enqueue(new Callback<Match>() {
            @Override
            public void onResponse(Call<Match> call, Response<Match> response) {
                listener.onShowMatchSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Match> call, Throwable t) {
                listener.onShowMatchError("El partido no se puede visualizar");
                t.printStackTrace();
            }
        });
    }
}
