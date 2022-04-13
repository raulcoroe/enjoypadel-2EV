package com.svalero.enjoypadel.presenter;

import com.svalero.enjoypadel.contract.CenterListContract;
import com.svalero.enjoypadel.domain.Center;
import com.svalero.enjoypadel.model.CenterListModel;
import com.svalero.enjoypadel.view.AddMatchView;
import com.svalero.enjoypadel.view.CenterListView;

import java.util.List;

public class CenterListPresenter implements CenterListContract.Presenter, CenterListContract.Model.OnDeleteCenterListener, CenterListContract.Model.OnLoadCentersListener {

    private CenterListModel model;
    private CenterListView view;

    public CenterListPresenter(CenterListView view) {
        model = new CenterListModel(view.getApplicationContext());
        this.view = view;
    }

    @Override
    public void loadAllCenters() {
        model.loadAllCenters(this);
    }


    @Override
    public void deleteCenter(Center center) {
        model.deleteCenter(center, this);
    }

    @Override
    public void onLoadCentersSuccess(List<Center> centers) {
        view.listAllCenters(centers);
    }

    @Override
    public void onLoadCentersError(String message) {
        view.showMessage(message);
    }

    @Override
    public void onDeleteCenterSuccess(String message) {
        view.showMessage(message);
    }

    @Override
    public void onDeleteCenterError(String message) {
        view.showMessage(message);
    }
}
