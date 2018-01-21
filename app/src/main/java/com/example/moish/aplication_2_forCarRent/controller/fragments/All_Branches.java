package com.example.moish.aplication_2_forCarRent.controller.fragments;

import android.app.Fragment;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moish.aplication_2_forCarRent.R;
import com.example.moish.aplication_2_forCarRent.model.adapter.BranchAdapter;
import com.example.moish.aplication_2_forCarRent.model.adapter.CarAdapter;
import com.example.moish.aplication_2_forCarRent.model.backend.DBManagerFactory;
import com.example.moish.aplication_2_forCarRent.model.backend.Functions;
import com.example.moish.aplication_2_forCarRent.model.entities.Branch;
import com.example.moish.aplication_2_forCarRent.model.entities.Car;
import com.example.moish.aplication_2_forCarRent.model.entities.CarReserve;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import static com.example.moish.aplication_2_forCarRent.R.id.idClientResult;
import static com.example.moish.aplication_2_forCarRent.R.id.reserveIDResult;

/**
 * Created by moish on 17/01/2018.
 */

public class All_Branches extends Fragment implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    View view;
    Spinner sp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_all_branches, container, false);

        sp = (Spinner) view.findViewById(R.id.fragment_simpleSpinner);
        sp.setOnItemSelectedListener(this);

        getListItems();

        return view;
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
                initItemBySpinnerView(branches);
            }
        }.execute();

    }


    private void initItemBySpinnerView(List<Branch> branches) {


        BranchAdapter adapter = new BranchAdapter(branches, getActivity());

        sp.setAdapter(adapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        final Branch branch = (Branch) adapterView.getSelectedItem();

        new AsyncTask<Void, Void, List<Car>>(){

            List<Car> cars;

            @Override
            protected List<Car> doInBackground(Void... voids) {
                cars = DBManagerFactory.getManager().getFreeCarOfBranch(branch);
                return cars;
            }

            @Override
            protected void onPostExecute(List<Car> cars) {
                initItemByListView(cars);
            }
        }.execute();

   }

    private void initItemByListView(List<Car> freeCars){

        ListView lv = (ListView) view.findViewById(R.id.freeCars);
        lv.setOnItemClickListener(this);

        CarAdapter adapter = new CarAdapter(freeCars, getActivity());

        lv.setAdapter(adapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        final Car car = (Car)adapterView.getItemAtPosition(i);

        Random r = new Random();
        final int reserveID = r.nextInt(200000);

        new AsyncTask<Void, Void, List<CarReserve>>(){

            List<CarReserve> reserves;

            @Override
            protected List<CarReserve> doInBackground(Void... voids) {
                reserves = DBManagerFactory.getManager().getCarReserve();
                return reserves;
            }

            @Override
            protected void onPostExecute(List<CarReserve> reserves) {
                isIdDefined(reserves, reserveID, car);
            }
        }.execute();


    }

    public void isIdDefined(List<CarReserve> reserves, int reserveID, Car car){

        for (CarReserve cr: reserves) {

            if(cr.getReserveNumber_id() == reserveID){
                Random r = new Random();
                reserveID = r.nextInt(2000000);
                isIdDefined(reserves,reserveID, car);
                break;
            }
        }

        createReserve(reserveID, car);
    }

    public void createReserve(final int reserveID, Car car){

        final TextView reserveIDTextView = (TextView) view.findViewById(reserveIDResult);
        EditText clientIdEditText = (EditText)view.findViewById(idClientResult);
        String currentTime = Calendar.getInstance().getTime().toString();

        int idClient = Integer.parseInt(clientIdEditText.getText().toString());
        int isOpened = 1;
        int idCar = car.getCarNumber_id();
        String rentBeggining = currentTime;
        String rentEndDate = "";
        double starKilometers = car.getKilometersTraveled();
        double endKilometers = 0;
        int isFueled = 1;
        int liters = 0;
        double totalToPay = 0;

        CarReserve carReserve = new CarReserve(idClient,isOpened,idCar,rentBeggining,rentEndDate,starKilometers,endKilometers
                                                ,isFueled,liters,totalToPay,reserveID);

        final ContentValues values = Functions.carReserveToContentValues(carReserve);

        new AsyncTask<Void, Void, Long>(){
            @Override
            protected void onPostExecute(Long aLong) {
                Toast.makeText(getContext(), String.valueOf(reserveID), Toast.LENGTH_LONG).show();
                reserveIDTextView.setText("Your reserve code is: "+ String.valueOf(reserveID)+ ", please, save this number." );
            }

            @Override
            protected Long doInBackground(Void... voids) {
                return DBManagerFactory.getManager().addCarReserve(values);
            }
        }.execute();

    }
}
