package com.commin.pro.lectureschedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.commin.pro.lectureschedule.dao.DBHelper;
import com.commin.pro.lectureschedule.dao.Dao2Lecture;
import com.commin.pro.lectureschedule.model.Model2Lecture;
import com.commin.pro.lectureschedule.page.lecture.Page2Lecture;

import java.util.HashMap;

public class Application extends AppCompatActivity {

    private final DBHelper db = new DBHelper(Application.this, "lecture.db", null, 9);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_application);

        Dao2Lecture.setDatabase(db);
        startActivity(new Intent(Application.this, Page2Lecture.class));
        finish();
    }
}
