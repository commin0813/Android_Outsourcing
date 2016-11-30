package com.commin.pro.exerciseproject.page.calendar;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;


import com.commin.pro.exerciseproject.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by user on 2016-11-28.
 */
public class Adapter2Calendar extends ArrayAdapter<Date> {

    private HashSet<Date> eventDays;
    private LayoutInflater inflater;
    private Context context;


    public Adapter2Calendar(Context context, ArrayList<Date> days, HashSet<Date> eventDays) {
        super(context, R.layout.page_calendar_item_day, days);
        this.context = context;
        this.eventDays = eventDays;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Date date = getItem(position);
        int day = date.getDate();
        int month = date.getMonth();
        int year = date.getYear();

        Date today = new Date();

        if (view == null) {
            view = inflater.inflate(R.layout.page_calendar_item_day, parent, false);

        }

        view.setBackgroundResource(0);
        if (eventDays != null) {
            for (Date eventDate : eventDays) {
                if (eventDate.getDate() == day &&
                        eventDate.getMonth() == month &&
                        eventDate.getYear() == year) {
                    view.setBackgroundResource(R.drawable.reminder);
                    break;
                }
            }
        }
        TextView textView = (TextView) view.findViewById(R.id.tv_calendar_day);
        textView.setTypeface(null, Typeface.NORMAL);
        textView.setTextColor(context.getResources().getColor(R.color.greyed_out));

//            if (month != today.getMonth() || year != today.getYear()) {
//                // if this day is outside current month, grey it out
//                textView.setTextColor(getResources().getColor(R.color.greyed_out));
//            }
        Calendar currentDate =CalendarView.getCalendarInstance();
        if (month == currentDate.get(Calendar.MONTH)) {
            // if this day is outside current month, grey it out
            textView.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            if (year == today.getYear() && month == today.getMonth() && day == today.getDate()) {
                textView.setTypeface(null, Typeface.BOLD);
                textView.setTextColor(context.getResources().getColor(R.color.today));
            }
        }
//            if (year == today.getYear() && month == today.getMonth() && day == today.getDate()) {
//                // if it is today, set it to blue/bold
//                ((TextView) view).setTypeface(null, Typeface.BOLD);
//                ((TextView) view).setTextColor(getResources().getColor(R.color.today));
//            }

        textView.setText(String.valueOf(date.getDate()));

        return view;
    }


}
