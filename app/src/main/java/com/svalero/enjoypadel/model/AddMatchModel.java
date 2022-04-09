package com.svalero.enjoypadel.model;

import androidx.room.Room;

import com.svalero.enjoypadel.api.EnjoyPadelApi;
import com.svalero.enjoypadel.api.EnjoyPadelApiInterface;
import com.svalero.enjoypadel.contract.AddMatchContract;
import com.svalero.enjoypadel.database.AppDatabase;
import com.svalero.enjoypadel.domain.Match;
import com.svalero.enjoypadel.domain.dto.MatchDTO;
import com.svalero.enjoypadel.view.AddMatchView;

import org.modelmapper.ModelMapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMatchModel implements AddMatchContract.Model {

    private AddMatchView view;
    private AppDatabase db;
    private EnjoyPadelApiInterface api;

    public AddMatchModel(AddMatchView view) {
        this.view = view;

        api = EnjoyPadelApi.buildInstance();
    }

    @Override
    public void addMatch(Match match, OnAddMatchListener listener) {

        ModelMapper mapper = new ModelMapper();
        MatchDTO matchDTO = mapper.map(match, MatchDTO.class);
        matchDTO.setPlayer1Id(match.getPlayers(0).getId());
        matchDTO.setPlayer2Id(match.getPlayers(1).getId());
        matchDTO.setPlayer3Id(match.getPlayers(2).getId());
        matchDTO.setPlayer4Id(match.getPlayers(3).getId());
        matchDTO.setCenterId(match.getSportCenter().getId());

        api.addMatch(matchDTO).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                listener.onAddMatchSuccess("Partido añadido");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onAddMatchError("No se ha añadido el partido");
                t.printStackTrace();
            }
        });
    }

    @Override
    public void modifyMatch(Match match, OnModifyMatchListener listener) {
        api.modifyMatch(match.getId(), match).enqueue(new Callback<Match>() {
            @Override
            public void onResponse(Call<Match> call, Response<Match> response) {
                listener.onModifyMatchSuccess("Partido modificado");
            }

            @Override
            public void onFailure(Call<Match> call, Throwable t) {
                listener.onModifyMatchError("El partido no se ha modificado");
                t.printStackTrace();
            }
        });
    }
}