package com.example.moish.aplication_2_forCarRent.controller.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.moish.aplication_2_forCarRent.R;
import com.example.moish.aplication_2_forCarRent.controller.MainActivity;
import com.example.moish.aplication_2_forCarRent.model.backend.DBManagerFactory;
import com.example.moish.aplication_2_forCarRent.model.entities.Client;


public class RegisterFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        findViews(view);
    }

    private TextInputLayout usernameWrapper;
    private EditText usernameEditText;
    private TextInputLayout passwordWrapper;
    private EditText passwordEditText;
    private Button registerButton;

    private void findViews(View view) {
        usernameWrapper = (TextInputLayout)view.findViewById( R.id.usernameWrapper );
        usernameEditText = (EditText)view.findViewById( R.id.usernameEditText );
        passwordWrapper = (TextInputLayout)view.findViewById( R.id.passwordWrapper );
        passwordEditText = (EditText)view.findViewById( R.id.passwordEditText );
        registerButton = (Button)view.findViewById( R.id.registerButton );

        registerButton.setOnClickListener( this );
    }

    
    @Override
    public void onClick(View v) {
        if ( v == registerButton ) {
            register(v);
        }
    }

    private void register(final View v) {

        final int id = Integer.parseInt((usernameEditText.getText().toString()));

        new AsyncTask<Void, Void, Client>() {
            @Override
            protected Client doInBackground(Void... voids) {
                return DBManagerFactory.getManager().findClientById(id);
            }

            @Override
            protected void onPostExecute(Client client) {
                if (client == null) {
                    usernameWrapper.setError("User does not exist");
                } else {
                    validateRegister(client, v);
                }
            }
        }.execute();

    }

    private void validateRegister(Client client, View v) {

        String passwordEntered = passwordEditText.getText().toString();

        if (passwordEntered.length() > 5) {// ok
            Snackbar.make(v, "Signed Up", Snackbar.LENGTH_SHORT);
            updateClient(client.getId(), passwordEntered, v);//todo method
            ((MainActivity) getActivity()).setClient(client);// save client

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

            Fragment fragment = new Home();
            fragmentTransaction.replace(R.id.fragment_container, fragment);

            fragmentTransaction.commit();
        } else {
            passwordWrapper.setError("At least 6 characters long!");
        }

    }

    private void updateClient(final long id, final String passwordEntered, final View v) {
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                return DBManagerFactory.getManager().updateClient(id, passwordEntered);
            }

            @Override
            protected void onPostExecute(Long id) {
                if (id > 0) {
                    Toast.makeText(v.getContext(), "ID: " + id, Toast.LENGTH_LONG).show();
                }
            }
        }.execute();
    }


}
