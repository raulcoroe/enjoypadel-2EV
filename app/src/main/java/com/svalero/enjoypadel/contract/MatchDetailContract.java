package com.svalero.enjoypadel.contract;

import com.svalero.enjoypadel.domain.Match;

import java.util.List;

public interface MatchDetailContract {

    interface Model {

        interface OnShowMatch {
            void onShowMatchSuccess(Match match);
            void onShowMatchError(String message);
        }

        void matchDetail(int matchId, OnShowMatch listener);
    }

    interface Presenter {
        void matchDetail(int matchId);
    }

    interface View {
        void loadMatchDetail(Match match);
        void showMessage(String message);
    }
}
