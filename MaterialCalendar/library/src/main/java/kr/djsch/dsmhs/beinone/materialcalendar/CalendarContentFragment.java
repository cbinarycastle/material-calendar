package kr.djsch.dsmhs.beinone.materialcalendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by BeINone on 2017-05-13.
 */

public class CalendarContentFragment extends Fragment {

    private static final String KEY_DATE = "date";
    private static final String KEY_SELECTED_DATE = "selectedDate";

    private TextView mMonthTV;
    private LinearLayout mContainer;

    private OnSelectedDayChangedListener mOnSelectedDayChangedListener;

    public static CalendarContentFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putLong(KEY_DATE, date.getTime());

        CalendarContentFragment fragment = new CalendarContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static CalendarContentFragment newInstance(Date calendarDate, Date selectedDate) {
        Bundle args = new Bundle();
        args.putLong(KEY_DATE, calendarDate.getTime());
        args.putLong(KEY_SELECTED_DATE, selectedDate.getTime());

        CalendarContentFragment fragment = new CalendarContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar_content, container, false);

        Date date = new Date(getArguments().getLong(KEY_DATE));
        Date selectedDate = new Date(getArguments().getLong(KEY_SELECTED_DATE));
        init(view, date, selectedDate);

        return view;
    }

    public void clear() {
        clearDayViews();
    }

    public void setOnSelectedDayChangedListener(OnSelectedDayChangedListener l) {
        mOnSelectedDayChangedListener = l;
    }

    private void init(View rootView, Date date, Date selectedDate) {
        mMonthTV = (TextView) rootView.findViewById(R.id.tv_calendar_content_month);
        mContainer = (LinearLayout) rootView.findViewById(R.id.container_calendar_content);

        SimpleDateFormat sdf = new SimpleDateFormat(getString(R.string.date_format), Locale.getDefault());
        mMonthTV.setText(sdf.format(date));

        initDayViews(date, selectedDate);
    }

    private void initDayViews(Date date, Date selectedDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal = CalendarUtils.setDayOfMonth(cal, 1);

        int month = CalendarUtils.getMonth(cal);
        while (month == CalendarUtils.getMonth(cal)) {
            WeekView weekView = new WeekView(getContext());
            for (int dayOfWeek = CalendarUtils.getDayOfWeek(cal); dayOfWeek <= Calendar.SATURDAY; dayOfWeek++) {
                boolean isSelected = false;

                Calendar selectedCal = Calendar.getInstance();
                selectedCal.setTime(selectedDate);
                if (!cal.after(selectedCal) && !cal.before(selectedCal)) {  // date == selectedDate
                    isSelected = true;
                }
                weekView.setDayView(CalendarUtils.getDayOfWeek(cal), cal, isSelected);

                cal.add(Calendar.DAY_OF_MONTH, 1);
                if (month != CalendarUtils.getMonth(cal)) {
                    break;
                }
            }

            weekView.setOnDaySelectedListener(new WeekView.OnDaySelectedListener() {
                @Override
                public void onDaySelected(DayView v, Date date) {
                    clearDayViews();
                    v.setSelected(true);
                    if (mOnSelectedDayChangedListener != null) {
                        mOnSelectedDayChangedListener.onSelectedDayChanged(date);
                    }
                }
            });

            mContainer.addView(weekView);
        }
    }

    private void clearDayViews() {
        for (int index = 0; index < mContainer.getChildCount(); index++) {
            WeekView weekView = (WeekView) mContainer.getChildAt(index);
            weekView.clearDayViews();
        }
    }

    public interface OnSelectedDayChangedListener {
        void onSelectedDayChanged(Date date);
    }
}
