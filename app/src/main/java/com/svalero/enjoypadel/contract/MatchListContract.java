package com.svalero.enjoypadel.contract;

import com.svalero.enjoypadel.domain.Match;

import java.util.List;

public interface MatchListContract {

    interface Model {
//        interface OnLoadPlayersListener {
//            void onLoadPlayersSuccess(List<Player> players);
//            void onLoadPlayersError(String message);
//        }
//
//        interface OnDeletePlayerListener {
//            void onDeletePlayerSuccess(String message);
//            void onDeletePlayerError(String message);
//        }

        List<Match> loadAllMatches();
        void deleteMatch(Match match);

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
