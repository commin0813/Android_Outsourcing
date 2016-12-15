package com.commin.pro.lectureschedule.page.lecture;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.commin.pro.lectureschedule.ApplicationProperty;
import com.commin.pro.lectureschedule.R;
import com.commin.pro.lectureschedule.dao.Dao2Lecture;
import com.commin.pro.lectureschedule.model.Model2Lecture;
import com.commin.pro.lectureschedule.page.lecture_add.Page2LectureAdd;
import com.commin.pro.lectureschedule.page.lecture_edit.Page2LectureEdit;
import com.commin.pro.lectureschedule.page.note.Page2Note;
import com.commin.pro.lectureschedule.util.UtilCheck;
import com.commin.pro.lectureschedule.util.UtilCustomDialog;
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
    private ImageView iv_button_add_lecture;
    private ImageView iv_button_edit_lecture;
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
        iv_button_add_lecture = (ImageView) findViewById(R.id.iv_button_add_lecture);
        iv_button_edit_lecture = (ImageView) findViewById(R.id.iv_button_edit_lecture);

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
        iv_button_add_lecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Page2Lecture.this, Page2LectureAdd.class), ApplicationProperty.REQUEST_CODE_FOR_LECTURE_ADD);
            }
        });

        iv_button_edit_lecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Page2Lecture.this, Page2LectureEdit.class), ApplicationProperty.REQUEST_CODE_FOR_LECTURE_EDIT);
            }
        });

        gv_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Model2Lecture model = content_item.get(position);
                if (model.isData()) {
                    UtilDialog.showToast(Page2Lecture.this, "클릭되었습니다." + model.getId());
                }
            }
        });
        gv_content.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                final Model2Lecture model = content_item.get(position);
                if(model.isData()){
                    if(!model.isEvents()){
                        String str = UtilCheck.checkDay(model.getId());
                        UtilDialog.openCustomDialogConfirm(Page2Lecture.this, str+"요일 메모", "작성 하실래요?", "예", "아니오", new UtilCustomDialog.OnClickListener() {
                            @Override
                            public void onClick() {
                                startActivityForResult(new Intent(Page2Lecture.this, Page2Note.class),ApplicationProperty.REQUEST_CODE_FOR_NOTE);
                            }
                        }, new UtilCustomDialog.OnClickListener() {
                            @Override
                            public void onClick() {
                                return;
                            }
                        });
                    }else{
                        UtilDialog.openCustomDialogConfirm(Page2Lecture.this, "삭제", "삭제 할래요?", "예", "아니오", new UtilCustomDialog.OnClickListener() {
                            @Override
                            public void onClick() {
                                Dao2Lecture.deleteData(model);
                                queryDataGrid();
                                adapter2GridContent.notifyDataSetChanged();
                                adapter2GridContent.notifyDataSetInvalidated();
                            }
                        }, new UtilCustomDialog.OnClickListener() {
                            @Override
                            public void onClick() {
                                return;
                            }
                        });
                    }
                }
                return false;
            }
        });
    }

    private void queryDataGrid() {

        day_item.clear();
        content_item.clear();

        ArrayList<Model2Lecture> models = Dao2Lecture.queryAllData();

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
            }
            if (colum_index >= NumColum) {
                colum_index = 0;
            }
            model.setColumn_index(colum_index);
            model.setId(row_index + ApplicationProperty.OPERATOR_ID + colum_index);

            for (Model2Lecture mo : models) {
                if (model.getId().equals(mo.getId())) {
                    model = mo;
                }
            }
            colum_index++;
            content_item.add(model);
        }


        adapter2GridContent.notifyDataSetChanged();
        adapter2GridContent.notifyDataSetInvalidated();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            queryDataGrid();
            adapter2GridContent.notifyDataSetChanged();
        } else {
            UtilDialog.showToast(Page2Lecture.this, "취소 되었습니다.");
        }
    }
}
