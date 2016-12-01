package com.commin.pro.exerciseproject.page.photo_edit;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.commin.pro.exerciseproject.R;
import com.commin.pro.exerciseproject.model.Model2Excercise;
import com.commin.pro.exerciseproject.util.UtilImage;

import java.util.Date;

public class Page2PhotoEdit extends AppCompatActivity {
    private static final String LOG_TAG="Page2PhotoEdit";

    private Button btn_save_edit, btn_add_line, btn_add_text;
    private ImageView iv_photo_user_image_edit;

    private String user_photo_path;
    private Bitmap user_photo;
    private Bitmap user_photo2;
    private Date selected_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_photo_edit_layout);
        createGUI();
    }

    private void createGUI() {
        Model2Excercise model = (Model2Excercise) getIntent().getSerializableExtra("Model2Excercise");
        user_photo_path = model.getUser_photo_path();
        selected_date = model.getDate();
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

    private void init_imageview() {
        int degree = UtilImage.getExifOrientation(user_photo_path);

        user_photo = UtilImage.getBitmap(user_photo_path, 0, 0, false);
        user_photo = UtilImage.getRotatedBitmap(user_photo, degree);

        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iv_photo_user_image_edit.setImageBitmap(user_photo);
                    }
                });
            }
        }).start();


    }

    private void init_click_handler() {
        btn_add_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//              final  BitmapDrawable bitmapDrawable =new BitmapDrawable(Page2PhotoEdit.this,"아아아아",user_photo.getGenerationId());


//                final Drawable d = new Drawable() {
//                    @Override
//                    public void draw(Canvas canvas) {
//                        canvas = new Canvas(user_photo);
//                        Paint paint = new Paint();
//                        paint.setColor(getResources().getColor(R.color.colorRed36));
//                        paint.setTextSize(50.0f);
//                        paint.setTypeface(Typeface.DEFAULT);
//                        paint.setAntiAlias(true);
//                        canvas.drawText("WOW!!!!!!!!!",50, 100, paint);
//                        canvas.drawBitmap(user_photo,user_photo.getWidth(),user_photo.getHeight(),paint);
//                    }
//
//                    @Override
//                    public void setAlpha(int alpha) {
//
//                    }
//
//                    @Override
//                    public void setColorFilter(ColorFilter colorFilter) {
//
//                    }
//
//                    @Override
//                    public int getOpacity() {
//                        return 0;
//                    }
//                };
                iv_photo_user_image_edit.destroyDrawingCache();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                iv_photo_user_image_edit.setImageDrawable(bitmapDrawable);
                            }
                        });
                    }
                }).start();


                if(btn_save_edit ==  btn_add_line){
                    Log.d(LOG_TAG,"같다");
                }else{
                    Log.d(LOG_TAG,"다르다");
                }
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
