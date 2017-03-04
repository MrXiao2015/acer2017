package android7.acer.learn.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import acer.java7.OnClick;
import android7.acer.learn.R;
import learn.android7.acer.mylibrary.ViewFinder;

public class DrawActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        ViewFinder.inject(this);

    }

    @OnClick(R.id.draw_search)
    void startSearchActivity(){
        Intent intent = new Intent(this,SearchActivity.class);
        startActivity(intent);
    }
}
