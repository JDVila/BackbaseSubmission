package com.jdvila.backbasesubmission.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jdvila.backbasesubmission.R;
import com.jdvila.backbasesubmission.models.City;
import com.jdvila.backbasesubmission.viewholders.CityViewHolder;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityViewHolder> {

    private List<City> cityList;

    public CityAdapter(List<City> cityList) {
        this.cityList = cityList;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View childView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.city_itemview, viewGroup, false);
        return new CityViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder cityViewHolder, int i) {
        City city = cityList.get(i);
        cityViewHolder.onBind(city);
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }
}
