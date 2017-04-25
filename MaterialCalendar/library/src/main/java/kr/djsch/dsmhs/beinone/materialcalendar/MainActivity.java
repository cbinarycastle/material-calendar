package kr.djsch.dsmhs.beinone.materialcalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarview);
        calendarView.setOnSelectedDayChangedListener(new CalendarView.OnSelectedDayChangedListener() {
            @Override
            public void onSelectedDayChanged(Date date) {
                Toast.makeText(MainActivity.this, date.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
