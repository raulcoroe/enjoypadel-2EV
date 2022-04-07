package com.svalero.enjoypadel.presenter;

import com.svalero.enjoypadel.contract.CenterDetailContract;
import com.svalero.enjoypadel.contract.MatchDetailContract;
import com.svalero.enjoypadel.domain.Center;
import com.svalero.enjoypadel.domain.Match;
import com.svalero.enjoypadel.model.CenterDetailModel;
import com.svalero.enjoypadel.model.MatchDetailModel;
import com.svalero.enjoypadel.view.CenterDetailView;
import com.svalero.enjoypadel.view.MatchDetailView;

public class MatchDetailPresenter implements MatchDetailContract.Presenter {

    private MatchDetailModel model;
    private MatchDetailView view;

    public MatchDetailPresenter(MatchDetailView view) {
        this.view = view;
        model = new MatchDetailModel(view.getApplicationContext());
    }

    @Override
    public void matchDetail(int matchId) {
        Match match = model.matchDetail(matchId);
        view.loadMatchDetail(match);
    }
}
