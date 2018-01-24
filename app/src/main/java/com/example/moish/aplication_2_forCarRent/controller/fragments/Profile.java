package com.example.moish.aplication_2_forCarRent.controller.fragments;

import android.app.Fragment;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moish.aplication_2_forCarRent.R;
import com.example.moish.aplication_2_forCarRent.model.backend.DBManagerFactory;
import com.example.moish.aplication_2_forCarRent.model.backend.Functions;
import com.example.moish.aplication_2_forCarRent.model.entities.Car;
import com.example.moish.aplication_2_forCarRent.model.entities.CarReserve;

import java.util.Calendar;
import java.util.List;

/**
 * Created by moish on 17/01/2018.
 */




public class Profile extends Fragment implements View.OnClickListener {

    private final String UserName = "fischbei";
    private final String WEB_URL = "http://" + UserName + ".vlab.jct.ac.il/";


    private View mView;


    private EditText _id;
    private EditText Start_kilometers;
    private EditText Total_kilometers;
    private EditText TotalToPay;
    private EditText kilometrage;
    private EditText hour;
    private EditText fuel;
    private Button button_Fisnish;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_profile, container, false);

        _id = (EditText) mView.findViewById(R.id.IdEditText);
        Start_kilometers = (EditText) mView.findViewById(R.id.Start_kilometers);
        fuel = (EditText) mView.findViewById((R.id.liters_of_fuel));
        Total_kilometers = (EditText) mView.findViewById(R.id.End_kilometers);
        TotalToPay = (EditText) mView.findViewById(R.id.TotalToPay);
        hour = (EditText) mView.findViewById(R.id.hour);
        kilometrage = (EditText) mView.findViewById(R.id.kilometers_EditText);
        button_Fisnish = (Button) mView.findViewById(R.id.ButtomFinish);

        button_Fisnish.setOnClickListener(this);

        return mView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ButtomFinish:
                if (isFullTheAllTexBox() == false) {
                    Toast.makeText(getContext(), "There is an empty field, please fill in: ", Toast.LENGTH_LONG).show();
                } else {

//verify if exist this id car
                    new AsyncTask<Void, Void, List<CarReserve>>() {

                        List<CarReserve> reserves;

                        @Override
                        protected List<CarReserve> doInBackground(Void... voids) {
                            reserves = DBManagerFactory.getManager().getCarReserve();
                            return reserves;
                        }

                        @Override
                        protected void onPostExecute(List<CarReserve> reserves) {
                            int a = Integer.parseInt(_id.getText().toString());
                            boolean isIdExist = false;
                            for (CarReserve reserve : reserves) {
                                if (reserve.getCarNumber() == a) {
                                    isIdExist = true;
                                    startTheFuctions();
                                }
                            }
                            if (isIdExist == false) {
                                Toast.makeText(getContext(), "These Id car does not exist in the reserves.", Toast.LENGTH_LONG).show();
                            }
                        }
                    }.execute();
                }
        }
    }

    void startTheFuctions() {

        int y = Integer.parseInt(_id.getText().toString());
        reserveList(y);


    }


    private void EndReserve() {
        final ContentValues values = new ContentValues();
        try {
            long id = Long.valueOf(_id.getText().toString());

            values.put(Functions.CarConst.CAR_NUMBER, _id.getText().toString());
            values.put(Functions.CarConst.KILOMETERS_TRAVELED, kilometrage.getText().toString());
            values.put(Functions.CarConst.MODEL, 25);
            values.put(Functions.CarConst.FIXED_BRANCH, 22);

            new AsyncTask<Void, Void, Long>() {

                @Override
                protected Long doInBackground(Void... voids) {
                    return DBManagerFactory.getManager().updateCar(values);
                }
            }.execute();
        } catch (Exception e) {
            e.toString();
        } finally {
            //this.finish();
        }
    }

    //..............................................................update the isOpen from contracts....................
    private void closeTheIsOpened(CarReserve reserve2) {

        final ContentValues values = new ContentValues();
        try {
            long id = Long.valueOf(_id.getText().toString());

            reserve2.setOpened(0);
            values.put(Functions.CarReserveConst.RESERVE_NUMBER, Integer.toString(reserve2.getReserveNumber_id()));
            values.put(Functions.CarReserveConst.IS_OPENED, Integer.toString(reserve2.isOpened()));
            values.put(Functions.CarReserveConst.RENT_END_DATE, currentHour());
            values.put(Functions.CarReserveConst.END_KILOMETERS, kilometrage.getText().toString());



            new AsyncTask<Object, Object, Void>() {

                @Override
                protected Void doInBackground(Object... voids) {
                    DBManagerFactory.getManager().updateCarReserve(values);
                    return null;
                }
            }.execute();
        } catch (Exception e) {
            e.toString();
        } finally {
        }

    }


    //to verify if all fieldss are fill in
    boolean isFullTheAllTexBox() {
        String ID = _id.getText().toString();
        String KM = kilometrage.getText().toString();


        if (ID.isEmpty() || KM.isEmpty()) {
            return false;
        }
        return true;

    }

    String currentHour() {
        String mydate = Calendar.getInstance().getTime().toString();
        return mydate;
    }


    public void reserveList(final int id) {

        new AsyncTask<Void, Void, List<CarReserve>>() {

            List<CarReserve> reserves;

            @Override
            protected List<CarReserve> doInBackground(Void... voids) {
                reserves = DBManagerFactory.getManager().getCarReserve();
                return reserves;
            }

            @Override
            protected void onPostExecute(List<CarReserve> reserves) {
                TotalToPay(reserves, id);
            }
        }.execute();
    }

    private void TotalToPay(List<CarReserve> reserves, int id) {


        CarReserve reserve2 = null;
        double startKilometers = 0;
        for (CarReserve reserve : reserves) {
            if (reserve.getCarNumber() == id) {
                reserve2 = reserve;
                startKilometers = reserve.getStartKilometers();
                break;
            }


        }
        int f = (Integer.parseInt(fuel.getText().toString()));

        if (reserve2.isOpened() == 1) {
            if (startKilometers > Integer.parseInt(kilometrage.getText().toString())) {

                Toast.makeText(getContext(), "Please, insert the correct end kilometers. ", Toast.LENGTH_LONG).show();
            } else {
                if (f < 0) {
                    Toast.makeText(getContext(), "Please, insert the correct litersof fuel. ", Toast.LENGTH_LONG).show();
                } else {
                    closeTheIsOpened(reserve2);

                    String x = currentHour();
                    hour.setText(" " + x);

                    Start_kilometers.setText("Kilometers in the beggining: " + startKilometers);
                    Total_kilometers.setText("Total ilometers runing : " + (Integer.parseInt(kilometrage.getText().toString()) - startKilometers));
                    TotalToPay.setText("Total To Pay: " + (((Integer.parseInt(kilometrage.getText().toString()) - startKilometers) * 5.5) - (f * 2.5)) + "$");
                    carList(id);

                    EndReserve();
                    Toast.makeText(getContext(), "The kilometer was update: ", Toast.LENGTH_LONG).show();
                    Start_kilometers.setVisibility(View.VISIBLE);
                    Total_kilometers.setVisibility(View.VISIBLE);
                    TotalToPay.setVisibility(View.VISIBLE);
                    hour.setVisibility(View.VISIBLE);
                }


            }
        } else {
            Toast.makeText(getContext(), "The reserve is already closed. ", Toast.LENGTH_LONG).show();
        }
    }




    void carList(final int id) {

        List<Car> LisrOfcars;

        new AsyncTask<Void, Void, List<Car>>(){

            List<Car> cars;

            @Override
            protected List<Car> doInBackground(Void... voids) {
                cars = DBManagerFactory.getManager().getCar();
                return cars;
            }

            @Override
            protected void onPostExecute(List<Car> cars) {
                freeCar(cars, id);
            }
        }.execute();


    }


    void freeCar(List<Car> cars, int id) {


        Car newCar = null;

        for (Car car : cars) {
            if (car.getCarNumber_id() == id) {
                fulfillDetailsBox(car);
                newCar = car;
                break;
            }
        }

        ContentValues value = new ContentValues(Functions.carToContentValues(newCar));
        addToFreeCars(value);
    }

    public void addToFreeCars(final ContentValues value){

        new AsyncTask<Void, Void, ContentValues>(){
            @Override
            protected ContentValues doInBackground(Void... voids) {
                DBManagerFactory.getManager().addFreeCars(value);
                return value;
            }

            @Override
            protected void onPostExecute(ContentValues value){

            }
        }.execute();

    }


    private void fulfillDetailsBox(Car car){


        TextView fixedBranch;
        TextView kilometerTraveled;
        TextView model;

        fixedBranch = (TextView) mView.findViewById(R.id.fixed);
        kilometerTraveled = (TextView) mView.findViewById(R.id.km);
        model = (TextView) mView.findViewById(R.id.mod);


        fixedBranch.setText("Branch: " + car.getFixedBranch());
        kilometerTraveled.setText("Kilometers traveled: " + car.getKilometersTraveled());//+ String.valueOf(car.getKilometersTraveled()));
        model.setText("Model: " + car.getModel());//+ Integer.toString(car.getModel()));
    }

}

//
//
//