package com.svalero.enjoypadel.contract;

import com.svalero.enjoypadel.domain.Center;

public interface AddCenterContract {

    interface Model {
        void addCenter(Center center);
    }

    interface Presenter {
        void addCenter(Center center);
    }

    interface View {
        void addCenter(android.view.View view);
    }
}
