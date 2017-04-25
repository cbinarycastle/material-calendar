package kr.djsch.dsmhs.beinone.materialcalendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by BeINone on 2017-04-13.
 */

public class WeekView extends LinearLayout {

    private View mView;

    private int mDayBackgroundColor;
    private int mSelectedDayBackgroundColor;
    private int mDayTextColor;

    private OnDaySelectedListener mOnDaySelectedListener;

    public WeekView(Context context) {
        super(context);

        mDayBackgroundColor = ContextCompat.getColor(context, R.color.colorPrimaryLight);
        mSelectedDayBackgroundColor = ContextCompat.getColor(context, R.color.colorAccentLight);
        mDayTextColor = ContextCompat.getColor(context, R.color.textColorDisabledLight);

        init();
    }

    public WeekView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.WeekView, 0, 0);
        try {
            int defaultDayBackgroundColor = ContextCompat.getColor(context, R.color.colorPrimaryLight);
            int defaultDaySelectedBackgroundColor = ContextCompat.getColor(context, R.color.colorAccentLight);
            int defaultDayTextColor = ContextCompat.getColor(context, R.color.textColorDisabledLight);
            mDayBackgroundColor = a.getColor(R.styleable.WeekView_dayBackgroundColor, defaultDayBackgroundColor);
            mSelectedDayBackgroundColor = a.getColor(R.styleable.WeekView_selectedDayBackgroundColor, defaultDaySelectedBackgroundColor);
            mDayTextColor = a.getColor(R.styleable.WeekView_dayTextColor, defaultDayTextColor);
        } finally {
            a.recycle();
        }

        init();
    }

    private void init() {
        mView = LayoutInflater.from(getContext()).inflate(R.layout.view_week, this, true);
    }

    public void setDayView(int dayOfWeek, final Calendar calendar) {
        DayView dayView = (DayView) findViewWithTag(String.valueOf(dayOfWeek));

        dayView.setBackgroundColor(mDayBackgroundColor);
        dayView.setTextColor(mDayTextColor);
        dayView.setSelectedBackgroundColor(mSelectedDayBackgroundColor);

        dayView.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        dayView.setDate(calendar.getTime());
        dayView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(true);
                if (mOnDaySelectedListener != null) {
                    mOnDaySelectedListener.onDaySelected((DayView) v, ((DayView) v).getDate());
                }
            }
        });
    }

    public void clearDayViews() {
        LinearLayout container = (LinearLayout) this.getChildAt(0);
        for (int index = 0; index < container.getChildCount(); index++) {
            DayView dayView = (DayView) container.getChildAt(index);
            dayView.setSelected(false);
        }
    }

    public void setOnDaySelectedListener(OnDaySelectedListener l) {
        mOnDaySelectedListener = l;
    }

    public interface OnDaySelectedListener {
        void onDaySelected(DayView v, Date date);
    }
}
