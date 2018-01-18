package com.example.moish.aplication_2_forCarRent.controller.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.moish.aplication_2_forCarRent.R;
import com.example.moish.aplication_2_forCarRent.controller.MainActivity;
import com.example.moish.aplication_2_forCarRent.model.adapter.CarAdapter;
import com.example.moish.aplication_2_forCarRent.model.backend.DBManagerFactory;
import com.example.moish.aplication_2_forCarRent.model.entities.Car;

import java.util.List;

/**
 * Created by moish on 17/01/2018.
 */

public class AllFreeCars extends Fragment {


    ListView lv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_all_free_cars, container, false);
        lv = (ListView) rootView.findViewById(R.id.All_the_free_cars);

        return rootView;
    }


    private void getListItems(){

        new AsyncTask<Void, Void, List<Car>>(){

            List<Car> cars;

            @Override
            protected List<Car> doInBackground(Void... voids) {
                cars = DBManagerFactory.getManager().getFreeCars();
                return cars;
            }

            @Override
            protected void onPostExecute(List<Car> cars) {
                initItemByListView(cars);
            }
        }.execute();

    }

    private void initItemByListView(List<Car> cars){

        CarAdapter adapter = new CarAdapter(cars, this);

        lv.setAdapter(adapter);
    }

}
