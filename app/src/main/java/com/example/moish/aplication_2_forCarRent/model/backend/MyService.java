package com.example.moish.aplication_2_forCarRent.model.backend;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.moish.aplication_2_forCarRent.model.entities.Car;

import java.util.ArrayList;
import java.util.List;

public class MyService extends Service {

    final String TAG = "myservice";
    static boolean ServiceRun;// = false;

    static {
        ServiceRun = false;
    }

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");

    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        // TODO: Return the communication channel to the service.
        return null;
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        if (!ServiceRun) {
            ServiceRun = true;
            Toast.makeText(this, "run service", Toast.LENGTH_LONG).show();

            Thread t = new Thread() {
                int numberOfCars;
                @Override
                public void run() {


                    List<Car> freeCars = new ArrayList<Car>();
                    freeCars = DBManagerFactory.getManager().getFreeCars();
                    numberOfCars = freeCars.size();
                    while (true) {

                        try {
                            Thread.sleep(10000);
                            Log.d(TAG, "thread run ..");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (!(DBManagerFactory.getManager().isUpdate(numberOfCars))) {
                            numberOfCars = DBManagerFactory.getManager().getFreeCars().size();
                            Log.d(TAG, "isUpdate run ..");
                            Intent intent1 = new Intent("com.example.moish.aplication_2_forCarRent.UPDATE");
                            intent1.putExtra("package name", intent.getStringExtra("package path"));
                            intent1.putExtra("class name", intent.getStringExtra("class path"));
                            MyService.this.sendBroadcast(intent1);
                        }


                    }
                }
            };
            t.start();
            return START_STICKY;
        }

        Toast.makeText(this, "The service is already running", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

}