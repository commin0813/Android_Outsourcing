package com.commin.pro.lectureschedule.page.lecture_add;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.commin.pro.lectureschedule.ApplicationProperty;
import com.commin.pro.lectureschedule.R;
import com.commin.pro.lectureschedule.model.Model2Lecture;
import com.commin.pro.lectureschedule.util.UtilDialog;

import java.util.ArrayList;

public class Page2LectureAdd extends AppCompatActivity {
    private RadioGroup radio_group;
    private RadioButton radio_btn_mon, radio_btn_the, radio_btn_wed, radio_btn_thu, radio_btn_frd, radio_btn_sat, radio_btn_sun;
    private EditText ed_class_name, ed_professor_name, ed_classroom_name;
    private TextView tv_day_name;
    private LinearLayout ll_layer_box;
    private Spinner sp_start_time, sp_end_time;

    private Button btn_complete_add;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_page_lecture_add);
        createGUI();
        init_listener();
    }

    private void createGUI() {
        btn_complete_add = (Button)findViewById(R.id.btn_complete_add);
        radio_group = (RadioGroup) findViewById(R.id.radio_group);
        radio_btn_mon = (RadioButton) findViewById(R.id.radio_btn_mon);
        radio_btn_the = (RadioButton) findViewById(R.id.radio_btn_the);
        radio_btn_wed = (RadioButton) findViewById(R.id.radio_btn_wed);
        radio_btn_thu = (RadioButton) findViewById(R.id.radio_btn_thu);
        radio_btn_frd = (RadioButton) findViewById(R.id.radio_btn_frd);
        radio_btn_sat = (RadioButton) findViewById(R.id.radio_btn_sat);
        radio_btn_sun = (RadioButton) findViewById(R.id.radio_btn_sun);

        tv_day_name = (TextView) findViewById(R.id.tv_day_name);
        ll_layer_box = (LinearLayout) findViewById(R.id.ll_layer_box);
        ed_class_name = (EditText) findViewById(R.id.ed_class_name);
        ed_professor_name = (EditText) findViewById(R.id.ed_professor_name);
        ed_classroom_name = (EditText) findViewById(R.id.ed_classroom_name);

        sp_start_time = (Spinner) findViewById(R.id.sp_start_time);
        ArrayList<String> items= new ArrayList<String>();
        String []  str =  getResources().getStringArray(R.array.time_08_19);
        for(String ss : str){
            items.add(ss);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Page2LectureAdd.this, android.R.layout.simple_spinner_dropdown_item, items);
        sp_start_time.setAdapter(adapter);
        sp_end_time = (Spinner) findViewById(R.id.sp_end_time);
        sp_end_time.setAdapter(adapter);




    }
    private boolean checkNull(){
        if(ed_class_name.getText().toString().equals("")&&ed_classroom_name.getText().toString().equals("")&&ed_professor_name.getText().toString().equals("")){
            return true;
        }

        return false;
    }
    private void init_listener(){
        btn_complete_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkNull()){
                    UtilDialog.showToast(Page2LectureAdd.this,"값을 모두 입력하세요.");
                    return;
                }
                RadioButton radioButton = (RadioButton)findViewById(radio_group.getCheckedRadioButtonId());
                String selected_day = radioButton.getText().toString();
                int colum_index =0;
                switch (selected_day){
                    case "월":colum_index=1;break;
                    case "화":colum_index=2;break;
                    case "수":colum_index=3;break;
                    case "목":colum_index=4;break;
                    case "금":colum_index=5;break;
                    case "토":colum_index=6;break;
                    case "일":colum_index=7;break;
                }
                String selected_start_time = sp_start_time.getSelectedItem().toString();
                int start_row_index = 0;
                String [] time = getResources().getStringArray(R.array.time_08_19);
                for(int i = 0 ; i <time.length;i++){
                    if(selected_start_time.equals(time[i])){
                        start_row_index = i;
                        break;
                    }
                }

                String id = start_row_index+ ApplicationProperty.OPERATOR_ID+colum_index;


                Model2Lecture model = new Model2Lecture();

            }
        });

        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int view) {
                if(!ll_layer_box.isShown()) {
                    ll_layer_box.setVisibility(View.VISIBLE);
                }

                switch (view) {
                    case R.id.radio_btn_mon: {//월
                        tv_day_name.setText(radio_btn_mon.getText().toString());
                        break;
                    }
                    case R.id.radio_btn_the: {//화
                        tv_day_name.setText(radio_btn_the.getText().toString());
                        break;
                    }
                    case R.id.radio_btn_wed: {//수
                        tv_day_name.setText(radio_btn_wed.getText().toString());
                        break;
                    }
                    case R.id.radio_btn_thu: {//목
                        tv_day_name.setText(radio_btn_thu.getText().toString());
                        break;
                    }
                    case R.id.radio_btn_frd: {//금
                        tv_day_name.setText(radio_btn_frd.getText().toString());
                        break;
                    }
                    case R.id.radio_btn_sat: {//토
                        tv_day_name.setText(radio_btn_sat.getText().toString());
                        break;
                    }
                    case R.id.radio_btn_sun: {//일
                        tv_day_name.setText(radio_btn_sun.getText().toString());
                        break;
                    }
                }
            }
        });
    }
}
