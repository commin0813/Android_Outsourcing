package com.commin.pro.exerciseproject.page.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Date;

/**
 * Created by user on 2016-12-02.
 */
public class BarView extends View{
    Context context;
    public BarView(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);
        Paint paint=new Paint();

        paint.setColor(Color.CYAN);
        paint.setStrokeWidth(100.0f);
        paint.setTextSize(100.0f);

        canvas.drawPoint(100,100,paint);
        canvas.drawText("ddddd",0,0,paint);


    }

    public interface EventDraw {
       void onScreenClick(Date date);
    }
}
