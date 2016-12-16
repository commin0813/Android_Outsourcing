package com.commin.pro.lectureschedule.page.lecture;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
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
import com.commin.pro.lectureschedule.page.lecture_view.Page2LectureView;
import com.commin.pro.lectureschedule.page.note.Page2Note;
import com.commin.pro.lectureschedule.page.note_view.Page2NoteView;
import com.commin.pro.lectureschedule.util.UtilCheck;
import com.commin.pro.lectureschedule.util.UtilCustomDialog;
import com.commin.pro.lectureschedule.util.UtilDate;
import com.commin.pro.lectureschedule.util.UtilDialog;
import com.commin.pro.lectureschedule.widget.DialogProgress;

import java.util.ArrayList;
import java.util.HashSet;

public class Page2Lecture extends AppCompatActivity {
    private static final String LOG_TAG="Page2Lecture";

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


        gv_content = (GridView) findViewById(R.id.gv_content);



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
                    if(model.isMemo()){

                        Intent intent = new Intent(Page2Lecture.this, Page2NoteView.class);
                        intent.putExtra("model",model);
                        startActivity(intent);
                    }else if(model.isEvents()){

                        Intent intent = new Intent(Page2Lecture.this, Page2LectureView.class);
                        intent.putExtra("model",model);
                        startActivity(intent);
                    }

                }
            }
        });
        gv_content.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                final Model2Lecture model = content_item.get(position);
                if(model.isData()){


                    if(!model.isEvents()&&!model.isMemo()){
                        String str = UtilCheck.checkDay(model.getId());
                        UtilDialog.openCustomDialogConfirm(Page2Lecture.this, str+"요일 메모", "작성 하실래요?", "예", "아니오", new UtilCustomDialog.OnClickListener() {
                            @Override
                            public void onClick() {
                                try {
                                    DialogProgress.run(Page2Lecture.this, new DialogProgress.ProgressTaskIf() {
                                        @Override
                                        public Object run() throws Exception {
                                            Intent intent = new Intent(Page2Lecture.this, Page2Note.class);
                                            intent.putExtra("model",model);
                                            startActivityForResult(intent,ApplicationProperty.REQUEST_CODE_FOR_NOTE);
                                            return null;
                                        }
                                    });
                                }catch (Exception e){

                                }


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
        //ArrayLIst에있는 데이터를 말끔히 청소를 해줘야합니다. 그래야 데이터가 겹치는것을 방지할수있어요.
        day_item.clear();
        content_item.clear();

        //화면 갱신을 위해서는 Adapter를 데이터가 변경될때마다 다시 생성해주어서 GridVIew에 Set 해야 합니다.
        adapter2GridDay = new Adapter2GridDay(Page2Lecture.this, R.layout.item_gird_day, day_item);
        gv_day.invalidateViews();//혹시나하는마음에 GridView 청소
        gv_day.setAdapter(adapter2GridDay);

        //위와 동일
        adapter2GridContent = new Adapter2GridContent(Page2Lecture.this, R.layout.item_grid_content, content_item);
        gv_content.invalidateViews();
        gv_content.setAdapter(adapter2GridContent);


//        ArrayList<Model2Lecture> models = Dao2Lecture.queryAllData();
        ArrayList<Model2Lecture>  models = null;
        try {
              models = (ArrayList<Model2Lecture>) DialogProgress.run(Page2Lecture.this, new DialogProgress.ProgressTaskIf() {
                @Override
                public Object run() throws Exception {
                    return Dao2Lecture.queryAllData();
                }
            });
        }catch (Exception e){
            Log.w(LOG_TAG,e);
        }

        //day setting - 마지막 요일 구하기
        String[] arr_string_day = getResources().getStringArray(R.array.days_7);

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
        } else {
            UtilDialog.showToast(Page2Lecture.this, "취소 되었습니다.");
        }
    }
}
