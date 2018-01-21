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
import android.widget.ListView;
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
    private Button button_Fisnish;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_profile, container, false);
        _id = (EditText) mView.findViewById(R.id.IdEditText);
        Start_kilometers = (EditText) mView.findViewById(R.id.Start_kilometers);
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
                    EndReserve();
                    Toast.makeText(getContext(), "The kilometer was update: ", Toast.LENGTH_LONG).show();

                    //  hour = (EditText) mView.findViewById(R.id.hour);
                    String x = currentHour();
                    hour.setText(" " + x);
                    int y = Integer.parseInt(_id.getText().toString());
                    reserveList(y);

                    Start_kilometers.setVisibility(View.VISIBLE);
                    Total_kilometers.setVisibility(View.VISIBLE);
                    TotalToPay.setVisibility(View.VISIBLE);
                    hour.setVisibility(View.VISIBLE);
                }
        }
    }

    //to finish the reserve
//the function will uptade the car kilometers and put the car in the free cars
    private void EndReserve() {

        final ContentValues values = new ContentValues();
        try {
            long id = Long.valueOf(_id.getText().toString());


            values.put(Functions.CarConst.CAR_NUMBER, _id.getText().toString());
            values.put(Functions.CarConst.KILOMETERS_TRAVELED, kilometrage.getText().toString());
            values.put(Functions.CarConst.MODEL, 25);
            values.put(Functions.CarConst.FIXED_BRANCH, 22);

            new AsyncTask<Void, Void, Long>() {
               /* @Override
                protected void onPostExecute(Long aLong) {
                    Toast.makeText(getBaseContext(), "ID: " + aLong, Toast.LENGTH_LONG).show();
                    Log.d("client", values.toString());
                }*/

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

        String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        return mydate;
    }


    void reserveList(final int id) {

        List<CarReserve> LisrOfReserve;
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

        double startKilometers = 0;
        for (CarReserve reserve : reserves) {
            if (reserve.getCarNumber() == id) {
                startKilometers = reserve.getStartKilometers();
                break;
            }


        }
        Start_kilometers.setText("Kilometers in the beggining: " + startKilometers);
        Total_kilometers.setText("Kilometers in the end: " + (Integer.parseInt(kilometrage.getText().toString()) - startKilometers));
        TotalToPay.setText("Total To Pay: " + (Integer.parseInt(kilometrage.getText().toString()) - startKilometers) * 5.5 + "$");

    }


    void carlistList(final int id) {

        List<Car> LisrOfcars;
        new AsyncTask<Void, Void, List<Car>>() {

            List<Car> car;


            @Override
            protected List<Car> doInBackground(Void... voids) {
                car = DBManagerFactory.getManager().getCar();
                return car;
            }

          //  @Override
            protected void onPostExecute(List<CarReserve> reserves) {
                freeCar(car, id);
            }
        }.execute();

    }


    void freeCar(List<Car> LisrOfcars, int id) {
        for (Car car : LisrOfcars) {
            if (car.getCarNumber_id() == id) {
              //  startKilometers = reserve.getStartKilometers();
                break;
            }
        }


    }

}