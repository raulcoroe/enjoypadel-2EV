package com.svalero.enjoypadel.contract;

import com.svalero.enjoypadel.domain.Match;

public interface AddMatchContract {

    interface Model {
        void addMatch(Match match);
        void modifyMatch(Match match);
    }

    interface Presenter {
        void addMatch(Match match);
        void modifyMatch(Match match);
    }

    interface View {
        void addOrModifyMatch(android.view.View view);
    }
}
