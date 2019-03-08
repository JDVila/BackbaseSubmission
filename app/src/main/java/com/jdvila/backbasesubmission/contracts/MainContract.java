package com.jdvila.backbasesubmission.contracts;

import com.jdvila.backbasesubmission.models.City;

import java.util.List;

public interface MainContract {
    interface Model {
        void loadData();
        void getData(String data);
    }

    interface View {
        void initViews(List<City> data);
        void startProgressDialog();
        void stopProgressDialog();
        void loadJSONFromAsset(final String asset);
        void logResults(List<City> data);
    }

    interface Presenter {
        void fetchData();
        void initViews(List<City> data);
        void getJsonData(String asset);
        void setData(String json);
        void returnData(List<City> data);
        void detachView();
        void attachView(View view);
    }
}
