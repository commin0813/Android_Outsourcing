package com.commin.pro.lectureschedule.page.lecture_edit;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.commin.pro.lectureschedule.R;
import com.commin.pro.lectureschedule.util.UtilShare;

import java.util.ArrayList;

public class Page2LectureEdit extends Activity {
    private Spinner sp_edit_last_day, sp_edit_last_time;
    private Button btn_save_setting;
    public SharedPreferences.Editor editor;
    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_page_lecture_edit);
        createGUI();
        setLastDay();
        setLastTime();
        loadPreferences();
    }

    private void createGUI() {
        sp_edit_last_day = (Spinner) findViewById(R.id.sp_edit_last_day);
        sp_edit_last_time = (Spinner) findViewById(R.id.sp_edit_last_time);
        btn_save_setting = (Button) findViewById(R.id.btn_save_setting);
        btn_save_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int timeResourceValue = getTimeResourceValue();
                int dayResourceValue = getDayResourceValue();
                if (timeResourceValue != 0 && dayResourceValue != 0) {
//                    UtilShare.savePreferences(editor,);
                }
            }
        });
    }

    private int getTimeResourceValue() {
        int position = sp_edit_last_time.getSelectedItemPosition();
        switch (position) {
            case 0:
                return R.array.time_08_19;//
            case 1:
                return R.array.time_08_18;//
            case 2:
                return R.array.time_08_17;//
            case 3:
                return R.array.time_08_16;//
            case 4:
                return R.array.time_08_15;//
            case 5:
                return R.array.time_08_14;//
            case 6:
                return R.array.time_08_13;//
        }
        return 0;
    }

    private int getDayResourceValue() {
        int position = sp_edit_last_day.getSelectedItemPosition();
        switch (position) {
            case 0:
                return R.array.days_7;//
            case 1:
                return R.array.days_6;//
            case 2:
                return R.array.days_5;//
        }
        return 0;
    }

    private void setLastDay() {
        ArrayList<String> items2 = new ArrayList<String>();
        String[] day = getResources().getStringArray(R.array.days_7);
        for (int i = day.length - 1; i > 3; i--) {
            items2.add(day[i] + "요일");
        }

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(Page2LectureEdit.this, android.R.layout.simple_spinner_item, items2);
        sp_edit_last_day.setAdapter(adapter2);
    }

    private void setLastTime() {
        ArrayList<String> items = new ArrayList<String>();
        String[] time = getResources().getStringArray(R.array.time_08_19);
        for (int i = time.length - 1; i > 4; i--) {
            items.add(time[i] + "시");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Page2LectureEdit.this, android.R.layout.simple_spinner_item, items);
        sp_edit_last_time.setAdapter(adapter);
    }

    private void loadPreferences() {
        sharedPreferences = UtilShare.getSharedPreferences(UtilShare.SAHRE_STATUS, Page2LectureEdit.this);
        editor = UtilShare.getEditor(sharedPreferences);
    }
}
