package com.commin.pro.exerciseproject.page.game;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.commin.pro.exerciseproject.R;
import com.commin.pro.exerciseproject.page.photo.Page2Photo;

public class Page2Game extends AppCompatActivity {
    TextView tv_game_title;
    LinearLayout ll_container;
    BarView barView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_game_layout);
        createGUI();
    }

    private void createGUI() {
        init_elements();
    }

    private void init_elements() {
        barView = new BarView(Page2Game.this);
//
        tv_game_title = (TextView) findViewById(R.id.tv_game_title);
        tv_game_title.setSelected(true);
//
        ll_container = (LinearLayout) findViewById(R.id.ll_container);
        barView.setVisibility(View.VISIBLE);
        ll_container.addView(barView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
    }
}
