package com.svalero.enjoypadel.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.enjoypadel.api.EnjoyPadelApi;
import com.svalero.enjoypadel.api.EnjoyPadelApiInterface;
import com.svalero.enjoypadel.contract.MatchListContract;
import com.svalero.enjoypadel.database.AppDatabase;
import com.svalero.enjoypadel.domain.Match;
import com.svalero.enjoypadel.view.MatchListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchListModel implements MatchListContract.Model {

    private Context context;
    private AppDatabase db;
    private EnjoyPadelApiInterface api;

    public MatchListModel(Context applicationContext) {
        this.context = applicationContext;

        api = EnjoyPadelApi.buildInstance();
    }

    @Override
    public void loadAllMatches(OnLoadMatchesListener listener) {
        Call<List<Match>> callMatches = api.getMatches();
        callMatches.enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                List<Match> matches = response.body();
                listener.onLoadMatchesSuccess(matches);
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {
                listener.onLoadMatchesError("No se ha podido cargar la lista de partidos");
            }
        });
        api.getMatches();
    }

}
