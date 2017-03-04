package android7.acer.learn.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import acer.java7.OnClick;
import android7.acer.learn.R;
import learn.android7.acer.mylibrary.ViewFinder;

public class UIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);

        ViewFinder.inject(this);

    }

    @OnClick(R.id.ui_number_picker)
    void startUIActivity(){
        startActivity(new Intent(UIActivity.this, NumberPickerActivity.class));
    }

    @OnClick(R.id.ui_3d)
    void startUI3DActivity(){
        startActivity(new Intent(UIActivity.this, NumberPickerActivity.class));
    }
}
