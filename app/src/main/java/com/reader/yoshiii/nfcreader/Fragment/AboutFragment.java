package com.reader.yoshiii.nfcreader.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reader.yoshiii.nfcreader.Main.AboutActivity;
import com.reader.yoshiii.nfcreader.R;

/**
 * Created by KIM on 2/7/2016.
 */
public class AboutFragment extends Fragment {

    TextView about;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_fourth, container, false);

        about = (TextView) rootView.findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);

            }
        });

        return rootView;
    }

}
