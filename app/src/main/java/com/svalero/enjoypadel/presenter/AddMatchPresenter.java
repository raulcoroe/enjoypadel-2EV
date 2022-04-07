package com.svalero.enjoypadel.presenter;


import com.svalero.enjoypadel.contract.AddMatchContract;
import com.svalero.enjoypadel.domain.Center;
import com.svalero.enjoypadel.domain.Match;
import com.svalero.enjoypadel.domain.Player;
import com.svalero.enjoypadel.model.AddMatchModel;
import com.svalero.enjoypadel.model.CenterListModel;
import com.svalero.enjoypadel.model.PlayerListModel;
import com.svalero.enjoypadel.view.AddMatchView;

import java.util.ArrayList;
import java.util.List;

public class AddMatchPresenter implements AddMatchContract.Presenter {

    private AddMatchModel model;
    private PlayerListModel modelPlayer;
    private CenterListModel modelCenter;
    private AddMatchView view;
    private List<Player> players;
    private List<Center> centers;

    public AddMatchPresenter(AddMatchView view) {
        this.view = view;
        model = new AddMatchModel(view);
        players = new ArrayList<>();
        centers = new ArrayList<>();
    }

    @Override
    public void addMatch(Match match) {
        model.addMatch(match);
    }

    @Override
    public void modifyMatch(Match match) {
        model.modifyMatch(match);
    }

    public void chargeSpinners() {
        players = modelPlayer.loadAllPlayers();
        centers = modelCenter.loadAllCenters();
        view.chargeElements(players, centers);
    }
}
