package com.jdvila.backbasesubmission.presenters;

import com.jdvila.backbasesubmission.contracts.MainContract;
import com.jdvila.backbasesubmission.models.City;
import com.jdvila.backbasesubmission.repository.CityRepository;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private MainContract.Model model;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        this.model = CityRepository.getInstance(this);
    }

    @Override
    public void fetchData() {
        model.loadData();
    }

    @Override
    public void initViews(List<City> data) {
        view.initViews(data);
    }

    @Override
    public void getJsonData(String asset) {
        view.loadJSONFromAsset(asset);
    }

    @Override
    public void setData(String json) {
        model.getData(json);
    }

    @Override
    public void returnData(List<City> data) {
        this.initViews(data);
        view.stopProgressDialog();
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
    }


}
