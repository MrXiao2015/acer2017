package android7.acer.learn;

import android.app.Application;
import android.content.Context;

/**
 * Created by xiaozheng on 2017/2/20.
 */

public class LearnApplication extends Application {
    private static Context instance;

    public static Context getInstance(){
        if (instance == null) {
            throw new RuntimeException("LearnApplication not created");
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }
}
