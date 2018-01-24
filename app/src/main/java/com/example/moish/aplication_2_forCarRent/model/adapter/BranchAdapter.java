package com.example.moish.aplication_2_forCarRent.model.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.moish.aplication_2_forCarRent.R;
import com.example.moish.aplication_2_forCarRent.model.entities.Branch;

import java.util.List;

/**
 * Created by moish on 09/12/2017.
 */

public class BranchAdapter extends BaseAdapter{

    private final List<Branch> branchs;
    private final Activity activity;

    public BranchAdapter(List<Branch> Branch, Activity activity) {
        this.branchs = Branch;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return branchs.size();
    }

    @Override
    public Object getItem(int i) {
        return branchs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return ((Branch) getItem(i)).getBranchNumber_id();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null) {
            view = activity.getLayoutInflater()
                    .inflate(R.layout.item_row, viewGroup, false);
        }
        Branch branch = branchs.get(i);

        try {



            TextView ide = (TextView) view.findViewById(R.id.itemId);
            TextView name = (TextView) view.findViewById(R.id.itemName);

            name.setText(branch.getCity() + " " + branch.getStreet());
            ide.setText(Integer.toString(branch.getBranchNumber_id()));
            //ide.setText(client.getId());

        }catch (Exception ex){ex.toString();}
        return view;

    }
    {
    }
}


