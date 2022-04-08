package com.svalero.enjoypadel.contract;

import com.svalero.enjoypadel.domain.Center;
import com.svalero.enjoypadel.domain.Match;

import java.util.List;

public interface CenterListContract {

    interface Model {
        interface OnLoadCentersListener {
            void onLoadCentersSuccess(List<Center> centers);
            void onLoadCentersError(String message);
        }

        interface OnDeleteCenterListener {
            void onDeleteCenterSuccess(String message);
            void onDeleteCenterError(String message);
        }

        void loadAllCenters(OnLoadCentersListener listener);
        void deleteCenter(Center center, OnDeleteCenterListener listener);

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
