package com.example.moish.aplication_2_forCarRent.controller;

import android.app.Activity;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moish.aplication_2_forCarRent.R;
import com.example.moish.aplication_2_forCarRent.model.backend.DBManagerFactory;
import com.example.moish.aplication_2_forCarRent.model.backend.Functions;

import static com.example.moish.aplication_2_forCarRent.R.id.IdEditText;


public class AddClientActivity extends Activity implements View.OnClickListener{


    private EditText lastNameEditText;
    private EditText firstNameEditText;
    private EditText clientIdEditText;
    private EditText telephoneEditText;
    private EditText emailEditText;
    private EditText creditCardEditText;
    private EditText password;
    private Button addClientButton;


    private void findViews() {
        lastNameEditText = (EditText)findViewById( R.id.LastNameEditText);
        firstNameEditText = (EditText)findViewById( R.id.FirstNameEditText );
        clientIdEditText = (EditText)findViewById( IdEditText);
        telephoneEditText = (EditText)findViewById( R.id.PhoneEditText );
        emailEditText = (EditText)findViewById( R.id.EmailEditText );
        creditCardEditText = (EditText)findViewById( R.id.creditCardNumberEditText );
        password = (EditText)findViewById( R.id.password_client );
        addClientButton = (Button)findViewById( R.id.addClientButton);

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


            new AsyncTask<Void, Void, Long>(){
                @Override
                protected void onPostExecute(Long aLong) {
                    Toast.makeText(getBaseContext(), "ID: " + aLong, Toast.LENGTH_LONG).show();
                    Log.d("client", values.toString());
                }

                @Override
                protected Long doInBackground(Void... voids) {
                    return DBManagerFactory.getManager().addClient(values);
                }
            }.execute();
        }
        catch (Exception e){e.toString();
        }
        finally{
            this.finish();
        }
    }


    @Override
    public void onClick(View v) {
        if ( v == addClientButton ) {
            if(isFullTheAllTexBox()== true)
            addClient();
            else { Toast.makeText(getBaseContext(), "There is an empty field, please fill in: ", Toast.LENGTH_LONG).show();}
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        findViews();
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
