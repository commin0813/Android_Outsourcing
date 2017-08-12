package com.commin.pro.commincalendar;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.commin.pro.commincalendar.page.calendar.Page2Calendar;

public class Application extends AppCompatActivity {
    private ImageView intro_image = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_application);
        start();

    }

    private void start() {
        intro_image = (ImageView) findViewById(R.id.iv_intro);
        fadeAnimation(intro_image, false);
    }

    public void fadeAnimation(final View tv, boolean isfadeOut) {
        final Animation animationFade;
        tv.setAlpha(0f);
        if (isfadeOut) {
            animationFade = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        } else {
            animationFade = AnimationUtils.loadAnimation(this, R.anim.fadein);
        }
        Handler mhandler = new Handler();
        mhandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                tv.setAlpha(1f);
                tv.startAnimation(animationFade);
            }
        }, 0);

        mhandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Application.this,Page2Calendar.class));
                finish();
            }
        },1500);

    }
}
