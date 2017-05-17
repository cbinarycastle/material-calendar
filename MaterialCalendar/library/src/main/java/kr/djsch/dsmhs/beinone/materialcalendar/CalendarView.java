package kr.djsch.dsmhs.beinone.materialcalendar;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by BeINone on 2017-04-01.
 */

public class CalendarView extends LinearLayout {

    private static final int STYLE_LIGHT = 11;
    private static final int STYLE_DARK = 12;
    private static final int PICK_MODE_DAY = 21;
    private static final int PICK_MODE_WEEK = 22;

    private int mStyle;
    private int mPickMode;
    private int mButtonColor;

    private View mView;
    private ImageButton mPrevIB;
    private ImageButton mNextIB;

    private ViewPager mViewPager;
    private OnSelectedDayChangedListener mOnSelectedDayChangedListener;

    public CalendarView(Context context) {
        super(context);

        initMonthNavigator();
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.CalendarView, 0, 0);

        try {
            mStyle = a.getInteger(R.styleable.CalendarView_style, STYLE_LIGHT);
            int primaryColor = 0, secondaryColor = 0, accentColor = 0,
                    primaryTextColor = 0, secondaryTextColor = 0, disabledTextColor = 0;
            mPickMode = a.getInteger(R.styleable.CalendarView_pickMode, PICK_MODE_DAY);
            switch (mStyle) {
                case STYLE_LIGHT:
                    primaryColor = ContextCompat.getColor(context, R.color.colorPrimaryLight);
                    secondaryColor = ContextCompat.getColor(context, R.color.colorSecondaryLight);
                    accentColor = ContextCompat.getColor(context, R.color.colorAccentLight);
                    primaryTextColor = ContextCompat.getColor(context, R.color.textColorPrimaryLight);
                    secondaryTextColor = ContextCompat.getColor(context, R.color.textColorSecondaryLight);
                    disabledTextColor = ContextCompat.getColor(context, R.color.textColorDisabledLight);
                    break;
                case STYLE_DARK:
                    primaryColor = ContextCompat.getColor(context, R.color.colorPrimaryDark);
                    secondaryColor = ContextCompat.getColor(context, R.color.colorSecondaryDark);
                    accentColor = ContextCompat.getColor(context, R.color.colorAccentDark);
                    primaryTextColor = ContextCompat.getColor(context, R.color.textColorPrimaryDark);
                    secondaryTextColor = ContextCompat.getColor(context, R.color.textColorSecondaryDark);
                    disabledTextColor = ContextCompat.getColor(context, R.color.textColorDisabledDark);
                    break;
                default: break;
            }
            mButtonColor = a.getColor(R.styleable.CalendarView_buttonColor, secondaryTextColor);
        } finally {
            a.recycle();
        }

        init();
        initMonthNavigator();
    }

    private void init() {
        mView = LayoutInflater.from(getContext()).inflate(R.layout.view_calendar, this, true);

        mViewPager = (ViewPager) findViewById(R.id.viewpager_calendar);
        mPrevIB = (ImageButton) findViewById(R.id.ib_calendar_prev);
        mNextIB = (ImageButton) findViewById(R.id.ib_calendar_next);

        mViewPager.setAdapter(new CalendarPagerAdapter(
                ((AppCompatActivity) getContext()).getSupportFragmentManager()));
        mViewPager.setCurrentItem(CalendarPagerAdapter.START_POSITION);
    }

    private void initMonthNavigator() {
        mPrevIB.setColorFilter(mButtonColor, PorterDuff.Mode.MULTIPLY);
        mPrevIB.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
            }
        });

        mNextIB.setColorFilter(mButtonColor, PorterDuff.Mode.MULTIPLY);
        mNextIB.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
            }
        });
    }

    public void setOnSelectedDayChangedListener(OnSelectedDayChangedListener l) {
        mOnSelectedDayChangedListener = l;
    }

    public interface OnSelectedDayChangedListener {
        void onSelectedDayChanged(Date date);
    }
}
