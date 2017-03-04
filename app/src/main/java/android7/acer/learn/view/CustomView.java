package android7.acer.learn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xiaozheng on 2017/2/28.
 */

public class CustomView extends View {
    private Paint mPaint;

    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画直线
        canvas.drawLine(100,100,200,100,mPaint);

        RectF rectF = new RectF(100,150,300,350);
        //画矩形
        canvas.drawRect(rectF,mPaint);

        //画圆角矩形
        canvas.drawRoundRect(rectF,50,50,mPaint);

        //画椭圆
        canvas.drawOval(rectF,mPaint);

        //画圆
        canvas.drawCircle(300,300,100,mPaint);

        //绘制圆弧
        canvas.drawArc(rectF,0,90,true,mPaint);
    }
}
