package kr.djsch.dsmhs.beinone.materialcalendar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.Calendar;

/**
 * Created by BeINone on 2017-05-14.
 */

public class CalendarPagerAdapter extends FragmentStatePagerAdapter {

    public static final int NUM_PAGES = 1000;
    public static final int START_POSITION = NUM_PAGES / 2;

    private final Calendar BASE_CAL;

    public CalendarPagerAdapter(FragmentManager fm) {
        super(fm);
        BASE_CAL = Calendar.getInstance();
    }

    @Override
    public Fragment getItem(int position) {
        int howFarFromStart = position - START_POSITION;
        Calendar cal = (Calendar) BASE_CAL.clone();
        cal.add(Calendar.MONTH, howFarFromStart);

        return CalendarContentFragment.newInstance(cal.getTime());
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
