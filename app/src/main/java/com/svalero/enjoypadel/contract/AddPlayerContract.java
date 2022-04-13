package com.svalero.enjoypadel.contract;

import com.svalero.enjoypadel.domain.Player;

public interface AddPlayerContract {

    interface Model {
        interface OnAddPlayerListener {
            void onAddPlayerSuccess(String message);
            void onAddPlayerError(String message);
        }

        interface OnModifyPlayerListener {
            void onModifyPlayerSuccess(String message);
            void onModifyPlayerError(String message);
        }

        void addPlayer(Player player, OnAddPlayerListener listener);
        void modifyPlayer(Player player, OnModifyPlayerListener listener);
    }

    interface Presenter {
        void addPlayer(Player player);
        void modifyPlayer(Player player);
    }

    interface View {
        void addOrModifyPlayer(android.view.View view);
        void showMessage(String message);
    }
}
