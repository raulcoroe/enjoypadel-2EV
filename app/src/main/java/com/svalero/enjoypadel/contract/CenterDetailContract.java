package com.svalero.enjoypadel.contract;

import com.svalero.enjoypadel.domain.Center;

public interface CenterDetailContract {

    interface Model {
        interface OnShowCenter {
            void onShowCenterSuccess(Center center);
            void onShowCenterError(String message);
        }

        void centerDetail(int centerId, OnShowCenter listener);
    }

    interface Presenter {
        void centerDetail(int centerId);
    }

    interface View {
        void loadCenterDetail(Center center);
        void showMessage(String message);

    }
}
