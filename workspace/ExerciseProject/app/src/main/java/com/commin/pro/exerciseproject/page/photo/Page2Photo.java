package com.commin.pro.exerciseproject.page.photo;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.commin.pro.exerciseproject.ApplicationProperty;
import com.commin.pro.exerciseproject.R;
import com.commin.pro.exerciseproject.dao.Dao2Excercise;
import com.commin.pro.exerciseproject.model.Model2Excercise;
import com.commin.pro.exerciseproject.util.UtilDialog;
import com.commin.pro.exerciseproject.util.UtilImage;
import com.commin.pro.exerciseproject.util.UtilText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Page2Photo extends AppCompatActivity {
    private Button btn_photo, btn_edit, btn_save;
    private ImageView iv_photo_user_image;
    private Spinner sp_event_date_selector;
    private ArrayList<String> items;
    private SimpleDateFormat df = new SimpleDateFormat("MM월 dd일");
    private String image_path = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_photo_layout);
        createGUI();

    }

    private void createGUI() {
        init_elements();
        init_spinner();
        init_click_handler();
    }

    private void init_elements() {
        btn_photo = (Button) findViewById(R.id.btn_photo);
        btn_edit = (Button) findViewById(R.id.btn_edit);
        btn_save = (Button) findViewById(R.id.btn_save);
        iv_photo_user_image = (ImageView) findViewById(R.id.iv_photo_user_image);
        iv_photo_user_image.setScaleType(ImageView.ScaleType.FIT_XY);
        sp_event_date_selector = (Spinner) findViewById(R.id.sp_event_date_selector);
    }

    private void init_spinner() {
        HashMap<Date, Model2Excercise> map = Dao2Excercise.getHashMap();
        items = new ArrayList<String>();

        items.add(getResources().getString(R.string.sp_title));
        for (Date date : map.keySet()) {
            items.add(df.format(date));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Page2Photo.this, android.R.layout.simple_spinner_dropdown_item, items);
        sp_event_date_selector.setAdapter(adapter);

    }

    private void init_click_handler() {
        btn_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playCameraOrGallery(view);
            }
        });
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        sp_event_date_selector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View view, int position, long l) {
                if (items.get(position).equals(getResources().getString(R.string.sp_title))) {

                    return;
                }
                Date date = Dao2Excercise.queryDate(items.get(position), df);
                if (date == null) {
                    return;
                }
                UtilDialog.showToast(Page2Photo.this, df.format(date));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void playCameraOrGallery(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_photo:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());
                try {
                    intent.putExtra("return-data", true);
                    startActivityForResult(Intent.createChooser(intent,
                            "Complete action using"), ApplicationProperty.PICK_FROM_CAMERA);
                } catch (ActivityNotFoundException e) {
                }

                break;

            //갤러리 부분 추가시 이곳에 추가하시면됩니다.
            //case R.id.btn_gallery:
        }

        if (intent == null) {
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {

            Uri uri_datas = intent.getData();

            if (uri_datas != null) {
                image_path = getPath(uri_datas);
                int degree = UtilImage.getExifOrientation(image_path);
                Bitmap user_image = null;
                user_image = UtilImage.getBitmap(image_path, 0, 0, false);
                user_image = UtilImage.getRotatedBitmap(user_image, degree);

                if (user_image != null) {
                    iv_photo_user_image.setImageBitmap(user_image);

                } else {
                    UtilDialog.openError(Page2Photo.this, UtilText.getText(Page2Photo.this, R.string.load_image_file_fail), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                }
            }
        } else if (resultCode == RESULT_CANCELED) {
            return;
        }
    }

    private String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


}
