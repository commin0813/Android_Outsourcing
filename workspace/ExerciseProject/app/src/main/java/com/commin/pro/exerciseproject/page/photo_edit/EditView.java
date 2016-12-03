package com.commin.pro.exerciseproject.page.photo_edit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.commin.pro.exerciseproject.ApplicationProperty;

/**
 * Created by user on 2016-12-02.
 */
public class EditView extends View implements Page2PhotoEdit.EditHandler {

    private Paint mPaint;
    private Bitmap bitmap;

    private Canvas c;
    private String text = null;
    private boolean status = false;

    private int code = 0;


    public EditView(Context context) {
        super(context);
        mPaint = new Paint();
        this.bitmap = Page2PhotoEdit.user_photo;


    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    protected void onDraw(Canvas canvas) {

//        Bitmap copyBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
//        bitmap = copyBitmap;
//        c = new Canvas(copyBitmap);
//        mPaint.setStrokeWidth(100.0f);
//        c.drawLine(10, 0, 500, 0, mPaint);

        Bitmap copyBitmap = null;
        copyBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);

        switch (code) {
            case ApplicationProperty.DRAW_LINE: {
                break;
            }
            case ApplicationProperty.ADD_TEXT: {
                if (text != null) {
                    copyBitmap = Page2PhotoEdit.user_photo.copy(Bitmap.Config.ARGB_8888, true);
                    bitmap = copyBitmap;
                    Canvas text_canvas = new Canvas(copyBitmap);
                    mPaint.setTextSize(50);
                    mPaint.setColor(Color.BLUE);
                    text_canvas.drawText(text, 10, 50, mPaint);

                }

                break;
            }

        }

        if (copyBitmap == null) {
            return;
        }

        canvas.drawBitmap(copyBitmap, getWidth() / 5, getHeight() / 5, new Paint());

    }

    @Override
    public void onDrawText(String text) {
        this.text = text;
        code = ApplicationProperty.ADD_TEXT;
        invalidate();
    }

    @Override
    public void onSaveBitmap() {
//        code = ApplicationProperty.SAVE_BITMAP;
        Page2PhotoEdit.user_photo = bitmap;
    }


//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//
//                invalidate();
//                break;
//        }
//        return true;
//
//    }
}
