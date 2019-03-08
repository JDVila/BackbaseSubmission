package com.jdvila.backbasesubmission.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jdvila.backbasesubmission.contracts.MainContract;
import com.jdvila.backbasesubmission.models.City;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CityRepository implements MainContract.Model {

    private static CityRepository instance;
    private final MainContract.Presenter presenter;
    private List<City> sortedCityList;

    private CityRepository(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public static CityRepository getInstance(MainContract.Presenter presenter) {
        if (instance != null) {
            return instance;
        }
        instance = new CityRepository(presenter);
        return instance;
    }

    @Override
    public void getData(String data) {
        if (data == null || data.trim().isEmpty()) {
            sortList(new ArrayList<City>());
        }
        sortList(getDataFromSource(data));
    }

    private List<City> getDataFromSource(String data) {
        Type type = new TypeToken<List<City>>() {
        }.getType();
        return new Gson().fromJson(data, type);
    }

    private void sortList(List<City> unsortedList) {
        Collections.sort(unsortedList);
        sortedCityList = unsortedList;
        presenter.returnData(sortedCityList);
    }

    @Override
    public void loadData() {
        presenter.getJsonData("cities.json");
    }

}
