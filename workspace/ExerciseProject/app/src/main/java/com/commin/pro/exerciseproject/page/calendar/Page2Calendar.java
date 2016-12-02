package com.commin.pro.exerciseproject.page.calendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.Toast;

import com.commin.pro.exerciseproject.ApplicationProperty;
import com.commin.pro.exerciseproject.R;
import com.commin.pro.exerciseproject.dao.Dao2Excercise;
import com.commin.pro.exerciseproject.model.Model2Excercise;
import com.commin.pro.exerciseproject.page.calendar_view.Page2CalendarView;
import com.commin.pro.exerciseproject.page.menu_list.Page2MenuList;
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
        CalendarView cv = ((CalendarView) findViewById(R.id.calendar_view));
        cv.setEventHandler(new CalendarView.EventHandler() {
            @Override
            public void onDayLongPress(Date date) {
                DateFormat df = SimpleDateFormat.getDateInstance();
                UtilDialog.showToast(Page2Calendar.this, df.format(date));
            }

            @Override
            public void onDayClick(Date date) {

                Model2Excercise model =  Model2Excercise.getModel(date);
                if(model == null){
                    UtilDialog.showToast(Page2Calendar.this,"운동한 기록이 없습니다.");
                    return;
                }
                DateFormat df = SimpleDateFormat.getDateInstance();
                if(!df.format(model.getDate()).equals(df.format(date))){
                    UtilDialog.showToast(Page2Calendar.this,"운동한 기록이 없습니다.");
                    return;
                }

                Intent intent = new Intent(Page2Calendar.this, Page2CalendarView.class);
                intent.putExtra("Model2Excercise", model);
                startActivityForResult(intent, ApplicationProperty.REQUEST_CODE_FOR_CALENDAR_VIEW);
            }
        });
    }

}
