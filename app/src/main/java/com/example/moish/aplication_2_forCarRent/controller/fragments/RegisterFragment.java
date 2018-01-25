
package com.example.moish.aplication_2_forCarRent.controller.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.moish.aplication_2_forCarRent.R;
import com.example.moish.aplication_2_forCarRent.controller.MainActivity;
import com.example.moish.aplication_2_forCarRent.model.backend.DBManagerFactory;
import com.example.moish.aplication_2_forCarRent.model.backend.Functions;
import com.example.moish.aplication_2_forCarRent.model.entities.Client;


public class RegisterFragment extends Fragment implements View.OnClickListener {


/*
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
*/



    private EditText lastNameEditText;
    private EditText firstNameEditText;
    private EditText clientIdEditText;
    private EditText telephoneEditText;
    private EditText emailEditText;
    private EditText creditCardEditText;
    private EditText password;
    private Button addClientButton;

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.activity_add_client, container, false);
        findViews();

        return view;
    }
    private void findViews() {
        lastNameEditText = (EditText) view.findViewById( R.id.LastNameEditText);
        firstNameEditText = (EditText)view.findViewById( R.id.FirstNameEditText );
        clientIdEditText = (EditText)view.findViewById( R.id.IdEditText);
        telephoneEditText = (EditText)view.findViewById( R.id.PhoneEditText );
        emailEditText = (EditText)view.findViewById( R.id.EmailEditText );
        creditCardEditText = (EditText)view.findViewById( R.id.creditCardNumberEditText );
        addClientButton = (Button)view.findViewById( R.id.addClientButton);
        password = (EditText)view.findViewById((R.id.password_client));


        addClientButton.setOnClickListener( this );
    }


    private void addClient(){
        final ContentValues values = new ContentValues();
        try{
            long id = Long.valueOf(clientIdEditText.getText().toString());



            values.put(Functions.ClientConst.ID, clientIdEditText.getText().toString());
            values.put(Functions.ClientConst.FIRSTNAME, firstNameEditText.getText().toString());
            values.put(Functions.ClientConst.LASTTNAME, lastNameEditText.getText().toString());
            values.put(Functions.ClientConst.PHONE, telephoneEditText.getText().toString());
            values.put(Functions.ClientConst.EMAIL, emailEditText.getText().toString());
            values.put(Functions.ClientConst.CREDITCARDNUMBER, creditCardEditText.getText().toString());
            values.put(Functions.ClientConst.PASSWORD, password.getText().toString());

            Client client = Functions.contentValuesToClient(values);
            ((MainActivity) getActivity()).setClient(client);// save client


            new AsyncTask<Object, Object, Void>(){
                @Override
                protected void onPostExecute(Void aVoid) {
                    finish();
                }

                @Override
                protected Void doInBackground(Object... voids) {
                    DBManagerFactory.getManager().addClient(values);
                    return null;
                }
            }.execute();
        }
        catch (Exception e){e.toString();
        }
        finally{
            //this.finish();
        }
    }

    private void validateRegister(Client client, View v) {

        String passwordEntered = password.getText().toString();

        if (passwordEntered.length() > 5) {// ok
            Snackbar.make(v, "Signed Up", Snackbar.LENGTH_SHORT);

        } else {
            password.setError("At least 6 characters long!");
        }

    }


    public void finish(){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        Fragment fragment = new Home();
        fragmentTransaction.replace(R.id.fragment_container, fragment);

        fragmentTransaction.commit();
      }
    @Override
    public void onClick(View v) {
        if ( v == addClientButton ) {
            if(isFullTheAllTexBox()== true)
                addClient();
            else { Toast.makeText(getContext(), "There is an empty field, please fill in: ", Toast.LENGTH_LONG).show();}
        }
    }



    private void clearEditTexts(EditText[] editTexts){
        for (EditText editText : editTexts){
            editText.setText("");
        }
    }

    //this funcion check if the all field are filled
    boolean isFullTheAllTexBox(){
        String LN = lastNameEditText.getText().toString();
        String FN = firstNameEditText.getText().toString();
        String CI = clientIdEditText.getText().toString();
        String TL = telephoneEditText.getText().toString();
        String EM = emailEditText.getText().toString();
        String CC = creditCardEditText.getText().toString();
        if(LN.isEmpty()|| FN.isEmpty() || CI.isEmpty() || TL.isEmpty() || EM.isEmpty() || CC.isEmpty()){
            return false;
        }
        return true;

    }
}
