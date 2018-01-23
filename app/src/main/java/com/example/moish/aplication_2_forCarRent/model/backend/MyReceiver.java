package com.example.moish.aplication_2_forCarRent.model.backend;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.moish.aplication_2_forCarRent.controller.fragments.All_Branches;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Log.d("my service" , "onReceive");

        Toast.makeText(context,intent.getAction(), Toast.LENGTH_LONG).show();

        String pathName = intent.getStringExtra("package name");
        String className = intent.getStringExtra("class name");

        if(className.equals(All_Branches.class.toString())){
            
        }

    //    throw new UnsupportedOperationException("Not yet implemented");
    }
}
