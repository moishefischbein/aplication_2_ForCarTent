package com.example.moish.aplication_2_forCarRent.controller.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.moish.aplication_2_forCarRent.R;
import com.example.moish.aplication_2_forCarRent.model.adapter.BranchAdapter;
import com.example.moish.aplication_2_forCarRent.model.adapter.ClientAdapter;
import com.example.moish.aplication_2_forCarRent.model.entities.Branch;

import java.util.List;

/**
 * Created by moish on 17/01/2018.
 */

public class All_Branches extends Fragment {

View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
                view = inflater.inflate(R.layout.fragment_all_branches, container, false);
        return view;
    }


    private void initItemByListView(List<Branch> branches){
        ListView lv = (ListView) view.findViewById(R.id.clientsList);

        BranchAdapter adapter = new BranchAdapter(branches, this);

        lv.setAdapter(adapter);
}
