package android7.acer.learn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xiaozheng on 2017/3/1.
 */

public class SearchView extends View {

    private Paint mPaint;

    private int mWidth;
    private int mHeight;

    private float currentValue = 0;

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public SearchView(Context context) {
        super(context);

        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(8);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLUE);

        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.rotate(45);

        Path path = new Path();
        path.addCircle(0, 0, 25, Path.Direction.CW);
        path.lineTo(50, 0);

        fadeIn(path, canvas);

    }

    private void fadeIn(Path path, Canvas canvas) {
        PathMeasure pathMeasure = new PathMeasure(path, false);

        currentValue += 0.05;
        if (currentValue > 1) {
            currentValue = 0;
        }

        Path fadeInPath = new Path();
        pathMeasure.getSegment(0, currentValue * pathMeasure.getLength(), fadeInPath, true);

        canvas.drawPath(fadeInPath, mPaint);

        invalidate();
    }

}



