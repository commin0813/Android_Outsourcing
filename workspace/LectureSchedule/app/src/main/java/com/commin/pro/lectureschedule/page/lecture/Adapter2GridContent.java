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
import com.commin.pro.lectureschedule.util.UtilCheck;

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
        TextView tv_item_description = (TextView) view.findViewById(R.id.tv_item_description);
        TextView tv_item_start_time = (TextView) view.findViewById(R.id.tv_item_start_time);
        tv_item_start_time.setSelected(true);
        TextView tv_item_end_time = (TextView) view.findViewById(R.id.tv_item_end_time);
        tv_item_end_time.setSelected(true);

        Model2Lecture model = (Model2Lecture) item.get(position);

        if (!model.isData()) {
            tv_item_start_time.setText(model.getName_value());
        }

        if (model.isEvents()) {
            tv_item_start_time.setTextColor(context.getResources().getColor(R.color.colorWhiteFA));
            tv_item_description.setTextColor(context.getResources().getColor(R.color.colorWhiteFA));
            tv_item_end_time.setTextColor(context.getResources().getColor(R.color.colorWhiteFA));
            if (model.getPosition().equals("top")) {
                if (UtilCheck.checkPeriod(model.getStart_time(), model.getEnd_time()) <= 1) {
                    tv_item_start_time.setText(UtilCheck.checkTime(model.getStart_time()) + " " + model.getStart_time() + " : 00");
                    tv_item_description.setText(
                            model.getClass_name() + "\n" +
                                    model.getClassroom_name() + "\n");
                    tv_item_end_time.setText(UtilCheck.checkTime(model.getEnd_time()) + " " + model.getEnd_time() + " : 00" + "\n");
                } else {
                    tv_item_start_time.setText(UtilCheck.checkTime(model.getStart_time()) + " " + model.getStart_time() + " : 00");
                    tv_item_description.setText(model.getClass_name() + "\n" +
                            model.getClassroom_name() + "\n"

                    );
                }
            }
            if (model.getPosition().equals("bottom")) {
                tv_item_description.setText(model.getProfessor_name());
                tv_item_end_time.setText(UtilCheck.checkTime(model.getEnd_time()) + " " + model.getEnd_time() + " : 00" + "\n");
            }

            switch (UtilCheck.checkDay(model.getId())) {
                case "월":view.setBackgroundColor(context.getResources().getColor(R.color.colorWhiteBlue));
                    break;
                case "화":view.setBackgroundColor(context.getResources().getColor(R.color.colorBrown));
                    break;
                case "수":view.setBackgroundColor(context.getResources().getColor(R.color.colorDarkOrange));
                    break;
                case "목":view.setBackgroundColor(context.getResources().getColor(R.color.colorRed36));
                    break;
                case "금":view.setBackgroundColor(context.getResources().getColor(R.color.colorWhiteBlue));
                    break;
                case "토":view.setBackgroundColor(context.getResources().getColor(R.color.colorBrown));
                    break;
                case "일":view.setBackgroundColor(context.getResources().getColor(R.color.colorDarkOrange));
                    break;
            }

        }

        return view;
    }

}
