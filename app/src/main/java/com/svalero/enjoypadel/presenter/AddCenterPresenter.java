package com.svalero.enjoypadel.presenter;

import com.svalero.enjoypadel.contract.AddCenterContract;
import com.svalero.enjoypadel.contract.AddMatchContract;
import com.svalero.enjoypadel.contract.AddPlayerContract;
import com.svalero.enjoypadel.domain.Center;
import com.svalero.enjoypadel.domain.Player;
import com.svalero.enjoypadel.model.AddCenterModel;
import com.svalero.enjoypadel.model.AddPlayerModel;
import com.svalero.enjoypadel.view.AddCenterView;
import com.svalero.enjoypadel.view.AddPlayerView;

public class AddCenterPresenter implements AddCenterContract.Presenter, AddCenterContract.Model.OnAddCenterListener {

    private AddCenterModel model;
    private AddCenterView view;

    public AddCenterPresenter(AddCenterView view) {
        this.view = view;
        model = new AddCenterModel(view.getApplicationContext());
    }

    public void addCenter(Center center) {
        model.addCenter(center, this);
    }

    @Override
    public void onAddCenterSuccess(String message) {
        view.showMessage(message);
    }

    @Override
    public void onAddCenterError(String message) {
        view.showMessage(message);
    }
}
