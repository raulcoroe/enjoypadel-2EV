package com.svalero.enjoypadel.contract;

import com.svalero.enjoypadel.domain.Match;

import java.util.List;

public interface MatchListContract {

    interface Model {
        interface OnLoadMatchesListener {
            void onLoadMatchesSuccess(List<Match> matches);
            void onLoadMatchesError(String message);
        }

        interface OnDeleteMatchListener {
            void onDeleteMatchSuccess(String message);
            void onDeleteMatchError(String message);
        }

        void loadAllMatches(OnLoadMatchesListener listener);
        void deleteMatch(Match match, OnDeleteMatchListener listener);

    }

    interface View {
        void listAllMatches(List<Match> matches);
        void showMessage(String message);
    }

    interface Presenter {
        void loadAllMatches();
        void deleteMatch(Match match);
    }

}
