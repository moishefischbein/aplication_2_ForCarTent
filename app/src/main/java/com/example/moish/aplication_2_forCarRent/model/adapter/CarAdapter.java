package com.example.moish.aplication_2_forCarRent.model.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.moish.aplication_2_forCarRent.R;
import com.example.moish.aplication_2_forCarRent.model.entities.Car;

import java.util.List;

/**
 * Created by moish on 09/12/2017.
 */

public class CarAdapter extends BaseAdapter{

    private final List<Car> cars;
    private final Activity activity;

    public CarAdapter(List<Car> cars, Activity activity) {
        this.cars = cars;
        this.activity = activity;
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

        View v = activity.getLayoutInflater()
                .inflate(R.layout.item_row, viewGroup, false);

        Car car = cars.get(i);

        try {



            TextView ide = (TextView) v.findViewById(R.id.itemId);
            TextView name = (TextView) v.findViewById(R.id.itemName);

            name.setText(Integer.toString(car.getModel()));
            ide.setText(Integer.toString(car.getCarNumber_id()));


        }catch (Exception ex){ex.toString();}
        return v;

    }


}
