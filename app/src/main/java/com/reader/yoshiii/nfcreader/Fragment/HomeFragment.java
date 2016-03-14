package com.reader.yoshiii.nfcreader.Fragment;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.reader.yoshiii.nfcreader.R;

/**
 * Created by KIM on 2/7/2016.
 */
public class HomeFragment extends Fragment {

    ImageView logo;
    int counter = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // Initialization + Dev console setup

        logo = (ImageView) rootView.findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                counter++;

                if(counter == 5){

                    if("Scan".equals(ScanFragment.scan.getText().toString())) {
                        ScanFragment.scan.setText("Write");
                        Toast.makeText(getActivity(), "Developer mode unlocked!", Toast.LENGTH_SHORT).show();
                    }

                    else{
                        ScanFragment.scan.setText("Scan");
                        Toast.makeText(getActivity(), "Developer mode locked!", Toast.LENGTH_SHORT).show();
                    }
                    counter = 0;
                }

            }
        });

        return rootView;
    }

}
