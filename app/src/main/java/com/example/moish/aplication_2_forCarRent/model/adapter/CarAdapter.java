package com.example.moish.aplication_2_forCarRent.model.adapter;

import android.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.view.*;

import com.example.moish.aplication_2_forCarRent.R;
import com.example.moish.aplication_2_forCarRent.controller.fragments.AllFreeCars;
import com.example.moish.aplication_2_forCarRent.model.entities.Car;

import java.util.List;

import static android.view.View.inflate;

/**
 * Created by moish on 09/12/2017.
 */

public class CarAdapter extends BaseAdapter{

    private final List<Car> cars;
    private final Fragment fragment;

    public CarAdapter(List<Car> cars, AllFreeCars activity) {
        this.cars = cars;
        this.fragment = activity;
    }

    @Override
    public int getCount() {
        return cars.size();
    }

    @Override
    public Object getItem(int i) {
        return cars.get(i);
    }

    @Override
    public long getItemId(int i) {
        return ((Car) getItem(i)).getCarNumber_id();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = inflate(R.layout.item_row, 1, false);
               // .inflate(R.layout.item_row, viewGroup, false);

        Car car = cars.get(i);

        try {



            TextView ide = (TextView) v.findViewById(R.id.itemId);
            TextView name = (TextView) v.findViewById(R.id.itemName);

            name.setText(Integer.toString(car.getModel()));
            ide.setText(Integer.toString(car.getCarNumber_id()));


        }catch (Exception ex){ex.toString();}
        return v;

    }
    {
    }

}
