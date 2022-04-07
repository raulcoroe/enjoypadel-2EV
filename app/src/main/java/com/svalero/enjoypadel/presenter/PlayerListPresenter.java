package com.svalero.enjoypadel.presenter;

import com.svalero.enjoypadel.contract.PlayerListContract;
import com.svalero.enjoypadel.domain.Player;
import com.svalero.enjoypadel.model.PlayerListModel;
import com.svalero.enjoypadel.view.PlayerListView;

import java.util.List;

public class PlayerListPresenter implements PlayerListContract.Presenter, PlayerListContract.Model.OnLoadPlayersListener {

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
        model.deletePlayer(player);
    }

    @Override
    public void onLoadPlayersSuccess(List<Player> players) {
        view.listAllPlayers(players);
    }

    @Override
    public void onLoadPlayersError(String message) {
        view.showMessage(message);
    }
}
