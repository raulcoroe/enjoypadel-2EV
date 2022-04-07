package com.svalero.enjoypadel.contract;

import com.svalero.enjoypadel.domain.Center;

public interface CenterDetailContract {

    interface Model {
        Center centerDetail(int centerId);
    }

    interface Presenter {
        void centerDetail(int centerId);
    }

    interface View {
        void loadCenterDetail(Center center);
    }
}
