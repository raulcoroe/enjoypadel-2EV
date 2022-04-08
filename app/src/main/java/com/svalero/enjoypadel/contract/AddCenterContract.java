package com.svalero.enjoypadel.contract;

import com.svalero.enjoypadel.domain.Center;

public interface AddCenterContract {

    interface Model {
        interface OnAddCenterListener {
            void onAddCenterSuccess(String message);
            void onAddCenterError(String message);
        }

        void addCenter(Center center, OnAddCenterListener listener);
    }

    interface Presenter {
        void addCenter(Center center);
    }

    interface View {
        void addCenter(android.view.View view);
        void showMessage(String message);
    }
}
