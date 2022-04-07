package com.svalero.enjoypadel.presenter;

import com.svalero.enjoypadel.contract.CenterDetailContract;
import com.svalero.enjoypadel.domain.Center;
import com.svalero.enjoypadel.model.CenterDetailModel;
import com.svalero.enjoypadel.view.CenterDetailView;

public class CenterDetailPresenter implements CenterDetailContract.Presenter {

    private CenterDetailModel model;
    private CenterDetailView view;

    public CenterDetailPresenter(CenterDetailView view) {
        this.view = view;
        model = new CenterDetailModel(view.getApplicationContext());
    }

    @Override
    public void centerDetail(int centerId) {
        Center center = model.centerDetail(centerId);
        view.loadCenterDetail(center);
    }
}
