package com.asi.draw1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import java.lang.Math;

/**
 * Created by ashah on 3/5/2016.
 */

public class DrawView extends View {

    private static final int DEFAULT_CIRCLE_COLOR = Color.RED;
    private static final int DEFAULT_LINE_COLOR = Color.BLUE;

    private int circleColor = DEFAULT_CIRCLE_COLOR;
    private int lineColor = DEFAULT_LINE_COLOR;
    private Paint paint;

    private int num_sides=10;

    public DrawView(Context context)
    {
        super(context);
        init(context, null);
    }

    public DrawView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs)
    {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    public void setCircleColor(int circleColor)
    {
        this.circleColor = circleColor;
        invalidate();
    }

    public int getCircleColor()
    {
        return circleColor;
    }

    public void set_num_sides(int n)
    {
        num_sides=n;
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        int w = getWidth();
        int h = getHeight();
        int i = 0, j = 0;
        double angle = 0;

        int pl = getPaddingLeft();
        int pr = getPaddingRight();
        int pt = getPaddingTop();
        int pb = getPaddingBottom();

        int usableWidth = w - (pl + pr);
        int usableHeight = h - (pt + pb);

        int radius = Math.min(usableWidth, usableHeight) / 2;
        int cx = pl + (usableWidth / 2);
        int cy = pt + (usableHeight / 2);

        int[] x = new int[20];
        int[] y = new int[20];

        for (i=0; i<num_sides; i++){
            angle = (double)i*2.0*Math.PI /(double)num_sides ;
            x[i] = cx + (int) (radius*Math.cos(angle));
            y[i] = cy + (int) (radius*Math.sin(angle));
        }

        paint.setColor(circleColor);
        canvas.drawCircle(cx, cy, radius, paint);
        paint.setColor(lineColor);
        paint.setStrokeWidth(5);
        for (i=0;  i<num_sides-1; i++) {
            for (j=i+1; j < num_sides; j++) {
                canvas.drawLine(x[i], y[i], x[j], y[j], paint);
            }
        }
        canvas.drawLine(x[1],y[1],x[2],y[2],paint);
    }

}