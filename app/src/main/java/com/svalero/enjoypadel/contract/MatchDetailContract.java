package com.svalero.enjoypadel.contract;

import com.svalero.enjoypadel.domain.Match;

public interface MatchDetailContract {

    interface Model {
        Match matchDetail(int matchId);
    }

    interface Presenter {
        void matchDetail(int matchId);
    }

    interface View {
        void loadMatchDetail(Match match);
    }
}
