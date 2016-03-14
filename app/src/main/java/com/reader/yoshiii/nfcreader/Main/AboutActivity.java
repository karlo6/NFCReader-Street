package com.reader.yoshiii.nfcreader.Main;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.reader.yoshiii.nfcreader.R;

/**
 * Created by KIM on 2/3/2016.
 */
public class AboutActivity extends Activity {

    TextView _1565, _1571, _1573, _1945;
    LinearLayout _1565_layout, _1571_layout, _1573_layout, _1945_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        _1565 = (TextView)findViewById(R.id.tv_1565);
        _1571 = (TextView)findViewById(R.id.tv_1571);
        _1573 = (TextView)findViewById(R.id.tv_1573);
        _1945 = (TextView)findViewById(R.id.tv_1945);

        _1565_layout = (LinearLayout) findViewById(R.id.date_1565_layout);
        _1571_layout = (LinearLayout) findViewById(R.id.date_1571_layout);
        _1573_layout = (LinearLayout) findViewById(R.id.date_1573_layout);
        _1945_layout = (LinearLayout) findViewById(R.id.date_1945_layout);

        _1565.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(_1565_layout.getVisibility() == View.GONE){
                    _1565_layout.setVisibility(View.VISIBLE);
                }

                else{
                    _1565_layout.setVisibility(View.GONE);
                }

            }
        });

        _1571.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(_1571_layout.getVisibility() == View.GONE){
                    _1571_layout.setVisibility(View.VISIBLE);
                }

                else{
                    _1571_layout.setVisibility(View.GONE);
                }

            }
        });

        _1573.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(_1573_layout.getVisibility() == View.GONE){
                    _1573_layout.setVisibility(View.VISIBLE);
                }

                else{
                    _1573_layout.setVisibility(View.GONE);
                }

            }
        });

        _1945.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(_1945_layout.getVisibility() == View.GONE){
                    _1945_layout.setVisibility(View.VISIBLE);
                }

                else{
                    _1945_layout.setVisibility(View.GONE);
                }
            }
        });

    }
}
