package kr.djsch.dsmhs.beinone.materialcalendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by BeINone on 2017-04-01.
 */

public class CalendarView extends LinearLayout {

    private static final int STYLE_LIGHT = 11;
    private static final int STYLE_DARK = 12;
    private static final int PICK_MODE_DAY = 21;
    private static final int PICK_MODE_WEEK = 22;

    private View mView;
    private int mStyle;
    private int mPickMode;
    private int mMonthNavigatorTextColor;
    private int mMonthNavigatorButtonColor;
    private int mMonthNavigatorBackgroundColor;

    public CalendarView(Context context) {
        super(context);
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
            mMonthNavigatorTextColor = a.getColor(R.styleable.CalendarView_monthNavigatorTextColor, primaryTextColor);
            mMonthNavigatorButtonColor = a.getColor(R.styleable.CalendarView_monthNavigatorButtonColor, secondaryTextColor);
            mMonthNavigatorBackgroundColor = a.getColor(R.styleable.CalendarView_monthNavigatorBackgroundColor, primaryColor);
        } finally {
            a.recycle();
        }

        mView = LayoutInflater.from(context).inflate(R.layout.view_calendar, this, true);

        initMonthNavigator();
    }

    private void initMonthNavigator() {
        MonthNavigator monthNavigator = (MonthNavigator) findViewById(R.id.monthnavigator_calendar);
        monthNavigator.setTextColor(mMonthNavigatorTextColor);
        monthNavigator.setButtonColor(mMonthNavigatorButtonColor);
        monthNavigator.setBackgroundColor(mMonthNavigatorBackgroundColor);
    }

}
