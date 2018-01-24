package com.example.moish.aplication_2_forCarRent.model.backend;

import android.widget.Filter;

import com.example.moish.aplication_2_forCarRent.model.adapter.CarAdapter;
import com.example.moish.aplication_2_forCarRent.model.entities.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 24/01/2018.
 */

public class CarFilter extends Filter {

    FilterResults results = new FilterResults();
    List<Car> freeCars = new ArrayList<Car>();
    CarAdapter carAdapter;

    public CarFilter(List<Car> cars, CarAdapter adapter) {
        super();
        freeCars = cars;
        carAdapter = adapter;
    }


    @Override
    protected FilterResults performFiltering(final CharSequence constraint) {

        // We implement here the filter logic
        if (constraint == null || constraint.length() == 0) {
            // No filter implemented we return all the list
            results.values = freeCars;
            results.count = freeCars.size();
        } else {
            // We perform filtering operation
            List<Car> nCars = new ArrayList<Car>();

            for (Car c : freeCars) {
                if (String.valueOf(c.getModel()).startsWith(constraint.toString()))
                    nCars.add(c);
            }
            results.values = nCars;
            results.count = nCars.size();
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        // Now we have to inform the adapter about the new list filtered
        if (results.count == 0)
            carAdapter.notifyDataSetInvalidated();
        else {
            freeCars.removeAll(freeCars);
            freeCars = (List<Car>) results.values;
            carAdapter.notifyDataSetChanged();
        }
    }
}
