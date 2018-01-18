package com.example.moish.aplication_2_forCarRent.model.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.moish.aplication_2_forCarRent.R;
import com.example.moish.aplication_2_forCarRent.model.entities.CarModel;
import com.example.moish.aplication_2_forCarRent.model.entities.Client;

import java.util.List;

/**
 * Created by moish on 09/12/2017.
 */

public class CarModelAdapter extends BaseAdapter {


    private final List<CarModel> carModel;
    private final Activity activity;

    public CarModelAdapter(List<CarModel> carModel, Activity activity) {
        this.carModel = carModel;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return carModel.size();
    }

    @Override
    public Object getItem(int i) {
        return carModel.get(i);
    }

    @Override
    public long getItemId(int i) {
        return ((Client) getItem(i)).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = activity.getLayoutInflater()
                .inflate(R.layout.item_row, viewGroup, false);

        CarModel client = carModel.get(i);

        try {



            TextView ide = (TextView) v.findViewById(R.id.itemId);
            TextView name = (TextView) v.findViewById(R.id.itemName);

            name.setText(client.getCompanyName() + " " + client.getModelName());
            ide.setText(Integer.toString(client.getModelCode_id()));
            //ide.setText(client.getId());

        }catch (Exception ex){ex.toString();}
        return v;

    }
    {
    }
}

