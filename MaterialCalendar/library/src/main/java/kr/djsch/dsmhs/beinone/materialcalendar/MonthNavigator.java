package kr.djsch.dsmhs.beinone.materialcalendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by BeINone on 2017-04-02.
 */

public class MonthNavigator extends LinearLayout {

    // main view
    private View mView;

    private ImageButton mPrevIB;
    private ImageButton mNextIB;
    private TextView mDateTV;

    private Calendar mCalendar;

    private OnMonthChangedListener mOnMonthChangedListener;

    private int mTextColor;
    private int mBtnColor;
    private int mBackgroundColor;

    public MonthNavigator(Context context) {
        super(context);
        init();
    }

    public MonthNavigator(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MonthNavigator, 0, 0);
        try {
            int defaultTextColor = ContextCompat.getColor(context, R.color.textColorPrimaryLight);
            int defaultBtnColor = ContextCompat.getColor(context, R.color.textColorSecondaryLight);
            int defaultBackgroundColor = ContextCompat.getColor(context, R.color.colorPrimaryLight);
            mTextColor = a.getColor(R.styleable.MonthNavigator_textColor, defaultTextColor);
            mBtnColor = a.getColor(R.styleable.MonthNavigator_buttonColor, defaultBtnColor);
            mBackgroundColor = a.getColor(R.styleable.MonthNavigator_backgroundColor, defaultBackgroundColor);
        } finally {
            a.recycle();
        }

        init();
    }

    private void init() {
        mView = LayoutInflater.from(getContext()).inflate(R.layout.view_monthnavigator, this, true);

        mPrevIB = (ImageButton) mView.findViewById(R.id.ib_slimdatepicker_prev);
        mNextIB = (ImageButton) mView.findViewById(R.id.ib_slimdatepicker_next);
        mDateTV = (TextView) mView.findViewById(R.id.tv_slimdatepicker_date);
        mCalendar = Calendar.getInstance();

        setBackgroundColor(mBackgroundColor);
        setTextColor(mTextColor);
        setButtonColor(mBtnColor);

        mPrevIB.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                prevMonth();
            }
        });

        mNextIB.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                nextMonth();
            }
        });

        // initialize the date text view
        setMonthTV(getMonthString());
    }

    private void nextMonth() {
        mCalendar.add(Calendar.MONTH, 1);
        setMonthTV(getMonthString());
        if (mOnMonthChangedListener != null) {
            mOnMonthChangedListener.onMonthChanged(mCalendar.getTime());
        }
    }

    private void prevMonth() {
        mCalendar.add(Calendar.MONTH, -1);
        setMonthTV(getMonthString());
        if (mOnMonthChangedListener != null) {
            mOnMonthChangedListener.onMonthChanged(mCalendar.getTime());
        }
    }

    private void setMonthTV(String date) {
        mDateTV.setText(date);
    }

    private String getMonthString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월", Locale.KOREA);
        return sdf.format(mCalendar.getTime());
    }

    public Calendar getCalendar() {
        return mCalendar;
    }

    public void setTextColor(int color) {
        mTextColor = color;
        mDateTV.setTextColor(color);
    }

    public void setButtonColor(int color) {
        mBtnColor = color;
        mPrevIB.setColorFilter(color);
        mPrevIB.setColorFilter(color);
    }

    public void setBackgroundColor(int color) {
        mBackgroundColor = color;
        super.setBackgroundColor(color);
    }

    public void setOnMonthChangedListener(@Nullable OnMonthChangedListener l) {
        mOnMonthChangedListener = l;
    }

    public interface OnMonthChangedListener {
        void onMonthChanged(Date date);
    }
}
