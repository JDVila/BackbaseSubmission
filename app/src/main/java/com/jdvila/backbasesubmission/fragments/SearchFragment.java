package com.jdvila.backbasesubmission.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jdvila.backbasesubmission.R;
import com.jdvila.backbasesubmission.adapters.CityAdapter;
import com.jdvila.backbasesubmission.models.City;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener{

    private static final String SEARCH_DATA = "search_data";
    private static final String CITY_LIST = "cityList";
    private OnDataChangedListener onDataChangedListener;
    private SearchView searchView;
    private List<City> cityList;
    private RecyclerView cityCountryRecyclerView;

    public SearchFragment(){
    }

    public static SearchFragment newInstance(List<City> param1) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putSerializable(CITY_LIST, (Serializable) param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cityList = (List<City>) getArguments().getSerializable(CITY_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setViews(view, savedInstanceState);
    }

    private void setViews(@NonNull View view, @Nullable Bundle savedInstanceState) {
        searchView = view.findViewById(R.id.city_searchview);
        searchView.setOnQueryTextListener(this);
        cityCountryRecyclerView = view.findViewById(R.id.recyclerView);
        cityCountryRecyclerView.setAdapter(new CityAdapter(cityList));
        cityCountryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void onCitySelection(String lat, String lon, String name) {
        if (onDataChangedListener != null) {
            onDataChangedListener.onSelectionMade(lat, lon, name);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDataChangedListener) {
            onDataChangedListener = (OnDataChangedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnDataChangedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onDataChangedListener = null;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        List<City> newCityList = new ArrayList<>();
        for(City city : cityList) {
            if(city.getName().toLowerCase().startsWith(s.toLowerCase())) {
                newCityList.add(city);
            }
        }
        cityCountryRecyclerView.setAdapter(new CityAdapter(newCityList));
        return false;
    }

    public interface OnDataChangedListener {

        void onSelectionMade(String lat, String lon, String name);
    }
}