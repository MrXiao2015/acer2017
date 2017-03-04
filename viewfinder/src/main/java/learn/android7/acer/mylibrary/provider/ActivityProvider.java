package learn.android7.acer.mylibrary.provider;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * Created by xiaozheng on 2017/2/21.
 */

public class ActivityProvider implements Provider {
    @Override
    public Context getContext(Object source) {
        return ((Activity) source);
    }

    @Override
    public View findView(Object source, int id) {
        return ((Activity) source).findViewById(id);
    }
}
