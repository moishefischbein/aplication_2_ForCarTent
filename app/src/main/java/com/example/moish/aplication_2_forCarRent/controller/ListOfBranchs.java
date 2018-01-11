package com.example.moish.aplication_2_forCarRent.controller;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.example.moish.aplication_2_forCarRent.R;
import com.example.moish.aplication_2_forCarRent.model.backend.DBManagerFactory;
import com.example.moish.aplication_2_forCarRent.model.backend.Functions;
import com.example.moish.aplication_2_forCarRent.model.entities.Branch;

import static com.example.moish.aplication_2_forCarRent.R.id.IdEditText;

import java.util.List;

/**
 * Created by moish on 09/12/2017.
 */

public class ListOfBranchs extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_branch);

       getListItems();
    }

    private void getListItems(){

        new AsyncTask<Void, Void, List<Branch>>(){

            List<Branch> branches;

            @Override
            protected List<Branch> doInBackground(Void... voids) {
                branches = DBManagerFactory.getManager().getBranchs();
                return branches;
            }

            @Override
            protected void onPostExecute(List<Branch> branches) {
                initItemByListView(branches);
            }
        }.execute();

    }

    private void initItemByListView(List<Branch> branches){

        ListView lv = (ListView) findViewById(R.id.BranchesList);

        BranchAdapter adapter = new BranchAdapter(branches, this);

        lv.setAdapter(adapter);
    }

}

