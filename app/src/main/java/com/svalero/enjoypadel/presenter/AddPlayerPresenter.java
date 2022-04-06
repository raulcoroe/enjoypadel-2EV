package com.svalero.enjoypadel.presenter;

import com.svalero.enjoypadel.contract.AddPlayerContract;
import com.svalero.enjoypadel.domain.Player;
import com.svalero.enjoypadel.model.AddPlayerModel;
import com.svalero.enjoypadel.view.AddPlayerView;

public class AddPlayerPresenter implements AddPlayerContract.Presenter {

    private AddPlayerModel model;
    private AddPlayerView view;

    public AddPlayerPresenter(AddPlayerView view) {
        this.view = view;
        model = new AddPlayerModel(view);

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
