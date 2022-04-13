package com.svalero.enjoypadel.contract;

import com.svalero.enjoypadel.domain.Match;

import java.util.List;

public interface MatchListContract {

    interface Model {
        interface OnLoadMatchesListener {
            void onLoadMatchesSuccess(List<Match> matches);
            void onLoadMatchesError(String message);
        }

        void loadAllMatches(OnLoadMatchesListener listener);
    }

    interface Presenter {
        void loadAllMatches();
    }

    interface View {
        void listAllMatches(List<Match> matches);
        void showMessage(String message);
    }

}
