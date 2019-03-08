package com.jdvila.backbasesubmission;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jdvila.backbasesubmission.contracts.MainContract;
import com.jdvila.backbasesubmission.fragments.CityMapsFragment;
import com.jdvila.backbasesubmission.fragments.SearchFragment;
import com.jdvila.backbasesubmission.models.City;
import com.jdvila.backbasesubmission.presenters.MainPresenter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View, SearchFragment.OnDataChangedListener {

    private ProgressDialog dialog;
    private MainContract.Presenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter(this);
        mainPresenter.fetchData();
    }

    @Override
    public void logResults(List<City> cityList) {
        for (int i = 0; i < cityList.size(); i++) {
            Log.d("Jose", cityList.get(i).getName() + ", " + cityList.get(i).getCountry());
        }
    }

    @Override
    public void loadJSONFromAsset(final String asset) {
        startProgressDialog();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                StringBuilder jsonStringBuilder = new StringBuilder();
                try {
                    InputStream is = getResources().getAssets().open(asset);
                    BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                    String str;
                    while ((str = br.readLine()) != null) {
                        jsonStringBuilder.append(str);
                    }
                    br.close();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                mainPresenter.setData(jsonStringBuilder.toString());
            }
        };
        AsyncTask.execute(runnable);
    }

    @Override
    public void initViews(List<City> data) {
        SearchFragment searchFragment = SearchFragment.newInstance(data);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_frameLayout, searchFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void startProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.setTitle(getResources().getString(R.string.dialog_title));
        dialog.setMessage(getResources().getString(R.string.dialog_body));
        dialog.show();
    }

    @Override
    public void stopProgressDialog() {
        dialog.dismiss();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.detachView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attachView(this);
    }

    @Override
    public void onSelectionMade(String lat, String lon, String name) {
        CityMapsFragment cityMapsFragment = CityMapsFragment.newInstance(lat, lon, name);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_frameLayout, cityMapsFragment);
        fragmentTransaction.addToBackStack(getResources().getString(R.string.maps_backstack_key));
        fragmentTransaction.commit();
    }
}
