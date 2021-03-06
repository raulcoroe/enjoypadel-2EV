package com.svalero.enjoypadel.contract;

import com.svalero.enjoypadel.domain.Center;
import com.svalero.enjoypadel.domain.Match;
import com.svalero.enjoypadel.domain.Player;

import java.util.List;

public interface AddMatchContract {

    interface Model {

        interface OnAddMatchListener {
            void onAddMatchSuccess(String message);
            void onAddMatchError(String message);
        }

        void addMatch(Match match, OnAddMatchListener listener);
    }

    interface Presenter {
        void addMatch(Match match);
    }

    interface View {
        void createMatch(android.view.View view);
        void showMessage(String message);
    }
}
