package com.svalero.enjoypadel.presenter;

import com.svalero.enjoypadel.contract.AddPlayerContract;
import com.svalero.enjoypadel.domain.Player;
import com.svalero.enjoypadel.model.AddplayerModel;
import com.svalero.enjoypadel.view.AddPlayerView;

public class AddPlayerPresenter implements AddPlayerContract.Presenter {

    private AddplayerModel model;
    private AddPlayerView view;

    public AddPlayerPresenter(AddPlayerView view) {
        this.view = view;
        model = new AddplayerModel(view);

    }

    @Override
    public void addPlayer(Player player) {
        model.addPlayer(player);
    }

    @Override
    public void modifyPlayer(Player player) {
        model.modifyPlayer(player);
    }
}
