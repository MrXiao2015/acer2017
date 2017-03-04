package android7.acer.learn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import acer.android7.pmonitor.annotation.DebugTrace;
import acer.java7.OnClick;
import android7.acer.learn.db.DatabaseHelper;
import android7.acer.learn.entities.Account;
import android7.acer.learn.ui.DrawActivity;
import android7.acer.learn.ui.DropDownActivity;
import android7.acer.learn.ui.UIActivity;
import learn.android7.acer.mylibrary.ViewFinder;

public class MainActivity extends AppCompatActivity {

    private static String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewFinder.inject(this);

    }

    @OnClick(R.id.main_ui)
    void startUIActivity(){
        startActivity(new Intent(MainActivity.this, UIActivity.class));
    }

    @OnClick(R.id.main_tools)
    void startToolsActivity(){

    }

    @OnClick(R.id.main_drop_down)
    void startDropDownActivity() {
        startActivity(new Intent(MainActivity.this, DropDownActivity.class));
    }

    @OnClick(R.id.main_aspectj)
    @DebugTrace
    void testAspectj() {
        Toast.makeText(this, "aspectj", Toast.LENGTH_SHORT).show();
        sleep(100);
    }

    @OnClick(R.id.main_ormlite)
    void testOrmlite() {
        RuntimeExceptionDao<Account, Integer> dao = DatabaseHelper.getInstance(MainActivity.this).getAccountDao();
        Account account = new Account();
        account.setId(100);
        account.setName("xiaoming");
        account.setPassword("password");

        dao.create(account);

        Log.d(LOG_TAG, account.toString());

        dao.updateId(account, 200);

        Log.d(LOG_TAG, account.toString());
    }

    @OnClick(R.id.main_draw)
    void testDraw() {
        Intent intent = new Intent(this, DrawActivity.class);
        startActivity(intent);
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
