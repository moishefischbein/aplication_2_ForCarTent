package com.example.moish.aplication_2_forCarRent.controller;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.example.moish.aplication_2_forCarRent.R;
import com.example.moish.aplication_2_forCarRent.model.adapter.ClientAdapter;
import com.example.moish.aplication_2_forCarRent.model.backend.DBManagerFactory;
import com.example.moish.aplication_2_forCarRent.model.entities.Client;

import java.util.List;


public class ListOfClients extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_client);

        getListItems();
    }

    private void getListItems(){

        new AsyncTask<Void, Void, List<Client>>(){

            List<Client> clients;

            @Override
            protected List<Client> doInBackground(Void... voids) {
                clients = DBManagerFactory.getManager().getClients();
                return clients;
            }

            @Override
            protected void onPostExecute(List<Client> clients) {
                initItemByListView(clients);
            }
        }.execute();

    }

    private void initItemByListView(List<Client> clients){

        ListView lv = (ListView) findViewById(R.id.clientsList);

        ClientAdapter adapter = new ClientAdapter(clients, this);

        lv.setAdapter(adapter);
    }

}





