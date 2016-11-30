package com.commin.pro.exerciseproject.page.do_excercise_beginner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;

import com.commin.pro.exerciseproject.R;

public class Page2DoExcerciseBegin extends AppCompatActivity {
    private static String LOG_TAG="Page2DoExcerciseBegin";

    private CheckBox beginner_excercize_fisrt,beginner_excercize_second,beginner_excercize_third;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_do_excercise_begin_layout);
        createGUI();
    }

    private void createGUI(){
        init_elements();
    }
    private void init_elements(){
        beginner_excercize_fisrt=(CheckBox)findViewById(R.id.beginner_excercize_fisrt);
        beginner_excercize_second=(CheckBox)findViewById(R.id.beginner_excercize_second);
        beginner_excercize_third=(CheckBox)findViewById(R.id.beginner_excercize_third);

    }
}
