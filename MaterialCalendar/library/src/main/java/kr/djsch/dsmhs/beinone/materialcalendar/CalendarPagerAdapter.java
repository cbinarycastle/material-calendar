package kr.djsch.dsmhs.beinone.materialcalendar;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by BeINone on 2017-05-14.
 */

public class CalendarPagerAdapter extends FragmentPagerAdapter {

    public static final int NUM_PAGES = 1000;
    public static final int START_POSITION = NUM_PAGES / 2;

    private final Calendar BASE_CAL;

    private CalendarContentFragment[] mFragments;
    private Date mSelectedDate;

    public CalendarPagerAdapter(FragmentManager fm) {
        super(fm);
        BASE_CAL = Calendar.getInstance();
        mSelectedDate = new Date();
        mFragments = new CalendarContentFragment[NUM_PAGES];
    }

    @Override
    public Fragment getItem(final int position) {
        int howFarFromStart = position - START_POSITION;
        Calendar cal = (Calendar) BASE_CAL.clone();
        cal.add(Calendar.MONTH, howFarFromStart);

        Calendar selectedCal = Calendar.getInstance();
        selectedCal.setTime(mSelectedDate);

        CalendarContentFragment fragment;
        if (cal.get(Calendar.MONTH) == selectedCal.get(Calendar.MONTH)) {
            fragment = CalendarContentFragment.newInstance(cal.getTime(), mSelectedDate);
        } else {
            fragment = CalendarContentFragment.newInstance(cal.getTime());
        }
        fragment.setOnSelectedDayChangedListener(new CalendarContentFragment.OnSelectedDayChangedListener() {
            @Override
            public void onSelectedDayChanged(Date date) {
                mSelectedDate = date;
                mFragments[position - 1].clear();
                mFragments[position + 1].clear();
            }
        });
        mFragments[position] = fragment;

        return fragment;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
