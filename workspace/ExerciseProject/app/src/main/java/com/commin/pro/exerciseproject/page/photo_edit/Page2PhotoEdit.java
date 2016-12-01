package com.commin.pro.exerciseproject.page.photo_edit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.commin.pro.exerciseproject.R;
import com.commin.pro.exerciseproject.dao.Dao2Excercise;
import com.commin.pro.exerciseproject.util.UtilDialog;

import java.util.Date;

public class Page2PhotoEdit extends AppCompatActivity {
    private Button btn_save_edit, btn_add_line, btn_add_text;
    private ImageView iv_photo_user_image_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_photo_edit_layout);
        createGUI();
    }

    private void createGUI() {
        init_elements();
        init_imageview();
        init_click_handler();
    }

    private void init_elements() {
        btn_save_edit = (Button) findViewById(R.id.btn_save_edit);
        btn_add_line = (Button) findViewById(R.id.btn_add_line);
        btn_add_text = (Button) findViewById(R.id.btn_add_text);
        iv_photo_user_image_edit = (ImageView) findViewById(R.id.iv_photo_user_image_edit);
        iv_photo_user_image_edit.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    private void init_imageview(){

    }

    private void init_click_handler() {
        btn_add_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_add_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_save_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
