package com.commin.pro.lectureschedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.commin.pro.lectureschedule.page.lecture.Page2Lecture;

public class Application extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_application);

        startActivity(new Intent(Application.this, Page2Lecture.class));
    }
}
