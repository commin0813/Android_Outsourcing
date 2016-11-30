package com.commin.pro.exerciseproject.page.calendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.Toast;

import com.commin.pro.exerciseproject.ApplicationProperty;
import com.commin.pro.exerciseproject.R;
import com.commin.pro.exerciseproject.page.calendar_view.Page2CalendarView;
import com.commin.pro.exerciseproject.util.UtilDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class Page2Calendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_calendar_layout);
        createGUI();
    }

    private void createGUI() {
        HashSet<Date> events = new HashSet<>();
        events.add(new Date());

        CalendarView cv = ((CalendarView) findViewById(R.id.calendar_view));
        cv.updateCalendar(events);

        cv.setEventHandler(new CalendarView.EventHandler() {
            @Override
            public void onDayLongPress(Date date) {
                DateFormat df = SimpleDateFormat.getDateInstance();
                UtilDialog.showToast(Page2Calendar.this,df.format(date));
            }

            @Override
            public void onDayClick(Date date) {
//                DateFormat df = SimpleDateFormat.getDateInstance();
//                Toast.makeText(Page2Calendar.this, df.format(date), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Page2Calendar.this, Page2CalendarView.class);
                startActivityForResult(intent, ApplicationProperty.REQUEST_CODE_FOR_CALENDAR_VIEW);
            }
        });
    }

}
