package com.svalero.enjoypadel.model;

import android.os.StrictMode;

import com.svalero.enjoypadel.R;
import com.svalero.enjoypadel.api.EnjoyPadelApi;
import com.svalero.enjoypadel.api.EnjoyPadelApiInterface;
import com.svalero.enjoypadel.contract.AddMatchContract;
import com.svalero.enjoypadel.database.AppDatabase;
import com.svalero.enjoypadel.domain.Match;
import com.svalero.enjoypadel.domain.Player;
import com.svalero.enjoypadel.domain.dto.MatchDTO;
import com.svalero.enjoypadel.view.AddMatchView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void addMatch(Match match, OnAddMatchListener listener){

        List<Player> players = Arrays.asList(match.getPlayers());
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setRound(match.getRound());
        matchDTO.setDate(match.getDate());
        matchDTO.setMatchScore(match.getMatchScore());
        matchDTO.setDuration(match.getDuration());
        matchDTO.setPlayer1(players.get(0).getId());
        matchDTO.setPlayer2(players.get(1).getId());
        matchDTO.setPlayer3(players.get(2).getId());
        matchDTO.setPlayer4(players.get(3).getId());
        matchDTO.setCenter(match.getCenter().getId());

        Call<Void> call = api.addMatch(matchDTO);
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (call.isExecuted()){
            listener.onAddMatchSuccess("Partido añadido");
        } else {
            listener.onAddMatchError("El partido no se ha podido añadir");
        }
    }
}