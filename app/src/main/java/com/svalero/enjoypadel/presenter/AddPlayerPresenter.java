package com.svalero.enjoypadel.presenter;

import com.svalero.enjoypadel.contract.AddPlayerContract;
import com.svalero.enjoypadel.domain.Player;
import com.svalero.enjoypadel.model.AddPlayerModel;
import com.svalero.enjoypadel.view.AddPlayerView;

public class AddPlayerPresenter implements AddPlayerContract.Presenter, AddPlayerContract.Model.OnAddPlayerListener, AddPlayerContract.Model.OnModifyPlayerListener {

    private AddPlayerModel model;
    private AddPlayerView view;

    public AddPlayerPresenter(AddPlayerView view) {
        this.view = view;
        model = new AddPlayerModel(view);

    }

    @Override
    public void addPlayer(Player player) {
        model.addPlayer(player, this);
    }

    @Override
    public void modifyPlayer(Player player) {
        model.modifyPlayer(player, this);
    }

    @Override
    public void onAddPlayerSuccess(String message) {
        view.showMessage(message);
    }

    @Override
    public void onAddPlayerError(String message) {
        view.showMessage(message);
    }

    @Override
    public void onModifyPlayerSuccess(String message) {
        view.showMessage(message);
    }

    @Override
    public void onModifyPlayerError(String message) {
        view.showMessage(message);
    }
}
