package com.commin.pro.lecture.page.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.commin.pro.lecture.R;

public class Page2Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
    }

    public void back(View view){
        onBackPressed();
    }
}
