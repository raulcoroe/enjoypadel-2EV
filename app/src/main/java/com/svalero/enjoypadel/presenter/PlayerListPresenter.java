package com.svalero.enjoypadel.presenter;

import com.svalero.enjoypadel.contract.PlayerListContract;
import com.svalero.enjoypadel.domain.Player;
import com.svalero.enjoypadel.model.CenterListModel;
import com.svalero.enjoypadel.model.PlayerListModel;
import com.svalero.enjoypadel.view.AddMatchView;
import com.svalero.enjoypadel.view.MatchListView;
import com.svalero.enjoypadel.view.PlayerListView;

import java.util.List;

public class PlayerListPresenter implements PlayerListContract.Presenter, PlayerListContract.Model.OnLoadPlayersListener,PlayerListContract.Model.OnDeletePlayerListener {

    private PlayerListModel model;
    private PlayerListView view;

    public PlayerListPresenter(PlayerListView view) {
        model = new PlayerListModel(view.getApplicationContext());
        this.view = view;
    }


    @Override
    public void loadAllPlayers() {
        model.loadAllPlayers(this);
    }

    @Override
    public void deletePlayer(Player player) {
        model.deletePlayer(player, this);
    }

    @Override
    public void onLoadPlayersSuccess(List<Player> players) {
        view.listAllPlayers(players);
    }

    @Override
    public void onLoadPlayersError(String message) {
        view.showMessage(message);
    }

    @Override
    public void onDeletePlayerSuccess(String message) {
        view.showMessage(message);
    }

    @Override
    public void onDeletePlayerError(String message) {
        view.showMessage(message);
    }
}
