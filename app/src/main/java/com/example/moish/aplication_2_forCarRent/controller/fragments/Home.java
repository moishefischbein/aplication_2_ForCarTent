package com.example.moish.aplication_2_forCarRent.controller.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moish.aplication_2_forCarRent.R;

/**
 * Created by moish on 17/01/2018.
 */

public class Home extends Fragment implements View.OnClickListener  {

    private View mView;

    private TextView textView2;
    private Button call;
    private Button webS;
    private Button mail;
    private ImageView imageView2;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-01-23 21:03:36 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_home, container, false);

        textView2 = (TextView) mView.findViewById(R.id.textView2);
        call = (Button) mView.findViewById(R.id.call);
        webS = (Button) mView.findViewById(R.id.webSite);
        mail = (Button) mView.findViewById(R.id.mail);
        imageView2 = (ImageView) mView.findViewById(R.id.imageView2);

        call.setOnClickListener(this);
        webS.setOnClickListener(this);
        mail.setOnClickListener(this);

        return mView;
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2018-01-23 21:03:36 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if (v == call) {
            // Handle clicks for call
            String number = "12345678";
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" +number));
            startActivity(intent);
        } else if (v == webS) {
            // Handle clicks for button4
            webS.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("http://www.buzzingandroid.com"));
                    startActivity(intent);
                }
            });
        } else if (v == mail) {
            // Handle clicks for mail
           // public void composeEmail(String[] addresses, String subject) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, "ghjg");
                intent.putExtra(Intent.EXTRA_SUBJECT, "jjbb");
              //  if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
               // }
    }
}}