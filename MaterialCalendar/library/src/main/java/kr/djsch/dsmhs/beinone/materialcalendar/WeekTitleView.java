package kr.djsch.dsmhs.beinone.materialcalendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by BeINone on 2017-04-09.
 */

public class WeekTitleView extends LinearLayout {

    private View mView;

    private TextView mSundayTV;
    private TextView mMondayTV;
    private TextView mTuesdayTV;
    private TextView mWednesdayTV;
    private TextView mThursdayTV;
    private TextView mFridayTV;
    private TextView mSaturdayTV;

    private int mTextColor;
    private int mBackgroundColor;

    public WeekTitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MonthNavigator, 0, 0);
        try {
            int defaultTextColor = ContextCompat.getColor(context, R.color.textColorDisabledLight);
            int defaultBackgroundColor = ContextCompat.getColor(context, R.color.colorPrimaryLight);
            mTextColor = a.getColor(R.styleable.MonthNavigator_textColor, defaultTextColor);
            mBackgroundColor = a.getColor(R.styleable.MonthNavigator_backgroundColor, defaultBackgroundColor);
        } finally {
            a.recycle();
        }

        init();
    }

    public void init() {
        mView = LayoutInflater.from(getContext()).inflate(R.layout.view_weektitle, this, true);

        mSundayTV = (TextView) mView.findViewById(R.id.tv_weektitle_sunday);
        mMondayTV = (TextView) mView.findViewById(R.id.tv_weektitle_monday);
        mTuesdayTV = (TextView) mView.findViewById(R.id.tv_weektitle_tuesday);
        mWednesdayTV = (TextView) mView.findViewById(R.id.tv_weektitle_wednesday);
        mThursdayTV = (TextView) mView.findViewById(R.id.tv_weektitle_thursday);
        mFridayTV = (TextView) mView.findViewById(R.id.tv_weektitle_friday);
        mSaturdayTV = (TextView) mView.findViewById(R.id.tv_weektitle_saturday);

        setBackgroundColor(mBackgroundColor);
    }

    public void setBackgroundColor(int color) {
        mBackgroundColor = color;
        super.setBackgroundColor(color);
    }

    public void setTextColor(int color) {
        mTextColor = color;
        mSundayTV.setTextColor(color);
        mMondayTV.setTextColor(color);
        mTuesdayTV.setTextColor(color);
        mWednesdayTV.setTextColor(color);
        mThursdayTV.setTextColor(color);
        mFridayTV.setTextColor(color);
        mSaturdayTV.setTextColor(color);
    }
}
