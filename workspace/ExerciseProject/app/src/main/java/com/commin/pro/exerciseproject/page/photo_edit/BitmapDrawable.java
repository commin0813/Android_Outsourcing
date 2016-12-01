package com.commin.pro.exerciseproject.page.photo_edit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * Created by hyungwoo on 2016-12-02.
 */
public class BitmapDrawable extends android.graphics.drawable.BitmapDrawable{
    private String name;
    private Bitmap bitmap;
    private Paint textPaint;

    public BitmapDrawable(Context ctx, String strName, int bitmapId) {
        // TODO Auto-generated constructor stub
        super(BitmapFactory.decodeResource(ctx.getResources(), bitmapId));
        bitmap = BitmapFactory.decodeResource(ctx.getResources(), bitmapId);
        name = strName;
        this.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
        textPaint = new Paint();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, this.getBounds().left, this.getBounds().top, textPaint);
        textPaint.setTextSize(10);
        textPaint.setTypeface(Typeface.DEFAULT);
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        int textWidth = getTextWidth(name) / 2;
        int centerX = this.getBounds().width() / 2;
        int centerY = this.getBounds().height() / 2;
        canvas.drawText(name, centerX - textWidth, centerY, textPaint);
    }

    int getTextWidth(String text) {
        int count = text.length();
        float[] widths = new float[count];
        textPaint.getTextWidths(text, widths);
        int textWidth = 0;
        for (int i = 0; i < count; i++)
            textWidth += widths[i];
        return textWidth;
    }
}
