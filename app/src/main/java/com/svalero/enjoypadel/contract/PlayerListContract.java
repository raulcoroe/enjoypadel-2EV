package com.svalero.enjoypadel.contract;

import com.svalero.enjoypadel.domain.Player;

import java.util.List;

public interface PlayerListContract {

    interface Model {
        interface OnLoadPlayersListener {
            void onLoadPlayersSuccess(List<Player> players);
            void onLoadPlayersError(String message);
        }

        interface OnDeletePlayerListener {
            void onDeletePlayerSuccess(String message);
            void onDeletePlayerError(String message);
        }

        void loadAllPlayers(OnLoadPlayersListener listener);
        void deletePlayer(Player player);

    }

    interface View {
        void listAllPlayers(List<Player> players);
        void showMessage(String message);
    }

    interface Presenter {
        void loadAllPlayers();
        void deletePlayer(Player player);
    }

}
