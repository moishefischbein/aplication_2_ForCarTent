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


//todo do login and close itself if everything is ok
public class LoginFragment extends Fragment implements View.OnClickListener {
    //todo set client of main activity when login is succesful

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
    }

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton;
    private TextInputLayout usernameWrapper;
    private TextInputLayout passwordWrapper;

    private void findViews(View view) {
        usernameEditText = (EditText) view.findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) view.findViewById(R.id.passwordEditText);
        loginButton = (Button) view.findViewById(R.id.loginButton);
        registerButton = (Button) view.findViewById(R.id.registerButton);
        usernameWrapper = (TextInputLayout) view.findViewById(R.id.usernameWrapper);
        passwordWrapper = (TextInputLayout) view.findViewById(R.id.passwordWrapper);

        usernameWrapper.setHint("Your Id");
        passwordWrapper.setHint("Password");

        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == loginButton) {
            login(v);
        } else if (v == registerButton) {
            register();
        }
    }

    private void login(final View v) {
        //user is id and password is user chosen
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
                    validateLogin(client, v);
                }
            }
        }.execute();

    }

    private void validateLogin(Client client, View v) {

        String password = client.getPassword();//todo password
        String passwordEntered = passwordEditText.getText().toString();

        if(password == null) {
            passwordWrapper.setError("Wrong password");
            Toast.makeText(getContext(),"Worng password", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.compareTo(passwordEntered) == 0) {// ok
            Snackbar.make(v, "Logged In", Snackbar.LENGTH_SHORT).show();
            ((MainActivity) getActivity()).setClient(client);// save client
            //getFragmentManager().beginTransaction().remove(this).commit();

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

            Fragment fragment = new Home();
            fragmentTransaction.replace(R.id.fragment_container, fragment);

            fragmentTransaction.commit();
        } else {
            passwordWrapper.setError("Wrong password");
        }

    }

    private void register() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new RegisterFragment());
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}

