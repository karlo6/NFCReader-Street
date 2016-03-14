package com.reader.yoshiii.nfcreader.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.reader.yoshiii.nfcreader.R;

/**
 * Created by KIM on 2/7/2016.
 */
public class HelpFragment extends Fragment {

    TextView help;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_fifth, container, false);

        help = (TextView) rootView.findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Not available yet", Toast.LENGTH_SHORT).show();

            }
        });

        return rootView;
    }

}
