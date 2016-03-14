package com.reader.yoshiii.nfcreader.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.reader.yoshiii.nfcreader.Main.HandlerActivity;
import com.reader.yoshiii.nfcreader.R;

/**
 * Created by KIM on 2/7/2016.
 */
public class ScanFragment extends Fragment {

    public static TextView scan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_second, container, false);

        //Initialization

        scan = (TextView) rootView.findViewById(R.id.scan);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if("Scan".equals(scan.getText().toString())) {

                    HandlerActivity.scanEnabled = true;

                    scan.setText("Scanning");
                    scan.setEnabled(false);

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            // This method will be executed once the timer is over
                            // Start your app main activity

                            if ("Scanning".equals(scan.getText().toString())) {

                                HandlerActivity.scanEnabled = false;
                                scan.setText("Scan");
                                scan.setEnabled(true);
                                Toast.makeText(getActivity(), "Scanning timeout", Toast.LENGTH_SHORT).show();

                            }

                        }
                    }, 5000);

                } else {

                    showPopupWrite(getActivity(), "Please specify tag code: ", "Proceed");
                }

            }
        });



        return rootView;
    }


    //  A popup dialog showing confirmation for writing details

    public void showPopupWrite(Activity activity, String message, String okString){

        final EditText writeValue = new EditText(getActivity());

        int maxLengthofEditText = 3;
        writeValue.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLengthofEditText)});

        AlertDialog.Builder popupMsg = new AlertDialog.Builder(activity);
        popupMsg.setTitle("Notice");
        popupMsg.setMessage(message);
        popupMsg.setPositiveButton(okString, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                HandlerActivity.NFCvalue = writeValue.getText().toString();
                HandlerActivity.writeEnabled = true;
                scan.setText("Writing");
                scan.setEnabled(false);

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        // Start your app main activity

                        if ("Writing".equals(scan.getText().toString())) {

                            HandlerActivity.writeEnabled = false;
                            scan.setText("Write");
                            scan.setEnabled(true);
                            Toast.makeText(getActivity(), "Writing timeout", Toast.LENGTH_SHORT).show();

                        }

                    }
                }, 5000);


            }
        });

        popupMsg.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        popupMsg.setView(writeValue);
        popupMsg.show();

    }

}
