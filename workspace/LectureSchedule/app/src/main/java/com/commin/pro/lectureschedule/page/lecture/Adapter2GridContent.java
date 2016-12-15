package com.commin.pro.lectureschedule.page.lecture;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.commin.pro.lectureschedule.R;
import com.commin.pro.lectureschedule.model.Model2Lecture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2016-12-15.
 */
public class Adapter2GridContent extends ArrayAdapter<Model2Lecture> {

    Context context;
    LayoutInflater inflater;
    ArrayList item;
    public Adapter2GridContent(Context context, int resource, ArrayList<Model2Lecture> item) {
        super(context, resource, item);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.item = item;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            view = inflater.inflate(R.layout.item_grid_content, parent, false);
        }
        TextView textView = (TextView)view.findViewById(R.id.tv_item_time);

        Model2Lecture model = (Model2Lecture) item.get(position);

        if(model.isData()){
            textView.setText(model.getName_value()+model.getId());
        }else{
            textView.setText(model.getName_value());
        }
        if(model.isEvents()){
            view.setBackgroundColor(context.getResources().getColor(R.color.colorBrown));
        }

        return view;
    }

}
