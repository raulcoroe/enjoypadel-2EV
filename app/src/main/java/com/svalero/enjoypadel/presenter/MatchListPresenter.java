package com.svalero.enjoypadel.presenter;


import com.svalero.enjoypadel.contract.MatchListContract;
import com.svalero.enjoypadel.domain.Match;
import com.svalero.enjoypadel.model.MatchListModel;
import com.svalero.enjoypadel.view.MatchListView;

import java.util.List;

public class MatchListPresenter implements MatchListContract.Presenter, MatchListContract.Model.OnLoadMatchesListener {

    private MatchListModel model;
    private MatchListView view;

    public MatchListPresenter(MatchListView view) {
        model = new MatchListModel(view.getApplicationContext());
        this.view = view;
    }

    @Override
    public void loadAllMatches() {
        model.loadAllMatches(this);
    }

    @Override
    public void onLoadMatchesSuccess(List<Match> matches) {
        view.listAllMatches(matches);
    }

    @Override
    public void onLoadMatchesError(String message) {
        view.showMessage(message);
    }


}
