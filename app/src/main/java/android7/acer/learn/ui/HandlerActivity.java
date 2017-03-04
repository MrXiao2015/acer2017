package android7.acer.learn.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import android7.acer.learn.R;

public class HandlerActivity extends AppCompatActivity {
    private TextView mTextView;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            mTextView.setText("onClick");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        mTextView = (TextView) findViewById(R.id.handler_tv);

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);

                handler.sendMessage(new Message());
            }
        }).start();

    }
}
