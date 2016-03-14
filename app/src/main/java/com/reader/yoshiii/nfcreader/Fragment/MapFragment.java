package com.reader.yoshiii.nfcreader.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reader.yoshiii.nfcreader.Main.GoogleMapActivity;
import com.reader.yoshiii.nfcreader.R;

/**
 * Created by KIM on 2/7/2016.
 */
public class MapFragment extends Fragment {

    TextView openMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_third, container, false);

        //Initialization + Map

        openMap = (TextView) rootView.findViewById(R.id.map);
        openMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), GoogleMapActivity.class);
                startActivity(intent);

            }
        });

        return rootView;
    }

}
