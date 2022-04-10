package com.svalero.enjoypadel.presenter;

import com.svalero.enjoypadel.contract.CenterDetailContract;
import com.svalero.enjoypadel.contract.MatchDetailContract;
import com.svalero.enjoypadel.domain.Center;
import com.svalero.enjoypadel.domain.Match;
import com.svalero.enjoypadel.model.CenterDetailModel;
import com.svalero.enjoypadel.model.MatchDetailModel;
import com.svalero.enjoypadel.view.CenterDetailView;
import com.svalero.enjoypadel.view.MatchDetailView;

public class MatchDetailPresenter implements MatchDetailContract.Presenter, MatchDetailContract.Model.OnShowMatch {

    private MatchDetailModel model;
    private MatchDetailView view;

    public MatchDetailPresenter(MatchDetailView view) {
        this.view = view;
        model = new MatchDetailModel(view.getApplicationContext());
    }

    @Override
    public void matchDetail(int matchId) {
        model.matchDetail(matchId, this);
    }

    @Override
    public void onShowMatchSuccess(Match match) {
        view.loadMatchDetail(match);
    }

    @Override
    public void onShowMatchError(String message) {
        view.showMessage(message);
    }
}
