package com.reader.yoshiii.nfcreader.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.reader.yoshiii.nfcreader.Fragment.AboutFragment;
import com.reader.yoshiii.nfcreader.Fragment.HelpFragment;
import com.reader.yoshiii.nfcreader.Fragment.HomeFragment;
import com.reader.yoshiii.nfcreader.Fragment.MapFragment;
import com.reader.yoshiii.nfcreader.Fragment.ScanFragment;

/**
 * Created by KIM on 2/7/2016.
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                return new HomeFragment();
            case 1:
                return new ScanFragment();
            case 2:
                return new MapFragment();
            case 3:
                return new AboutFragment();
            case 4:
                return new HelpFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 5;
    }
}
