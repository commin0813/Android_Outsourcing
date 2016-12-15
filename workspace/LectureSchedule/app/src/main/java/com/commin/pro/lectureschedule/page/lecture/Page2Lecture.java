package com.commin.pro.lectureschedule.page.lecture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.commin.pro.lectureschedule.ApplicationProperty;
import com.commin.pro.lectureschedule.R;
import com.commin.pro.lectureschedule.model.Model2Lecture;
import com.commin.pro.lectureschedule.util.UtilDate;
import com.commin.pro.lectureschedule.util.UtilDialog;

import java.util.ArrayList;
import java.util.HashSet;

public class Page2Lecture extends AppCompatActivity {
    private GridView gv_day;
    private GridView gv_content;
    private Adapter2GridDay adapter2GridDay;
    private Adapter2GridContent adapter2GridContent;
    private int device_width;
    private int device_height;
    private int NumColum;
    private int NumRow;
    private ArrayList<String> day_item;

    private ArrayList<Model2Lecture> content_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_page_lecture);
        setDeviceWidthHeight();
        createGUI();
        init_listener();
    }

    private void setDeviceWidthHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        device_width = displayMetrics.widthPixels;// 가로
        device_height = displayMetrics.heightPixels;// 세로
    }

    private void createGUI() {

        content_item = new ArrayList<Model2Lecture>();
        day_item = new ArrayList<String>();

        gv_day = (GridView) findViewById(R.id.gv_day);
        adapter2GridDay = new Adapter2GridDay(Page2Lecture.this, R.layout.item_gird_day, day_item);
        gv_day.setAdapter(adapter2GridDay);

        gv_content = (GridView) findViewById(R.id.gv_content);

        adapter2GridContent = new Adapter2GridContent(Page2Lecture.this, R.layout.item_grid_content, content_item);
        gv_content.setAdapter(adapter2GridContent);

        queryDataGrid();
    }

    private void init_listener() {
        gv_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Model2Lecture model = content_item.get(position);
                if (model.isData()) {
                    UtilDialog.showToast(Page2Lecture.this, "클릭되었습니다." + model.getId());
                }
            }
        });
    }

    private void queryDataGrid(){

        day_item.clear();


        //DB 연동 시 DB에서 가져온 데이터로 Setting

        String[] arr_string_day = getResources().getStringArray(R.array.days_5);
        day_item.add("");
        for (int i = 0; i < arr_string_day.length; i++) {
            day_item.add(arr_string_day[i]);
        }
        NumColum = day_item.size();
        gv_day.setColumnWidth(device_width / NumColum);
        adapter2GridDay.notifyDataSetChanged();






        String[] arr_string_time = getResources().getStringArray(R.array.time_08_19);
        NumRow = arr_string_time.length;
        gv_content.setNumColumns(NumColum);
        gv_content.setColumnWidth(device_width / NumColum);

        int cnt = 0;
        int row_index = 0;
        int colum_index = 0;
        Model2Lecture model;
        for (int i = 0; i < NumColum * NumRow; i++) {
            model = new Model2Lecture();
            model.setRow_index(row_index);
            if (i % NumColum == 0) {
                model.setData(false);
                model.setName_value(arr_string_time[cnt]);
                if (cnt >= 1) {
                    row_index++;
                }
                cnt++;
            } else {
                model.setData(true);
                model.setName_value("");
                if(i%22 ==0){
                    model.setEvents(true);
                }

            }
            if (colum_index >= NumColum) {
                colum_index = 0;
            }
            model.setColumn_index(colum_index);
            model.setId(row_index + ApplicationProperty.OPERATOR_ID + colum_index);
            colum_index++;
            content_item.add(model);
        }


        adapter2GridContent.notifyDataSetChanged();

    }




}
