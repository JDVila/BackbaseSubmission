package com.jdvila.backbasesubmission.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jdvila.backbasesubmission.R;
import com.jdvila.backbasesubmission.fragments.SearchFragment;
import com.jdvila.backbasesubmission.models.City;

public class CityViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;

    public CityViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.itemview_textview);
    }

    public void onBind(final City city) {
        String s = city.getName() + ", " + city.getCountry();
        final String lat = city.getCoord().getLat();
        final String lon = city.getCoord().getLon();
        textView.setText(s);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SearchFragment.OnDataChangedListener)itemView.getContext()).onSelectionMade(lat, lon);
            }
        });
    }
}
