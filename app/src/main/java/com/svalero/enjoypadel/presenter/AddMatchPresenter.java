package com.svalero.enjoypadel.presenter;


import com.svalero.enjoypadel.contract.AddMatchContract;
import com.svalero.enjoypadel.contract.CenterListContract;
import com.svalero.enjoypadel.contract.PlayerListContract;
import com.svalero.enjoypadel.domain.Center;
import com.svalero.enjoypadel.domain.Match;
import com.svalero.enjoypadel.domain.Player;
import com.svalero.enjoypadel.model.AddMatchModel;
import com.svalero.enjoypadel.model.CenterListModel;
import com.svalero.enjoypadel.model.PlayerListModel;
import com.svalero.enjoypadel.view.AddMatchView;

import java.util.ArrayList;
import java.util.List;

public class AddMatchPresenter implements AddMatchContract.Presenter, AddMatchContract.Model.OnAddMatchListener, AddMatchContract.Model.OnModifyMatchListener, PlayerListContract.Model.OnLoadPlayersListener, CenterListContract.Model.OnLoadCentersListener {

    private AddMatchModel model;
    private PlayerListModel modelPlayer;
    private CenterListModel modelCenter;
    private AddMatchView view;
    private List<Player> players;
    private List<Center> centers;

    public AddMatchPresenter(AddMatchView view) {
        this.view = view;
        model = new AddMatchModel(view);
        modelPlayer = new PlayerListModel(view);
        modelCenter = new CenterListModel(view);
        players = new ArrayList<>();
        centers = new ArrayList<>();
    }

    public void loadPlayers(){
        modelPlayer.loadAllPlayers(this);
    }

    public void loadCenters(){
        modelCenter.loadAllCenters(this);
    }

    @Override
    public void addMatch(Match match) {
        model.addMatch(match, this);
    }

    @Override
    public void modifyMatch(Match match) {
        model.modifyMatch(match, this);
    }


    @Override
    public void onAddMatchSuccess(String message) {
        view.showMessage(message);
    }

    @Override
    public void onAddMatchError(String message) {
        view.showMessage(message);
    }

    @Override
    public void onModifyMatchSuccess(String message) {
        view.showMessage(message);
    }

    @Override
    public void onModifyMatchError(String message) {
        view.showMessage(message);
    }

    @Override
    public void onLoadPlayersSuccess(List<Player> players) {
        view.loadPlayers(players);
    }

    @Override
    public void onLoadPlayersError(String message) {
        view.showMessage(message);
    }

    @Override
    public void onLoadCentersSuccess(List<Center> centers) {
        view.loadCenters(centers);
    }

    @Override
    public void onLoadCentersError(String message) {
        view.showMessage(message);
    }
}
