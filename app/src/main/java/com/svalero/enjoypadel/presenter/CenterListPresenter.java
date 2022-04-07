package com.svalero.enjoypadel.presenter;

import com.svalero.enjoypadel.contract.CenterListContract;
import com.svalero.enjoypadel.domain.Center;
import com.svalero.enjoypadel.model.CenterListModel;
import com.svalero.enjoypadel.view.CenterListView;

import java.util.List;

public class CenterListPresenter implements CenterListContract.Presenter {

    private CenterListModel model;
    private CenterListView view;

    public CenterListPresenter(CenterListView view) {
        model = new CenterListModel(view.getApplicationContext());
        this.view = view;
    }

    @Override
    public void loadAllCenters() {
        List<Center> centers = model.loadAllCenters();
        view.listAllCenters(centers);
    }


    @Override
    public void deleteCenter(Center center) {
        model.deleteCenter(center);
    }
}
