package com.svalero.enjoypadel.presenter;


import com.svalero.enjoypadel.contract.AddMatchContract;
import com.svalero.enjoypadel.domain.Match;
import com.svalero.enjoypadel.model.AddMatchModel;
import com.svalero.enjoypadel.view.AddMatchView;

public class AddMatchPresenter implements AddMatchContract.Presenter {

    private AddMatchModel model;
    private AddMatchView view;

    public AddMatchPresenter(AddMatchView view) {
        this.view = view;
        model = new AddMatchModel(view);

    }

    @Override
    public void addMatch(Match match) {
        model.addMatch(match);
    }

    @Override
    public void modifyMatch(Match match) {
        model.modifyMatch(match);
    }

}
