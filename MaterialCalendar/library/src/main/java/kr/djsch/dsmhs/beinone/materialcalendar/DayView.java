package kr.djsch.dsmhs.beinone.materialcalendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by BeINone on 2017-04-13.
 */

public class DayView extends LinearLayout {

    private View mView;

    private TextView mDayTV;

    private Drawable mDayBackground;
    private int mBackgroundColor;
    private int mSelectedBackgroundColor;
    private int mTextColor;

    private Calendar mCalendar;

    public DayView(Context context) {
        super(context);

        mBackgroundColor = ContextCompat.getColor(context, R.color.colorPrimaryLight);
        mSelectedBackgroundColor = ContextCompat.getColor(context, R.color.colorAccentLight);
        mTextColor = ContextCompat.getColor(context, R.color.textColorDisabledLight);

        init();
    }

    public DayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DayView, 0, 0);
        try {
            int defaultBackgroundColor = ContextCompat.getColor(context, R.color.colorPrimaryLight);
            int defaultSelectedBackgroundColor = ContextCompat.getColor(context, R.color.colorAccentLight);
            int defaultTextColor = ContextCompat.getColor(context, R.color.textColorDisabledLight);
            mBackgroundColor = a.getColor(R.styleable.DayView_backgroundColor, defaultBackgroundColor);
            mSelectedBackgroundColor = a.getColor(R.styleable.DayView_dayBackgroundColor, defaultSelectedBackgroundColor);
            mTextColor = a.getColor(R.styleable.DayView_textColor, defaultTextColor);
        } finally {
            a.recycle();
        }

        init();
    }

    private void init() {
        mView = LayoutInflater.from(getContext()).inflate(R.layout.view_day, this, true);

        mDayTV = (TextView) mView.findViewById(R.id.tv_day_day);
        // make mDayTV into square
        mDayTV.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mDayTV.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mDayTV.setHeight(mDayTV.getMeasuredWidth());
            }
        });

        mDayBackground = ContextCompat.getDrawable(getContext(), R.drawable.background_day);
        mDayBackground.setColorFilter(mBackgroundColor, PorterDuff.Mode.MULTIPLY);

        setBackgroundColor(mBackgroundColor);
        setTextColor(mTextColor);
        setSelectedBackgroundColor(mSelectedBackgroundColor);
    }

    private void setDayBackground(int color) {
        mDayBackground.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        mDayTV.setBackground(mDayBackground);
    }

    public void setBackgroundColor(int color) {
        mBackgroundColor = color;
        super.setBackgroundColor(color);
    }

    public void setTextColor(int color) {
        mTextColor = color;
        mDayTV.setTextColor(color);
    }

    public void setSelectedBackgroundColor(int color) {
        mSelectedBackgroundColor = color;
        mDayBackground.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
    }

    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected) {
            setDayBackground(mSelectedBackgroundColor);
        } else {
            setDayBackground(mBackgroundColor);
        }
    }

    public void setDay(int day) {
        mDayTV.setText(String.valueOf(day));
    }

    public void setDate(Date date) {
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(date);
    }

    public Date getDate() {
        return mCalendar.getTime();
    }
}
