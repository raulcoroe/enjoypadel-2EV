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

        db = Room.databaseBuilder(context,
                AppDatabase.class, "tournament").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
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


    @Override
    public void deleteMatch(Match match, OnDeleteMatchListener listener) {
        Call<Void> callMatches = api.deleteMatch(match.getId());
        callMatches.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                listener.onDeleteMatchSuccess("Partido eliminado");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onDeleteMatchError("El partido no se ha podido eliminar");
            }
        });
    }

}
