package com.svalero.enjoypadel.contract;

import com.svalero.enjoypadel.domain.Center;
import com.svalero.enjoypadel.domain.Match;

import java.util.List;

public interface CenterListContract {

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

        List<Center> loadAllCenters();
        void deleteCenter(Center center);

    }

    interface View {
        void listAllCenters(List<Center> centers);
        void showMessage(String message);
    }

    interface Presenter {
        void loadAllCenters();
        void deleteCenter(Center center);
    }

}
