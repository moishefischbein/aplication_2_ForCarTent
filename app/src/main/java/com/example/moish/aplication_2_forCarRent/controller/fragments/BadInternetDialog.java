package com.example.moish.aplication_2_forCarRent.controller.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
/**
 * Created by Daniel on 24/01/2018.
 */

public class BadInternetDialog extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Your internet is slow, so the App will not work properly. Do you want to continue?")
                    .setPositiveButton("no", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // yes action!
                            getOut();
                        }
                    })
                    .setNegativeButton("yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog                     }
                        }});
            // Create the AlertDialog object and return it
            return builder.create();
        }

        public void getOut() {

            System.exit(0);
            super.onDestroyView();
        }
}



