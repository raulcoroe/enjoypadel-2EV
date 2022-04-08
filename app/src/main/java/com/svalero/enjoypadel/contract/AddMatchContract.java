package com.svalero.enjoypadel.contract;

import com.svalero.enjoypadel.domain.Center;
import com.svalero.enjoypadel.domain.Match;
import com.svalero.enjoypadel.domain.Player;

import java.util.List;

public interface AddMatchContract {

    interface Model {
        void addMatch(Match match);
        void modifyMatch(Match match);
    }

    interface Presenter {
        void addMatch(Match match);
        void modifyMatch(Match match);
        void chargeSpinners();
    }

    interface View {
        void createMatch(android.view.View view);
        void chargeElements(List<Player> players, List<Center> centers);
        void showMessage(String message);
    }
}
