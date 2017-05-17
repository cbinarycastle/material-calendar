package kr.djsch.dsmhs.beinone.materialcalendar;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by BeINone on 2017-05-13.
 */

public class CalendarContentFragment extends Fragment {

    private static final String KEY_DATE = "date";

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar_content, container, false);

        Date date = new Date(getArguments().getLong(KEY_DATE));
        init(view, date);

        return view;
    }

    private void init(View rootView, Date date) {
        mMonthTV = (TextView) rootView.findViewById(R.id.tv_calendar_content_month);
        mContainer = (LinearLayout) rootView.findViewById(R.id.container_calendar_content);

        SimpleDateFormat sdf = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sdf = new SimpleDateFormat(getString(R.string.date_format), getResources().getConfiguration().getLocales().get(0));
        } else {
            sdf = new SimpleDateFormat(getString(R.string.date_format), getResources().getConfiguration().locale);
        }
        mMonthTV.setText(sdf.format(date));

        initDayViews(date);
    }

    private void initDayViews(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal = CalendarUtils.setDayOfMonth(cal, 1);

        int month = CalendarUtils.getMonth(cal);
        while (month == CalendarUtils.getMonth(cal)) {
            WeekView weekView = new WeekView(getContext());
            for (int dayOfWeek = CalendarUtils.getDayOfWeek(cal); dayOfWeek <= Calendar.SATURDAY; dayOfWeek++) {
                weekView.setDayView(CalendarUtils.getDayOfWeek(cal), cal);
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

    public void setOnSelectedDayChangedListener(OnSelectedDayChangedListener l) {
        mOnSelectedDayChangedListener = l;
    }

    public interface OnSelectedDayChangedListener {
        void onSelectedDayChanged(Date date);
    }
}
