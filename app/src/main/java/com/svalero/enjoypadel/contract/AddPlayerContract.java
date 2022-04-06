package com.svalero.enjoypadel.contract;

import android.view.View;

import com.svalero.enjoypadel.domain.Player;

public interface AddPlayerContract {

    interface Model {
        void addPlayer(Player player);
        void modifyPlayer(Player player);
    }

    interface Presenter {
        void addPlayer(Player player);
        void modifyPlayer(Player player);
    }

    interface View {
        void addOrModifyPlayer(android.view.View view);
    }
}
