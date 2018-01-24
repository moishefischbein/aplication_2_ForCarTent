package com.example.moish.aplication_2_forCarRent.controller.fragments;




import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.moish.aplication_2_forCarRent.R;
import com.example.moish.aplication_2_forCarRent.model.adapter.CarAdapter;
import com.example.moish.aplication_2_forCarRent.model.backend.DBManagerFactory;
import com.example.moish.aplication_2_forCarRent.model.entities.Car;

import java.util.List;

/**
 * Created by moish on 17/01/2018.
 */

public class AllFreeCars extends Fragment implements SearchView.OnQueryTextListener{


    View rootView;
    CarAdapter adapter;
    SearchView filter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       rootView = inflater.inflate(R.layout.fragment_all_free_cars, container, false);

        filter = (SearchView) rootView.findViewById(R.id.search_view);
        filter.setOnQueryTextListener(this);

        getListItems();
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

        if(cars == null) {
            Toast.makeText(getContext(), "There is no free cars, Sorry", Toast.LENGTH_LONG).show();
            return;
        }
        ListView lv = (ListView) rootView.findViewById(R.id.freeCarsList);

        adapter = new CarAdapter(cars, getActivity());

        lv.setAdapter(adapter);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        /*int count = 0;
        while (adapter == null && count < 1000)
            count++;*/

        if (adapter == null) {

            BadInternetDialog badInternetDialog = new BadInternetDialog();
            badInternetDialog.show(getFragmentManager(), "badInternet");
        }
        Toast.makeText(getContext(), newText, Toast.LENGTH_LONG).show();
        adapter.getFilter().filter(newText);
        adapter.notifyDataSetChanged();
        return false;

    }
}
