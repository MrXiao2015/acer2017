package android7.acer.learn.ui;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;

import android7.acer.learn.R;
import android7.acer.learn.tool.ScreenUtil;

/**
 * 下拉关闭页面
 */
public class DropDownActivity extends AppCompatActivity {
    private GestureDetectorCompat gestureDetectorCompat;
    static final float LEFT = ScreenUtil.dp2px(45);
    static final float VELOCITY = ScreenUtil.dp2px(100);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_down);

        gestureDetectorCompat = new GestureDetectorCompat(this,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (e1 != null && Math.abs(velocityY) > Math.abs(1.5 * velocityX) && velocityY > VELOCITY) {
                    onBackPressed();
                    return true;
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
